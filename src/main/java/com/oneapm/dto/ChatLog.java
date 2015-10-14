package com.oneapm.dto;

public class ChatLog {
	private String nickName;
	private String chatTime;
	private String message;
	private String qq;
	
	public ChatLog(String nickName, String chatTime, String message, String qq) {
		this.nickName = nickName;
		this.chatTime = chatTime;
		this.message = message;
		this.qq = qq;
	}
	public ChatLog(){
		
	}
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
}
