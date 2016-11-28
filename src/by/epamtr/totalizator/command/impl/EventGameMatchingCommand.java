package by.epamtr.totalizator.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class EventGameMatchingCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(EventGameMatchingCommand.class.getName());
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		HttpSession session = request.getSession(false);
		String prevUrl = session.getAttribute("currentUrl").toString();
		int selectedGameCupounId = Integer.valueOf(prevUrl.substring(prevUrl.lastIndexOf("=") + 1, prevUrl.lastIndexOf("=") + 2));
		int selectedEventId = Integer.valueOf(request.getParameter("event").toString());
		
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			boolean result = adminService.matchEventAndGame(selectedGameCupounId, selectedEventId);
			if (result) {
				//TODO message on page
				request.getSession(false).setAttribute("eventCreationMessage", "Successfully matched event and game");
				//url =" http://localhost:8080/TotalizatorController?command=search-matching-events&game=" + selectedGameCupounId;
				
				url ="http://localhost:8080/Totalizator/" + prevUrl;
			//	System.out.println(url);
				//System.out.println("http://localhost:8080/Totalizator/Controller?command=search-matching-events&game=9++2016-11-21+00%3A00%3A00.0+-+2016-11-25+23%3A59%3A00.0");
			} else {
				//TODO message on page
				request.getSession(false).setAttribute("eventCreationMessage", "Failed Adding Event");
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-admin-page";
			}

		} catch (ServiceException e) {
			Logger.error(e);
			request.getSession(false).setAttribute("eventCreationMessage", "Failed matching event and game");
			url = "http://localhost:8080/Totalizator/Controller?command=go-to-event-creation";
		}
		return url;
	}

}
