package org.aidos.tree.adapter;

import org.aidos.tree.ClassException;
import org.aidos.tree.FlowBlock;
import org.aidos.tree.node.FieldInstruction;
import org.aidos.tree.node.IntInstruction;
import org.aidos.tree.node.JumpInstruction;
import org.aidos.tree.node.LabelInstruction;
import org.aidos.tree.node.LdcInstruction;
import org.aidos.tree.node.LocalVarNode;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

/**
 * This class adapts method instructions and stores them in memory.
 * @author `Discardedx2
 */
public class InstructionLoadAdapter extends MethodAdapter {

	/**
	 * The method visitor to load the instructions from.
	 */
	private MethodDecNode method;
	/**
	 * The position of the current instruction.
	 */
	private InstructionPointer pointer;
	/**
	 * Constructs a new {@link InstructionLoadAdapter}.
	 * @param method The method to store instructions in.
	 */
	public InstructionLoadAdapter(MethodVisitor mv, MethodDecNode method) {
		super(mv);
		this.method = method;
		this.pointer = new InstructionPointer(method.getInstructions());
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		method.getInstructions()[pointer.getOffset()] = new IntInstruction(method, pointer.getOffset(), opcode, operand);
		pointer.increment();
		super.visitIntInsn(opcode, operand);
	}
	
	@Override
	public void visitLdcInsn(Object cst) {
		method.getInstructions()[pointer.getOffset()] = new LdcInstruction(method, pointer.getOffset(), cst);
		pointer.increment();
		super.visitLdcInsn(cst);
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		method.getInstructions()[pointer.getOffset()] =  new LocalVarNode(method, name, desc, signature, start, end, index, pointer.getOffset(), -1);
		pointer.increment();
		super.visitLocalVariable(name, desc, signature, start, end, index);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		method.getInstructions()[pointer.getOffset()] =  new FieldInstruction(method, pointer.getOffset(), opcode, name, desc);
		pointer.increment();
		super.visitFieldInsn(opcode, owner, name, desc);
	}
	
	@Override
	public void visitLabel(Label label) {	
		method.getInstructions()[pointer.getOffset()] = new LabelInstruction(method, label, pointer.getOffset());
		pointer.increment();
		super.visitLabel(label);
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		ClassException exception = new ClassException(method, type, start, handler, end);
		method.getExceptions().add(exception);
		super.visitTryCatchBlock(start, end, handler, type);
	}
	
	@Override
	public void visitJumpInsn(int opcode, Label label) {
		method.getInstructions()[pointer.getOffset()] = new JumpInstruction(method, label, pointer.getOffset());
		method.getFlowBlocks()[pointer.getOffset()] = new FlowBlock(null, 0);
		pointer.increment();
		super.visitJumpInsn(opcode, label);
	}

	/**
	 * Gets the {@link MethodDecNode}.
	 * @return The method to store instructions in.
	 */
	public MethodDecNode getMethod() {
		return method;
	}
}
