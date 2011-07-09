package org.aidos.tree.util;

import java.lang.reflect.Field;

/**
 * This class contains all utilities relating to opcodes/instructions.
 * @author `Discardedx2
 */
public class InstructionUtil {
	/**
	 * Gets the name of an instruction by it's opcode.
	 * @param opcode The instruction opcode to search for.
	 * @return The name of the instruction.
	 */
	public static String getInstructionName(int opcode) {
		try {
			Field[] flds = Class.forName("org.objectweb.asm.Opcodes").getFields();
			int count = 0;
			for (Field f : flds) {
				if (f.getGenericType().toString().contains("int")) {
					if (count >= 48) {
						if (f.getInt(null) == opcode) {
							return (f.getName());
						}
					}
				}
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "N/A " + opcode;
	}
}
