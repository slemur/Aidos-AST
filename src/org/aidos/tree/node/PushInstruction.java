package org.aidos.tree.node;

/**
 * Represents any instruction that pushes an opcode onto the stack.
 * @author `Discardedx2
 */
public class PushInstruction extends InstructionNode {
	
	/**
	 * The integer value of this instruction.
	 */
	private int value;
	
	/**
	 * Constructs a new {@link PushInstruction}.
	 * @param owner The owner of this node.
	 * @param value The value of this instruction.
	 * @param position The code position of this instruction.
	 * @param opcode This instruction's opcode.
	 */
	public PushInstruction(MethodDecNode owner, int value, int position, int opcode) {
		super(owner, position, opcode);
		this.value = value;
	}

	/**
	 * Gets the value of this instruction.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}
}
