package org.aidos.tree;

/**
 * This class represents a {@link ClassFile}'s interface (if it has any).
 * @author `Discardedx2
 */
public class ClassInterface {
	
	/**
	 * The interface's name.
	 */
	private String name;
	
	/**
	 * Constructs a new {@link ClassInterface}.
	 * @param name The interface name.
	 */
	public ClassInterface(String name) {
		this.name = name;
	}

	/**
	 * Gets the interface's name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the interface's name.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
