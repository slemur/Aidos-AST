package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents an addition expression for the opcode IADD.
 * @author `Discardedx2
 */
public class AddExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link AddExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public AddExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.ADD);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() + ((PushInstruction) right).getValue();
	}

}
