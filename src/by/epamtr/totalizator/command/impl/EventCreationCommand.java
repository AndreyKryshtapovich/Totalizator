package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class EventCreationCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(EventCreationCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;

		String eventName = request.getParameter("name");
		String startDate = request.getParameter("start-date");
		String startTimeHours = request.getParameter("start-time-hours");
		String startTimeMinutes = request.getParameter("start-time-minutes");
		String endDate = request.getParameter("end-date");
		String endTimeHours = request.getParameter("end-time-hours");
		String endTimeMinutes = request.getParameter("end-time-minutes");
		String teamOne = request.getParameter("team-one");
		String teamTwo = request.getParameter("team-two");

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();

		try {
			boolean result = adminService.createNewEvent(eventName, startDate, startTimeHours, startTimeMinutes, endDate, endTimeHours, endTimeMinutes, teamOne, teamTwo);
			if (result) {
				//TODO message on page
				boolean eventAddResult = true;
				request.getSession(false).setAttribute("result", eventAddResult);
				//request.getSession(false).setAttribute("eventCreationMessage", "Added New Event");
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
			} else {
				//TODO message on page
				//request.getSession(false).setAttribute("eventCreationMessage", "Failed Adding Event");
				boolean eventAddResult = false;
				request.getSession(false).setAttribute("result", eventAddResult);
				//url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
			}

		} catch (ServiceException e) {
			Logger.error(e);
			//request.getSession(false).setAttribute("eventCreationMessage", "Failed Adding Event");
			//url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
			boolean eventAddResult = false;
			request.getSession(false).setAttribute("result", eventAddResult);
			//url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
			url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
		
		}

		return url;
	}

}
