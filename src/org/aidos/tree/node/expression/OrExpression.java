package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a bitwise OR expression.
 * @author `Discardedx2
 */
public class OrExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link OrExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public OrExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.OR);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() | ((PushInstruction) right).getValue();
	}

}
