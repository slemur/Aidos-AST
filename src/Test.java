import java.io.FileNotFoundException;
import java.io.IOException;

import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.InstructionNode;
import org.aidos.tree.node.IntIncrementInstruction;
import org.aidos.tree.node.MethodDecNode;

public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		ClassFile client = tree.getClassFiles().get("client");
		try {
			tree.getJar().write("r.jar", tree.getJar().getClassFiles());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
