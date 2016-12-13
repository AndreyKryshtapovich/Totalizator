package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

public class EditEventCommand implements Command {

	private final static Logger Logger = LogManager.getLogger(EditEventCommand.class.getName());
	private final static String EVENT_ID = "eventId";
	private final static String GAME_COUPON_ID = "gameCouponId";
	private final static String NAME = "name";
	private final static String TEAM_ONE = "team-one";
	private final static String TEAM_TWO = "team-two";
	private final static String RESULT_ID = "resultId";
	private final static String START_DATE = "start-date";
	private final static String START_TIME_HOURS = "start-time-hours";
	private final static String START_TIME_MINUTES = "start-time-minutes";
	private final static String END_DATE = "end-date";
	private final static String END_TIME_HOURS = "end-time-hours";
	private final static String END_TIME_MINUTES = "end-time-minutes";
	private final static String STATUS = "status";
	private final static String GAME_EVENTS_URL = "gameEventsUrl";
	private final static String LOCALHOST = "http://localhost:8080/Totalizator/";
	private final static String GO_TO_ERROR_PAGE = "http://localhost:8080/Totalizator/Controller?command=go-to-error-page";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;

		int eventId = Integer.valueOf(request.getParameter(EVENT_ID));
		int gameCouponId = Integer.valueOf(request.getParameter(GAME_COUPON_ID));
		String eventName = request.getParameter(NAME);
		String teamOne = request.getParameter(TEAM_ONE);
		String teamTwo = request.getParameter(TEAM_TWO);

		int resultId = Integer.valueOf(request.getParameter(RESULT_ID));

		String startDate = request.getParameter(START_DATE);
		String startTimeHours = request.getParameter(START_TIME_HOURS);
		String startTimeMinutes = request.getParameter(START_TIME_MINUTES);

		String endDate = request.getParameter(END_DATE);
		String endTimeHours = request.getParameter(END_TIME_HOURS);
		String endTimeMinutes = request.getParameter(END_TIME_MINUTES);
		
		String correctStartDate = Utils.concatStringDate(startDate, startTimeHours, startTimeMinutes);
		String correctEndDate = Utils.concatStringDate(endDate, endTimeHours, endTimeMinutes);
		
		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate = Timestamp.valueOf(correctEndDate);
		int status = Integer.valueOf(request.getParameter(STATUS));

		Event event = new Event();
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setGameCuponId(gameCouponId);
		event.setTeamOne(teamOne);
		event.setTeamTwo(teamTwo);
		event.setResultId(resultId);
		event.setStartDate(eventStartDate);
		event.setEndDate(eventEndDate);
		event.setStatus(status);

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();

		String gameEventsUrl = null;
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(GAME_EVENTS_URL)){
					gameEventsUrl = cookie.getValue();
				}
			}
		}

		try {
			boolean result = adminService.updateEvent(event);
			if (result) {
				url = LOCALHOST + gameEventsUrl;
			} else {
				url = GO_TO_ERROR_PAGE;
			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}

		return url;
	}

}
