package org.aidos.tree.node.expression;

import org.aidos.tree.node.PushInstruction;

/**
 * Represents a modulus expression for the opcode IREM.
 * @author `Discardedx2
 */
public class ModulusExpression extends ArithmeticExpressionNode {

	/**
	 * Constructs a new {@link ModulusExpression}.
	 * @param left The integer on the left hand assignment.
	 * @param right The integer on the right hand assignment.
	 */
	public ModulusExpression(PushInstruction left, PushInstruction right) {
		super(left, right, Operator.MODULUS);
	}

	@Override
	public void invoke() {
		invokedValue = ((PushInstruction) left).getValue() % ((PushInstruction) right).getValue();
	}

}
