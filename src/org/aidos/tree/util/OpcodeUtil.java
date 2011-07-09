package org.aidos.tree.util;

import java.lang.reflect.Field;

public class OpcodeUtil {
	public static String getOpcodeName(int opcode) {
		try {
			Field[] flds = Class.forName("org.objectweb.asm.Opcodes").getFields();
			int cnt = 0;
			for (Field f : flds) {
				if (f.getGenericType().toString().contains("int")) {
					if (cnt >= 48) {
						if (f.getInt(null) == opcode) {
							return (f.getName());
						}
					}
				}
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "N/A " + opcode;
	}
}
