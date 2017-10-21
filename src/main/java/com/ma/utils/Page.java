package com.ma.utils;

import java.util.ArrayList;

public class Page<T> {
	private int pageSize = 3;
	//当前页
	private int currentPage;
	//总页数
	private int pageTotal;
	//待封装的数据对象
	private ArrayList<?> item;
	public ArrayList<?> getItem() {
		return item;
	}
	public void setItem(ArrayList<T> item) {
		this.item = item;
	}
	//调用时需传入总数据条数 和当前页
	public Page(int count,int currentPage){
		this.currentPage = currentPage;
		this.pageTotal = count / this.pageSize;
		if((count % this.pageSize) != 0){
			this.pageTotal++;
		}
		
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
}
