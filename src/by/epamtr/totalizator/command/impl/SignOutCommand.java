package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;

public class SignOutCommand implements Command {
	private final static String LOGIN = "login";
	private final static String GO_TO_INDEX_PAGE = "http://localhost:8080/Totalizator/index.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		request.getSession(false).removeAttribute(LOGIN);
		request.getSession().invalidate();
		String url = GO_TO_INDEX_PAGE;
		return url;
		
	}
}
