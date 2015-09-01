package com.oneapm.dto.tag;

public enum Language {

    DEFIND(0, "语言"),
    JAVA(1, "java"),
    PHP(2, "php"),
    NODEJS(3, "nodejs"),
    PYTHON(4, "python"),
    DOTNET(5, "dotnet"),
    RUBY(6, "ruby"),
    ANDROID(7, "Android"),
    IOS(8, "iOS"),
    BROWSER(9, "browser"),
    SERVER(10, "server"),
    CI(11, "ci"),
    PLUGIN(12, "plugin");
    private Integer id;
    private String name;
    private Language(Integer id, String name){
        setId(id);
        setName(name);
    }
    
    public static String getName(Integer id){
        for(Language language : Language.values()){
            if(language.getId().equals(id)){
                return language.getName();
            }
        }
        return null;
    }
    
    public static int getId(String name){
        for(Language language : Language.values()){
            if(language.getName().equals(name)){
                return language.getId();
            }
        }
        return 0;
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
