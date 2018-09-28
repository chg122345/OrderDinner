package org.jleopard.ihotel.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="encodingFilter",urlPatterns="/*")
public class EncodingFilter implements Filter {
    
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest req =(HttpServletRequest) request;
		if(req.getMethod().equals("GET")) {
			EncodingRequest er =new EncodingRequest(req);
			chain.doFilter(er, response);
		}else {
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig fConfig)  throws ServletException{
		
	}

}
