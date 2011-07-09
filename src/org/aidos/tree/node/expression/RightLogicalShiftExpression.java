package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a right logical shift expression.
 * @author `Discardedx2
 */
public class RightLogicalShiftExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link RightLogicalShiftExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public RightLogicalShiftExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.RIGHT_LOGICAL_SHIFT);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() >>> ((PushInstruction) right).getValue();
	}

}
