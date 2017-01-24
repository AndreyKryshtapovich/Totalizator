package by.epamtr.totalizator.command.impl;

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

/**
 * Class is designed to authenticate a user and create a session for him.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class SignInCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(SignInCommand.class.getName());
	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String USER = "user";
	private final static String LOCALHOST = "index.jsp";
	private final static String SHOW_EVENTS_COMMAND_URL = "Controller?command=show-events";
	private final static String GO_TO_ADMIN_PAGE = "Controller?command=go-to-admin-page";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";

	/**
	 * Method calls required service method and returns a path to the user page
	 * for person with role "user" or to the administrator page for person with
	 * pole "admin". In case of incorrect credentials returns path to the index
	 * page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		GeneralOperationService generalService = factory.getGeneralOperationService();

		User user = null;
		try {

			user = generalService.signIn(request.getParameter(LOGIN), request.getParameter(PASSWORD).getBytes());

			if (user == null) {
				url = LOCALHOST;
			} else {
				if (user.getRole().equals(USER)) {
					request.getSession(true).setAttribute(LOGIN, request.getParameter(LOGIN));
					request.getSession(true).setAttribute(USER, user);
					url = SHOW_EVENTS_COMMAND_URL;
				} else {
					request.getSession(true).setAttribute(LOGIN, request.getParameter(LOGIN));
					request.getSession(true).setAttribute(USER, user);
					url = GO_TO_ADMIN_PAGE;
				}
			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}
		return url;
	}
}
