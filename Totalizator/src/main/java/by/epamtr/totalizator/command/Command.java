package by.epamtr.totalizator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.exception.CommandException;
/**
 * Implementation of this interface will provide correct processing of request from client and 
 * generating a response for him.
 * 
 * @author Andrey
 *
 */
public interface Command {
	String  execute(HttpServletRequest request,HttpServletResponse response) throws CommandException;
}
