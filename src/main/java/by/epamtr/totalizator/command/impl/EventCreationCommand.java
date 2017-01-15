package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class EventCreationCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(EventCreationCommand.class.getName());
	private final static String NAME = "name";
	private final static String TEAM_ONE = "team-one";
	private final static String TEAM_TWO = "team-two";
	private final static String START_DATE = "start-date";
	private final static String START_TIME_HOURS = "start-time-hours";
	private final static String START_TIME_MINUTES = "start-time-minutes";
	private final static String END_DATE = "end-date";
	private final static String END_TIME_HOURS = "end-time-hours";
	private final static String END_TIME_MINUTES = "end-time-minutes";
	private final static String RESULT = "result";
	private final static String GO_TO_EVENT_CREATION = "Controller?command=go-to-event-creation";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String LOCALHOST = "index.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;

		if(request.getSession(false) == null){
			url = LOCALHOST;
			return url;
		}
		String eventName = request.getParameter(NAME);
		String startDate = request.getParameter(START_DATE);
		String startTimeHours = request.getParameter(START_TIME_HOURS);
		String startTimeMinutes = request.getParameter(START_TIME_MINUTES);
		String endDate = request.getParameter(END_DATE);
		String endTimeHours = request.getParameter(END_TIME_HOURS);
		String endTimeMinutes = request.getParameter(END_TIME_MINUTES);
		String teamOne = request.getParameter(TEAM_ONE);
		String teamTwo = request.getParameter(TEAM_TWO);

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		EventDTO eventDTO = new EventDTO();
		
		eventDTO.setEventName(eventName);
		eventDTO.setStartDate(startDate);
		eventDTO.setStartTimeHours(startTimeHours);
		eventDTO.setStartTimeMinutes(startTimeMinutes);
		eventDTO.setEndDate(endDate);
		eventDTO.setEndTimeHours(endTimeHours);
		eventDTO.setEndTimeMinutes(endTimeMinutes);
		eventDTO.setTeamOne(teamOne);
		eventDTO.setTeamTwo(teamTwo);
		
		try {
			boolean result = adminService.createNewEvent(eventDTO);
			boolean eventAddResult;
			if (result) {
				eventAddResult = true;
				request.getSession(false).setAttribute(RESULT, eventAddResult);
				url = GO_TO_EVENT_CREATION;
			} else {
				eventAddResult = false;
				request.getSession(false).setAttribute(RESULT, eventAddResult);
				url = GO_TO_EVENT_CREATION;
			}

		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}

		return url;
	}

}
