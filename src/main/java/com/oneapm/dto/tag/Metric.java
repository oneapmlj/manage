package com.oneapm.dto.tag;

public enum Metric {
//    DEFIND(0, "定位", ""),
//    POINT(1, "重点", ""),
//    COMMON(3, "普通", ""),
//    UNBIN(2, "未定义", ""),
//    UNUSE(4, "关闭", "");
    DEFIND(0, "定位", ""),
    POINT(1, "企业", ""),
    COMMON(3, "开发者", ""),
    UNBIN(2, "未定义", "")
   ,UNUSE(4, "关闭", "")
    ;
    //TODO
    //黑名单
    private Integer id;
    private String name;
    private String description;
    
    private Metric(Integer id, String name, String description){
        setId(id);
        setName(name);
        setDescription(description);
    }

    public static Metric findById(Integer id){
        for(Metric metric : Metric.values()){
            if(metric.id.equals(id)){
                return metric;
            }
        }
        return null;
    }
    
    public static Metric findById(String name){
        for(Metric metric : Metric.values()){
            if(metric.name.equals(name)){
                return metric;
            }
        }
        return null;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
