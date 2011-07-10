package org.aidos.tree.node;

import org.objectweb.asm.Label;

/**
 * Represents a label used for jumping from one instruction to another.
 * @author `Discardedx2
 */
public class LabelInstruction extends InstructionNode {

	/**
	 * This node's label.
	 */
	private Label label;
	
	/**
	 * Constructs a new {@link LabelInstruction}.
	 * @param owner The method owner.
	 * @param label The label. 
	 * @param codePosition This node's code position.
	 */
	public LabelInstruction(MethodDecNode owner, Label label, int codePosition) {
		super(owner, codePosition, -1);
		this.label = label;
	}

	/**
	 * Gets this node's label.
	 * @return The label.
	 */
	public Label getLabel() {
		return label;
	}

}
