package com.ma.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.ma.sendmail.SendMail;

public class OracleTestCase {
	
	public void oracleTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","ma","123456");
			
			String sql = "select * from t_user";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet re = pst.executeQuery();
			
			while(re.next()) {
				System.out.println(re.getString("name"));
			}
			re.close();
			pst.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void mailTest() {
		SendMail.send("15239131507@163.com", "ma000000", "1103173210@qq.com", "测试", "我是m");
	}

}
