package by.epamtr.totalizator.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.controller.PageName;


public class GoToRegistrationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String url = "Controller?command=go-to-registration";
		request.getSession(false).setAttribute("currentUrl", url);

		String page = PageName.REGISTRATION;
		return page;
		
		/*RequestDispatcher dispatcher = request.getRequestDispatcher(PageName.REGISTRATION);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Logger.error("Failed forwarding to the page");
		} catch (IOException e) {
			Logger.error("Failed forwarding to the page");
		}
*/
	}

}
