package com.idan.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/rest")
public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession(false);
		String pageRequest = req.getRequestURL().toString();
		if(session != null || pageRequest.endsWith("/login") || pageRequest.endsWith("/coupons") || pageRequest.endsWith("/companies")) {
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie:cookies) {
				req.setAttribute(cookie.getName(), cookie.getValue());
			}
			
			return;
		}
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(401);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
