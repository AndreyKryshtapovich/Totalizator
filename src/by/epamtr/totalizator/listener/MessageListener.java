package by.epamtr.totalizator.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import by.epamtr.totalizator.controller.filter.CommandChecker;

public class MessageListener implements ServletRequestListener {

	private final static String COMMAND = "command";
	private final static String METHOD = "get";
	private final static String RESULT = "result";
	public MessageListener() {

	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		CommandChecker checker = new CommandChecker();
		String requestMethod = checker.getMethod(request.getParameter(COMMAND));
		if (requestMethod != null) {
			if (requestMethod.equals(METHOD)) {
				request.getSession(false).removeAttribute(RESULT);
			}
		}
	}

	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
