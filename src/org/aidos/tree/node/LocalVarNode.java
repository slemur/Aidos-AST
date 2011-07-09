package org.aidos.tree.node;

import org.objectweb.asm.Label;

/**
 * Represents a local variable.
 * @author `Discardedx2
 */
public class LocalVarNode extends InstructionNode {

	/**
	 * The owner of this node.
	 */
	private MethodDecNode owner;
	/**
	 * This node's name.
	 */
	private String name;
	/**
	 * This node's descriptor.
	 */
	private String descriptor;
	/**
	 * This node's signature.
	 */
	private String signature;
	/**
	 * This node's start label.
	 */
	private Label start;
	/**
	 * This node's end label.
	 */
	private Label end;
	/**
	 * This node's index.
	 */
	private int index;
	
	/**
	 * Constructs a new LocalVarnode
	 * @param owner The owner of this node.
	 * @param name The name of this node.
	 * @param desc The description of this node.
	 * @param signature The signature of this node.
	 * @param start The start label of this node.
	 * @param end The end label of this node.
	 * @param index This node's index.
	 * @param position This node's code position in it's owner.
	 * @param opcode This node's opcode.
	 */
	public LocalVarNode(MethodDecNode owner, String name, String desc, String signature, Label start, Label end, int index, int position, int opcode) {
		super(owner, position, opcode);
		this.owner = owner;
		this.name = name;
		this.descriptor = desc;
		this.signature = signature;
		this.start = start;
		this.end = end;
		this.index = index;
	}

	/**
	 * Gets this node's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets this node's name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets this node's descriptor.
	 * @return the descriptor
	 */
	public String getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets this node's descriptor.
	 * @param descriptor the descriptor to set
	 */
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Gets this node's signature.
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets this node's signature.
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
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
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets this node's owner.
	 * @return the owner
	 */
	public MethodDecNode getOwner() {
		return owner;
	}

}
