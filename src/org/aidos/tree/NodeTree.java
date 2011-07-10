package org.aidos.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.aidos.tree.analyzer.BytecodeAnalyzer;
import org.aidos.tree.analyzer.flow.FlowAnalyzer;
import org.aidos.tree.analyzer.flow.FlowBlock;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.tac.TACStructure;
import org.aidos.tree.util.InstructionPointer;

/**
 * Represents our abstract syntax tree.
 * @author `Discardedx2
 */
public class NodeTree implements Iterable<ClassFile> {

	/**
	 * The logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(NodeTree.class.getName());
	/**
	 * The jar file to load.
	 */
	private Jar jar;
	/**
	 * The loaded nodes.
	 */
	private Map<String, ClassFile> classFiles = new HashMap<String, ClassFile>();
	/**
	 * The bytecode analyzers.
	 */
	private List<BytecodeAnalyzer<?>> analyzers = new ArrayList<BytecodeAnalyzer<?>>();
	/**
	 * The {@link TACStructure} of this tree.
	 */
	private TACStructure tacStructure = new TACStructure();

	/**
	 * Builds a new {@link NodeTree} hierarchy.
	 * @param jar The jar to load from.
	 */
	public NodeTree(Jar jar) {
		this.jar = jar;
		analyzers.add(new FlowAnalyzer());
		LOGGER.info("Parsing nodes into the tree.");
		for (ClassFile node : jar.getClassFiles().values()) {
			classFiles.put(node.getName(), node);
		}
		LOGGER.info("Parsed a total of "+classFiles.size()+" class files into our syntax tree.");
		LOGGER.info("Analyzing flow block and building the 3AC structure.");
		analyze();
	}

	/**
	 * Analyzes the bytecode.
	 */
	public void analyze() {
		long cur = System.currentTimeMillis();
		int total = 0;
		for (ClassFile cf : classFiles.values()) {
			for (MethodDecNode method : cf.getMethods()) {
				InstructionPointer pointer = new InstructionPointer(method.getInstructions());
				if (method.isAnalyzationRequired()) {
					total += method.getFlowBlocks().size();
					//LOGGER.info("Method: "+cf.getName()+"."+method.getName()+" "+method.getDescriptor()+" contains "+method.getFlowBlocks().size()+" flow blocks.");
					for (BytecodeAnalyzer<?> analyzer : analyzers) {
						for (FlowBlock block : method.getFlowBlocks()) {
							if (block != null) {
								if (block.isAnalyzed()) {
									continue;
								}
								block.setAnalyzed(true);
							}
							analyzer.onAnalyze(block, this, method, pointer);
						}
						if (!BytecodeAnalyzer.queue.isEmpty()) {
							for (Object o : BytecodeAnalyzer.queue) {
								if (o instanceof FlowBlock) {
									FlowBlock block = (FlowBlock) o;
									if (block.isAnalyzed()) {
										continue;
									}
									block.setAnalyzed(true);
									block.getAnalyzer().onAnalyze(block, this, method, pointer);
								}
							}
						}
					}
				}
			}
		}
		LOGGER.info("3AC parsed! Time taken: "+(System.currentTimeMillis() - cur) +"ms. Total blocks analyzed: "+total);
	}

	@Override
	public Iterator<ClassFile> iterator() {
		return classFiles.values().iterator();
	}

	/**
	 * Gets the loaded jar file.
	 * @return The jar file.
	 */
	public Jar getJar() {
		return jar;
	}

	/**
	 * Gets the loaded class files.
	 * @return The class files.
	 */
	public Map<String, ClassFile> getClassFiles() {
		return classFiles;
	}

	/**
	 * Gets the bytecode analyzers.
	 * @return The analyzers.
	 */
	public List<BytecodeAnalyzer<?>> getAnalyzers() {
		return analyzers;
	}

	/**
	 * Gets the {@link TACStructure} representation of this tree.
	 * @return The structure.
	 */
	public TACStructure getTacStructure() {
		return tacStructure;
	}
}
