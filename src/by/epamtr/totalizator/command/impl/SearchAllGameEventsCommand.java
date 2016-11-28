package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
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

public class SearchAllGameEventsCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(SearchMatchingEventsCommand.class.getName());
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = "Controller?command=search-all-events&game=" + request.getParameter("game");
		request.getSession(false).setAttribute("currentUrl", url);
		
		Cookie newCookie = new Cookie("gameEventsUrl",url);
		response.addCookie(newCookie);
		
		String page = null;
		List<Event> eventsList = null;
		
		String parameters = request.getParameter("game");
		if(parameters.isEmpty()){
			page = PageName.ERROR_PAGE;
			return page;
		}
		int gameCupounId =Integer.valueOf(parameters.substring(0, 1)); 
		String startDate = parameters.substring(3,25);
		String endDate = parameters.substring(27);
		Timestamp gameStartDate = Timestamp.valueOf(startDate);
		Timestamp gameEndDate = Timestamp.valueOf(endDate);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			eventsList = adminService.showEventsByGameCupounId(gameCupounId);
		} catch (ServiceException e) {
			Logger.error(e);
		}
		JSPListBean jsp = new JSPListBean(eventsList);
		request.setAttribute("events", jsp);
		request.setAttribute("gameCupounId", gameCupounId);
		request.setAttribute("gameStartDate", gameStartDate);
		request.setAttribute("gameEndDate", gameEndDate);
		
		page = PageName.EVENT_DETAILS;
		return page;
	}

}
