package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ma.service.NotifyService;

@WebServlet("/admin/readnotify")
public class NotifyRead extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NotifyService notifyService = new NotifyService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ids = req.getParameter("ids");
		System.out.println(ids);
		//根据ids把t_notify中对应的id数据state设置为1
		notifyService.updateRead(ids);
		
		Map<String,Object> maps = new HashMap<>();
		maps.put("state", "success");
		sendJson(maps, resp);
	}
}
