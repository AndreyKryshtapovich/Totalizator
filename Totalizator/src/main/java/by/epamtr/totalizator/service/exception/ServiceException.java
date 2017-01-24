package by.epamtr.totalizator.service.exception;
/**
 * Thrown when an issue occurred while processing service layer's methods.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs a ServiceException with no detail message.
	 */
	public ServiceException() {
		super();
	}
	/**
	 * Constructs a ServiceException with the specified detail message.
	 * 
	 * @param message
	 *            special detail message.
	 */
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
