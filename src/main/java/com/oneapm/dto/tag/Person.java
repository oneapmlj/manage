package com.oneapm.dto.tag;

public enum Person {

    DEFIND(0, "请选择"),
    GRADE1(1, "<49"),
    GRADE2(2, "50-99"),
    GRADE3(3, "100-199"),
    GRADE4(4, "200-499"),
    GRADE5(5, "500-999"),
    GRADE6(6, "1000-1999"),
    GRADE7(7, ">2000");
    private Integer id;
    private String name;
    private Person(Integer id, String name){
        setId(id);
        setName(name);
    }
    
    public static String getName(Integer id){
        for(Person person : Person.values()){
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
