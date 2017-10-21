package com.ma.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.ma.dao.UserDao;
import com.ma.entity.User;
import com.ma.utils.Config;

public class LoginService {
	UserDao userDao = new UserDao();
	
	public User findUser(String username, String password) {
		
		User user = userDao.findByUsername(username);
		String salt = Config.get("salt");
		
		password = DigestUtils.md5Hex(password+salt);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}else {
			return null;
		}
	}
	
}
