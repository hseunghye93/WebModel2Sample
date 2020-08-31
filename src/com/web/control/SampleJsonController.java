package com.web.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.action.ActionJson;

@WebServlet("/SampleJsonController")
public class SampleJsonController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> command = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String fileName = config.getInitParameter("sampleJsonConfig");
		String realpath = config.getServletContext().getRealPath("/WEB-INF");
		Properties pr = new Properties();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(new File(realpath, fileName));
			pr.load(fi);
			
			Iterator<Object> it = pr.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = pr.getProperty(key);
				Class className = Class.forName(value);
				Object instance = className.newInstance();
				command.put(key, instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public SampleJsonController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//    	response.setContentType("text/html;charset-utf-8");
		response.setContentType("application/json;charset-utf-8");
		
		String commands = request.getRequestURI();
		String rootPath = request.getServletContext().getContextPath();
		commands = commands.substring(rootPath.length());

		ActionJson a = (ActionJson)command.get(commands);
		String jsonStr = "";
		jsonStr = a.execute(request, response);
		
		response.getWriter().append(jsonStr).append(request.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
