package com.oneapm.dto.tag;

public enum Category {

        DEFIND(0, "请选择"), YUNYING(1, "运营商"), ZHENGFU(2, "政府"), DIANLI(3, "电力"), JINRONG(4, "金融"), DIANSHANG(5, "电商"), TUANGOU(6, "团购"),
        JIAOYU(7, "教育"), CANYIN(8, "餐饮"), LVYOU(9, "旅游"), PIAOWU(10, "票务"), YILIAO(11, "医疗"), WULIU(14, "物流"), SHEJIAO(16, "社交"),ZIMEITI(15, "自媒体"),
        QITA_INT(12, "其他互联网"), QITA_COM(13, "其他企业"), YOUXI(17, "游戏"), QITA_IT(18, "传统IT"), QICHE(19, "汽车"),O2O(20, "O2O");

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
