package by.epamtr.totalizator.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.listbean.JSPGameListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.Controller;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class GoToEditSearchEventCommand implements Command {

	private final static Logger Logger = LogManager.getLogger(Controller.class.getName());
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = "Controller?command=go-to-edit-search-event";
		request.getSession(false).setAttribute("currentUrl", url);
		
		String page = null;
		List<GameCupoun> gamesList = null;
		List<Event> eventsList = new ArrayList();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			gamesList = adminService.showAllGames();
		} catch (ServiceException e) {
			Logger.error(e);
		}
		
		JSPGameListBean jspGameListBean = new JSPGameListBean(gamesList);
		request.setAttribute("games", jspGameListBean);
		
		page = PageName.EDIT_SEARCH_EVENT;
		return page;
	}

}
