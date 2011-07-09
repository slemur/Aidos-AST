package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a subtraction expression for the opcode IADD.
 * @author `Discardedx2
 */
public class SubtractExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link SubtractExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public SubtractExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.SUBTRACT);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() - ((PushInstruction) right).getValue();
	}

}
