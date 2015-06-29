package com.oneapm.web;

public interface ErrorMsg {

	public static final String SERVERERROR ="抱歉!服务器正忙，请稍候再试。";
	public static final String SESSIONOUT = "您请求的页面或操作需要登录，请先登录。";
	public static final String ONLINEERROR ="当前操作需要处于登出状态，请退出后再尝试。";
	public static final String Lack_DATA = "系统错误！初始化数据出错。";
}
