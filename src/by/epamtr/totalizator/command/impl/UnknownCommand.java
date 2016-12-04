package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;


public class UnknownCommand implements Command {
	private final static String UNKNOWN_COMMAND_URL = "Controller?command=unknown";
	private final static String CURRENT_URL = "currentUrl";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = UNKNOWN_COMMAND_URL;
		request.getSession(false).setAttribute(CURRENT_URL, url);

		String page = PageName.ERROR_PAGE;
		return page;

	}

}
