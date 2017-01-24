package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

/**
 * Class is designed to process a request for forwarding administrator to the
 * page where he can create new game coupons.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public class GoToGameCreationCommand implements Command {
	private final static String GO_TO_GAME_CREATION_PAGE = "Controller?command=go-to-game-creation";
	private final static String CURRENT_URL = "currentUrl";

	/**
	 * Method saves current URL in session. Returns required path to the page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = GO_TO_GAME_CREATION_PAGE;
		String page = null;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);

		page = PageName.GAME_CREATION;
		return page;

	}

}
