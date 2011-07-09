package org.aidos.tree.exception;

/**
 * This exception is thrown when an {@link InstructionPointer} is unable to grab an instruction
 * at a certain code offset.
 * @author `Discardedx2
 */
public class NotValidInstructionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@link NotValidInstructionException}.
	 */
	public NotValidInstructionException() {
        super();
    }

	/**
	 * Constructs a new {@link NotValidInstructionException}.
	 * @param message The message to print.
	 */
    public NotValidInstructionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link NotValidInstructionException}.
     * @param message The message to print.
     * @param cause The cause of the exception.
     */
    public NotValidInstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link NotValidInstructionException}.
     * @param cause The cause of the exception.
     */
    public NotValidInstructionException(Throwable cause) {
        super(cause);
    }

	
}
