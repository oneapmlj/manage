package com.oneapm.dto.tag;
public enum Loudou {
    
//    BEGIN(0, "进度", ""),
//    GOUTONG(1, "沟通", ""),
//    JIAOLIU(2, "交流", ""),
//    CESHI(3, "测试", ""),
//    SHENGCHAN(4, "生产", ""),
//    CAIGOU(5, "采购", ""),
//    FINISH(6, "完成", "");
    BEGIN(0, "新分配", ""),
    JIAOLIU(1, "交流", ""),
    CESHI(2, "测试", ""),
    CAIGOU(3, "商务", ""),
    FINISH_SUCCESS(4, "完成/成单", ""),
    FINISH_FAIL(5, "完成/输单", "");
    
    private Integer id;
    private String name;
    private String description;
    private Loudou(Integer id, String name, String description){
        setId(id);
        setName(name);
        setDescription(description);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public static Loudou findById(Integer id){
        for(Loudou loudou : Loudou.values()){
            if(loudou.getId().equals(id)){
                return loudou;
            }
        }
        return null;
    }
    public static Loudou findByName(String name){
        for(Loudou loudou : Loudou.values()){
            if(loudou.getName().equals(name)){
                return loudou;
            }
        }
        return null;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}

