package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.ReplyDao;

@WebServlet("/admin/articlereplydel")
public class ArticleReplyDel extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ReplyDao replyDao = new ReplyDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String replyid = req.getParameter("del");
		if(StringUtils.isNumeric(replyid)) {
			replyDao.deleteById(replyid);
			Map<String,Object> map = new HashMap<>();
			map.put("state", "success");
			sendJson(map, resp);
		}else {
			//抛出异常
		}
	}

}
