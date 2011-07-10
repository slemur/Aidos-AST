package org.aidos.tree.encoding;

import java.util.ArrayList;
import java.util.List;

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

public class MethodEncoder extends MethodAdapter {

	private MethodDecNode method;
	private InstructionPointer pointer;
	private List<Label[]> labels = new ArrayList<Label[]>();

	public MethodEncoder(MethodVisitor mv, MethodDecNode method) {
		super(mv);
		this.method = method;
		this.pointer = new InstructionPointer(method.getInstructions());
	}

	public void encode() {
		super.visitCode();
		for (Attribute attribute : method.getAttributes()) {
			super.visitAttribute(attribute);
		}
		labels.addAll(getLabels());
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

	public List<Label[]> getLabels() {
		List<Label[]> labels = new ArrayList<Label[]>();
		int offset = 0;
		while (pointer.get(offset) != null) {
			InstructionNode insn = pointer.get(offset);
			if (insn instanceof LabelInstruction) {
				Label[] larray = new Label[2];
				larray[0] = ((LabelInstruction)insn).getLabel();
				larray[1] = new Label();
				labels.add(larray);
			}
			offset++;
		}
		return labels;
	}

}
