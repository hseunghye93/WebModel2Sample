package com.web.sampleJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.web.action.ActionJson;

public class ActionLogOut implements ActionJson {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String result = "SUCCESS";
		
		// request.getSession().invalidate();
		 request.getSession().setAttribute("loginDto", null);
		
		json.put("result", result);
		return json.toString();
	}

}
