package by.epamtr.totalizator.dao.exception;

/**
 * Thrown when an issue occurred while working with data storage.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a DAOException with no detail message.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Constructs a DAOException with the specified detail message.
	 * 
	 * @param message
	 *            special detail message.
	 */
	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
