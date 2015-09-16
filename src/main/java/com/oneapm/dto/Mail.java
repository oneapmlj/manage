package com.oneapm.dto;

public class Mail {
    private Long id;
    private Long infoId;
    private String sendTime;
    private int mailMode;
    private Long adminId;
    private String adminName;
    private String modeName;
    private String companyName;
    private String mailContent;
    private Long groupId;
    public Mail(){}
    public Mail(Long id, Long infoId, String sendTime, int mailMode, Long adminId, String mailContent){
        setSendTime(sendTime);
        setId(id);
        setMailMode(mailMode);
        setAdminId(adminId);
        setInfoId(infoId);
        setMailContent(mailContent);
    }
    public String getSendTime() {
        return sendTime;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    public int getMailMode() {
        return mailMode;
    }
    public void setMailMode(int mailMode) {
        this.mailMode = mailMode;
    }
    public Long getAdminId() {
        return adminId;
    }
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    public Long getInfoId() {
        return infoId;
    }
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getModeName() {
        return modeName;
    }
    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
public String getMailContent() {
        return mailContent;
}
public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
}
public Long getGroupId() {
	return groupId;
}
public void setGroupId(Long groupId) {
	this.groupId = groupId;
}

}
