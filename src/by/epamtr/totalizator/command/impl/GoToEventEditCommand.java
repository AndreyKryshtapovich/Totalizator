package by.epamtr.totalizator.command.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

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
	private final static String ZERO = "0";
	private final static String FOUR = "4";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		StringBuilder sb = new StringBuilder();
		String page = null;

		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}

		sb.append(GO_TO_EVENT_EDIT_WITH_PARAMS);
		sb.append(request.getParameter(EVENT_ID));
		sb.append(GAME_ID_PARAM);
		sb.append(request.getParameter(GAME_ID));
		sb.append(EVENT_NAME_PARAM);
		sb.append(request.getParameter(EVENT_NAME));
		sb.append(TEAM_ONE_PARAM);
		sb.append(request.getParameter(TEAM_ONE));
		sb.append(TEAM_TWO_PARAM);
		sb.append(request.getParameter(TEAM_TWO));
		sb.append(RESULT_ID_PARAM);
		sb.append(request.getParameter(RESULT_ID));
		sb.append(START_DATE_PARAM);
		sb.append(request.getParameter(START_DATE));
		sb.append(END_DATE_PARAM);
		sb.append(request.getParameter(END_DATE));
		sb.append(STATUS_ID_PARAM);
		sb.append(request.getParameter(STATUS_ID));

		String url = sb.toString();

		request.getSession(false).setAttribute(CURRENT_URL, url);

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();

		Map<Integer, String> results = null;
		Map<Integer, String> status = null;
		GameCupoun game = null;

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

		try {
			int gameCupounId = Integer.valueOf(request.getParameter(GAME_ID));
			game = adminService.getGameByGameCupounId(gameCupounId);
		} catch (ServiceException e) {
			page = PageName.ERROR_PAGE;
			return page;
		}
		
		String fullStartDate = request.getParameter(START_DATE);

		String startDate = Utils.parseDateFromFullDate(fullStartDate);
		String startTimeHours = Utils.parseHoursFromFullDate(fullStartDate);
		String startTimeMinutes = Utils.parseMinutesFromFullDate(fullStartDate);

		String fullEndDate = request.getParameter(END_DATE);

		String endDate = Utils.parseDateFromFullDate(fullEndDate);
		String endTimeHours = Utils.parseHoursFromFullDate(fullEndDate);
		String endTimeMinutes = Utils.parseMinutesFromFullDate(fullEndDate);
		String gameStatus = status.get(game.getStatus());

		request.setAttribute("gameStatus", gameStatus);
		request.setAttribute(EVENT_ID, request.getParameter(EVENT_ID));
		request.setAttribute(EVENT_NAME, request.getParameter(EVENT_NAME));
		request.setAttribute(GAME_ID, request.getParameter(GAME_ID));
		request.setAttribute(TEAM_ONE, request.getParameter(TEAM_ONE));
		request.setAttribute(TEAM_TWO, request.getParameter(TEAM_TWO));

		String selectedRes = request.getParameter(RESULT_ID);
		
		if(selectedRes.equals(ZERO)){
			selectedRes = FOUR;
		}
		
		request.setAttribute("selectedRes", selectedRes);
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
