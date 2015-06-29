package com.oneapm.dto;

public class NoteType {

    private Long id;
    private Long father;
    private String name;
    private String description;
    private String createTime;
    private int status;
    private Long adminId;
    private int todu;
    
    public NoteType(Long id, Long father, String name, String createTime, int status, Long adminId, int todu){
        setId(id);
        setFather(father);
        setName(name);
        setCreateTime(createTime);
        setStatus(status);
        setAdminId(adminId);
        setTodu(todu);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFather() {
        return father;
    }
    public void setFather(Long father) {
        this.father = father;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Long getAdminId() {
        return adminId;
    }
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTodu() {
        return todu;
    }
    public void setTodu(int todu) {
        this.todu = todu;
    }
}
