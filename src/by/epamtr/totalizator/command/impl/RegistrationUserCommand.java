package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;


public class RegistrationUserCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(RegistrationUserCommand.class.getName());
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		/*String url = "Controller?command=registration-user&firstName=" + request.getParameter("firstName")
				+ "&lastName=" + request.getParameter("lastName") + "&login=" + request.getParameter("login")
				+ "&password=" + request.getParameter("password") + "&rep-password="
				+ request.getParameter("rep-password") + "&sex=" + request.getParameter("sex") + "&e-mail="
				+ request.getParameter("e-mail") + "&country=" + request.getParameter("country") + "&city="
				+ request.getParameter("city") + "&address=" + request.getParameter("address");
*/
		//request.getSession(false).setAttribute("currentUrl", url);

		String url = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientOperationService clientService = factory.getClientOperationService();

		try {
			boolean result = clientService.registrationUser(request.getParameter("firstName"),
					request.getParameter("lastName"), request.getParameter("register-login"), request.getParameter("register-password").getBytes(),
					request.getParameter("rep-password").getBytes(), request.getParameter("sex"), request.getParameter("e-mail"),
					request.getParameter("country"), request.getParameter("city"), request.getParameter("address"));

			if (result) {
				boolean registrationResult = true;
				request.getSession(false).setAttribute("result", registrationResult);
				url = "http://localhost:8080/Totalizator/index.jsp";
			} else {
				boolean registrationResult = false;
				request.getSession(false).setAttribute("result", registrationResult);
				url = "http://localhost:8080/Totalizator/Controller?command=go-to-registration";
			}
		} catch (ServiceException e) {
			Logger.error(e);
			boolean registrationResult = false;
			request.getSession(false).setAttribute("result", registrationResult);
			url = "http://localhost:8080/Totalizator/Controller?command=go-to-registration";
		}
		return url;

	}

}
