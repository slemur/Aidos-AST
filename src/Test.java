import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;


public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		ClassFile client = tree.getClassFiles().get("client");
		MethodDecNode main = client.getMethods().get("main");
		System.out.println(main.info());
	}

}
