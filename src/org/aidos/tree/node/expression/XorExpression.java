package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a xor expression.
 * @author `Discardedx2
 */
public class XorExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link XorExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public XorExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.XOR);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() ^ ((PushInstruction) right).getValue();
	}

}
