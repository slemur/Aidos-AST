package org.aidos.tree.node;

import org.objectweb.asm.Label;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

/**
 * Represents a GOTO instruction.
 * @author `Discardedx2
 */
public class JumpInstruction extends InstructionNode {

	/**
	 * The label to jump to.
	 */
	private Label jumpTo;
	
	/**
	 * Constructs a new {@link JumpInstruction}.
	 * @param owner This node's owner.
	 * @param label The label to jump to.
	 * @param codePosition This node's code position.
	 * @param opcode This node's opcode.
	 */
	public JumpInstruction(MethodDecNode owner, Label label, int codePosition) {
		super(owner, codePosition, Opcodes.GOTO);
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
