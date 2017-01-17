package by.epamtr.totalizator.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.EventsListDTO;
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
	private final static String EVENTS = "events";
	private final static String GAME_CUPOUN_ID = "gameCupounId";
	private final static String GAME_START_DATE = "gameStartDate";
	private final static String GAME_END_DATE = "gameEndDate";
	private final static String GAME = "game";
	private final static String SEARCH_MATCHING_EVENTS_URL = "Controller?command=search-matching-events&game=";
	private final static String CURRENT_URL = "currentUrl";
	private final static String MATCHED_EVENTS = "matchedEvents";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = SEARCH_MATCHING_EVENTS_URL + request.getParameter(GAME);
		String page = null;
		if(request.getSession(false) == null){
			page = PageName.INDEX_PAGE;
			return page;
		}
		request.getSession(false).setAttribute(CURRENT_URL, url);
		
		List<Event> matchedEventsList = null;
		String parameters = request.getParameter(GAME);
		EventsListDTO unmatchedEventsDTO = new EventsListDTO();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			unmatchedEventsDTO = adminService.getUnmatchedEvents(parameters);
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			Logger.error(e);
			return page;
		}
		
		JSPListBean jsp = new JSPListBean(unmatchedEventsDTO.getEventList());
		request.setAttribute(EVENTS, jsp);
		request.setAttribute(GAME_CUPOUN_ID, unmatchedEventsDTO.getGameCupounId());
		request.setAttribute(GAME_START_DATE, unmatchedEventsDTO.getGameStartDate());
		request.setAttribute(GAME_END_DATE, unmatchedEventsDTO.getGameEndDate());
		
		try {
			matchedEventsList = adminService.getEventsByGameCupounId(unmatchedEventsDTO.getGameCupounId());
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			Logger.error(e);
			return page;
		}
		JSPListBean matchedEventsJsp = new JSPListBean(matchedEventsList);
		request.setAttribute(MATCHED_EVENTS, matchedEventsJsp);
		
		page = PageName.EVENT_GAME_MATCHING;
		return page;
	}

}
