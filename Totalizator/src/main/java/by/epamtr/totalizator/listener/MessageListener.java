package by.epamtr.totalizator.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import by.epamtr.totalizator.controller.filter.CommandChecker;

public class MessageListener implements ServletRequestListener {

	private final static String COMMAND = "command";
	private final static String METHOD = "get";
	private final static String RESULT = "result";
	private final static String BET_AMOUNT_RESULT = "betAmountResult";
	private final static String PARAM_RESULT = "param_result";
	private final static String SUCCESS_CLOSE_MSG = "successCloseMsg";
	private final static String CANSELLED_MSG = "canselledMsg";
	private final static String ROLLBACK_MSG = "rollbackMsg";

	public MessageListener() {

	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		CommandChecker checker = new CommandChecker();
		String requestMethod = checker.getMethod(request.getParameter(COMMAND));
		if (requestMethod != null) {
			if (requestMethod.equals(METHOD)) {
				if (request.getSession(false) != null) {
					request.getSession(false).removeAttribute(RESULT);
					request.getSession(false).removeAttribute(BET_AMOUNT_RESULT);
					request.getSession(false).removeAttribute(PARAM_RESULT);
					
					request.getSession(false).removeAttribute( SUCCESS_CLOSE_MSG );
					request.getSession(false).removeAttribute( CANSELLED_MSG );
					request.getSession(false).removeAttribute( ROLLBACK_MSG );
				}
			}
		}
	}

	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
