package by.epamtr.totalizator.command.impl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class UnmatchEventCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(UnmatchEventCommand.class.getName());
	private final static String EVENT = "eventId";
	private final static String GAME_ID = "gameId";
	private final static String LOCALHOST = "http://localhost:8080/Totalizator/";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String GAME_EVENTS_URL = "gameEventsUrl";
	private final static String IN_DEVELOPING = "In developing";
	private final static String RESULT = "result";
	private final static String CURRENT_URL = "currentUrl";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		Map<Integer, String> status = null;
		String event = request.getParameter(EVENT).toString();
		GameCupoun game = null;
		
		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		
		if (event.isEmpty()) {
			url = GO_TO_ERROR_PAGE;
			return url;
		}

		int selectedEventId = Integer.valueOf(event);

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
				boolean result = adminService.unmatchEventAndGame(selectedEventId);
				if (result) {
					url = LOCALHOST + gameEventsUrl;
				} else {
					url = GO_TO_ERROR_PAGE;
				}
			} catch (ServiceException e) {
				Logger.error(e);
				url = GO_TO_ERROR_PAGE;
			}
		} else {
			request.getSession(false).setAttribute(RESULT, false);
			url = request.getSession(false).getAttribute(CURRENT_URL).toString();
		}
		return url;
	}

}
