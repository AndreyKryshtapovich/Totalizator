package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.controller.PageName;


public class GoToRegistrationCommand implements Command {
	private final static String GO_TO_REGISTRATION_PAGE = "Controller?command=go-to-registration";
	private final static String CURRENT_URL = "currentUrl";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String url = GO_TO_REGISTRATION_PAGE;
		request.getSession(false).setAttribute(CURRENT_URL, url);

		String page = PageName.REGISTRATION;
		return page;
		
	}

}
