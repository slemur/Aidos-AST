package org.aidos.tree.node;

import org.aidos.tree.NodeTree;
import org.objectweb.asm.Opcodes;


/**
 * This is the type of {@link Node} used to solve/simplify 
 * mathematical expressions. 
 * @author `Discardedx2
 */
public class EquationNode extends Node {

	/**
	 * Represents the node on the left side of the expression.
	 */
	private IntInstruction left;
	/**
	 * Represents the node on the right side of the expression.
	 */
	private IntInstruction right;
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
		NOT(-1), XOR(Opcodes.IXOR),
		LEFT_SHIFT(-1), RIGHT_SHIFT(-1), 
		RIGHT_LOGICAL_SHIFT(-1), MODULUS(-1);
		
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
	public EquationNode(NodeTree tree, IntInstruction left, IntInstruction right, Operator operator) {
		super(operator.getOpcode());
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	/**
	 * Invokes the {@link IntInstruction}s with the set operator.
	 * @param leftFirst {@code true} if the left operator should be 
	 * @return The invoked value of the two nodes.
	 */
	public int getInvokedValue() {
		int leftVal = left.getValue();
		int rightVal = right.getValue();
		switch(operator) {
		case ADD:
			return leftVal + rightVal;
		case MULTIPLY:
			return leftVal * rightVal;
		case AND:
			return leftVal & rightVal;
		case OR:
			return leftVal | rightVal;
		case XOR:
			return leftVal ^ rightVal;
		case SUBTRACT:
			return rightVal - leftVal;
		case DIVIDE:
			return rightVal / leftVal;
		case LEFT_SHIFT:
			return rightVal << leftVal;
		case RIGHT_SHIFT:
			return rightVal >> leftVal;
		case RIGHT_LOGICAL_SHIFT:
			return rightVal >>> leftVal;
		case MODULUS:
			return rightVal % leftVal;
		}
		return -1;
	}

	/**
	 * Gets the left node.
	 * @return The left node.
	 */
	public Node getLeftNode() {
		return left;
	}

	/**
	 * Gets the right node.
	 * @return The right node.
	 */
	public Node getRightNode() {
		return right;
	}

	/**
	 * Gets the operator node.
	 * @return The operator.
	 */
	public Operator getOperator() {
		return operator;
	}

}
