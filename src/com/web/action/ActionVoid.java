package com.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionVoid {
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

}
