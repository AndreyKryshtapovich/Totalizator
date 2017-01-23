package by.epamtr.totalizator.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * This filter sets encoding of requests and responses to UTF-8.
 * 
 * @author Andrey
 *
 */

public class CharsetFilter implements Filter {
	private final static String CHARACTER_ENCODING = "characterEncoding";
	private String encoding;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter(CHARACTER_ENCODING);
	}

}
