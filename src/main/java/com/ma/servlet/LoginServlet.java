package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ma.entity.User;
import com.ma.service.LoginService;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("username".equals(cookie.getName())) {
					req.setAttribute("username", cookie.getValue());
				}
			}
		}
		forward("user/login", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		String callback = req.getParameter("callback");
		
		String ip = req.getRemoteAddr();
		if(StringUtils.isNoneEmpty(remember)) {
			Cookie cookie = new Cookie("username", username);
			cookie.setDomain(ip);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*21*7);
			resp.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("username", username);
			cookie.setDomain("127.0.0.1");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}

		LoginService loginService = new LoginService();
		User user = loginService.findUser(username, password);
		Map<String,Object> map = new HashMap<>();
		if(user != null) {
			//创建session
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
//			if(StringUtils.isNotEmpty(callback)) {
//				resp.sendRedirect(callback);
//			}
//			resp.sendRedirect("/user/home");
			
			map.put("state", "success");
			map.put("callback",callback);
			sendJson(map, resp);
		}else {
			map.put("state", "error");
			sendJson(map, resp);
		}
		
		
	}
}
