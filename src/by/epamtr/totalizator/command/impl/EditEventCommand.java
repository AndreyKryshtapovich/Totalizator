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

public class EditEventCommand implements Command {

	private final static Logger Logger = LogManager.getLogger(EditEventCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;

		int eventId = Integer.valueOf(request.getParameter("eventId"));
		int gameCouponId = Integer.valueOf(request.getParameter("gameCouponId"));
		String eventName = request.getParameter("name");
		String teamOne = request.getParameter("team-one");
		String teamTwo = request.getParameter("team-two");

		int resultId = Integer.valueOf(request.getParameter("resultId"));

		String startDate = request.getParameter("start-date");
		String startTimeHours = request.getParameter("start-time-hours");
		String startTimeMinutes = request.getParameter("start-time-minutes");

		String endDate = request.getParameter("end-date");
		String endTimeHours = request.getParameter("end-time-hours");
		String endTimeMinutes = request.getParameter("end-time-minutes");

		String correctStartDate = startDate + " " + startTimeHours + ":" + startTimeMinutes + ":" + "00";
		String correctEndDate = endDate + " " + endTimeHours + ":" + endTimeMinutes + ":" + "00";
		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate = Timestamp.valueOf(correctEndDate);
		int status = Integer.valueOf(request.getParameter("status"));

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
				if(cookie.getName().equals("gameEventsUrl")){
					gameEventsUrl = cookie.getValue();
				}
			}
		}

		try {
			boolean result = adminService.updateEvent(event);
			if (result) {
				url ="http://localhost:8080/Totalizator/" + gameEventsUrl;
			} else {
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-error-page";
			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = "http://localhost:8080/Totalizator/Controller?command=go-to-error-page";
		}

		return url;
	}

}
