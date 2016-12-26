package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

public class CloseGameCouponCommand implements Command {
	private final static String LOCALHOST = "http://localhost:8080/Totalizator/";
	private final static String GAME_COUPON_ID = "gameCouponId";
	private final static String USER = "user";
	private final static String ADMIN = "admin";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	private final static String GO_TO_EDIT_SEARCH = "http://localhost:8080/Totalizator/Controller?command=go-to-edit-search-event";
	private final static String SUCCESS_CLOSE_MSG = "successCloseMsg";
	private final static String CANSELLED_MSG = "canselledMsg";
	private final static String ROLLBACK_MSG = "rollbackMsg";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = null;
		int spResult = 0;

		if (request.getSession(false) == null) {
			url = LOCALHOST;
			return url;
		}
		
		User user = (User) request.getSession(false).getAttribute(USER);

		if (user != null && user.getRole().equals(ADMIN)) {
			
			int gameCouponId = Integer.valueOf(request.getParameter(GAME_COUPON_ID));

			ServiceFactory factory = ServiceFactory.getInstance();
			AdminOperationService adminService = factory.getAdminOperationService();

			try {
				spResult = adminService.closeGameCoupon(gameCouponId);
			} catch (ServiceException e) {
				url = GO_TO_ERROR_PAGE;
				return url;
			}
			
			url = GO_TO_EDIT_SEARCH;
			
			if(spResult == 1){
				request.getSession(false).setAttribute(SUCCESS_CLOSE_MSG, true);
			}
			
			if(spResult == -1){
				request.getSession(false).setAttribute(SUCCESS_CLOSE_MSG, false);
			}
			
			if(spResult == -2){
				request.getSession(false).setAttribute(CANSELLED_MSG, true);
			}
			if(spResult == -3){
				request.getSession(false).setAttribute(ROLLBACK_MSG, true);
			}
		} else {
			url = LOCALHOST;
		}
		return url;
	}

}