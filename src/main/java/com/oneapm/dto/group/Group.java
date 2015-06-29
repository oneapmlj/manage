package com.oneapm.dto.group;


public class Group {

        private Long id;
        private Long father;
        private boolean child;
        private int type;
        private int total;
        private int grade;
        private String name;
        private String description;
        
        public Group(Long id, Long father, boolean child, int type, int total, int grade, String name, String description){
                setId(id);
                setFather(father);
                setChild(child);
                setType(type);
                setTotal(total);
                setGrade(grade);
                setName(name);
                setDescription(description);
        }
        
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public Long getFather() {
                return father;
        }
        public void setFather(Long father) {
                this.father = father;
        }
        public boolean isChild() {
                return child;
        }
        public void setChild(boolean child) {
                this.child = child;
        }
        public int getType() {
                return type;
        }
        public void setType(int type) {
                this.type = type;
        }
        public int getTotal() {
                return total;
        }
        public void setTotal(int total) {
                this.total = total;
        }
        public int getGrade() {
                return grade;
        }
        public void setGrade(int grade) {
                this.grade = grade;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }
        
}
