package com.oneapm.dto.card;

public class Card {
    
    private Long id;
    
    private String name;
    
    private String branch;
    
    private int gender;
    
    private String position;
    
    private String phone;
    
    private String email;
    
    private String createTime;
    
    private String company;
    
    private Long from;
    
    private String fromName;
    
    private Long infoId;
    
    private String changeTime;
    
    private String mark;
    
    private String qq;
    
    private Long groupId;
    
    public Card(Long id, String name, String branch, String position, String phone, String email,
            String createTime, Long from, Long infoId, String changeTime,
            int gender, String qq){
        setId(id);
        setName(name);
        setBranch(branch);
        setPosition(position);
        setPhone(phone);
        setEmail(email);
        setCreateTime(createTime);
        setCompany(company);
        setFrom(from);
        setInfoId(infoId);
        setChangeTime(changeTime);
        setGender(gender);
        setQq(qq);
    }
    public Card(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
    public String getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getFromName() {
        return fromName;
    }
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
    
    
}
