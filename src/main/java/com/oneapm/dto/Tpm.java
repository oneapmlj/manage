package com.oneapm.dto;

public class Tpm {

    private Long appId;
    private Long userId;
    
    public Tpm(){
        
    }
    public Tpm(Long appId){
        setAppId(appId);
    }
    public Tpm(Long app_id, Long userId){
        setAppId(app_id);
        setUserId(userId);
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
}
