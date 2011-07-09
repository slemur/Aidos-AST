package org.aidos.tree.node;


/**
 * Represents a node as a field instruction.
 * @author `Discardedx2
 */
public class FieldInstruction extends InstructionNode {
	
	/**
	 * This node's owner.
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
	 * Constructs a new {@link FieldInstruction}.
	 * @param descriptor The descriptor of this field instruction.
	 * @param name The name of this field instruction.
	 * @param fieldInstruction The field instruction of this node.
	 */
	public FieldInstruction(MethodDecNode owner, int position, int opcode, String name, String descriptor) {
		super(owner, position, opcode);
		this.owner = owner;
		this.name = name;
		this.descriptor = descriptor;
	}

	/**
	 * Gets this node's name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets this node's name.
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets this node's descriptor.
	 * @return The descriptor.
	 */
	public String getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets this node's descriptor.
	 * @param descriptor the descriptor to set.
	 */
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Gets this node's owner.
	 * @return The owner.
	 */
	public MethodDecNode getOwner() {
		return owner;
	}
	
}
