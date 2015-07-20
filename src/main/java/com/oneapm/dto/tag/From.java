package com.oneapm.dto.tag;
public enum From {
    
    DEFIND(0, "不确定"),
    SOUSUO(1,"搜索"),
    PENGYOU(2,"朋友介绍"),
    ZHANHUI(3,"展会"),
    MEITI(4,"媒体"),
    QQ(5,"QQ"),
    WEIBO(6,"微博"),
    WEIXIN(7,"微信"),
    YOUJIAN(8,"邮件"),
    LUNTAN(9,"论坛"),
    KAIFAZHE(10,"开发者平台");
    
    private Integer id;
    private String name;
    private From(Integer id, String name){
        setId(id);
        setName(name);
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
    public static String getName(Integer id) {
            for (From person : From.values()) {
                    if (person.getId().equals(id)) {
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

