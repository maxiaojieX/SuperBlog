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
import com.ma.dao.LableDao;
import com.ma.entity.Article;
import com.ma.entity.Lable;
import com.ma.entity.Node;
import com.ma.service.ArticleService;

@WebServlet("/admin/modify")
public class ArticleListModify extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ArticleDao articleDao = new ArticleDao();
	LableDao lableDao = new LableDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String modifyId = req.getParameter("modifyId");
		//需要判断modifyId是否为正常数字
		if(StringUtils.isNumeric(modifyId)) {
			//如果是，请求转发至add页，并且把article带过去
			//根据modifyId查询该article是否存在
			Article article = articleDao.findById(Integer.parseInt(modifyId));
			if(article != null) {
				//存在
				req.setAttribute("article", article);
				//把对应的lablelist也存进去
				List<Lable> lablelist = lableDao.findByInner(Integer.parseInt(modifyId));
				ArticleService articleService = new ArticleService();
				ArrayList<Node> nodelist = (ArrayList<Node>) articleService.findAllNode();
				req.setAttribute("nodelist", nodelist);
				req.setAttribute("lablelist", lablelist);
				forward("admin/article_add", req, resp);
			}
			
		}else {
			//如果不是，抛出异常
			
		}
	}

}
