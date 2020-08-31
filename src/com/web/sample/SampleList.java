package com.web.sample;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.action.Action;
import com.web.action.ActionFoward;
import com.web.dao.SampleDao;
import com.web.dto.SampleDto;

public class SampleList implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		int curPage = 1;
		if(request.getParameter("curPage") != null
				&& !"".equals(request.getParameter("curPage"))){
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		 
		SampleDao dao = new SampleDao();
		ArrayList<SampleDto> ar = dao.sampleList(curPage);
		HashMap<String, Integer> hs =dao.samplePage(curPage);
		request.setAttribute("boardList", ar);
		request.setAttribute("page", hs);
		ActionFoward af = new ActionFoward();
		af.setRedirect(true);
		af.setPath("/sample/sampleList.jsp");
		af.toString();
		return af;
	}

}

