package org.aidos.tree;

import org.aidos.tree.node.MethodDecNode;
import org.objectweb.asm.Label;

/**
 * Represents an exception in a {@link MethodDecNode}.
 * @author `Discardedx2
 */
public class ClassException {

	/**
	 * The owner of this node.
	 */
	private MethodDecNode owner;
	/**
	 * This node's type.
	 */
	private String type;
	/**
	 * This node's start label.
	 */
	private Label start;
	/**
	 * This node's handler label.
	 */
	private Label handler;
	/**
	 * This node's end label.
	 */
	private Label end;
	
	/**
	 * Constructs a new ExceptionInstruction.
	 * @param owner The owner of this node.
	 * @param position This node's code position.
	 * @param type This exception's type.
	 * @param start This start of this exception.
	 * @param handler The handler of this exception.
	 * @param end The end of this exception.
	 */
	public ClassException(MethodDecNode owner, String type, Label start, Label handler, Label end) {
		this.owner = owner;
		this.type = type;
		this.start = start;
		this.handler = handler;
		this.end = end;
	}

	/**
	 * Gets this node's type.
	 * @return the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets this node's type.
	 * @param name the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets this node's start label.
	 * @return the start label.
	 */
	public Label getStart() {
		return start;
	}
	
	/**
	 * Sets this node's start label.
	 * @param start the start label to set
	 */
	public void setStart(Label start) {
		this.start = start;
	}
	
	/**
	 * Gets this node's handler label.
	 * @return the handler label.
	 */
	public Label getHandler() {
		return handler;
	}
	
	/**
	 * Sets this node's start label.
	 * @param start the start label to set
	 */
	public void setHandler(Label handler) {
		this.handler = handler;
	}

	/**
	 * Gets this node's end label.
	 * @return the end label.
	 */
	public Label getEnd() {
		return end;
	}

	/**
	 * Sets this node's end label.
	 * @param end the end label to set
	 */
	public void setEnd(Label end) {
		this.end = end;
	}

	/**
	 * Gets this node's owner.
	 * @return the owner
	 */
	public MethodDecNode getOwner() {
		return owner;
	}

}
