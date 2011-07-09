import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;


public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		for (ClassFile cf : tree.getClassFiles().values()) {
			for (MethodDecNode mdn : cf.getMethods().values()) {
				InstructionPointer pointer = new InstructionPointer(mdn.getInstructions());
				for (InstructionNode current = pointer.current(); pointer.hasNext();) {
					InstructionNode next = pointer.next();
					InstructionNode prev = pointer.prev();
					System.out.println(prev+", "+current+", "+next);
				}
			}
		}
	}

}
