package com.ma.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ma.dao.NodeDao;
import com.ma.entity.Node;

@WebServlet("/admin/articlenodeadd")
public class ArticleNodeAdd extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NodeDao nodeDao = new NodeDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取到newnodename
		String newnodename = req.getParameter("newnodename");
		newnodename = new String(newnodename.getBytes("ISO8859-1"),"UTF-8");
		Map<String,Object> map = new HashMap<>();
		//判断该node是否存在
		
		Node node = nodeDao.findNodeBynodename(newnodename);
		if(node != null) {
			//如果存在
			map.put("state", "error");
			sendJson(map,resp);
		}else {
			//调用DAO新增node
			nodeDao.addNewNode(newnodename);
			map.put("state", "success");
			sendJson(map,resp);
		}
		
		
		
	}
}
