package com.ma.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ma.entity.User;
import com.ma.utils.Config;
import com.ma.utils.DbHelp;

public class UserDao {
	
	public User findByUsername(String username) {
		String sql = "select * from t_user where username = ?";
		return DbHelp.query(sql, new BeanHandler<>(User.class), username);
	}

	public void add(String username, String password) {
		String salt = Config.get("salt");
		password = DigestUtils.md5Hex(password+salt);
		String sql = "insert into t_user (username,password) values (?,?)";
		DbHelp.upDate(sql, username,password);
	}

}
