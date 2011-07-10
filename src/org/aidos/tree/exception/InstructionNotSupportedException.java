package org.aidos.tree.exception;

/**
 * This exception is thrown when an instruction type that the AST
 * does not support is being used.
 * @author `Discardedx2
 */
public class InstructionNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@link InstructionNotSupportedException}.
	 */
	public InstructionNotSupportedException() {
        super();
    }

	/**
	 * Constructs a new {@link InstructionNotSupportedException}.
	 * @param message The message to print.
	 */
    public InstructionNotSupportedException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link InstructionNotSupportedException}.
     * @param message The message to print.
     * @param cause The cause of the exception.
     */
    public InstructionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link InstructionNotSupportedException}.
     * @param cause The cause of the exception.
     */
    public InstructionNotSupportedException(Throwable cause) {
        super(cause);
    }

	
}
