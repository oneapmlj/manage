package com.oneapm.dto.tag;

public enum Category {

//        DEFIND(0, "请选择"), YUNYING(1, "运营商"), ZHENGFU(2, "政府"), DIANLI(3, "电力"), JINRONG(4, "金融"), DIANSHANG(5, "电商"), TUANGOU(6, "团购"),
//        JIAOYU(7, "教育"), CANYIN(8, "餐饮"), LVYOU(9, "旅游"), PIAOWU(10, "票务"), YILIAO(11, "医疗"), WULIU(14, "物流"), SHEJIAO(16, "社交"),ZIMEITI(15, "自媒体"),
//        QITA_INT(12, "其他互联网"), QITA_COM(13, "其他企业"), YOUXI(17, "游戏"), QITA_IT(18, "传统IT"), QICHE(19, "汽车"),O2O(20, "O2O");
        
        DEFIND(0, "请选择"),DIANZISHANGWU(1,"电子商务"),SHEJIAO(2,"社交"),GUANGGAO(3,"广告"),YOUXIDONGMAN(4,"游戏动漫"),YINGJIANFUWU(5, "硬件服务"),
        MEITI(6,"媒体"),GONGJURUANJIAN(7,"工具软件"),SHENGHUOFUWU(8,"生活服务"),JINRONG(9,"金融"),YILIAOJIANKANG(10,"医疗健康"),QIYEFUWU(11, "企业服务"),
        LVYOUHUWAI(12, "旅游户外"),FANGCHANJIAJU(13, "房产家居"),WENHUAYULE(14, "文化娱乐"),JIAOYUPEIXUN(15, "教育培训"),QICHEJIAOTONG(16,"汽车交通"),
        WULIU(17, "物流"),ZHENGFUGUOQI(18, "政府国企"),WAIBAO(19,"外包"),ZHICHANG(20,"职场"),QITA(21, "其他");
        private Integer id;

        private String name;

        private Category(Integer id, String name) {
                setName(name);
                setId(id);
        }

        public static String getName(Integer id) {
                for (Category category : Category.values()) {
                        if (id.equals(category.getId())) {
                                return category.getName();
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
