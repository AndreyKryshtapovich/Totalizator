package by.epamtr.totalizator.command.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

/**
 * Class is designed to process a request for making a bet. Available for user
 * only.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class MakeBetCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(MakeBetCommand.class.getName());
	private final static String USER = "user";
	private final static String LOCALHOST = "index.jsp";
	private final static String CURRENT_URL = "currentUrl";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String SHOW_EVENTS_URL = "Controller?command=show-events";
	private final static String RESULT = "result";
	private final static String CREDIT_CARD = "credit-card";

	/**
	 * Method checks user's role. Gets all parameters, creates DTO object and
	 * calls required service method.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(USER)) {

			String prevUrl = request.getSession(false).getAttribute(CURRENT_URL).toString();
			Map<String, String> userResultMap = null;
			List<Event> eventsList = null;
			
			userResultMap = Utils.parseUserResultMap(prevUrl);

			String betAmount = Utils.parseBetAmount(prevUrl);
			String gameCouponId = Utils.parseGameCouponId(prevUrl);

			MakeBetDTO makeBetDTO = new MakeBetDTO();

			makeBetDTO.setUser(user);
			makeBetDTO.setGameCouponId(gameCouponId);
			makeBetDTO.setUserResultMap(userResultMap);
			makeBetDTO.setBetAmount(betAmount);

			ServiceFactory factory = ServiceFactory.getInstance();
			ClientOperationService clientService = factory.getClientOperationService();

			int gameId = Integer.valueOf(gameCouponId);

			try {
				eventsList = clientService.showEvents(gameId);
			} catch (ServiceException e1) {
				Logger.error(e1);
				url = GO_TO_ERROR_PAGE;
				return url;
			}
			makeBetDTO.setEventsList(eventsList);

			boolean result;

			try {
				result = clientService.makeBet(makeBetDTO, request.getParameter(CREDIT_CARD).getBytes());
				if (result) {
					url = SHOW_EVENTS_URL;
					request.getSession(false).setAttribute(RESULT, result);
				} else {
					url = SHOW_EVENTS_URL;
					request.getSession(false).setAttribute(RESULT, result);
				}
			} catch (ServiceException e) {
				Logger.error(e);
				url = GO_TO_ERROR_PAGE;

			}

		} else {
			url = LOCALHOST;
		}
		return url;
	}

}
