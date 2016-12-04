package by.epamtr.totalizator.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class GoToEventEditCommand implements Command {
	private final static String CURRENT_URL = "currentUrl";
	private final static String GO_TO_EVENT_EDIT_WITH_PARAMS = "Controller?command=go-to-event-edit&eventId=";
	private final static String EVENT_ID = "eventId";
	private final static String GAME_ID_PARAM = "&gameId=";
	private final static String GAME_ID = "gameId";
	private final static String EVENT_NAME_PARAM = "&eventName=";
	private final static String EVENT_NAME = "eventName";
	private final static String TEAM_ONE_PARAM = "&teamOne=";
	private final static String TEAM_ONE = "teamOne";
	private final static String TEAM_TWO_PARAM = "&teamTwo=";
	private final static String TEAM_TWO = "teamTwo";
	private final static String RESULT_ID_PARAM = "&resultId=";
	private final static String RESULT_ID = "resultId";
	private final static String START_DATE_PARAM = "&startDate=";
	private final static String START_DATE = "startDate";
	private final static String END_DATE_PARAM = "&endDate=";
	private final static String END_DATE = "endDate";
	private final static String STATUS_ID_PARAM = "&statusId=";
	private final static String STATUS_ID = "statusId";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String url = GO_TO_EVENT_EDIT_WITH_PARAMS + request.getParameter(EVENT_ID)
		+ GAME_ID_PARAM + request.getParameter(GAME_ID) + EVENT_NAME_PARAM + request.getParameter(EVENT_NAME)
		+ TEAM_ONE_PARAM + request.getParameter(TEAM_ONE) + TEAM_TWO_PARAM
		+ request.getParameter(TEAM_TWO) + RESULT_ID_PARAM + request.getParameter(RESULT_ID) + START_DATE_PARAM
		+ request.getParameter(START_DATE) + END_DATE_PARAM + request.getParameter(END_DATE) + STATUS_ID_PARAM
		+ request.getParameter(STATUS_ID);
		
		request.getSession(false).setAttribute(CURRENT_URL, url);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		Map<Integer,String> results = null;
		Map<Integer,String> status = null;
		String page = null;
		
		try {
			results = adminService.getResultDictionaryData();
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			return page;
		}
		
		try {
			status = adminService.getStatusDictionaryData();
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			return page;
		}
		
		String fullStartDate = request.getParameter(START_DATE);
		String startDate = fullStartDate.substring(0,fullStartDate.indexOf(" "));
	
		String startTimeHours = fullStartDate.substring(fullStartDate.indexOf(" ") + 1,fullStartDate.indexOf(":"));
		String startTimeMinutes = fullStartDate.substring(fullStartDate.indexOf(":") + 1,fullStartDate.indexOf(":") + 3);
		
		String fullEndDate = request.getParameter(END_DATE);
		String endDate = fullEndDate.substring(0,fullStartDate.indexOf(" "));
		String endTimeHours = fullEndDate.substring(fullStartDate.indexOf(" ") + 1,fullStartDate.indexOf(":"));
		String endTimeMinutes = fullEndDate.substring(fullStartDate.indexOf(":") + 1,fullStartDate.indexOf(":") + 3);
		
		
		request.setAttribute(EVENT_ID, request.getParameter(EVENT_ID));
		request.setAttribute(EVENT_NAME, request.getParameter(EVENT_NAME));
		request.setAttribute(GAME_ID, request.getParameter(GAME_ID));
		request.setAttribute(TEAM_ONE, request.getParameter(TEAM_ONE));
		request.setAttribute(TEAM_TWO, request.getParameter(TEAM_TWO));
		
		request.setAttribute("selectedRes", request.getParameter(RESULT_ID));
		request.setAttribute("resultsMap", results);
		
		request.setAttribute(START_DATE, startDate);
		
		request.setAttribute("startTimeHours", startTimeHours);
		request.setAttribute("startTimeMinutes", startTimeMinutes);
		request.setAttribute("endDate", endDate);
		request.setAttribute("endTimeHours", endTimeHours);
		request.setAttribute("endTimeMinutes", endTimeMinutes);
		
		request.setAttribute("selectedStatus", request.getParameter(STATUS_ID));
		request.setAttribute("statusMap", status);

		page = PageName.EDIT_EVENT;
		return page;
		
	}

}
