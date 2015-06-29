package com.oneapm.dto.card;

public class CardRecord {

    private Long id;
    
    private Long cardId;
    
    private Long adminId;
    
    private int changeType;
    
    private String createTime;
    
    private String mark;
    
    private String adminName;
    
    private String changeName;
    
    public CardRecord(){};
    public CardRecord(Long id,Long cardId, Long adminId, int changeType, String createTime, String mark){
        setCardId(cardId);
        setAdminId(adminId);
        setChangeType(changeType);
        setCreateTime(createTime);
        setMark(mark);
        setId(id);
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
