package com.ma.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.NodeDao;
import com.ma.entity.Node;
import com.ma.utils.Page;

@WebServlet("/admin/articlenodemanager")
public class ArticleNodeManagerServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NodeDao nodeDao = new NodeDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//有可能获取页数
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
		//查询t_node
		//查询t_node总数据条数
		int nodecount = nodeDao.findCountNode();
		Page<Node> page =new Page<>(nodecount,pa);
		List<Node> nodelist = nodeDao.findAllNode();
		page.setItem((ArrayList<Node>) nodelist);
		req.setAttribute("page", page);
		//跳转至articlenodename.jsp
		forward("admin/articlenodemanager", req, resp);
	}

}
