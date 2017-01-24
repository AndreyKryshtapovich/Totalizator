package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Class is designed to process request for matching event and particular game
 * coupon. The event will be associated with particular game coupon. Available
 * for administrator only.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class EventGameMatchingCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(EventGameMatchingCommand.class.getName());
	private final static String CURRENT_URL = "currentUrl";
	private final static String EVENT = "event";
	private final static String LOCALHOST = "index.jsp";
	private final static String G0_TO_ADMIN_PAGE = "Controller?command=go-to-admin-page";
	private final static String RESULT = "result";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String USER = "user";
	private final static String ADMIN = "admin";

	/**
	 * Method checks user's role. Gets all parameters from request and calls
	 * required service method.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(ADMIN)) {

			HttpSession session = request.getSession(false);
			String prevUrl = session.getAttribute(CURRENT_URL).toString();
			String gameCupounId = prevUrl.substring(prevUrl.lastIndexOf("=") + 1, prevUrl.lastIndexOf("=") + 2);
			String event = request.getParameter(EVENT).toString();

			if (event.isEmpty()) {
				url = GO_TO_ERROR_PAGE;
				return url;
			}

			ServiceFactory factory = ServiceFactory.getInstance();
			AdminOperationService adminService = factory.getAdminOperationService();

			try {
				boolean result = adminService.matchEventAndGame(gameCupounId, event);
				if (result) {
					url = prevUrl;
				} else {
					boolean registrationResult = false;
					request.getSession(false).setAttribute(RESULT, registrationResult);
					url = G0_TO_ADMIN_PAGE;
				}
			} catch (ServiceException e) {
				Logger.error(e);
				url = GO_TO_ERROR_PAGE;
			}
		} else {
			url = LOCALHOST;
		}
		return url;
	}

}
