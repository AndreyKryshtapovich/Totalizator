package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;

public class SignOutCommand implements Command {
	private final static String LOGIN = "login";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	/*	String url = "Controller?command=sign-out"; 
		request.getSession(false).setAttribute("currentUrl", url);*/ 
		
		request.getSession(false).removeAttribute(LOGIN);
		request.getSession().invalidate();
		//String url = "Controller?command=go-to-index-page";
		String url = "http://localhost:8080/Totalizator/index.jsp";
		return url;
		
	}
}
