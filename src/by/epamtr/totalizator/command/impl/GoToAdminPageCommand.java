package by.epamtr.totalizator.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;


public class GoToAdminPageCommand implements Command {
	private final static String GO_TO_ADMIN_PAGE = "Controller?command=go-to-admin-page";
	private final static String CURRENT_URL = "currentUrl";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String url = GO_TO_ADMIN_PAGE;
		request.getSession(false).setAttribute(CURRENT_URL, url);

		String page = PageName.ADMIN_PAGE;
		return page;
	}

}
