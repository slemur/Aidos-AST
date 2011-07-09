package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a multiplication expression for the opcode IMUL.
 * @author `Discardedx2
 */
public class MultiplyExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link MultiplyExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public MultiplyExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.MULTIPLY);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() * ((PushInstruction) right).getValue();
	}

}
