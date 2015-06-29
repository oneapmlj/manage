package com.oneapm.dto.tag;
public enum From {
    
    DEFIND(0, "请选择"),
    WU(1, "无");
    
    private Integer id;
    private String name;
    private From(Integer id, String name){
        setId(id);
        setName(name);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public static From findById(Integer id){
        for(From loudou : From.values()){
            if(loudou.getId().equals(id)){
                return loudou;
            }
        }
        return null;
    }
    public static From findByName(String name){
        for(From loudou : From.values()){
            if(loudou.getName().equals(name)){
                return loudou;
            }
        }
        return null;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}

