package com.oneapm.dto.info;

public class Active {

    private Long id;
    private Long appId;
    private Long userId;
    private int type;//1服务端，2移动端
    private String dataTime;
    private Long active;
    
    public Active(Long id, Long appId, Long userId, int type, String dataTime, Long active){
        setId(id);
        setAppId(appId);
        setUserId(userId);
        setType(type);
        setDataTime(dataTime);
        setActive(active);
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getDataTime() {
        return dataTime;
    }
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
