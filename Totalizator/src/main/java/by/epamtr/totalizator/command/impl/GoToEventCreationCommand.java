package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

/**
 * Class is designed to process request for forwarding administrator to the page
 * where he can create new events.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class GoToEventCreationCommand implements Command {
	private final static String CURRENT_URL = "currentUrl";
	private final static String GO_TO_EVENT_CREATION = "Controller?command=go-to-event-creation";

	/**
	 * Method saves current URL in session and returns required path to the
	 * page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = GO_TO_EVENT_CREATION;
		String page = null;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);

		page = PageName.EVENT_CREATION;
		return page;

	}

}
