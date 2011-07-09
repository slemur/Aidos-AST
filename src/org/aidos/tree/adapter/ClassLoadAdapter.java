package org.aidos.tree.adapter;

import org.aidos.tree.ClassFile;
import org.aidos.tree.ClassInterface;
import org.aidos.tree.Jar;
import org.aidos.tree.node.FieldDecNode;
import org.aidos.tree.node.MethodDecNode;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

/**
 * Generates the class structure of a compiled class and stores it
 * in a {@link ClassFile}.
 * @author `Discardedx2
 */
public class ClassLoadAdapter extends EmptyVisitor {

	/**
	 * The {@link ClassFile} to represent the loaded bytecode in.
	 */
	private ClassFile classFile;
	/**
	 * The {@link Jar} that the compiled classes were loaded from.
	 */
	private Jar jar;

	/**
	 * Constructs a new {@link ClassLoadAdapter}.
	 * @param jar The jar that the compiled classes were loaded from.
	 * @param classFile The class file to represent the loaded bytecode in.
	 * @param codeVisitor The {@link BytecodeVisitor} used to print class bytecode.
	 */
	public ClassLoadAdapter(Jar jar,ClassFile file) {
		this.classFile = file;
		this.jar = jar;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		classFile.setModifier(access);
		classFile.setName(name);
		classFile.setSignature(signature);
		classFile.setSuperClass(jar.getClassFiles().get(superName));
		for (String s : interfaces) {
			classFile.getInterfaces().add(new ClassInterface(s));
		}
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodDecNode mdn = new MethodDecNode(classFile, name, desc, signature, access);
		classFile.getMethods().put(name, mdn);
		return new InstructionLoadAdapter(super.visitMethod(access, name, desc, signature, exceptions), mdn);
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldDecNode fdn = new FieldDecNode(classFile, name, desc, signature, access, value);
		classFile.getFields().put(name, fdn);
		return super.visitField(access, name, desc, signature, value);
	}

	/**
	 * Gets the class that represents the loaded bytecode.
	 * @return The class.
	 */
	public ClassFile getClassFile() {
		return classFile;
	}

	/**
	 * Gets the jar we loaded the classes from.
	 * @return The jar.
	 */
	public Jar getJar() {
		return jar;
	}


}
