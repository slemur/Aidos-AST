package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a division expression for the opcode IDIV.
 * @author `Discardedx2
 */
public class DivideExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link DivideExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public DivideExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.DIVIDE);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() / ((PushInstruction) right).getValue();
	}

}
