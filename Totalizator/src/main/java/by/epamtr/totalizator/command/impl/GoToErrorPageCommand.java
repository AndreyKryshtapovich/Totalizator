package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

/**
 * Class is designed to process a request for forwarding user to the error page.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class GoToErrorPageCommand implements Command {
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String CURRENT_URL = "currentUrl";

	/**
	 * Method saves current URL in session and return required path to the page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = GO_TO_ERROR_PAGE;
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
