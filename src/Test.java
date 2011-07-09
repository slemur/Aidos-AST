import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.JumpInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;


public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		for (ClassFile cf : tree.getClassFiles().values()) {
			if (cf.getName().equals("client")) {
				for (MethodDecNode mdn : cf.getMethods()) {
					InstructionPointer pointer = new InstructionPointer(mdn.getInstructions());
					for (InstructionNode current = pointer.current(); pointer.hasCurrent();) {
						if (current instanceof JumpInstruction) {
							JumpInstruction jump = (JumpInstruction) current;
							System.out.println(jump+" ::: Instruction offset: "+jump.getCodePosition()+", Jump to info: "+jump.getJumpTo().info);
						}
					}
				}
			}
		}
	}
}
