package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;

/**
 * Class is designed for localization.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class ChangeLanguageCommand implements Command {

	private final static String LOCAL = "local";
	private final static String CURRENT_URL = "currentUrl";
	private final static String LOCALHOST = "index.jsp";

	/**
	 * Changes the session's attribute "local" and exceeds previous URL.
	 * Available for every user.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		HttpSession session = request.getSession(false);

		if (session == null) {
			url = LOCALHOST;
			return url;
		}
		session.setAttribute(LOCAL, request.getParameter(LOCAL));
		if (session.getAttribute(CURRENT_URL) != null) {
			url = session.getAttribute(CURRENT_URL).toString();
		} else {
			url = LOCALHOST;
		}
		return url;
	}

}
