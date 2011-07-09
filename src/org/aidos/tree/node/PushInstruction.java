package org.aidos.tree.node;

/**
 * Represents any instruction that pushes an opcode onto the stack.
 * @author `Discardedx2
 */
public class PushInstruction extends InstructionNode {
	
	private int value;
	
	public PushInstruction(MethodDecNode owner, int value, int position, int opcode) {
		super(owner, position, opcode);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
