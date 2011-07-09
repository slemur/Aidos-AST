package org.aidos.tree.node;

import java.util.ArrayList;
import java.util.List;

import org.aidos.tree.ClassFile;
import org.aidos.tree.util.OpcodeUtil;


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
				sb.append("\n\t\tOpcode: "+OpcodeUtil.getOpcodeName(instruction.getOpcode())+" ( "+instruction.getOpcode()+" ), type: "+instruction);
			}
		}
		return sb.toString();
	}

	public ClassFile getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public List<ExceptionInstruction> getExceptions() {
		return exceptions;
	}

	/**
	 * @return the instructions
	 */
	public InstructionNode[] getInstructions() {
		return instructions;
	}
}
