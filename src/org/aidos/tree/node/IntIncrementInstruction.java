package org.aidos.tree.node;


/**
 * Represents an instruction that loads a value from the constant pool.
 * @author `Discardedx2
 */
public class IntIncrementInstruction extends InstructionNode {

	/**
	 * This represents the index of the variable to increment.
	 */
	private int var;
	/**
	 * This represents the amount to increment the var by.
	 */
	private int increment;
	
	/**
	 * Constructs a new {@link IntIncrementInstruction}.
	 * @param owner The owner of this node.
	 * @param position The code position of this node in it's owner.
	 * @param var The constant value of this instruction.
	 */
	public IntIncrementInstruction(MethodDecNode owner, int position, int var, int increment) {
		super(owner, position, -1);
		this.var = var;
		this.increment = increment;
	}
	
	/**
	 * Gets the var.
	 * @return The var.
	 */
	public int getVar() {
		return var;
	}
	
	/**
	 * Gets the increment.
	 * @return The increment.
	 */
	public int getIncrement() {
		return increment;
	}

}
