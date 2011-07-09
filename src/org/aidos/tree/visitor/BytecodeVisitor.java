package org.aidos.tree.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * A class used to print class bytecode into the console.
 * @author `Discardedx2
 */
public class BytecodeVisitor implements ClassVisitor {

	/**
	 * {@code true} if the bytecode information should be printed out. Otherwise {@code false}.
	 */
	private boolean printable;

	public BytecodeVisitor(boolean print) {
		this.printable = false;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		if (printable) {
			System.out.print(name+" ");
			if (superName != null) {
				System.out.print("extends "+superName+" ");
			}
			boolean wroteImplements = false;
			if (interfaces.length > 0) {
				if (!wroteImplements) {
					System.out.print("implements ");
					wroteImplements = true;
				}
				int count = 0;
				for (String s : interfaces) {
					System.out.print(s);
					if (interfaces.length > 1) {
						System.out.print(", ");
					}
					count++;
				}
				if (count == interfaces.length) {
					System.out.println();
					System.out.println("{");
				}
			} else {
				System.out.println();
				System.out.println("{");
			}
		}
	}

	@Override
	public void visitSource(String source, String debug) {
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return null;
	}

	@Override
	public void visitAttribute(Attribute attr) {
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		if (printable) {
			System.out.println("\tFIELD: "+name+"  DESC:  "+desc);
		}
		return null;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if (printable) {
			System.out.println("\t\tMETHOD: "+name+"  DESC:  "+desc);
		}
		return null;
	}

	@Override
	public void visitEnd() {
		if (printable) {
			System.out.println("}");
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPrintable() {
		return printable;
	}

	public void setPrint(boolean print) {
		this.printable = print;
	}
}