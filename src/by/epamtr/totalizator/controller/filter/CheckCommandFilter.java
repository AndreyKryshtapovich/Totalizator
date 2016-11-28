package by.epamtr.totalizator.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamtr.totalizator.controller.PageName;

public class CheckCommandFilter implements Filter {

	private static final String COMMAND_NAME = "command";
	private final static Logger Logger = LogManager.getLogger(CheckCommandFilter.class.getName());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		HttpServletRequest req = (HttpServletRequest) request;
		CommandChecker commandChecker = new CommandChecker();
		String commandName = req.getParameter(COMMAND_NAME);
		String method = commandChecker.getMethod(commandName);
		if (method != null) {
			method = method.toUpperCase();
		}
		if (req.getMethod().equals(method)) {
			try {
				chain.doFilter(request, response);
			} catch (IOException e) {
				Logger.error(e);
				e.printStackTrace();
			} catch (ServletException e) {
				Logger.error(e);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageName.ERROR_PAGE);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				Logger.error(e);
			} catch (IOException e) {
				Logger.error(e);
			}
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
