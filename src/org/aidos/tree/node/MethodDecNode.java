package org.aidos.tree.node;

import java.util.ArrayList;
import java.util.List;

import org.aidos.tree.ClassFile;
import org.aidos.tree.util.InstructionUtil;


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
	private List<ExceptionInstruction> exceptions = new ArrayList<ExceptionInstruction>();
	/**
	 * This method's instructions.
	 */
	private InstructionNode[] instructions = new InstructionNode[10000];

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

	public String info() {
		StringBuilder sb = new StringBuilder("Name: "+name)
		.append("\nOwner: "+owner.getName())
		.append("\nModifier: "+modifier)
		.append("\nDescriptor: "+descriptor)
		.append("\nSignature: "+signature);
		if (exceptions.size() > 0) {
			sb.append("\nExceptions:");
			for (ExceptionInstruction e : exceptions) {
				sb.append("\n\t"+e.getType());
			}
		}
		if (instructions.length > 0) {
			sb.append("\nInstructions:");
			for (InstructionNode instruction : instructions) {
				sb.append("\n\t\tOpcode: "+InstructionUtil.getInstructionName(instruction.getOpcode())+" ( "+instruction.getOpcode()+" ), type: "+instruction);
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
	public List<ExceptionInstruction> getExceptions() {
		return exceptions;
	}

	/**
	 * Gets this node's instruction set.
	 * @return The instructions.
	 */
	public InstructionNode[] getInstructions() {
		return instructions;
	}
}
