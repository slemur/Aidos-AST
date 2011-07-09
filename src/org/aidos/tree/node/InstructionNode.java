package org.aidos.tree.node;

/**
 * Represents an instruction in an {@link MethodDecNode}.
 * @author `Discardedx2
 */
public abstract class InstructionNode extends Node {
	
	/**
	 * Represents the position of this instruction in the method owner.
	 */
	private int codePosition;
	
	/**
	 * Constructs a new {@link InstructionNode}.
	 * @param owner The owner of this node.
	 * @param codePosition The position of this instruction in it's owner.
	 * @param opcode The opcode of this instruction.
	 */
	public InstructionNode(MethodDecNode owner, int codePosition, int opcode) {
		super(opcode);
		this.codePosition = codePosition;
	}

	/**
	 * Gets the code position.
	 * @return The code position.
	 */
	public int getCodePosition() {
		return codePosition;
	}
	
}
