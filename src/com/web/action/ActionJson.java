package com.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionJson {
	
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
