package com.ma.datesource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.ma.utils.Config;

public class DataSource {
	private static final String DRIVER;
	private static final String URL;
	private static final String NAME;
	private static final String PASSWORD;
	private static final BasicDataSource dataSource = new BasicDataSource();
	
	static{
		DRIVER = Config.get("DRIVER");
		URL = Config.get("URL");
		NAME = Config.get("NAME");
		PASSWORD = Config.get("PASSWORD");
		
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(NAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(50);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5000);
	}
	public static  BasicDataSource getDataSource(){
		return dataSource;
	}

}
