package by.epamtr.totalizator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.exception.CommandException;

public interface Command {
	String  execute(HttpServletRequest request,HttpServletResponse response) throws CommandException;
}
