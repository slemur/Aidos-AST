package org.aidos.tree.node;

/**
 * Represents any type of node that invokes two or more other nodes.
 * @author `Discardedx2
 */
public abstract class InvocableNode extends Node {

	/**
	 * Represents the left assignment node.
	 */
	protected Node left;
	/**
	 * Represents the right assignment node.
	 */
	protected Node right;
	/**
	 * Represents the final invoked value.
	 */
	protected Object invokedValue;
	
	/**
	 * Constructs a new {@link InvocableNode}.
	 * @param left The left hand assignment.
	 * @param right The right hand assignment.
	 * @param opcode 
	 */
	public InvocableNode(Node left, Node right, int opcode) {
		super(opcode);
		this.left = left;
		this.right = right;
	}

	/**
	 * Gets the left hand assignment.
	 * @return The left assignment.
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * Gets the right hand assignment.
	 * @return The right assignment.
	 */
	public Node getRight() {
		return right;
	}
	
	/**
	 * Gets the invoked value.
	 * @return The invoked value.
	 */
	public Object getInvokedValue() {
		return invokedValue;
	}
	
	/**
	 * Invokes the two nodes together.
	 */
	public abstract void invoke();

}
