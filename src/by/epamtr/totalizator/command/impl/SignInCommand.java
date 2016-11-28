package by.epamtr.totalizator.command.impl;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.GeneralOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;


public class SignInCommand implements Command {
	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String USER = "user";
	private final static Logger Logger = LogManager.getLogger(SignInCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		/*String url = "Controller?command=sign-in" + "&login=" + request.getParameter(LOGIN) + "&password="
				+ request.getParameter(PASSWORD);
		
		request.getSession(false).setAttribute("currentUrl", url);*/

		ServiceFactory factory = ServiceFactory.getInstance();
		GeneralOperationService generalService = factory.getGeneralOperationService();

		User user = null;
		try {
			
			user = generalService.signIn(request.getParameter(LOGIN), request.getParameter(PASSWORD).getBytes());
			
			if (user == null) {
				//TODO
			url = "http://localhost:8080/Totalizator";
			} else {
				if (user.getRole().equals(USER)) {
					request.getSession(true).setAttribute(LOGIN, request.getParameter(LOGIN));
					// redirect for execution show-events command
					url = "http://localhost:8080/Totalizator/Controller?command=show-events";
					//return;
				} else {
					request.getSession(true).setAttribute(LOGIN, request.getParameter(LOGIN));
					url = "http://localhost:8080/Totalizator/Controller?command=go-to-admin-page";
				}

			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = "http://localhost:8080/Totalizator";
		}
		return url;

	}

}
