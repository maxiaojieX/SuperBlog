package com.ma.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
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

@WebServlet("/admin/picupload")
@MultipartConfig
public class PicUpload extends BaseServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Part part = req.getPart("file_key");
		String header = part.getHeader("content-Disposition");
		
		//拿到文件名
		String fileName = header.split(";")[2].split("\"")[1];
		
		
		String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		
		File file = new File("F:/superblog/headpic",newName);
		
		InputStream in = part.getInputStream();
		OutputStream out = new FileOutputStream(file);
		
		IOUtils.copy(in, out);
		out.flush();
		out.close();
		in.close();
		
		Map<String,Object> maps = new HashMap<>();
		
		maps.put("success", true);
		maps.put("file_path", "/avatar?name=" + newName);
		
		sendJson(maps, resp);
		
//		File file = new File("F:/superblog/headpic");
//		
//		req.setCharacterEncoding("UTF-8");
//		Part part = req.getPart("upload_file");
//		System.out.println(part);
//		
		
		//OK
		//String fileName = req.getParameter("name");
		//System.out.println(fileName);
		
		//String newName = fileName.substring(0,fileName.lastIndexOf("."))+ UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));;
		//InputStream in = part.getInputStream();
		//FileOutputStream out = new FileOutputStream(new File(file,newName));
		
		//IOUtils.copy(in, out);
		//out.flush();
		//out.close();
		//in.close();
		
		
	}
}
