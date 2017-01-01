package by.epamtr.totalizator.command.impl;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		
		int gameCouponId = Integer.valueOf(request.getParameter("gameCouponId"));
		
		String startDate = request.getParameter(START_DATE);
		String startTimeHours = request.getParameter(START_TIME_HOURS);
		String startTimeMinutes = request.getParameter(START_TIME_MINUTES);

		String endDate = request.getParameter(END_DATE);
		String endTimeHours = request.getParameter(END_TIME_HOURS);
		String endTimeMinutes = request.getParameter(END_TIME_MINUTES);
		
		String correctStartDate = Utils.concatStringDate(startDate, startTimeHours, startTimeMinutes);
		String correctEndDate = Utils.concatStringDate(endDate, endTimeHours, endTimeMinutes);
		
		Timestamp gameStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameEndDate = Timestamp.valueOf(correctEndDate);
		int status = Integer.valueOf(request.getParameter(STATUS));
		int minBetAmount = Integer.valueOf(request.getParameter("minBetAmount"));
		int jackpot = Integer.valueOf(request.getParameter("jackpot"));
		
		GameCupoun game = new GameCupoun();
		game.setGameCupounId(gameCouponId);
		game.setStartDate(gameStartDate);
		game.setEndDate(gameEndDate);
		game.setMinBetAmount(minBetAmount);
		game.setJackpot(jackpot);
		game.setStatus(status);
		
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
			boolean result = adminService.updateGame(game);
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
