package com.oneapm.dto.file;

public class FileDto {

    private Long id;
    
    private String name;
    
    private String code;
    
    private int grade;
    
    private int number;
    
    private int type;//类型：1目录，2文件
    
    private String ext;//后缀
    
    private Long father;
    
    private String createTime;
    
    private String downloadTime;
    
    private String viewTime;
    
    private int status;
    
    private String password;//密码大于等于4位有效
    
    private Long adminId;
    
    public FileDto(Long id, String name, String code, int grade, int type, String ext, Long father, String createTime,
            String downloadTime, String viewTime, int status, String password, Long adminId){
        setId(id);
        setName(name);
        setCode(code);
        setGrade(grade);
        setType(type);
        setExt(ext);
        setFather(father);
        setCreateTime(createTime);
        setDownloadTime(downloadTime);
        setViewTime(viewTime);
        setStatus(status);
        setPassword(password);
        setAdminId(adminId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getFather() {
        return father;
    }

    public void setFather(Long father) {
        this.father = father;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
