package com.oneapm.dto;

public class Aplication {

    private Long appId;
    
    private int agent;
    
    private Long userId;
    
    private String dataTime;
    
    private String lang;
    
    private Long agentId;
    
    private int total;
    
    private int agentNumber;
    
    public Aplication(Long appId, int agent, Long userId, String dataTime, String lang, Long agentId, int total, int agentNumber){
        setAppId(appId);
        setAgent(agent);
        setUserId(userId);
        setDataTime(dataTime);
        setLang(lang);
        setAgentId(agentId);
        setTotal(total);
        setAgentNumber(agentNumber);
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

public Long getAgentId() {
        return agentId;
}

public void setAgentId(Long agentId) {
        this.agentId = agentId;
}

public int getAgent() {
        return agent;
}

public void setAgent(int agent) {
        this.agent = agent;
}

public int getTotal() {
        return total;
}

public void setTotal(int total) {
        this.total = total;
}

public int getAgentNumber() {
        return agentNumber;
}

public void setAgentNumber(int agentNumber) {
        this.agentNumber = agentNumber;
}
    
}
