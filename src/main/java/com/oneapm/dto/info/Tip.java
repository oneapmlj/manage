package com.oneapm.dto.info;

public class Tip {

    private Long id;
    private Long from;
    private String fromName;
    private Long to;
    private String toName;
    private String dataTime;
    private Long infoId;
    
    public Tip(Long id, Long from, Long to, String dataTime, Long infoId){
        setFrom(from);
        setTo(to);
        setDataTime(dataTime);
        setId(id);
        setInfoId(infoId);
    }
    
    public Long getFrom() {
        return from;
    }
    public void setFrom(Long from) {
        this.from = from;
    }
    public String getFromName() {
        return fromName;
    }
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public Long getTo() {
        return to;
    }
    public void setTo(Long to) {
        this.to = to;
    }
    public String getToName() {
        return toName;
    }
    public void setToName(String toName) {
        this.toName = toName;
    }
    public String getDataTime() {
        return dataTime;
    }
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
}
