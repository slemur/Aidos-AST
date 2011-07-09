package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a left shift expression.
 * @author `Discardedx2
 */
public class LeftShiftExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link LeftShiftExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public LeftShiftExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.LEFT_SHIFT);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() << ((PushInstruction) right).getValue();
	}

}
