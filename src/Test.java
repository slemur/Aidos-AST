import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.LdcInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;

public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		ClassFile client = tree.getClassFiles().get("client");
		for (MethodDecNode mdn : client.getMethods()) {
			InstructionPointer pointer = new InstructionPointer(mdn.getInstructions());
			if (mdn.getName().equals("main")) {
				System.out.println(mdn.info());
				pointer.addLast(new LdcInstruction(mdn, 0, "Test"));
				System.out.println(mdn.info());
				LdcInstruction ldc = (LdcInstruction) pointer.getLast();
				System.out.println(ldc.getConstant());
				pointer.remove(ldc);
				System.out.println(mdn.info());
				return;
			}
		}
	}
}
