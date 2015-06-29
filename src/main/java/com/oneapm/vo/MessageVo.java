package com.oneapm.vo;

import com.oneapm.record.Message;

public class MessageVo {

    private Message message;
    
    private String fromName;
    
    private String toName;
    
    private String company;
    
    private Long infoId;
    
    public MessageVo(){}
    
    public MessageVo(Message message, String fromName, String toName, String company){
        setMessage(message);
        setFromName(fromName);
        setToName(toName);
        setCompany(company);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

}
