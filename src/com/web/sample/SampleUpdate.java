package com.web.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.action.Action;
import com.web.action.ActionFoward;

public class SampleUpdate implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward af = new ActionFoward();
		af.setRedirect(true);
		af.setPath("/sample/sampleUpdate.jsp");
		af.toString();
		return af;
	}

}
