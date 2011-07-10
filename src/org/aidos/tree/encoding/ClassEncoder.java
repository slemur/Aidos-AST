package org.aidos.tree.encoding;

import org.aidos.tree.ClassFile;
import org.aidos.tree.ClassInterface;
import org.aidos.tree.node.FieldDecNode;
import org.aidos.tree.node.MethodDecNode;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Encodes a modified class back into bytecode format.
 * @author `Discardedx2
 */
public class ClassEncoder extends ClassWriter {

	private ClassFile file;

	public ClassEncoder(ClassFile file, int flags) {
		super(flags);
		this.file = file;
	}

	public void encode() {
		String[] interfaces = new String[file.getInterfaces().size()];
		for (int i = 0; i < file.getInterfaces().size(); i++) {
			ClassInterface inter = file.getInterfaces().get(i);
			interfaces[i] = inter.getName();
		}
		super.visit(file.getClassVersion(), file.getModifier(), file.getName(), file.getSignature(), file.getSuperClass().getName(), interfaces);
		for (Attribute attribute : file.getAttributes()) {
			super.visitAttribute(attribute);
		}
		if (file.getOuterClassOwner() != null) {
			super.visitOuterClass(file.getOuterClassOwner(), file.getOuterClassName(), file.getOuterClassDescriptor());
		}
		if (file.getInnerClassName() != null) {
			super.visitInnerClass(file.getInnerClassName(), file.getInnerClassOuterName(), file.getInnerClassInnerName(), file.getInnerClassModifier());
		}
		for (FieldDecNode fdn : file.getFields()) {
			super.visitField(fdn.getModifier(), fdn.getName(), fdn.getDescriptor(), fdn.getSignature(), fdn.getValue());
		}
		for (MethodDecNode mdn : file.getMethods()) {
			String[] exceptions = new String[mdn.getExceptions().size()];
			for (int i = 0; i < file.getInterfaces().size(); i++) {
				ClassInterface inter = file.getInterfaces().get(i);
				exceptions[i] = inter.getName();
			}
			MethodEncoder me = new MethodEncoder(super.visitMethod(mdn.getModifier(), mdn.getName(), mdn.getDescriptor(), mdn.getSignature(), exceptions), mdn);
			me.encode();
		}
		super.visitEnd();
	}

}
