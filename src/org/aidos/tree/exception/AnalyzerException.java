package org.aidos.tree.exception;

/**
 * This exception is thrown when an {@link BytecodeAnalyzer} fails to analyze 
 * the requested bytecode.
 * @author `Discardedx2
 */
public class AnalyzerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@link AnalyzerException}.
	 */
	public AnalyzerException() {
        super();
    }

	/**
	 * Constructs a new {@link AnalyzerException}.
	 * @param message The message to print.
	 */
    public AnalyzerException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link AnalyzerException}.
     * @param message The message to print.
     * @param cause The cause of the exception.
     */
    public AnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link AnalyzerException}.
     * @param cause The cause of the exception.
     */
    public AnalyzerException(Throwable cause) {
        super(cause);
    }

	
}
