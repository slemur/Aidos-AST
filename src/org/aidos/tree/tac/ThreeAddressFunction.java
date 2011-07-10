package org.aidos.tree.tac;

import org.aidos.tree.node.InstructionNode;

/**
 * This represents a java bytecode instruction as a part
 * of our 3AC in the AST.
 * @author `Discardedx2
 */
public class ThreeAddressFunction {
	
	private InstructionNode instruction;
	private String function;
	
	public ThreeAddressFunction(InstructionNode instruction) {
		this.instruction = instruction;
	}

	public InstructionNode getInstruction() {
		return instruction;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
}
