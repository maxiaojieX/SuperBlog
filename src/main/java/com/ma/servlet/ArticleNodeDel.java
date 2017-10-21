package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.ArticleDao;
import com.ma.dao.NodeDao;
import com.ma.entity.Article;
import com.ma.entity.Node;

@WebServlet("/admin/articlenodedel")
public class ArticleNodeDel extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NodeDao nodeDao = new NodeDao();
	ArticleDao articleDao = new ArticleDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取nodenid
		String nodenid = req.getParameter("del");
		//判断nodenid是否正常
		if(StringUtils.isNotEmpty(nodenid)) {
			//判断是否为数字
			if(StringUtils.isNumeric(nodenid)) {
				//查询该nid是否存在
				Node node = nodeDao.findNodeByid(Integer.parseInt(nodenid));
				if(node != null) {
					//查询该节点下是否有article
					List<Article> articlelist = articleDao.findByNodeid(nodenid);
					Map<String, Object> map = new HashMap<>();
					if(articlelist.isEmpty()) {
						//调用DAO删除节点
						nodeDao.delByNid(nodenid);
						map.put("state", "success");
						sendJson(map,resp);
					}else {
						map.put("state", "error");
						sendJson(map,resp);
					}
				}
			}
		}else {
			//抛出异常
		}
		
	}
}
