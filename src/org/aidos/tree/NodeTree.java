package org.aidos.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

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
	 * Builds a new {@link NodeTree} hierarchy.
	 * @param jar The jar to load from.
	 */
	public NodeTree(Jar jar) {
		this.jar = jar;
		LOGGER.info("Parsing nodes into the tree.");
		for (ClassFile node : jar.getClassFiles().values()) {
			classFiles.put(node.getName(), node);
		}
		LOGGER.info("Parsed a total of "+classFiles.size()+" class files into our syntax tree.");
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
}
