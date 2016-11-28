package by.epamtr.totalizator.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_NAME = "command";
	private final CommandProvider provider = new CommandProvider();
	private final static Logger Logger = LogManager.getLogger(Controller.class.getName());

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String commandName = null;
		Command command = null;
		String page = null;
		commandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(commandName);

		try {
			page = command.execute(request, response);
		} catch (CommandException e) {
			Logger.error(e);
			page = PageName.ERROR_PAGE; // might be fatal error or some other
										// page
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				Logger.error(e);
			} catch (IOException e) {
				Logger.error(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String commandName = null;
		Command command = null;
		String url = null;

		commandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(commandName);
		try {
		url = command.execute(request, response);
		} catch (CommandException e) {
			Logger.error(e);
			//page = PageName.ERROR_PAGE; // might be fatal error or some other
										// page
		}

		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			Logger.error(e);
		}
 
	}

}
