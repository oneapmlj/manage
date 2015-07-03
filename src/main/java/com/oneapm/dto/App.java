package com.oneapm.dto;


public class App {

	private Long userId;
	
	private String appName;
	
	private String language;
	
	private String createTime;
	
	private Long appId;
	
	private String dataTime;
	
	private Long accountId;
	
	private String version;
	
	private Long active;
	
	private int agent;
	
	private Long agentId;
	
	
	public App(Long userId, String createTime, String language, Long appId, String appName, String dataTime, int agent, Long agentId){
		setAppId(appId);
		setUserId(userId);
		setCreateTime(createTime);
		setLanguage(language);
		setAppName(appName);
		setDataTime(dataTime);
		setAgent(agent);
		setAgentId(agentId);
	}

	public Long getUserId() {
		return userId;
	}
	
	public App(){
	    
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

public int getAgent() {
        return agent;
}

public void setAgent(int agent) {
        this.agent = agent;
}

public Long getAgentId() {
        return agentId;
}

public void setAgentId(Long agentId) {
        this.agentId = agentId;
}

}
