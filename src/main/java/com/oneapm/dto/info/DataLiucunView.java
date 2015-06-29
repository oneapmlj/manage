package com.oneapm.dto.info;

import java.util.List;

public class DataLiucunView {

    private List<Info> infos;
    private int length;
    private int percent;
    private int count;
    private String dataTimeStart;
    private String dataTimeEnd;
    private String inTimeStart;
    private String inTimeEnd;
    private int type;
    
    public List<Info> getInfos() {
        return infos;
    }
    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getPercent() {
        return percent;
    }
    public void setPercent(int percent) {
        this.percent = percent;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getDataTimeStart() {
        return dataTimeStart;
    }
    public void setDataTimeStart(String dataTimeStart) {
        this.dataTimeStart = dataTimeStart;
    }
    public String getDataTimeEnd() {
        return dataTimeEnd;
    }
    public void setDataTimeEnd(String dataTimeEnd) {
        this.dataTimeEnd = dataTimeEnd;
    }
    public String getInTimeStart() {
        return inTimeStart;
    }
    public void setInTimeStart(String inTimeStart) {
        this.inTimeStart = inTimeStart;
    }
    public String getInTimeEnd() {
        return inTimeEnd;
    }
    public void setInTimeEnd(String inTimeEnd) {
        this.inTimeEnd = inTimeEnd;
    }
public int getType() {
        return type;
}
public void setType(int type) {
        this.type = type;
}
    
}
