package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class SearchMatchingEventsCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(SearchMatchingEventsCommand.class.getName());
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = "Controller?command=search-matching-events&game=" + request.getParameter("game");
		request.getSession(false).setAttribute("currentUrl", url);
		
		String page = null;
		List<Event> eventsList = null;
		List<Event> matchedEventsList = null;
		
		String parameters = request.getParameter("game");
		/*int gameCupounId =Integer.valueOf(parameters.substring(0, 1)); 
		String startDate = parameters.substring(3,25);
		String endDate = parameters.substring(27);
		Timestamp gameStartDate = Timestamp.valueOf(startDate);
		Timestamp gameEndDate = Timestamp.valueOf(endDate);*/
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			eventsList = adminService.showUnmatchedEvents(parameters);
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			Logger.error(e);
			return page;
		}
		
		int gameCupounId =Integer.valueOf(parameters.substring(0, 1)); 
		String startDate = parameters.substring(3,25);
		String endDate = parameters.substring(27);
		Timestamp gameStartDate = Timestamp.valueOf(startDate);
		Timestamp gameEndDate = Timestamp.valueOf(endDate);
		
		//TODO utils or somehing like this 
		JSPListBean jsp = new JSPListBean(eventsList);
		request.setAttribute("events", jsp);
		request.setAttribute("gameCupounId", gameCupounId);
		request.setAttribute("gameStartDate", gameStartDate);
		request.setAttribute("gameEndDate", gameEndDate);
		
		try {
			matchedEventsList = adminService.showEventsByGameCupounId(gameCupounId);
		} catch (ServiceException e) {
			e.printStackTrace();
			Logger.error(e);
		}
		
		JSPListBean matchedEventsJsp = new JSPListBean(matchedEventsList);
		request.setAttribute("matchedEvents", matchedEventsJsp);
		
		page = PageName.EVENT_GAME_MATCHING;
		
		
		return page;
	}

}
