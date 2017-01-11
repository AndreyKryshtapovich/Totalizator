package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.GameCupounDTO;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

public class EditGameCouponCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(EditGameCouponCommand.class.getName());
	private final static String LOCALHOST = "http://localhost:8080/Totalizator/";
	private final static String START_DATE = "start-date";
	private final static String START_TIME_HOURS = "start-time-hours";
	private final static String START_TIME_MINUTES = "start-time-minutes";
	private final static String END_DATE = "end-date";
	private final static String END_TIME_HOURS = "end-time-hours";
	private final static String END_TIME_MINUTES = "end-time-minutes";
	private final static String STATUS = "status";
	private final static String GO_TO_ERROR_PAGE = "http://localhost:8080/Totalizator/Controller?command=go-to-error-page";
	private final static String CURRENT_URL = "currentUrl";
	private final static String GAME_EVENTS_URL = "gameEventsUrl";
	private final static String PARAM_RESULT = "param_result";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		
		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		
		String gameCouponId = request.getParameter("gameCouponId").toString();
		String startDate = request.getParameter(START_DATE);
		String startTimeHours = request.getParameter(START_TIME_HOURS);
		String startTimeMinutes = request.getParameter(START_TIME_MINUTES);

		String endDate = request.getParameter(END_DATE);
		String endTimeHours = request.getParameter(END_TIME_HOURS);
		String endTimeMinutes = request.getParameter(END_TIME_MINUTES);
		String status = request.getParameter(STATUS).toString();
		String minBetAmount = request.getParameter("minBetAmount").toString();
		String jackpot = request.getParameter("jackpot").toString();
		
		GameCupounDTO gameDTO = new GameCupounDTO();
		gameDTO.setGameCupounId(gameCouponId);
		
		gameDTO.setStartDate(startDate);
		gameDTO.setStartTimeHours(startTimeHours);
		gameDTO.setStartTimeMinutes(startTimeMinutes);
		
		gameDTO.setEndDate(endDate);
		gameDTO.setEndTimeHours(endTimeHours);
		gameDTO.setEndTimeMinutes(endTimeMinutes);
		
		gameDTO.setStatus(status);
		gameDTO.setMinBetAmount(minBetAmount);
		gameDTO.setJackpot(jackpot);
		
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
			boolean result = adminService.updateGame(gameDTO);
			if (result) {
				request.getSession(false).setAttribute(PARAM_RESULT, true);
				url = LOCALHOST + gameEventsUrl;
			} else {
				request.getSession(false).setAttribute(PARAM_RESULT, false);
				url = request.getSession(false).getAttribute(CURRENT_URL).toString();
			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}

		return url;
	}

}
