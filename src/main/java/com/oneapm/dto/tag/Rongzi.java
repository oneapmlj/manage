package com.oneapm.dto.tag;

public enum Rongzi {

        DEFIND(0, "未定义"), GRADE1(1, "天使"), GRADE2(2, "A轮"), GRADE3(3, "B轮"), GRADE4(4, "C轮"), GRADE5(5, "D轮"), GRADE8(8, "D轮以上"), GRADE6(6, "传统"), GRADE7(7, "其他"), GRADE9(9, "未融资");
        private Integer id;
        private String name;

        private Rongzi(Integer id, String name) {
                setId(id);
                setName(name);
        }

        public static String getName(Integer id) {
                for (Rongzi person : Rongzi.values()) {
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
