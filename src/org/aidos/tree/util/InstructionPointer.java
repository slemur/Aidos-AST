package org.aidos.tree.util;

import java.util.List;

import org.aidos.tree.analyzer.flow.FlowBlock;
import org.aidos.tree.exception.NotValidInstructionException;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.LabelInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.objectweb.asm.Label;

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
	public InstructionPointer(List<InstructionNode> instructionList) {
		this.instructions = new InstructionNode[instructionList.size()];
		for (int i = 0; i < instructionList.size(); i++) {
			instructions[i] = instructionList.get(i);
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
	 * Adds an instruction to the last index.
	 * @param add The instruction to add.
	 */
	public void addLast(InstructionNode add) {
		int last = getEmptySlot();
		add.setCodePosition(last);
		instructions[last] = add;
	}

	/**
	 * Adds an instruction before a specified instruction.
	 * @param instruction The instruction to add before.
	 * @param add The instruction to add.
	 */
	public void addBefore(InstructionNode instruction, InstructionNode add) {
		if (instruction.getCodePosition() - 1 < 0) {
			throw new NotValidInstructionException("Instruction before "+instruction.getCodePosition()+" is not availiable.");
		}
		instructions[instruction.getCodePosition() - 1] = add;
	}

	/**
	 * Adds an instruction before a specified position.
	 * @param pos The position to add before.
	 * @param add The instruction to add.
	 */
	public void addBefore(int pos, InstructionNode add) {
		if (pos - 1 < 0) {
			throw new NotValidInstructionException("Instruction before "+(pos - 1)+" is not availiable.");
		}
		instructions[pos - 1] = add;
	}

	/**
	 * Adds an instruction after a specified instruction.
	 * @param instruction The instruction to add after.
	 * @param add The instruction to add.
	 */
	public void addAfter(InstructionNode instruction, InstructionNode add) {
		if (instruction.getCodePosition() + 1 > instructions.length) {
			throw new NotValidInstructionException("Instruction after "+instruction.getCodePosition()+" is not availiable.");
		}
		instructions[instruction.getCodePosition() + 1] = add;
	}

	/**
	 * Adds an instruction after a specified position.
	 * @param pos The position to add after.
	 * @param add The instruction to add.
	 */
	public void addAfter(int pos, InstructionNode add) {
		if (pos + 1 > instructions.length) {
			throw new NotValidInstructionException("Instruction after "+pos+" is not availiable.");
		}
		instructions[pos + 1] = add;
	}

	/**
	 * Overwrites a specified instruction with another instruction.
	 * @param instruction The instruction to overwrite.
	 * @param replace The instruction to replace with.
	 */
	public void overwrite(InstructionNode instruction, InstructionNode replace) {
		instructions[instruction.getCodePosition()] = replace;
	}

	/**
	 * Overwrites a specified instruction with another instruction.
	 * @param pos The instruction to overwrite.
	 * @param replace The instruction to replace with.
	 */
	public void overwrite(int pos, InstructionNode add) {
		instructions[pos] = add;
	}

	/**
	 * Removes a specified instruction.
	 * @param instruction The instruction to remove.
	 */
	public void remove(InstructionNode instruction) {
		instructions[instruction.getCodePosition()] = null;
	}

	/**
	 * Removes an instruction at the specified index.
	 * @param pos The index to remove the instruction from.
	 */
	public void remove(int pos) {
		instructions[pos] = null;
	}

	/**
	 * Gets a node at a certain position.
	 * @param pos The position to get the node at.
	 * @return The node at the position.
	 */
	public InstructionNode get(int pos) {
		if (pos < 0 || pos > instructions.length) {
			throw new NotValidInstructionException("Instruction at "+pos+" is unavailable.");
		}
		return instructions[pos];
	}

	/**
	 * Gets the node at the last position.
	 * @return The node at the last position.
	 */
	public InstructionNode getLast() {
		return instructions[getEmptySlot() - 1];
	}

	/**
	 * Resolves a label's actual code position.
	 * @param label The label to resolve.
	 * @return The label's code position.
	 */
	public int resolveLabelPosition(Label label) {
		int start = offset;
		reset();
		for (int i = 0; i < instructions.length; i++) {
			InstructionNode node = instructions[i];
			if (node instanceof LabelInstruction) {
				LabelInstruction l = (LabelInstruction) node;
				if (l.getLabel() == label) {
					return offset;
				}
			} else {
				offset++;
			}
		}
		offset = start;
		return -1;
	}

	/**
	 * Resolves a flow block by it's position.
	 * @param method The method to grab the block from.
	 * @return The flow block.
	 */
	public FlowBlock resolveFlowBlock(MethodDecNode method, int pos) {
		try {
			int size = method.getFlowBlocks().size();
			if (pos > size || pos < 0) {
				return null;
			}
			return method.getFlowBlocks().get(pos);
		} catch (Exception e) {
			return null;
		}
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
	 * Jumps to an unsafe offset.
	 * @param offset The offset to jump to.
	 */
	public void jumpUnsafe(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the nearest empty instruction slot.
	 * @return The nearest empty slot.
	 */
	public int getEmptySlot() {
		for (int i = 0; i < instructions.length; i++) {
			if (instructions[i] == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks if there are more instructions to search through.
	 * @return {@code true} if there are instructions to be searched, otherwise {@code false}.
	 */
	public boolean hasCurrent() {
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
