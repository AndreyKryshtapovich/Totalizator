package by.epamtr.totalizator.command.exception;

/**
 * Thrown when some issue occurred on the Command layer.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class CommandException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a CommandException with no detail message.
	 */
	public CommandException() {
		super();
	}

	/**
	 * Constructs a CommandException with the specified detail message.
	 * 
	 * @param message
	 *            special detail message.
	 */
	public CommandException(String message) {
		super(message);
	}

	public CommandException(Exception e) {
		super(e);
	}

	public CommandException(String message, Exception e) {
		super(message, e);
	}
}
