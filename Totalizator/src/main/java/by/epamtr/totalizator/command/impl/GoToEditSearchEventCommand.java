package by.epamtr.totalizator.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.listbean.JSPGameListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Class is designed to process a request for forwarding administrator to the
 * page where he can select a game coupon (except closed and cancelled ones) and
 * search for it's events.
 * 
 * @author Andrey
 *
 */
public class GoToEditSearchEventCommand implements Command {

	private final static Logger Logger = LogManager.getLogger(GoToEditSearchEventCommand.class.getName());
	private final static String GO_TO_EDIT_SEARCH_EVENT = "Controller?command=go-to-edit-search-event";
	private final static String CURRENT_URL = "currentUrl";
	private final static String GAMES = "games";

	/**
	 * Method saves current URL in session. Gets all parameters and returns
	 * required path to the page.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = GO_TO_EDIT_SEARCH_EVENT;
		String page = null;

		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}

		request.getSession(false).setAttribute(CURRENT_URL, url);

		List<GameCupoun> gamesList = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();

		try {
			gamesList = adminService.getAllGames();
		} catch (ServiceException e) {
			Logger.error(e);
		}

		JSPGameListBean jspGameListBean = new JSPGameListBean(gamesList);
		request.setAttribute(GAMES, jspGameListBean);

		page = PageName.EDIT_SEARCH_EVENT;
		return page;
	}

}
