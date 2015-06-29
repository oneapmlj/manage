package com.oneapm.dto.tag;

public enum Uv {

    DEFIND(0, "请选择"),
    GRADE1(1, "100"),
    GRADE2(2, "1000"),
    GRADE3(3, "1W"),
    GRADE4(4, "10W"),
    GRADE5(5, "100W"),
    GRADE6(6, ">1000W"),
    GRADE7(7, ">1Y");
    private Integer id;
    private String name;
    private Uv(Integer id, String name){
        setId(id);
        setName(name);
    }
    
    public static String getName(Integer id){
        for(Uv person : Uv.values()){
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
