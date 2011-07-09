package org.aidos.tree.node;

import org.objectweb.asm.Opcodes;

public class LdcInstruction extends InstructionNode {

	/**
	 * This represents the LDC constant from the constant pool.
	 */
	private Object constant;
	
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
	
	public Object getConstant() {
		return constant;
	}

}
