package com.oneapm.dto;

public class Aplication {

    private Long appId;
    
    private int language;
    
    private Long userId;
    
    private String dataTime;
    
    private String lang;
    
    public Aplication(Long appId, int language, Long userId, String dataTime, String lang){
        setAppId(appId);
        setLanguage(language);
        setUserId(userId);
        setDataTime(dataTime);
        setLang(lang);
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
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
    
}
