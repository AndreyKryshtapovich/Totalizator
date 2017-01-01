package by.epamtr.totalizator.command.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

public class GoToGameCouponDetailsCommand implements Command {
	private final static String GO_TO_GAME_COUPON_DETAILS_WITH_PARAMS = "http://localhost:8080/Totalizator/Controller?command=go-to-game-coupon-details&gameCouponId=";
	private final static String GAME_COUPON_ID = "gameCouponId";
	private final static String CURRENT_URL = "currentUrl";
	private final static Logger Logger = LogManager.getLogger(GoToEventEditCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;

		if (request.getSession(false) == null) {
			page = PageName.INDEX_PAGE;
			return page;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(GO_TO_GAME_COUPON_DETAILS_WITH_PARAMS);
		sb.append(request.getParameter(GAME_COUPON_ID));

		String url = sb.toString();

		request.getSession(false).setAttribute(CURRENT_URL, url);
		String gameCouponId = request.getParameter(GAME_COUPON_ID).toString();

		if (gameCouponId.isEmpty()) {
			page = PageName.ERROR_PAGE;
			return page;
		}

		int selectedGameCouponId = Integer.valueOf(gameCouponId);

		ServiceFactory factory = ServiceFactory.getInstance();
		AdminOperationService adminService = factory.getAdminOperationService();
		GameCupoun game = null;
		Map<Integer, String> status = null;

		try {
			game = adminService.getGameByGameCupounId(selectedGameCouponId);
		} catch (ServiceException e) {
			Logger.error(e);
			page = PageName.ERROR_PAGE;
			return page;
		}

		try {
			status = adminService.getStatusDictionaryData();
		} catch (ServiceException e) {
			Logger.error(e);
			page = PageName.ERROR_PAGE;
			return page;
		}
		
		
		String fullStartDate = game.getStartDate().toString();
		
		String startDate = Utils.parseDateFromFullDate(fullStartDate);
		String startTimeHours = Utils.parseHoursFromFullDate(fullStartDate);
		String startTimeMinutes = Utils.parseMinutesFromFullDate(fullStartDate);
		
		String fullEndDate = game.getEndDate().toString();
		
		String endDate = Utils.parseDateFromFullDate(fullEndDate);
		String endTimeHours = Utils.parseHoursFromFullDate(fullEndDate);
		String endTimeMinutes = Utils.parseMinutesFromFullDate(fullEndDate);
		String gameStatus = status.get(game.getStatus());
		
		String minBetAmount = String.valueOf(game.getMinBetAmount());
		String jackpot = String.valueOf(game.getJackpot());
		
		request.setAttribute("statusMap", status);
		request.setAttribute("jackpot", jackpot);
		request.setAttribute("minBetAmount", minBetAmount);
		request.setAttribute("gameCouponId", selectedGameCouponId);
		request.setAttribute("gameStatus", gameStatus);
		request.setAttribute("selectedStatus", game.getStatus());
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("startTimeHours", startTimeHours);
		request.setAttribute("startTimeMinutes", startTimeMinutes);
		
		request.setAttribute("endDate", endDate);
		request.setAttribute("endTimeHours", endTimeHours);
		request.setAttribute("endTimeMinutes", endTimeMinutes);
		
		page = PageName.GAME_COUPON_DETAILS;
		
		return page;
	}

}
