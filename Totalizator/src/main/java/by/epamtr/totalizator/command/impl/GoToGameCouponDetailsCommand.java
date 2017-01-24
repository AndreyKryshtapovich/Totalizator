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

/**
 * Class is designed to process a request for forwarding administrator to the
 * page where he can view the game coupons's info in details and edit it.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class GoToGameCouponDetailsCommand implements Command {
	private final static String GO_TO_GAME_COUPON_DETAILS_WITH_PARAMS = "Controller?command=go-to-game-coupon-details&gameCouponId=";
	private final static String GAME_COUPON_ID = "gameCouponId";
	private final static String CURRENT_URL = "currentUrl";
	private final static String START_DATE = "startDate";
	private final static String START_TIME_HOURS = "startTimeHours";
	private final static String START_TIME_MINUTES = "startTimeMinutes";
	private final static String END_TIME_HOURS = "endTimeHours";
	private final static String END_TIME_MINUTES = "endTimeMinutes";
	private final static String END_DATE = "endDate";
	private final static String GAME_STATUS = "gameStatus";
	private final static String STATUS_MAP = "statusMap";
	private final static String JACKPOT = "jackpot";
	private final static String MIN_BET_AMOUNT = "minBetAmount";
	private final static String SELECTED_STATUS = "selectedStatus";
	private final static int CLOSED = 3;

	private final static Logger Logger = LogManager.getLogger(GoToEventEditCommand.class.getName());

	/**
	 * Method saves current URL in session. Gets all parameters. Returns
	 * required path to the page.
	 */
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

		status.remove(CLOSED);

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

		request.setAttribute(STATUS_MAP, status);
		request.setAttribute(JACKPOT, jackpot);
		request.setAttribute(MIN_BET_AMOUNT, minBetAmount);
		request.setAttribute(GAME_COUPON_ID, selectedGameCouponId);
		request.setAttribute(GAME_STATUS, gameStatus);
		request.setAttribute(SELECTED_STATUS, game.getStatus());

		request.setAttribute(START_DATE, startDate);
		request.setAttribute(START_TIME_HOURS, startTimeHours);
		request.setAttribute(START_TIME_MINUTES, startTimeMinutes);

		request.setAttribute(END_DATE, endDate);
		request.setAttribute(END_TIME_HOURS, endTimeHours);
		request.setAttribute(END_TIME_MINUTES, endTimeMinutes);

		page = PageName.GAME_COUPON_DETAILS;

		return page;
	}

}
