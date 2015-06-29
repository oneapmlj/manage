package com.oneapm.dto.info;

public class Dashboard {

        private Long id;
        private int sign;
        private int download;
        private int dowloadNew;
        private int app;
        private int appNew;
        private int data;
        private String dataTime;
        private int uv;
        private int newUv;
        
        public Dashboard(Long id, int sign, int download, int downloadNew, int app, int appNew, int data, String dataTime, int uv, int newUv){
                setId(id);
                setSign(sign);
                setDowloadNew(downloadNew);
                setDownload(download);
                setApp(app);
                setAppNew(appNew);
                setData(data);
                setDataTime(dataTime);
                setUv(uv);
                setNewUv(newUv);
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public int getSign() {
                return sign;
        }
        public void setSign(int sign) {
                this.sign = sign;
        }
        public int getDownload() {
                return download;
        }
        public void setDownload(int download) {
                this.download = download;
        }
        public int getDowloadNew() {
                return dowloadNew;
        }
        public void setDowloadNew(int dowloadNew) {
                this.dowloadNew = dowloadNew;
        }
        public int getApp() {
                return app;
        }
        public void setApp(int app) {
                this.app = app;
        }
        public int getAppNew() {
                return appNew;
        }
        public void setAppNew(int appNew) {
                this.appNew = appNew;
        }
        public int getData() {
                return data;
        }
        public void setData(int data) {
                this.data = data;
        }
        public String getDataTime() {
                return dataTime;
        }
        public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
        }
        public int getUv() {
                return uv;
        }
        public void setUv(int uv) {
                this.uv = uv;
        }
        public int getNewUv() {
                return newUv;
        }
        public void setNewUv(int newUv) {
                this.newUv = newUv;
        }
        
}
