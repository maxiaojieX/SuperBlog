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

@WebServlet("/admin/filedownload")
public class FileDownload extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("come in filedownload");
		req.setCharacterEncoding("UTF-8");
		String filename = req.getParameter("filename");
		
		File file = new File("F:/superblog/filemanager",filename);
		
		if(file.exists()) {
			resp.setContentType("application/octet-stream");
			//设置文件大小
			//resp.setContentLengthLong(file.length());
			//将name转为ISO 
			filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");
			//设置文件名
			resp.addHeader("Content-Disposition", "attachment;fileName=\""+filename+"\"");
			
			InputStream in = new FileInputStream(file);
			OutputStream out = resp.getOutputStream();
			
			IOUtils.copy(in, out);
			out.flush();
			out.close();
			in.close();
			
			
		}
		
		
	}

}
