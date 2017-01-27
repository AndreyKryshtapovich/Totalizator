package by.epamtr.totalizator.dao.connectionpool.exception;

/**
 * Thrown when an issue occurred while working with
 * {@link by.epamtr.totalizator.dao.connectionpool.ConnectionPool}
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ConnectionPoolException with no detail message.
	 */
	public ConnectionPoolException() {
		super();
	}

	/**
	 * Constructs a ConnectionPoolException with the specified detail message.
	 * 
	 * @param message
	 *            special detail message.
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
