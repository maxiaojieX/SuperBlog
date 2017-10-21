package com.ma.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ma.servlet.BaseServlet;

@WebServlet("/avatar")
public class Avatar extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		File file = new File("F:/superblog/headpic",name);
		
		if(!file.exists()) {
			//如果该文件不存在
			
		}else {
			
			InputStream in = new FileInputStream(file);
			OutputStream out = resp.getOutputStream();
			
			IOUtils.copy(in, out);
			out.flush();
			out.close();
			in.close();
		}
		
	}

}
