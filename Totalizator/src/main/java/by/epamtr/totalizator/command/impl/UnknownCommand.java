package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

/**
 * This class is designed to handle an unknown command. Used when user tries to
 * send an incorrect URL.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public class UnknownCommand implements Command {
	private final static String UNKNOWN_COMMAND_URL = "Controller?command=unknown";
	private final static String CURRENT_URL = "currentUrl";

	/**
	 * Method returns path to the index page if there are no session object for
	 * user. Otherwise return path to the error page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = UNKNOWN_COMMAND_URL;
		String page = null;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);

		page = PageName.ERROR_PAGE;
		return page;

	}

}
