package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;


public class GoToGameCreationCommand implements Command {
	private final static String GO_TO_GAME_CREATION_PAGE = "Controller?command=go-to-game-creation";
	private final static String CURRENT_URL = "currentUrl";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = GO_TO_GAME_CREATION_PAGE;
		request.getSession(false).setAttribute(CURRENT_URL, url);

		String page = PageName.GAME_CREATION;
		return page;

	}

}
