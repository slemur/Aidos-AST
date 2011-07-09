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
	
	public InstructionNode(MethodDecNode owner, int codePosition, int opcode) {
		super(opcode);
	}

	/**
	 * Gets the code position.
	 * @return The code position.
	 */
	public int getCodePosition() {
		return codePosition;
	}
	
}
