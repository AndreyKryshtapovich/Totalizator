package by.epamtr.totalizator.command.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.bean.listbean.JSPMapBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class GoToBetSubmitCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(GoToBetSubmitCommand.class.getName());
	private final static String USER = "user";
	private final static String RESULT = "result";
	private final static String BET_AMOUNT = "bet-amount";
	private final static String GO_TO_BET_SUBMIT_URL = "Controller?command=go-to-bet-submit";
	private final static String CURRENT_URL = "currentUrl";
	private final static String EVENTS = "events";
	private final static String USER_RESULT_MAP = "userResultMap";
	private final static String RESULT_PARAM = "&result";
	private final static String EQUAL_SYMBOL = "=";
	private final static String BET_AMOUNT_PARAM = "&bet-amount=";
	private final static String BET_AMOUNT_ATTR = "betAmount";
	private final static String GAME_COUPON_ID_PARAM = "&game-coupon-id=";
	private final static String BET_AMOUNT_RESULT = "betAmountResult";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuilder sb = new StringBuilder();
		String page = null;
		
		if(request.getSession(false) == null){
			page = PageName.INDEX_PAGE;
			return page;
		}
		
		sb.append(GO_TO_BET_SUBMIT_URL);
		for (int i = 1; i < 16; i++) {
			sb.append(RESULT_PARAM + new Integer(i).toString() + EQUAL_SYMBOL);
			sb.append(request.getParameter(RESULT + new Integer(i).toString()));
		}
		sb.append(BET_AMOUNT_PARAM);
		sb.append(request.getParameter(BET_AMOUNT));

		
		List<Event> eventsList = null;
		Map<String, String> userResultMap = new HashMap<>();
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(USER)) {

			for (int i = 1; i < 16; i++) {
				userResultMap.put(RESULT + new Integer(i).toString(),
						request.getParameter(RESULT + new Integer(i).toString()));
			}

			boolean flag = true;

			for (int i = 1; i < 16; i++) {
				String str = userResultMap.get(RESULT + new Integer(i).toString());
				if (str == null) {
					flag = false;

					break;
				}
			}

			ServiceFactory factory = ServiceFactory.getInstance();
			ClientOperationService clientService = factory.getClientOperationService();

			GameCupoun gameCupoun = new GameCupoun();
			try {
				gameCupoun = clientService.getOpenedGame();
			} catch (ServiceException e1) {
				Logger.error(e1);
				page = PageName.ERROR_PAGE;
				return page;
			}
			Integer betAmount = Integer.valueOf(request.getParameter(BET_AMOUNT));

			boolean betAmountResult = true;
			if (betAmount < gameCupoun.getMinBetAmount()) {
				betAmountResult = false;
				flag = false;
			}
			request.getSession(false).setAttribute(BET_AMOUNT_RESULT, betAmountResult);
			request.getSession(false).setAttribute(RESULT, flag);

			try {
				eventsList = clientService.showEvents();
			} catch (ServiceException e) {
				Logger.error(e);
				page = PageName.ERROR_PAGE;
				return page;
			}

			sb.append(GAME_COUPON_ID_PARAM);
			sb.append(eventsList.get(0).getGameCuponId());
			String url = sb.toString();
			request.getSession(false).setAttribute(CURRENT_URL, url);

			JSPListBean jsp = new JSPListBean(eventsList);
			JSPMapBean map = new JSPMapBean(userResultMap);
			request.setAttribute(EVENTS, jsp);
			request.setAttribute(BET_AMOUNT_ATTR, betAmount);
			request.setAttribute(USER_RESULT_MAP, map);

			page = PageName.BET_SUBMIT;

		} else {
			page = PageName.ERROR_PAGE;
		}
		return page;
	}
}
