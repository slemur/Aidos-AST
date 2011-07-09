import org.aidos.tree.Jar;
import org.aidos.tree.NodeTree;
import org.aidos.tree.node.IntInstruction;
import org.aidos.tree.node.expression.AddExpression;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;


public class Test {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree(Jar.load("./runescape_out.jar"));
		IntInstruction left = new IntInstruction(null, 0, Opcodes.BIPUSH, 5);
		IntInstruction right = new IntInstruction(null, 0, Opcodes.BIPUSH, 10);
		AddExpression add = new AddExpression(left, right);
		add.invoke();
		System.out.println(add.getInvokedValue());
	}
}
