package org.aidos.tree.util;

import org.aidos.tree.exception.NotValidInstructionException;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.MethodDecNode;

/**
 * This class is used to point towards different types of {@link InstructionNode}s inside
 * of methods by searching through each {@link MethodDecNode}'s instruction set.
 * Exceptions should never be thrown in this class, however if they are, it will be because
 * the pointer was unable to grab the instruction from the array.
 * @author `Discardedx2
 */
public class InstructionPointer {

	/**
	 * The current code offset.
	 */
	private int offset = 0;
	/**
	 * The previous code offset.
	 */
	private int previousOffset;
	/**
	 * This method's instruction set.
	 */
	private InstructionNode[] instructions;

	/**
	 * Constructs a new {@link InstructionPointer}.
	 * @param instructions The instructions to search through.
	 */
	public InstructionPointer(InstructionNode[] instructions) {
		this.instructions = instructions;
	}

	/**
	 * Increments the offset.
	 */
	public void increment() {
		offset++;
	}

	/**
	 * Decrements the offset.
	 */
	public void decrement() {
		offset--;
	}

	/**
	 * Resets the offset.
	 */
	public void reset() {
		offset = 0;
	}

	/**
	 * Gets the node at the current offset.
	 * @return The node at the current offset.
	 */
	public InstructionNode current() {
		if (offset < 0 || offset > instructions.length) {
			throw new NotValidInstructionException("Current instruction is unavailable.");
		}
		return instructions[offset];
	}

	/**
	 * Gets the node at the next offset.
	 * @return The node at the next offset.
	 */
	public InstructionNode next() {
		if (offset + 1 > instructions.length) {
			throw new NotValidInstructionException("Next instruction is unavailable.");
		}
		return instructions[offset++];
	}

	/**
	 * Gets the node at the previous offset.
	 * @return The node at the previous offset.
	 */
	public InstructionNode prev() {
		if (offset - 1 < 0) {
			throw new NotValidInstructionException("Previous instruction is unavailable.");
		}
		return instructions[offset--];
	}

	/**
	 * Jumps to a specified instruction.
	 * @param node The node to jump to.
	 */
	public void jump(InstructionNode node) {
		this.previousOffset = offset;
		this.offset = node.getCodePosition();
	}

	/**
	 * Jumps to a specified offset.
	 * @param offset The offset to jump to.
	 */
	public void jump(int offset) {
		this.previousOffset = offset;
		this.offset = offset;
	}

	/**
	 * Jumps back to the previous offset if there is one.
	 */
	public void jumpBack() {
		if (previousOffset != -1) {
			this.offset = previousOffset;
		}
	}

	/**
	 * Checks if there are more instructions to search through.
	 * @return {@code true} if there are instructions to be searched, otherwise {@code false}.
	 */
	public boolean hasNext() {
		return current() != null && instructions.length > offset++;
	}

	/**
	 * Gets the current pointer offset.
	 * @return The offset.
	 */
	public int getOffset() {
		return offset;
	}

}
