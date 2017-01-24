package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

/**
 * Class is designed to process a request for searching events that are matched
 * to the particular game coupon. Available for administrator only.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class SearchAllGameEventsCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(SearchMatchingEventsCommand.class.getName());
	private final static String SEARCH_ALL_EVENTS_URL = "Controller?command=search-all-events&game=";
	private final static String GAME = "game";
	private final static String CURRENT_URL = "currentUrl";
	private final static String GAME_EVENTS_URL = "gameEventsUrl";
	private final static String EVENTS = "events";
	private final static String GAME_CUPOUN_ID = "gameCupounId";
	private final static String GAME_START_DATE = "gameStartDate";
	private final static String GAME_END_DATE = "gameEndDate";
	private final static String IN_PROGRESS_FLAG = "InProgressFlag";
	private final static int IN_PROGRESS = 2;
	private final static String USER = "user";
	private final static String ADMIN = "admin";

	/**
	 * Method saves current URL in session. Gets all parameters and info and
	 * returns required path to the page with results of searching.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = SEARCH_ALL_EVENTS_URL + request.getParameter(GAME);
		String page = null;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(ADMIN)) {

			request.getSession(false).setAttribute(CURRENT_URL, url);

			Cookie newCookie = new Cookie(GAME_EVENTS_URL, url);
			response.addCookie(newCookie);

			List<Event> eventsList = null;
			String parameters = request.getParameter(GAME);
			GameCupoun currentGame = null;

			if (parameters.isEmpty()) {
				page = PageName.ERROR_PAGE;
				return page;
			}
			
			int gameCupounId = Integer.valueOf(Utils.parseParamGameCupounId(parameters));
			Timestamp gameStartDate = Timestamp.valueOf(Utils.parseParamGameCupounStartDate(parameters));
			Timestamp gameEndDate = Timestamp.valueOf(Utils.parseParamGameCupounEndDate(parameters));

			ServiceFactory factory = ServiceFactory.getInstance();
			AdminOperationService adminService = factory.getAdminOperationService();

			try {
				eventsList = adminService.getEventsByGameCupounId(gameCupounId);
			} catch (ServiceException e) {
				Logger.error(e);
			}

			try {
				currentGame = adminService.getGameByGameCupounId(gameCupounId);
			} catch (ServiceException e) {
				Logger.error(e);
			}

			if (currentGame.getStatus() == IN_PROGRESS) {

				request.setAttribute(IN_PROGRESS_FLAG, true);
			}

			JSPListBean jsp = new JSPListBean(eventsList);
			request.setAttribute(EVENTS, jsp);
			request.setAttribute(GAME_CUPOUN_ID, gameCupounId);
			request.setAttribute(GAME_START_DATE, gameStartDate);
			request.setAttribute(GAME_END_DATE, gameEndDate);

			page = PageName.EVENT_DETAILS;
		} else {
			page = PageName.INDEX_PAGE;
		}
		return page;
	}

}
