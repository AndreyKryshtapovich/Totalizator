package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;
/**
 * Class is designed to process a request for registration new user with role "user".
 * Available for every person.
 * 
 * @author Andrey
 *
 */

public class RegistrationUserCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(RegistrationUserCommand.class.getName());
	private final static String LAST_NAME = "lastName";
	private final static String FIRST_NAME = "firstName";
	private final static String REGISTER_LOGIN = "register-login";
	private final static String REGISTER_PASSWORD = "register-password";
	private final static String REPEAT_PASSWORD = "rep-password";
	private final static String SEX = "sex";
	private final static String E_MAIL = "e-mail";
	private final static String COUNTRY = "country";
	private final static String CITY = "city";
	private final static String ADDRESS = "address";
	private final static String GO_TO_INDEX_PAGE = "index.jsp";
	private final static String GO_TO_REGISTRATION_PAGE = "Controller?command=go-to-registration";
	private final static String RESULT = "result";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=go-to-error-page";
	
	/**
	 * Method gets all parameters, creates DTO and calls required service method.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		
		String url = null;
		if(request.getSession(false) == null){
			url = GO_TO_INDEX_PAGE;
			return url;
		}
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientOperationService clientService = factory.getClientOperationService();
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setFirstName(request.getParameter(FIRST_NAME));
		userDTO.setLastName(request.getParameter(LAST_NAME));
		userDTO.setRegisterLogin(request.getParameter(REGISTER_LOGIN));
		userDTO.setSex(request.getParameter(SEX));
		userDTO.seteMail(request.getParameter(E_MAIL));
		userDTO.setCountry(request.getParameter(COUNTRY));
		userDTO.setCity( request.getParameter(CITY));
		userDTO.setAddress(request.getParameter(ADDRESS));
		

		try {
			boolean result = clientService.registrationUser(userDTO, request.getParameter(REGISTER_PASSWORD).getBytes(),
					request.getParameter(REPEAT_PASSWORD).getBytes());

			boolean registrationResult;
			if (result) {
				registrationResult = true;
				request.getSession(false).setAttribute(RESULT, registrationResult);
				url = GO_TO_INDEX_PAGE;
			} else {
				registrationResult = false;
				request.getSession(false).setAttribute(RESULT, registrationResult);
				url = GO_TO_REGISTRATION_PAGE;
			}
		} catch (ServiceException e) {
			Logger.error(e);
			url = GO_TO_ERROR_PAGE;
		}
		return url;

	}

}
