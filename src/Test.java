import org.aidos.tree.ClassFile;
import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.IntInstruction;
import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.tac.ThreeAddressFunction;
import org.aidos.tree.util.TACPointer;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		ClassFile client = tree.getClassFiles().get("client");
		for (MethodDecNode mdn : client.getMethods()) {
			if (mdn.getName().equals("main")) {
				System.out.println(mdn.tacInfo(tree.getTacStructure()));
				return;
			}
		}
	}
}
