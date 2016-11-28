package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;

public class ChangeLanguageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		HttpSession session = request.getSession(false);
		session.setAttribute("local", request.getParameter("local"));
		if (session.getAttribute("currentUrl") != null) {
			url = "http://localhost:8080/Totalizator/" + session.getAttribute("currentUrl");
		} else {
			url = "http://localhost:8080/Totalizator";
		}
		return url;
	}

}
