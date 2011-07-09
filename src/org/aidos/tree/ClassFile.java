package org.aidos.tree;

import java.util.ArrayList;
import java.util.List;

import org.aidos.tree.node.FieldDecNode;
import org.aidos.tree.node.MethodDecNode;

/**
 * Represents a half-compiled java class in bytecode.
 * @author `Discardedx2
 */
public class ClassFile {

	/**
	 * This class' version (Default V1_6)
	 */
	private int classVersion;
	/**
	 * This class' modifier.
	 */
	private int modifier;
	/**
	 * The name of this class file.
	 */
	private String name;
	/**
	 * This signature of this class file.
	 */
	private String signature;
	/**
	 * This represents the super class of this file.
	 */
	private ClassFile superClass;
	/**
	 * The interfaces which were parsed into a {@link ClassInterface} for easier access.
	 */
	private List<ClassInterface> interfaces = new ArrayList<ClassInterface>();
	/**
	 * The parsed methods stored in seperate {@link MethodDecNode} for easy access.
	 */
	private List<MethodDecNode> methods = new ArrayList<MethodDecNode>();
	/**
	 * The parsed fields stored in seperate {@link FieldDecNode} for easy access.
	 */
	private List<FieldDecNode> fields = new ArrayList<FieldDecNode>();
	
	public ClassFile(int classVersion) {
		this.classVersion = classVersion;
	}
	

	/**
	 * Gets the class version.
	 * @return the classVersion
	 */
	public int getClassVersion() {
		return classVersion;
	}

	/**
	 * Sets the class version.
	 * @param classVersion the classVersion to set
	 */
	public void setClassVersion(int classVersion) {
		this.classVersion = classVersion;
	}

	/**
	 * Gets the class modifier.
	 * @return the modifier
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * Sets the class modifier.
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	/**
	 * Gets the class name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the class name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the class signature.
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets the class signature.
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * Gets the super class.
	 * @return the superClass
	 */
	public ClassFile getSuperClass() {
		return superClass;
	}

	/**
	 * Sets the super class.
	 * @param superClass the superClass to set
	 */
	public void setSuperClass(ClassFile superClass) {
		this.superClass = superClass;
	}

	/**
	 * Gets the interfaces.
	 * @return the interfaces
	 */
	public List<ClassInterface> getInterfaces() {
		return interfaces;
	}

	/**
	 * Gets the methods.
	 * @return the methods
	 */
	public List<MethodDecNode> getMethods() {
		return methods;
	}

	/**
	 * Gets the fields.
	 * @return the fields
	 */
	public List<FieldDecNode> getFields() {
		return fields;
	}
}
