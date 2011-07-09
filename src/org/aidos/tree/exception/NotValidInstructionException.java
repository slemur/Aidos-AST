package org.aidos.tree.exception;

public class NotValidInstructionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotValidInstructionException() {
        super();
    }

    public NotValidInstructionException(String message) {
        super(message);
    }

    public NotValidInstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidInstructionException(Throwable cause) {
        super(cause);
    }

	
}
