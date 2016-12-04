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
	private final static String CURRENT_URL = "currentUrl";
	private final static String EVENTS = "events";
	private final static String SHOW_EVENTS_URL = "Controller?command=show-events";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String url = SHOW_EVENTS_URL;
		request.getSession(false).setAttribute(CURRENT_URL, url);

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
		request.setAttribute(EVENTS, jsp);
		page = PageName.USER_PAGE;

		return page;

	}

}
