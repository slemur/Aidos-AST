package org.aidos.tree.node.expression;

import org.aidos.tree.node.IntInstruction;

/**
 * Represents an addition expression for the opcode IADD.
 * @author `Discardedx2
 */
public class AddExpression extends ArithmeticExpressionNode {

	public AddExpression(IntInstruction left, IntInstruction right) {
		super(left, right, Operator.ADD);
	}

	@Override
	public void invoke() {
		invokedValue = ((IntInstruction) left).getValue() + ((IntInstruction) right).getValue();
	}

}
