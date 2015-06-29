package com.oneapm.dto.file;

public class Wendang {

    private Long id;
    
    private String code;
    
    private int status;
    
    private String createTime;
    
    private String name;
    
    public Wendang(Long id, String code, int status, String createTime, String name){
        setId(id);
        setCode(code);
        setStatus(status);
        setCreateTime(createTime);
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
