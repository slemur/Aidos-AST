package org.aidos.tree.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aidos.tree.ClassException;
import org.aidos.tree.ClassFile;
import org.aidos.tree.analyzer.flow.FlowBlock;
import org.aidos.tree.tac.TACStructure;
import org.aidos.tree.tac.ThreeAddressFunction;
import org.aidos.tree.util.InstructionUtil;
import org.objectweb.asm.Attribute;


/**
 * TODO Properly documenting/finish documenting
 * @author `Discardedx2
 */
public class MethodDecNode extends Node {

	/**
	 * The owner of this method.
	 */
	private ClassFile owner;
	/**
	 * This method's name.
	 */
	private String name;
	/**
	 * This method's descriptor.
	 */
	private String descriptor;
	/**
	 * This method's signature.
	 */
	private String signature;
	/**
	 * This method's modifier.
	 */
	private int modifier;
	/**
	 * This method's exceptions.
	 */
	private List<ClassException> exceptions = new ArrayList<ClassException>();
	/**
	 * This method's instructions.
	 */
	private List<InstructionNode> instructions = new ArrayList<InstructionNode>();
	/**
	 * This method's flow blocks.
	 */
	private List<FlowBlock> flowBlocks = new ArrayList<FlowBlock>();
	/**
	 * The parsed attributes.
	 */
	private List<Attribute> attributes = new ArrayList<Attribute>();
	/**
	 * The max locals.
	 */
	private int maxLocals;
	/**
	 * The max stack.
	 */
	private int maxStackSize;
	

	/**
	 * Constructs a new {@link MethodDecNode}.
	 * @param owner The owner of this node.
	 * @param name The name of this node.
	 * @param descriptor This node's descriptor.
	 * @param signature This node's signature.
	 * @param modifier This node's modifier.
	 */
	public MethodDecNode(ClassFile owner, String name, String descriptor, String signature, int modifier) {
		super(-1);
		this.owner = owner;
		this.name = name;
		this.descriptor = descriptor;
		this.modifier = modifier;
	}

	/**
	 * Gets the info of this method by parsing it through a {@link StringBuilder}.
	 * @return The method info.
	 */
	public String info() {
		StringBuilder sb = new StringBuilder("Name: "+name)
		.append("\nOwner: "+owner.getName())
		.append("\nModifier: "+modifier)
		.append("\nDescriptor: "+descriptor)
		.append("\nSignature: "+signature);
		if (exceptions.size() > 0) {
			sb.append("\nExceptions:");
			for (ClassException e : exceptions) {
				sb.append("\n\t"+e.getType());
			}
		}
		if (instructions.size() > 0) {
			sb.append("\nInstructions:");
			for (InstructionNode instruction : instructions) {
				if (instruction != null) {
					sb.append("\n\t\tOpcode: "+InstructionUtil.getInstructionName(instruction.getOpcode())+" ( "+instruction.getOpcode()+" ), offset: "+instruction.getCodePosition()+", type: "+instruction);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Gets the info of this method's three address code by parsing it through a {@link StringBuilder}.
	 * @return The 3AC info.
	 */
	public String tacInfo(TACStructure structure) {
		StringBuilder sb = new StringBuilder("Name: "+name)
		.append("\nOwner: "+owner.getName())
		.append("\nModifier: "+modifier)
		.append("\nDescriptor: "+descriptor)
		.append("\nSignature: "+signature);
		if (instructions.size() > 0) {
			sb.append("\n3AC Functions:");
			for (Iterator<ThreeAddressFunction> itr = structure.iterator(); itr.hasNext();) {
				ThreeAddressFunction function = itr.next();
				InstructionNode instruction = function.getInstruction();
				if (instruction.getOwner().equals(this) && function.getFunction() != null) {
					sb.append("\n\t\tType: "+function.getFunction()+", Opcode: "+InstructionUtil.getInstructionName(instruction.getOpcode())+" ( "+instruction.getOpcode()+" ), offset: "+instruction.getCodePosition());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Gets the owner of this node.
	 * @return The owner.
	 */
	public ClassFile getOwner() {
		return owner;
	}

	/**
	 * Gets the name of this node.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets this node's name.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets this node's descriptor.
	 * @return The descriptor.
	 */
	public String getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets this node's descriptor.
	 * @param descriptor The descriptor to set.
	 */
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Gets this node's signature.
	 * @return The signature.
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets this node's signature.
	 * @param signature The signature to set.
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * Gets this node's modifier.
	 * @return The modifier.
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * Sets this node's modifier.
	 * @param modifier The modifier to set.
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	/**
	 * Gets this node's exception list.
	 * @return The exceptions.
	 */
	public List<ClassException> getExceptions() {
		return exceptions;
	}

	/**
	 * Gets this node's instruction set.
	 * @return The instructions.
	 */
	public List<InstructionNode> getInstructions() {
		return instructions;
	}

	/**
	 * Gets this method's flow block set.
	 * @return The flow blocks.
	 */
	public List<FlowBlock> getFlowBlocks() {
		return flowBlocks;
	}
	
	/**
	 * Gets the attributes.
	 * @return the attributes
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * Gets the max locals.
	 * @return The max locals.
	 */
	public int getMaxLocals() {
		return maxLocals;
	}

	/**
	 * Sets the max locals.
	 * @param maxLocals The max locals to set.
	 */
	public void setMaxLocals(int maxLocals) {
		this.maxLocals = maxLocals;
	}

	/**
	 * Gets the max stack size.
	 * @return The max stack.
	 */
	public int getMaxStackSize() {
		return maxStackSize;
	}

	/**
	 * Sets the max stack.
	 * @param maxStackSize The max stack to set.
	 */
	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}

	/**
	 * Checks if this method must be analyzed.
	 * @return {@code true} if analyzation is required.
	 */
	public boolean isAnalyzationRequired() {
		return flowBlocks.size() > 0;
	}
}
