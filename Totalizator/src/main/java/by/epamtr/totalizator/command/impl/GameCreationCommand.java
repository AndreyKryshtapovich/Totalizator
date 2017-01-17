package by.epamtr.totalizator.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.GameCupounDTO;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;


public class GameCreationCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(GameCreationCommand.class.getName());
	private final static String START_DATE = "start-date";
	private final static String START_TIME_HOURS = "start-time-hours";
	private final static String START_TIME_MINUTES = "start-time-minutes";
	private final static String END_DATE = "end-date";
	private final static String END_TIME_HOURS = "end-time-hours";
	private final static String END_TIME_MINUTES = "end-time-minutes";
	private final static String MIN_BET_AMOUNT = "min-bet-amount";
	private final static String GO_TO_GAME_CREATION_PAGE = "Controller?command=go-to-game-creation";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String LOCALHOST = "index.jsp";
	private final static String RESULT = "result";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		if(request.getSession(false) == null){
			url = LOCALHOST;
			return url;
		}
		
		String startDate = request.getParameter(START_DATE);
		String startTimeHours = request.getParameter(START_TIME_HOURS);
		String startTimeMinutes = request.getParameter(START_TIME_MINUTES);
		String endDate = request.getParameter(END_DATE);
		String endTimeHours = request.getParameter(END_TIME_HOURS);
		String endTimeMinutes = request.getParameter(END_TIME_MINUTES);
		String minBetAmount = request.getParameter(MIN_BET_AMOUNT);
	
		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		
		GameCupounDTO gameCupounDTO = new GameCupounDTO();
		gameCupounDTO.setStartDate(startDate);
		gameCupounDTO.setStartTimeHours(startTimeHours);
		gameCupounDTO.setStartTimeMinutes(startTimeMinutes);
		gameCupounDTO.setEndDate(endDate);
		gameCupounDTO.setEndTimeHours(endTimeHours);
		gameCupounDTO.setEndTimeMinutes(endTimeMinutes);
		gameCupounDTO.setMinBetAmount(minBetAmount);
		
		try {
			boolean result =  adminService.createNewGameCupoun(gameCupounDTO);
			if (result) {
				boolean gameCreatingResult = true;
				request.getSession(false).setAttribute(RESULT, gameCreatingResult);
				url = GO_TO_GAME_CREATION_PAGE;
			} else {
				boolean gameCreatingResult = false;
				request.getSession(false).setAttribute(RESULT, gameCreatingResult);
				url = GO_TO_GAME_CREATION_PAGE;
			}
		
		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}
		return url;
	}

}
