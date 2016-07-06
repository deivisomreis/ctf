package com.ctf.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ctf.sessioncontrol.AdminAware;
import com.ctf.sessioncontrol.UserAware;

public class ControlAccessPage extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		String uri = request.getRequestURI();
		
		if(uri.equals("/home") || uri.equals("/") || uri.equals("/adminlogin") || uri.equals("/userlogin") || uri.equals("/sobre"))
			return true;
		
		if(uri.startsWith("/admin/")){
			if(AdminAware.isAdmin(request.getSession().getAttribute("user")))
				return true;
			else{
				response.sendRedirect("/adminlogin");
				return false;
			}
		}
		
		if(uri.startsWith("/user/")){
			if(UserAware.isUser(request.getSession().getAttribute("user")))
				return true;
			else{
				response.sendRedirect("/userlogin");
				return false;
			}
		}
		
		else{
			response.sendRedirect("/");
			return false;
		}
	}
}
