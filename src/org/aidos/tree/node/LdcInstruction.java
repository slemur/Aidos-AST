package org.aidos.tree.node;

import org.objectweb.asm.Opcodes;

public class LdcInstruction extends InstructionNode {

	/**
	 * This represents the LDC constant from the constant pool.
	 */
	private Object constant;
	
	/**
	 * Constructs a new {@link LdcInstruction}.
	 * @param owner The owner of this node.
	 * @param position The code position of this node in it's owner.
	 * @param constant The constant value of this instruction.
	 */
	public LdcInstruction(MethodDecNode owner, int position, Object constant) {
		super(owner, position, Opcodes.LDC);
		this.constant = constant;
	}
	
	@Override
	public String toString() {
		if (constant instanceof String) {
			return (String) constant;
		}
		return super.toString();
	}
	
	/**
	 * Gets the constant.
	 * @return The constant.
	 */
	public Object getConstant() {
		return constant;
	}

}
