package by.epamtr.totalizator.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCoupon;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Class is designed to show user all events available for betting.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public class ShowEventsCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(ShowEventsCommand.class.getName());
	private final static String CURRENT_URL = "currentUrl";
	private final static String EVENTS = "events";
	private final static String SHOW_EVENTS_URL = "Controller?command=show-events";
	private final static String MIN_BET_AMOUNT = "minBetAmount";
	private final static String DRAWING = "drawing";

	/**
	 * Method saves current URL in session. Gets all info and returns path to
	 * the required page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = SHOW_EVENTS_URL;
		String page = null;
		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);

		List<Event> eventsList = null;
		GameCoupon game = new GameCoupon();

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientOperationService clientService = factory.getClientOperationService();

		try {
			game = clientService.getOpenedGame();
		} catch (ServiceException e) {
			Logger.error(e);
			page = PageName.USER_PAGE;
			return page;
		}

		try {
			eventsList = clientService.showEvents(game.getGameCupounId());
		} catch (ServiceException e) {
			Logger.error(e);
			page = PageName.USER_PAGE;
			return page;
		}

		JSPListBean jsp = new JSPListBean(eventsList);
		request.setAttribute(EVENTS, jsp);
		request.setAttribute(MIN_BET_AMOUNT, game.getMinBetAmount());
		request.setAttribute(DRAWING, game.getGameCupounId());
		page = PageName.USER_PAGE;

		return page;

	}

}
