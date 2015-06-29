package com.oneapm.dto;
/**
 * User 用户信息
 * @author abc_lj
 *
 */
public class User {

	private Long userId;
	
	private String name;
	
	private String email;
	
	private String company;
	
	private String phone;
	
	private String loginTime;
	
	private String createTime;
	
	private Long appId;
	
	private Long downloadId;
	
	private Download download;
	
	private App app;
	
	private String mailTime;
	
	private String callTime;
	public User(){
		
	}
	
	public User(Long userId, String name, String email, String company, String loginTime, String createTime){
		setUserId(userId);
		setName(name);
		setEmail(email);
		setCompany(company);
		setCreateTime(createTime);
		setLoginTime(loginTime);
		setApp(new App());
		setDownload(new Download());
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Download getDownload() {
		return download;
	}

	public void setDownload(Download download) {
		this.download = download;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public String getMailTime() {
		return mailTime;
	}

	public void setMailTime(String mailTime) {
		this.mailTime = mailTime;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }
}
