package org.aidos.tree.tac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class manages and represents java bytecode
 * as a 3AC (Three address code).
 * @author `Discardedx2
 */
public class TACStructure implements Iterable<ThreeAddressFunction> {
	/**
	 * The parsed functions.
	 */
	private List<ThreeAddressFunction> functions = new ArrayList<ThreeAddressFunction>();
	
	@Override
	public Iterator<ThreeAddressFunction> iterator() {
		return functions.iterator();
	}
	
	/**
	 * Stores a function.
	 * @param function The function to store.
	 * @return {@code true} if successful.
	 */
	public boolean store(ThreeAddressFunction function) {
		return functions.add(function);
	}
	
	/**
	 * Removes a function.
	 * @param function The function to remove.
	 * @return {@code true} if successful.
	 */
	public boolean remove(ThreeAddressFunction function) {
		return functions.remove(function);
	}
	
	/**
	 * Gets a {@link ThreeAddressFunction} by it's index.
	 * @param index The 3AC index to get.
	 * @return The function.
	 */
	public ThreeAddressFunction get(int index) {
		return functions.get(index);
	}
	
	/**
	 * Gets the parsed functions.
	 * @return The functions.
	 */
	public List<ThreeAddressFunction> getFunctions() {
		return functions;
	}
	
}
