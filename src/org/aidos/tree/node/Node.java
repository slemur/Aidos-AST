package org.aidos.tree.node;

/**
 * An interface, all classes that extend this class will be different types of nodes.
 * @author `Discardedx2
 */
public abstract class Node {
	
	/**
	 * The opcode of this node's instruction.
	 */
	private int opcode;
	/**
	 * {@code true} if this node has been analyzed. Otherwise false.
	 */
	private boolean analyzed;
	
	/**
	 * Constructs a new node.
	 * @param opcode The opcode of this node.
	 */
	public Node(int opcode) {
		this.opcode = opcode;
	}

	/**
	 * Gets this node's opcode.
	 * @return The opcode.
	 */
	public int getOpcode() {
		return opcode;
	}

	/**
	 * Sets this node's opcode.
	 * @param opcode The opcode to set.
	 */
	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	/**
	 * Checks if this node has been analyzed or not.
	 * @return {@code true} if this node has been analyzed. Otherwise false.
	 */
	public boolean isAnalyzed() {
		return analyzed;
	}

	/**
	 * Sets this class analyzed.
	 * @param analyzed The analyzed value to set.
	 */
	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}
	
	
}
