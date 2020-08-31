package com.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public abstract ActionFoward execute(HttpServletRequest request, HttpServletResponse response);

}
