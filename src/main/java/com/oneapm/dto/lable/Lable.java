package com.oneapm.dto.lable;

public class Lable {

        private Long id;
        private Long father;
        private boolean child;
        private int grade;
        private String name;
        private String description;
        private String key;
        private String from;
        private int next;
        public Lable(Long id, Long father, boolean child, int grade, String name, String description, String key, String from){
                setId(id);
                setFather(father);
                setChild(child);
                setGrade(grade);
                setName(name);
                setDescription(description);
                setFrom(from);
                setKey(key);
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
        public String getKey() {
                return key;
        }
        public void setKey(String key) {
                this.key = key;
        }
        public String getFrom() {
                return from;
        }
        public void setFrom(String from) {
                this.from = from;
        }
        public int getNext() {
                return next;
        }
        public void setNext(int next) {
                this.next = next;
        }
        
}
