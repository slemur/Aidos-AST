package org.aidos.tree.analyzer.flow;

import org.aidos.tree.analyzer.BytecodeAnalyzer;
import org.aidos.tree.exception.InstructionNotSupportedException;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.JumpInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;
import org.objectweb.asm.Opcodes;

/**
 * This class is used to analyze the flow block. Each GOTO instruction contains a label
 * which it attempts to jump too. This class' job is to identify the position
 * that the instruction wishes to jump too, and add it to the list of loaded flow blocks.
 * If the instruction is not a GOTO instruction, the analyzer will then turn the bytecode stack into
 * a low level IR.
 * TODO Add support for the JSR instruction.
 * TODO Make code for generating the low level IR.
 * @author `Discardedx2
 */
public class FlowAnalyzer implements BytecodeAnalyzer<FlowBlock> {

	@Override
	public boolean onAnalyze(Object object, MethodDecNode method, InstructionPointer pointer) {
		if (object instanceof FlowBlock) {
			int startPos = 0;
			FlowBlock block = (FlowBlock) object;
			if (block != null) {
				startPos = block.getFlowStartOffset();
			}
			for (int i = startPos; i < method.getFlowBlocks().length; i++) {
				InstructionNode current = pointer.get(i);
				if (current instanceof JumpInstruction) {
					if (current.getOpcode() == Opcodes.JSR) {
						throw new InstructionNotSupportedException("Jump-Subroutine opcodes are not supported");
					} else if (current.getOpcode() == Opcodes.GOTO) {
						JumpInstruction jump = (JumpInstruction) current;
						int targetPos = -1;
						try {
							targetPos = pointer.resolveLabelPosition(jump.getJumpTo());
						} catch (Exception e) {
							throw new RuntimeException("Unable to generate target position for flow label!", e.getCause());
						}
						FlowBlock target = method.getFlowBlocks()[targetPos];
						if (target == null) {
							target = new FlowBlock(this, targetPos);
							method.getFlowBlocks()[targetPos] = target;
							queue.add(target);
						}
						return true;
					}
				} else {
					//generate low level IR.
				}
			}
		}
		return false;
	}
	
}
