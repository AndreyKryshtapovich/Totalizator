package by.epamtr.totalizator.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;


public class ShowEventsCommand implements Command {
	private final static Logger Logger = LogManager.getLogger(ShowEventsCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = "Controller?command=show-events";
		request.getSession(false).setAttribute("currentUrl", url);

		String page = null;
		List<Event> eventsList = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientOperationService clientService = factory.getClientOperationService();

		try {
			eventsList = clientService.showEvents();
		} catch (ServiceException e) {
			Logger.error(e);
		}

		JSPListBean jsp = new JSPListBean(eventsList);
		request.setAttribute("events", jsp);
		page = PageName.USER_PAGE;

		return page;

	}

}
