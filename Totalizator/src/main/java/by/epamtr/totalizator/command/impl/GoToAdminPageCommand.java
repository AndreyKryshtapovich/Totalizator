package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.bean.entity.User;
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
	private final static String USER = "user";
	private final static String ADMIN = "admin";

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
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(ADMIN)) {
			request.getSession(false).setAttribute(CURRENT_URL, url);
		} else {
			page = PageName.INDEX_PAGE;
		}

		return page;
	}

}
