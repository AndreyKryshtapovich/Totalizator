package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
/**
 * This class is designed to log out a user and destroy his session object.
 * 
 * @author Andrey
 *
 */
public class SignOutCommand implements Command {
	private final static String LOGIN = "login";
	private final static String GO_TO_INDEX_PAGE = "index.jsp";
	/**
	 * Method invalidates user's session object and return path to the index page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		if(request.getSession(false) == null){
			url = GO_TO_INDEX_PAGE;
			return url;
		}
		request.getSession(false).removeAttribute(LOGIN);
		request.getSession().invalidate();
		url = GO_TO_INDEX_PAGE;
		return url;
		
	}
}
