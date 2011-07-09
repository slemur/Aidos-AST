package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents an bitwise AND expression.
 * @author `Discardedx2
 */
public class AndExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link AndExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public AndExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.AND);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() & ((PushInstruction) right).getValue();
	}

}
