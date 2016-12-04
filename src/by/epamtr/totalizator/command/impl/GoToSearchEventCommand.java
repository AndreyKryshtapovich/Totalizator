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

public class GoToSearchEventCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(GoToSearchEventCommand.class.getName());
	private final static String GO_TO_SEARCH_EVENT = "Controller?command=go-to-search-event";
	private final static String CURRENT_URL = "currentUrl";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = GO_TO_SEARCH_EVENT;
		request.getSession(false).setAttribute(CURRENT_URL, url);
		String page = null;
		List<GameCupoun> gamesList = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			gamesList = adminService.getGamesInDevelopment();
		} catch (ServiceException e) {
			Logger.error(e);
		}
		
		JSPGameListBean jspGameListBean = new JSPGameListBean(gamesList);
		request.setAttribute("games", jspGameListBean);
	

		page = PageName.SEARCH_EVENT;
		return page;

	}

}
