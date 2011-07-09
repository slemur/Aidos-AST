package org.aidos.tree.node;

import org.aidos.tree.ClassFile;

/**
 * Represents a declared field in a {@link ClassFile}.
 * @author `Discardedx2
 */
public class FieldDecNode extends Node {
	
	/**
	 * The {@link ClassFile} that this field is declared in.
	 */
	private ClassFile owner;
	/**
	 * This field's name.
	 */
	private String name;
	/**
	 * This field's descriptor (I, Z, J ect).
	 */
	private String descriptor;
	/**
	 * This field's signature.
	 */
	private String signature;
	/**
	 * This field's modifier.
	 */
	private int modifier;
	/**
	 * This field's object value.
	 */
	private Object value;
	
	/**
	 * Constructs a new FieldDecNode.
	 * @param owner The owner of this field.
	 * @param name This field's name.
	 * @param descriptor This field's descriptor.
	 * @param signature This field's signature.
	 * @param modifier This field's modifier.
	 * @param value This field's object value.
	 */
	public FieldDecNode(ClassFile owner, String name, String descriptor, String signature, int modifier, Object value) {
		super(-1);
		this.owner = owner;
		this.name = name;
		this.descriptor = descriptor;
		this.modifier = modifier;
		this.value = value;
	}

	/**
	 * This field's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets this field's name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets this field's descriptor.
	 * @return the descriptor
	 */
	public String getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets this field's descriptor.
	 * @param descriptor the descriptor to set
	 */
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Gets this field's signature.
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets this field's signature.
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * Gets this field's modifier.
	 * @return the modifier
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * Sets this field's modifier.
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	/**
	 * Gets this object's value.
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets this object's value.
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets this field's owner.
	 * @return the owner
	 */
	public ClassFile getOwner() {
		return owner;
	}
}
