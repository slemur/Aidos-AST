package org.aidos.tree.node;

/**
 * Represents an instruction with an opcode of either BIPUSH, SIPUSH, or ICONST_?
 * @author `Discardedx2
 */
public class IntInstruction extends InstructionNode {

	/**
	 * This node's owner.
	 */
	private MethodDecNode owner;
	/**
	 * The integer value of this instruction.
	 */
	private int value;
	
	/**
	 * Constructs a new {@link IntInstruction}.
	 * @param owner The owner of this node.
	 * @param position The code position of this node in it's owner.
	 * @param opcode The opcode of this node.
	 * @param value The operand value of this node.
	 */
	public IntInstruction(MethodDecNode owner, int position, int opcode, int value) {
		super(owner, position, opcode);
		this.owner = owner;
		this.value = value;
	}
	
	/**
	 * Gets this node's owner.
	 * @return The owner.
	 */
	public MethodDecNode getOwner() {
		return owner;
	}
	
	/**
	 * Gets the value of this instruction.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}
	
}
