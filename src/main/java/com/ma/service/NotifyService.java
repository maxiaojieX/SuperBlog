package com.ma.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.NotifyDao;
import com.ma.entity.Notify;
import com.ma.utils.Page;

public class NotifyService {

	NotifyDao notifyDao = new NotifyDao();
	
	public Page<Notify> findCountAll(String p) {
		int count = notifyDao.findCountAll();
		Page<Notify> page = new Page<>(count,Integer.parseInt(p));
		
		//查询所有t_notify
		int currentp = ((Integer.parseInt(p)-1)*3);
		List<Notify> notifylist = notifyDao.findAll(currentp);
		page.setItem((ArrayList<Notify>) notifylist);
		return page;
	}

	/**
	 * 根据ids把state设置为 1：已读
	 * @param ids
	 */
	public void updateRead(String ids) {
		if(StringUtils.isNotEmpty(ids)) {
			String[] notifyids = ids.split(",");
			for(String s : notifyids) {
				notifyDao.update(Integer.parseInt(s));
			}
		}
	}
	

}
