package org.aidos.tree.encoding;

import org.aidos.tree.node.FieldInstruction;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.IntIncrementInstruction;
import org.aidos.tree.node.IntInstruction;
import org.aidos.tree.node.JumpInstruction;
import org.aidos.tree.node.LabelInstruction;
import org.aidos.tree.node.LdcInstruction;
import org.aidos.tree.node.LocalVariableInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

/**
 * Encodes a modified class back into bytecode format.
 * @author `Discardedx2
 */
public class MethodEncoder extends MethodAdapter {

	/**
	 * Constructs a new {@link MethodEncoder}.
	 * @param mv The visitor to encode.
	 * @param method The method to encode.
	 */
	public MethodEncoder(MethodVisitor mv, MethodDecNode method) {
		super(mv);
		InstructionPointer pointer = new InstructionPointer(method.getInstructions());
		super.visitCode();
		for (Attribute attribute : method.getAttributes()) {
			super.visitAttribute(attribute);
		}
		pointer.jumpUnsafe(-1);
		while(pointer.next() != null) {
			InstructionNode current = pointer.current();
			if (current instanceof IntInstruction) {
				IntInstruction insn = (IntInstruction) current;
				super.visitIntInsn(insn.getOpcode(), insn.getValue());
				continue;
			} else if (current instanceof LdcInstruction) {
				LdcInstruction ldc = (LdcInstruction) current;
				super.visitLdcInsn(ldc.getConstant());
				continue;
			} else if (current instanceof FieldInstruction) {
				FieldInstruction fin = (FieldInstruction) current;
				super.visitFieldInsn(fin.getOpcode(), fin.getOwner().getName(), fin.getName(), fin.getDescriptor());
				continue;
			} else if (current instanceof LocalVariableInstruction) {
				LocalVariableInstruction lvi = (LocalVariableInstruction) current;
				Label start = lvi.getStart();
				Label end = lvi.getEnd();
				super.visitLocalVariable(lvi.getName(), lvi.getDescriptor(), lvi.getSignature(), start, end, lvi.getIndex());
				continue;
			} else if (current instanceof IntIncrementInstruction) {
				IntIncrementInstruction iinc = (IntIncrementInstruction) current;
				super.visitIincInsn(iinc.getVar(), iinc.getIncrement());
				continue;
			} else if (current instanceof JumpInstruction) {
				JumpInstruction jump = (JumpInstruction) current;
				super.visitJumpInsn(jump.getOpcode(), jump.getJumpTo());
				continue;
			} else if (current instanceof LabelInstruction) {
				LabelInstruction label = (LabelInstruction) current;
				super.visitLabel(label.getLabel());
				continue;
			}
		}
		super.visitMaxs(method.getMaxStackSize(), method.getMaxLocals());
		super.visitEnd();
	}
	
}
