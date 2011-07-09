import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.LdcInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;


public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		for (ClassFile cf : tree.getClassFiles().values()) {
			if(cf.getName().equals("Player")) {
				for (MethodDecNode mdn : cf.getMethods()) {
					InstructionPointer pointer = new InstructionPointer(mdn.getInstructions());
					while(pointer.hasCurrent()) {
						InstructionNode current = pointer.current();
						if (current instanceof LdcInstruction) {
							LdcInstruction ldc = (LdcInstruction) current;
							System.out.println(ldc.getConstant());
						}
					}
				}
			}
		}
	}
}
