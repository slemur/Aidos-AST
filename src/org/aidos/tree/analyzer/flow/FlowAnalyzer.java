package org.aidos.tree.analyzer.flow;

import java.util.logging.Logger;

import org.aidos.tree.NodeTree;
import org.aidos.tree.analyzer.BytecodeAnalyzer;
import org.aidos.tree.exception.InstructionNotSupportedException;
import org.aidos.tree.node.FieldInstruction;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.JumpInstruction;
import org.aidos.tree.node.LdcInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.node.PushInstruction;
import org.aidos.tree.tac.ThreeAddressFunction;
import org.aidos.tree.util.InstructionPointer;
import org.objectweb.asm.Opcodes;

/**
 * This class is used to analyze the flow block. Each GOTO instruction contains a label
 * which it attempts to jump too. This class' job is to identify the position
 * that the instruction wishes to jump too, and add it to the list of loaded flow blocks.
 * If the instruction is not a GOTO instruction, the analyzer will then turn the bytecode stack into
 * a 3AC.
 * TODO Add support for the JSR instruction.
 * TODO Make code for generating the {@link ThreeAddressFunction}s.
 * @author `Discardedx2
 */
public class FlowAnalyzer implements BytecodeAnalyzer<FlowBlock> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(FlowAnalyzer.class.getName());

	/**
	 * The opcodes used in for pushing values onto the stack.
	 */
	private static final int[] PUSH_OPCODES = new int[] {
		Opcodes.BIPUSH, Opcodes.SIPUSH, Opcodes.ICONST_0,
		Opcodes.ICONST_1, Opcodes.ICONST_2, Opcodes.ICONST_3,
		Opcodes.ICONST_4, Opcodes.ICONST_5, Opcodes.ICONST_M1
	};

	@Override
	public void onAnalyze(Object object, NodeTree tree, MethodDecNode method, InstructionPointer pointer) {
		if (object instanceof FlowBlock) {
			int startPos = 0;
			FlowBlock block = (FlowBlock) object;
			if (block != null) {
				startPos = block.getFlowStartOffset();
			}
			for (int i = startPos; i < method.getFlowBlocks().size(); i++) {
				InstructionNode current = pointer.get(i);
				if (current instanceof JumpInstruction) {
					if (current.getOpcode() == Opcodes.JSR) {
						throw new InstructionNotSupportedException("Jump-to-subroutine opcodes are not supported");
					} else if (current.getOpcode() == Opcodes.GOTO) {
						JumpInstruction jump = (JumpInstruction) current;
						int targetPos = pointer.resolveLabelPosition(jump.getJumpTo());
						FlowBlock target = pointer.resolveFlowBlock(method, targetPos);
						if (target == null) {
							target = new FlowBlock(this, targetPos);
							queue.add(target);
						} else {
							ThreeAddressFunction function = new ThreeAddressFunction(jump);
							function.setFunction("Jump");
							tree.getTacStructure().store(function);
						}
					}
				} else {
					ThreeAddressFunction function = new ThreeAddressFunction(current);
					if (current instanceof PushInstruction) {
						function.setFunction("Push");
					} else if (current instanceof FieldInstruction) {
						if (current.getOpcode() == Opcodes.PUTSTATIC) {
							function.setFunction("Push_Static_Field");
						} else if (current.getOpcode() == Opcodes.PUTFIELD) {
							function.setFunction("Push_Field");
						} else if (current.getOpcode() == Opcodes.GETSTATIC) {
							function.setFunction("Get_Static_Field");
						} else if (current.getOpcode() == Opcodes.GETFIELD) {
							function.setFunction("Get_Field");
						}
					}
					if (function.getFunction() != null) {
						tree.getTacStructure().store(function);
					}
				}
			}
		}
	}

}

