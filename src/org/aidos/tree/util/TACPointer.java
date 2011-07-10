package org.aidos.tree.util;

import java.util.List;

import org.aidos.tree.exception.NotValidInstructionException;
import org.aidos.tree.tac.ThreeAddressFunction;

/**
 * This class is used to point towards different types of {@link ThreeAddressFunction}s inside
 * of methods by searching through each {@link MethodDecfunction}'s function set.
 * Exceptions should never be thrown in this class, however if they are, it will be because
 * the pointer was unable to grab the function from the array.
 * @author `Discardedx2
 */
public class TACPointer {

	/**
	 * The current code offset.
	 */
	private int offset = 0;
	/**
	 * The previous code offset.
	 */
	private int previousOffset;
	/**
	 * This method's function set.
	 */
	private ThreeAddressFunction[] functions;

	/**
	 * Constructs a new {@link TACPointer}.
	 * @param functions The functions to search through.
	 */
	public TACPointer(List<ThreeAddressFunction> functionList) {
		this.functions = new ThreeAddressFunction[functionList.size()];
		for (int i = 0; i < functionList.size(); i++) {
			functions[i] = functionList.get(i);
		}
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
	 * Gets the function at the current offset.
	 * @return The function at the current offset.
	 */
	public ThreeAddressFunction current() {
		if (offset < 0 || offset > functions.length) {
			throw new NotValidInstructionException("Current function is unavailable.");
		}
		return functions[offset];
	}

	/**
	 * Gets the function at the next offset.
	 * @return The function at the next offset.
	 */
	public ThreeAddressFunction next() {
		if (offset + 1 > functions.length) {
			throw new NotValidInstructionException("Next function is unavailable.");
		}
		return functions[offset++];
	}

	/**
	 * Gets the function at the previous offset.
	 * @return The function at the previous offset.
	 */
	public ThreeAddressFunction prev() {
		if (offset - 1 < 0) {
			throw new NotValidInstructionException("Previous function is unavailable.");
		}
		return functions[offset--];
	}

	/**
	 * Adds a function to the last index.
	 * @param add The function to add.
	 */
	public void addLast(ThreeAddressFunction add) {
		int last = functions.length - 1;
		add.getInstruction().setCodePosition(last);
		functions[last] = add;
	}

	/**
	 * Adds a function before a specified function.
	 * @param function The function to add before.
	 * @param add The function to add.
	 */
	public void addBefore(ThreeAddressFunction function, ThreeAddressFunction add) {
		if (function.getInstruction().getCodePosition() - 1 < 0) {
			throw new NotValidInstructionException("Instruction before "+function.getInstruction().getCodePosition()+" is not availiable.");
		}
		functions[function.getInstruction().getCodePosition() - 1] = add;
	}

	/**
	 * Adds a function before a specified position.
	 * @param pos The position to add before.
	 * @param add The function to add.
	 */
	public void addBefore(int pos, ThreeAddressFunction add) {
		if (pos - 1 < 0) {
			throw new NotValidInstructionException("Instruction before "+(pos - 1)+" is not availiable.");
		}
		functions[pos - 1] = add;
	}

	/**
	 * Adds a function after a specified function.
	 * @param function The function to add after.
	 * @param add The function to add.
	 */
	public void addAfter(ThreeAddressFunction function, ThreeAddressFunction add) {
		if (function.getInstruction().getCodePosition() + 1 > functions.length) {
			throw new NotValidInstructionException("Instruction after "+function.getInstruction().getCodePosition()+" is not availiable.");
		}
		functions[function.getInstruction().getCodePosition() + 1] = add;
	}

	/**
	 * Adds a function after a specified position.
	 * @param pos The position to add after.
	 * @param add The function to add.
	 */
	public void addAfter(int pos, ThreeAddressFunction add) {
		if (pos + 1 > functions.length) {
			throw new NotValidInstructionException("Instruction after "+pos+" is not availiable.");
		}
		functions[pos + 1] = add;
	}

	/**
	 * Overwrites a specified function with another function.
	 * @param function The function to overwrite.
	 * @param replace The function to replace with.
	 */
	public void overwrite(ThreeAddressFunction function, ThreeAddressFunction replace) {
		functions[function.getInstruction().getCodePosition()] = replace;
	}

	/**
	 * Overwrites a specified function with another function.
	 * @param pos The function to overwrite.
	 * @param replace The function to replace with.
	 */
	public void overwrite(int pos, ThreeAddressFunction add) {
		functions[pos] = add;
	}

	/**
	 * Removes a specified function.
	 * @param function The function to remove.
	 */
	public void remove(ThreeAddressFunction function) {
		functions[function.getInstruction().getCodePosition()] = null;
	}

	/**
	 * Removes a function at the specified index.
	 * @param pos The index to remove the function from.
	 */
	public void remove(int pos) {
		functions[pos] = null;
	}

	/**
	 * Gets a function at a certain position.
	 * @param pos The position to get the function at.
	 * @return The function at the position.
	 */
	public ThreeAddressFunction get(int pos) {
		if (pos < 0 || pos > functions.length) {
			throw new NotValidInstructionException("Instruction at "+pos+" is unavailable.");
		}
		return functions[pos];
	}

	/**
	 * Gets the function at the last position.
	 * @return The function at the last position.
	 */
	public ThreeAddressFunction getLast() {
		return functions[getEmptySlot() - 1];
	}

	/**
	 * Jumps to a specified function.
	 * @param function The function to jump to.
	 */
	public void jump(ThreeAddressFunction function) {
		this.previousOffset = offset;
		this.offset = function.getInstruction().getCodePosition();
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
	 * Jumps to an unsafe offset.
	 * @param offset The offset to jump to.
	 */
	public void jumpUnsafe(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the nearest empty function slot.
	 * @return The nearest empty slot.
	 */
	public int getEmptySlot() {
		for (int i = 0; i < functions.length; i++) {
			if (functions[i] == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks if there are more functions to search through.
	 * @return {@code true} if there are functions to be searched, otherwise {@code false}.
	 */
	public boolean hasCurrent() {
		return current() != null && functions.length > offset++;
	}

	/**
	 * Gets the current pointer offset.
	 * @return The offset.
	 */
	public int getOffset() {
		return offset;
	}

}
