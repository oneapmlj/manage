package com.oneapm.dto;

public class Gongdan {

    private Long id;                //工单id
    private String title;           //标题
    private String email;           //请求者email
    private String dueTime;         //截至日期
    private int status;             //状态
    private Long assigneeId;        //受理者
    private String createTime;      //创建时间
    private String assigneTime;     //受理时间
    private String resolvedTime;    //解决时间
    private String closedTime;      //关闭时间
    private String source;          //工单来源
    private Long userId;
    
    public Gongdan(){}
    public Gongdan(Long id, String title, String email, String dueTime, int status, 
            Long assigneeId, String createTime, String assigneTime, String resolvedTime,
            String closedTime, String source){
        setId(id);
        setTitle(title);
        setEmail(email);
        setDueTime(dueTime);
        setStatus(status);
        setAssigneeId(assigneeId);
        setCreateTime(createTime);
        setAssigneTime(assigneTime);
        setResolvedTime(resolvedTime);
        setClosedTime(closedTime);
        setSource(source);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDueTime() {
        return dueTime;
    }
    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Long getAssigneeId() {
        return assigneeId;
    }
    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getAssigneTime() {
        return assigneTime;
    }
    public void setAssigneTime(String assigneTime) {
        this.assigneTime = assigneTime;
    }
    public String getResolvedTime() {
        return resolvedTime;
    }
    public void setResolvedTime(String resolvedTime) {
        this.resolvedTime = resolvedTime;
    }
    public String getClosedTime() {
        return closedTime;
    }
    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
