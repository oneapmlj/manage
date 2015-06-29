package com.oneapm.record;

public class Record {

    private Long id;
    private Long adminId;
    private Long infoId;
    private int type;
    private String createTime;
    private Long fromId;
    private int status;
    private Integer metric;
    private Integer loudou;
    private Integer lou;
    private Integer me;
    
    public Record(){};
    public Record(Long id, Long adminId, Long infoId, int type, String createTime,Long fromId,
            int status, Integer metric, Integer loudou, int lou, int me){
        setId(id);
        setAdminId(adminId);
        setInfoId(infoId);
        setType(type);
        setCreateTime(createTime);
        setFromId(fromId);
        setStatus(status);
        setMetric(metric);
        setLoudou(loudou);
        setMe(me);
        setLou(lou);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Long getFromId() {
        return fromId;
    }
    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Integer getMetric() {
        return metric;
    }
    public void setMetric(Integer metric) {
        this.metric = metric;
    }
    public Integer getLoudou() {
        return loudou;
    }
    public void setLoudou(Integer loudou) {
        this.loudou = loudou;
    }
    public Integer getLou() {
        return lou;
    }
    public void setLou(Integer lou) {
        this.lou = lou;
    }
    public Integer getMe() {
        return me;
    }
    public void setMe(Integer me) {
        this.me = me;
    }
    
}
