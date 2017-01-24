package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

/**
 * Class is designed to process a request for forwarding user to the
 * administrators page.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public class GoToAdminPageCommand implements Command {
	private final static String GO_TO_ADMIN_PAGE = "Controller?command=go-to-admin-page";
	private final static String CURRENT_URL = "currentUrl";

	/**
	 * Saves current URL in session and return required path to the page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = GO_TO_ADMIN_PAGE;
		String page = PageName.ADMIN_PAGE;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);

		return page;
	}

}
