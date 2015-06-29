package com.oneapm.dto.tag;

public enum Province {

    DEFIND(0, "请选择"),
    BEIJING(1, "北京"),
    SHANGHAI(2, "上海"),
    JIANGSU(3, "江苏"),
    GUANGDONG(4, "广东"),
    SICHUAN(5, "四川"),
    HUBEI(6, "湖北"),
    ZHEJIANG(7, "浙江"),
    HEBEI(8, "河北"),
    SHANXI(9, "山西"),
    LIAONING(10, "辽宁"),
    JILING(11, "吉林"),
    HEILONGJIANG(12, "黑龙江"),
    ANHUI(13, "安徽"),
    FUJIAN(14, "福建"),
    JIANGXI(15, "江西"),
    SHANDONG(16, "山东"),
    HENAN(17, "河南"),
    HUNAN(18, "湖南"),
    HAINAN(19, "海南"),
    GUIZHOU(20, "贵州"),
    YUNNAN(21, "云南"),
    SHANXI2(22, "陕西"),
    GANSU(23, "甘肃"),
    QINGHAI(24, "青海"),
    TAIWAN(25, "台湾"),
    TIANJING(26, "天津"),
    CHONGQING(27, "重庆"),
    GUANGXI(28, "广西"),
    NEIMENG(29, "内蒙"),
    XIZANG(30, "西藏"),
    NINGXIA(31, "宁夏"),
    XINJIANG(32, "新疆"),
    XIANGGANG(33, "香港"),
    AOMEN(34, "澳门");
    private Integer id;
    private String name;
    private Province(Integer id, String name){
        setId(id);
        setName(name);
    }
    
    public static String getName(Integer id){
        for(Province person : Province.values()){
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
