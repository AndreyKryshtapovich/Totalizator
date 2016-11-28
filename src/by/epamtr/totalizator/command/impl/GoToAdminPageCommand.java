package by.epamtr.totalizator.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;


public class GoToAdminPageCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String url = "Controller?command=go-to-admin-page";
		request.getSession(false).setAttribute("currentUrl", url);

		String page = PageName.ADMIN_PAGE;
		return page;
	}

}
