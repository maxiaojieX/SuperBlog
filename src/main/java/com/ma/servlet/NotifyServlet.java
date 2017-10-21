package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.NotifyDao;
import com.ma.entity.Notify;
import com.ma.service.NotifyService;
import com.ma.utils.Page;

@WebServlet("/admin/notify")
public class NotifyServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NotifyService notifyService = new NotifyService();
	NotifyDao notifyDao = new NotifyDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//有可能获取到page
		String p = req.getParameter("p");
		if(!StringUtils.isNotEmpty(p)) {
			p = "1";
		}
		//查询所有t_notify
		Page<Notify> page = notifyService.findCountAll(p);
		req.setAttribute("page", page);
		//forward至消息页面
		forward("admin/notify", req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询t_notify的state为0的数据条数
		int count = notifyDao.findNotRead();
		Map<String,Object> maps = new HashMap<>();
		maps.put("json", count);
		//把查到的未读条数以json的形式返回前端
		sendJson(maps, resp);
	}
}
