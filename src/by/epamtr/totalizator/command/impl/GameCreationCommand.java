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


public class GameCreationCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(GameCreationCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		/*String url = "Controller?command=game-creation&start-date=" + request.getParameter("start-date")
		+ "&start-time-hours=" + request.getParameter("start-time-hours") + "&start-time-minutes=" + request.getParameter("start-time-minutes")
		+ "&end-date=" + request.getParameter("end-date") + "&end-time-hours="
		+ request.getParameter("end-time-hours") + "&end-time-minutes=" + request.getParameter("end-time-minutes") + "&min-bet-amount="
		+ request.getParameter("min-bet-amount");
		request.getSession(false).setAttribute("currentUrl", url);*/
		
		String url = null;
		
		String startDate = request.getParameter("start-date");
		String startTimeHours = request.getParameter("start-time-hours");
		String startTimeMinutes = request.getParameter("start-time-minutes");
		String endDate = request.getParameter("end-date");
		String endTimeHours = request.getParameter("end-time-hours");
		String endTimeMinutes = request.getParameter("end-time-minutes");
		String minBetAmount = request.getParameter("min-bet-amount");
	
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		try {
			boolean result =  adminService.createNewGameCupoun(startDate, startTimeHours, startTimeMinutes, endDate, endTimeHours, endTimeMinutes, minBetAmount);
			if (result) {
				// now admin is redirected to the game creation page. In future to the page with list of all games
				// show-all-games command
				boolean gameCreatingResult = true;
				request.getSession(false).setAttribute("result", gameCreatingResult);
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-game-creation";
			} else {
				boolean gameCreatingResult = false;
				// in future admin will be redirected to the same game creation page with some message
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-game-creation";
			}
		
		} catch (ServiceException e) {
			Logger.error(e);
			//url = "http://localhost:8080/Totalizator/Controller?command=go-to-error-page";
			boolean gameCreatingResult = false;
			request.getSession(false).setAttribute("result", gameCreatingResult);
			url = "http://localhost:8080/Totalizator/Controller?command=go-to-game-creation";
		}
		return url;
	}

}
