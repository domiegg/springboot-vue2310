package com.film.util;

public class Page {
	private int index=0;				//序号
	private int pageSize=10;			//每页数目
	private int totalCount;				//总共数目
	private int pageNum;				//当前页码
	
	public void setPageNum(int pageNum){
		this.index=(pageNum-1)*pageSize;
		this.pageNum=pageNum;
	}

	
	public int getIndex() {
		return index;
	}

	/**
	 * 获取总共有多少页
	 */
	public int getPageCount() {
		if(totalCount%pageSize==0) {
			return totalCount/pageSize;
		}else {
			return totalCount/pageSize+1;
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.index=(this.pageNum-1)*pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
