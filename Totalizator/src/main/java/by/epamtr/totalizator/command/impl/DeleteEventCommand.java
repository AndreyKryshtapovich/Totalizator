package by.epamtr.totalizator.command.impl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.GameCoupon;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * This class is designed to process delete event request. This command
 * available for administrator only.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public class DeleteEventCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(DeleteEventCommand.class.getName());
	private final static String EVENT = "eventId";
	private final static String LOCALHOST = "index.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String GAME_EVENTS_URL = "gameEventsUrl";
	private final static String IN_DEVELOPING = "In developing";
	private final static String RESULT = "result";
	private final static String CURRENT_URL = "currentUrl";
	private final static String GAME_ID = "gameId";
	private final static String USER = "user";
	private final static String ADMIN = "admin";

	/**
	 * Gets all required dictionary data and calls service method to delete an
	 * event.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		String event = request.getParameter(EVENT).toString();
		Map<Integer, String> status = null;
		GameCoupon game = null;

		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(ADMIN)) {

			if (event.isEmpty()) {
				url = GO_TO_ERROR_PAGE;
				return url;
			}

			ServiceFactory factory = ServiceFactory.getInstance();
			AdminOperationService adminService = factory.getAdminOperationService();

			String gameEventsUrl = null;
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(GAME_EVENTS_URL)) {
						gameEventsUrl = cookie.getValue();
					}
				}
			}

			try {
				status = adminService.getStatusDictionaryData();
			} catch (ServiceException e) {
				url = GO_TO_ERROR_PAGE;
				return url;
			}

			try {
				int gameCupounId = Integer.valueOf(request.getParameter(GAME_ID));
				game = adminService.getGameByGameCupounId(gameCupounId);
			} catch (ServiceException e) {
				url = GO_TO_ERROR_PAGE;
				return url;
			}

			String gameStatus = status.get(game.getStatus());

			if (gameStatus.equals(IN_DEVELOPING)) {

				try {
					boolean result = adminService.deleteEvent(event);
					if (result) {
						url = gameEventsUrl;
					} else {
						url = GO_TO_ERROR_PAGE;
					}
				} catch (ServiceException e) {
					Logger.error(e);
					url = GO_TO_ERROR_PAGE;
				}
			} else {
				request.getSession(false).setAttribute(RESULT, true);
				url = request.getSession(false).getAttribute(CURRENT_URL).toString();
			}
		} else {
			url = LOCALHOST;
		}
		return url;
	}

}
