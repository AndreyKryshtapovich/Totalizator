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


public final class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_NAME = "command";
	private final CommandProvider provider = new CommandProvider();
	private final static Logger Logger = LogManager.getLogger(Controller.class.getName());

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String commandName = null;
		Command command = null;
		String page = null;
		commandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(commandName);

		try {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		} catch (CommandException e) {
			Logger.error(e);
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String commandName = null;
		Command command = null;
		String url = null;

		commandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(commandName);
		try {
		url = command.execute(request, response);
		response.sendRedirect(url);
		} catch (CommandException e) {
			Logger.error(e);
		}
 
	}

}
