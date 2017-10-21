package com.ma.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.ma.servlet.BaseServlet;

@WebServlet("/admin/fileupload")
@MultipartConfig
public class FileUpload extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		File file = new File("F:/superblog/filemanager");
		List<String> filenamelist = new ArrayList<>();
		if(file.exists()) {
			String[] files = file.list();
			for(String s : files) {
				filenamelist.add(s);
			}
		}
		req.setAttribute("filenamelist", filenamelist);
		resp.setCharacterEncoding("UTF-8");
		forward("admin/filemanager", req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("come in fileupload");
		File file = new File("F:/superblog/filemanager");
		
		req.setCharacterEncoding("UTF-8");
		Part part = req.getPart("file");
		
		//OK
		String fileName = req.getParameter("name");
		
		String newName = fileName.substring(0,fileName.lastIndexOf("."))+ UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));;
		
		InputStream in = part.getInputStream();
		FileOutputStream out = new FileOutputStream(new File(file,newName));
		
		IOUtils.copy(in, out);
		out.flush();
		out.close();
		in.close();
		
		Map<String,Object> maps = new HashMap<>();
		maps.put("state", "success");
		sendJson(maps, resp);
	}
}
