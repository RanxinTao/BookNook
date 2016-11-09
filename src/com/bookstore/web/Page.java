package com.bookstore.web;

import java.util.List;

public class Page<T> {
	
	private int pageNo; //current page number
	private List<T> list; //list of current page
	private int pageSize = 3; //number of records displayed in the current page
	private long totalItemNo; //total number of records
	
	public Page(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageNo() {
		if(pageNo < 0)
			pageNo = 1;
		if(pageNo > getTotalPageNo())
			pageNo = getTotalPageNo();
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public int getTotalPageNo() {
		int totalPageNo = (int)totalItemNo / pageSize;
		if(totalItemNo % pageSize != 0)
			totalPageNo++;
		return totalPageNo;
	}
	
	public void setTotalItemNo(long totalItemNo) {
		this.totalItemNo = totalItemNo;
	}
	
	public boolean hasNextPage() {
		if(getPageNo() < getTotalPageNo())
			return true;
		return false;
	}
	
	public boolean hasPrevPage() {
		if(getPageNo() > 1)
			return true;
		return false;
	}
	
	public int getPrevPageNo() {
		if(hasPrevPage())
			return getPageNo() - 1;
		return getPageNo();
	}
	
	public int getNextPageNo() {
		if(hasNextPage())
			return getPageNo() + 1;
		return getPageNo();
	}
}

