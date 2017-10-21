package com.ma.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.ArticleDao;
import com.ma.dao.ReplyDao;
import com.ma.entity.Article;
import com.ma.entity.Reply;
import com.ma.entity.T_ReplyAndArticle;
import com.ma.utils.Page;

@WebServlet("/admin/articlereplymanager")
public class ArticleReplyManager extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ReplyDao replyDao = new ReplyDao();
	ArticleDao articleDao = new ArticleDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//可能获取到page
		String p = req.getParameter("page");
		int pa = 1;
		//判断p是否正常
		if(StringUtils.isNotEmpty(p)) {
			if(StringUtils.isNumeric(p)) {
				pa = Integer.parseInt(p);
			}else {
				//抛出异常
				
			}
		}
		//查询reply数据总条
		int count = replyDao.findCountAllReply();
		
		Page<T_ReplyAndArticle> page = new Page<>(count,pa);
		
		List<Reply> replylist = replyDao.findAllReply(pa);
		
		List<T_ReplyAndArticle> T_ReplyAndArticlelist = new ArrayList<>();
		for(Reply re : replylist) {
			T_ReplyAndArticle tr = new T_ReplyAndArticle();
			Article article = articleDao.findById(re.getArticleid());
			tr.setTitle(article.getTitle());
			tr.setId(re.getId());
			tr.setContent(re.getContent());
			tr.setCreatetime(re.getCreatetime());
			tr.setUserip(re.getUserip());
			T_ReplyAndArticlelist.add(tr);
		}
		page.setItem((ArrayList<T_ReplyAndArticle>) T_ReplyAndArticlelist);
		req.setAttribute("page", page);
		forward("admin/articlereplymanager", req, resp);
		
	}
}
