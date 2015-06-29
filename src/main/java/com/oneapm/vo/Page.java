package com.oneapm.vo;

public class Page {

	private int nowPage;
	private int totalPage;
	public Page(int nowPage, int totalPage){
		setNowPage(nowPage);
		setTotalPage(totalPage);
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
