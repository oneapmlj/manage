package com.oneapm.record;

public class PushDeal {

    private Long id;
    
    private Long pushId;
    
    private Long adminId;
    
    private int type;//1.关闭2.推迟3.转交4.处理5.邮件,6分配
    
    private String createTime;

    private Long passId;
    
    private int mode;
    
    private Long callId;
    
    private String putTime;
    
    public PushDeal(Long id, Long pushId, Long adminId, int type, String createTime, Long passId, int mode, Long callId, String putTime){
        setId(id);
        setPushId(pushId);
        setAdminId(adminId);
        setType(type);
        setCreateTime(createTime);
        setPassId(passId);
        setMode(mode);
        setCallId(callId);
        setPutTime(putTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPushId() {
        return pushId;
    }

    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getPassId() {
        return passId;
    }

    public void setPassId(Long passId) {
        this.passId = passId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getPutTime() {
        return putTime;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }
}
