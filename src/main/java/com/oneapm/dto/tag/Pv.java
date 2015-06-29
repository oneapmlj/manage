package com.oneapm.dto.tag;

public enum Pv {

    DEFIND(0, "请选择"),
    GRADE1(1, "1W级"),
    GRADE2(2, "10W级"),
    GRADE3(3, "100W级"),
    GRADE4(4, "1000W级"),
    GRADE5(5, ">Y级");
    private Integer id;
    private String name;
    private Pv(Integer id, String name){
        setId(id);
        setName(name);
    }
    
    public static String getName(Integer id){
        for(Pv person : Pv.values()){
            if(person.getId().equals(id)){
                return person.getName();
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
}
