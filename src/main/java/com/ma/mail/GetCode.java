package com.ma.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.UserDao;
import com.ma.servlet.BaseServlet;

@WebServlet("/user/getcode")
public class GetCode extends BaseServlet{

	private static final long serialVersionUID = 1L;
	Map<String,String> maps = new HashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		
		System.out.println(email);
		
		//生成四位随机数
		String code = String.valueOf(System.currentTimeMillis()).substring(9,13);
		maps.put(email, code);
		
		SendMail s = new SendMail();
		s.sendMail(email, code);
		System.out.println(code);
	     Map<String,Object> js = new HashMap<>();
	     js.put("state", "success");
		 sendJson(js, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String code = req.getParameter("code");
		System.out.println(code+"code");
		
		Map<String,Object> json = new HashMap<>();
		if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(code)) {
			System.out.println(maps.get(email)+"maps");
			
			if(maps.get(email).equals(code)) {
				
				UserDao userDao = new UserDao();
				userDao.add(username,password);
				
				json.put("state", "success");
				sendJson(json, resp);
			}else {
				json.put("state", "error");
				sendJson(json, resp);
			}
		}else {
			json.put("state", "empty");
			sendJson(json, resp);
		}
		
		
	}
	

}
