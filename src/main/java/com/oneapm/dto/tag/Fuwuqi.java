package com.oneapm.dto.tag;

public enum Fuwuqi {

        DEFIND(0, "未选择"), GRADE1(1, "未知"), GRADE2(2, "自建机房"), GRADE3(3, "阿里云"), GRADE4(4, "腾讯云"), GRADE5(5, "亚马逊"), GRADE6(6, "新浪云"),
        GRADE7(7, "青云(qingcloud)"), GRADE8(8, "ucloud"), GRADE9(9, "金山云"), GRADE10(10, "百度云平台"), GRADE11(11, "京东云平台"), GRADE12(12, "美团云平台"), GRADE14(14, "coding云"),GRADE13(13, "其他云平台");
        private Integer id;
        private String name;

        private Fuwuqi(Integer id, String name) {
                setId(id);
                setName(name);
        }

        public static String getName(Integer id) {
                for (Fuwuqi person : Fuwuqi.values()) {
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
