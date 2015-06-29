package com.oneapm.vo;

import com.oneapm.record.Record;

public class RecordVo {

    private Record record;
    
    private String adminName;
    
    private String fromName;
    
    private String company;
    
    public RecordVo(){}
    
    public RecordVo(Record record, String adminName, String fromName, String company){
        setRecord(record);
        setAdminName(adminName);
        setFromName(fromName);
        setCompany(company);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    
}
