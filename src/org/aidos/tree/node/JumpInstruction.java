package org.aidos.tree.node;

import org.objectweb.asm.Label;

public class JumpInstruction extends InstructionNode {

	/**
	 * The label to jump to.
	 */
	private Label jumpTo;
	
	public JumpInstruction(MethodDecNode owner, Label label, int codePosition, int opcode) {
		super(owner, codePosition, opcode);
		this.jumpTo = label;
	}

	/**
	 * Gets the {@link Label} to jump to.
	 * @return The label.
	 */
	public Label getJumpTo() {
		return jumpTo;
	}

}
