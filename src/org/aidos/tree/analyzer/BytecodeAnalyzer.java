package org.aidos.tree.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.aidos.tree.node.MethodDecNode;
import org.aidos.tree.util.InstructionPointer;

/**
 * This class is used to analyze bytecode, so we can construct out abstract
 * syntax tree.
 * @author `Discardedx2
 */
public interface BytecodeAnalyzer<T extends Object> {
	/**
	 * The queue of objects that need to be reanalyzed.
	 */
	List<Object> queue = new ArrayList<Object>();
	
	/**
	 * Analyzes the java bytecode.
	 * @param object The object to analyze.
	 * @param method The method to analyze.
	 * @param pointer The pointer used to calculate instructions inside of the method.
	 * @return {@code true} if analyzation was successful, {@code false} if not.
	 */
	public boolean onAnalyze(Object object, MethodDecNode method, InstructionPointer pointer);
}
