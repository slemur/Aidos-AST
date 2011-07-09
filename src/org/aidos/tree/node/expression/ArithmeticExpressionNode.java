package org.aidos.tree.node.expression;

import org.aidos.tree.node.InvocableNode;
import org.aidos.tree.node.Node;
import org.aidos.tree.node.PushInstruction;
import org.objectweb.asm.Opcodes;


/**
 * This is the type of {@link Node} used to solve/simplify 
 * mathematical expressions. 
 * @author `Discardedx2
 */
public abstract class ArithmeticExpressionNode extends InvocableNode {
	
	/**
	 * Represents the operator used in the expression. i.e +, -, *, /
	 */
	private Operator operator;

	/**
	 * An enum used to select the type of operator
	 * used in an expression.
	 * @author `Discardedx2
	 */
	public enum Operator {
		ADD(Opcodes.IADD), SUBTRACT(Opcodes.ISUB),
		MULTIPLY(Opcodes.IMUL), DIVIDE(Opcodes.IDIV),
		AND(Opcodes.IAND), OR(Opcodes.IOR),
		XOR(Opcodes.IXOR), LEFT_SHIFT(-1), 
		RIGHT_SHIFT(-1), RIGHT_LOGICAL_SHIFT(-1),
		MODULUS(Opcodes.IREM);
		
		/**
		 * The opcode of this operator.
		 */
		private int opcode;

		/**
		 * Constructs an operator with a set string value.
		 * @param stringValue The value of the operator as a string.
		 */
		Operator(int opcode) {
			this.opcode = opcode;
		}
		
		/**
		 * Gets this operator's opcode.
		 * @return The opcode.
		 */
		public int getOpcode() {
			return opcode;
		}
	}

	/**
	 * Constructs a new EquationNode.
	 * @param left The node on the left side of the expression.
	 * @param right The node on the right side of the expression.
	 * @param operator The operator used in the expression.
	 */
	public ArithmeticExpressionNode(PushInstruction left, PushInstruction right, Operator operator) {
		super(left, right, operator.getOpcode());
		this.operator = operator;
	}

	/**
	 * Gets the operator node.
	 * @return The operator.
	 */
	public Operator getOperator() {
		return operator;
	}

}
