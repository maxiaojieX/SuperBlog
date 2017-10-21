package com.ma.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	static Properties prop = new Properties();
	static {
		InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	public static String get(String key) {
		return prop.getProperty(key);
	}

}
