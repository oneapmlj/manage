package com.oneapm.vo;

public class Quanxian {
        private int id;

        private String quanxian;

        private String name;

        private int status;

        private int all;

        public Quanxian(int id, String quanxian, String name, int all) {
                setId(id);
                setQuanxian(quanxian);
                setName(name);
                setAll(all);
                setStatus(0);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getQuanxian() {
                return quanxian;
        }

        public void setQuanxian(String quanxian) {
                this.quanxian = quanxian;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public int getAll() {
                return all;
        }

        public void setAll(int all) {
                this.all = all;
        }
}
