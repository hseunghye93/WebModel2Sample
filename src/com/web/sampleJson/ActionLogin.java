package com.web.sampleJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.web.action.ActionJson;
import com.web.dao.ActionLoginDao;
import com.web.dto.LoginDto;

public class ActionLogin implements ActionJson {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String result = "ERROR";
		
		LoginDto loginDto = new LoginDto();
		loginDto.setId(request.getParameter("id"));
		loginDto.setPassword(request.getParameter("pw"));
		
		ActionLoginDao loginDao = new ActionLoginDao();
		loginDto = loginDao.actionLogin(loginDto);
		
		if(loginDto != null){
			// 로그인 정보를 세션에 저장
			request.getSession().setAttribute("loginDto", loginDto);
			result = "SUCCESS";
		}

		json.put("result", result);
		return json.toString();
	}

}
