package com.oneapm.dto.info;

public class Dashboard {

        private Long id;
        private int sign;
        private int download;
        private int dowloadNew;
        private int app;
        private int appNew;
        private int uv;
        private int newUv;
        private int data_all;
        private int app_all;
        private int data_ai;
        private int app_ai;
        private int only_ai;
        private int data_mi;
        private int app_mi;
        private int only_mi;
        private int data_server;
        private int app_server;
        private int only_server;
        private String dataTime;
        
        public Dashboard(Long id, int sign, int download, int downloadNew, int app, int appNew, String dataTime, int uv, int newUv,
                        int data_all, int app_all, int data_ai, int app_ai, int only_ai, int data_mi, int app_mi, int only_mi, int data_server, int app_server, int only_server){
                setId(id);
                setSign(sign);
                setDowloadNew(downloadNew);
                setDownload(download);
                setApp(app);
                setAppNew(appNew);
                setDataTime(dataTime);
                setUv(uv);
                setNewUv(newUv);
                setData_all(data_all);
                setApp_all(app_all);
                setData_ai(data_ai);
                setApp_ai(app_ai);
                setOnly_ai(only_ai);
                setData_mi(data_mi);
                setApp_mi(app_mi);
                setOnly_mi(only_mi);
                setData_server(data_server);
                setApp_server(app_server);
                setOnly_server(only_server);
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
        public int getData_all() {
                return data_all;
        }
        public void setData_all(int data_all) {
                this.data_all = data_all;
        }
        public int getApp_all() {
                return app_all;
        }
        public void setApp_all(int app_all) {
                this.app_all = app_all;
        }
        public int getApp_ai() {
                return app_ai;
        }
        public void setApp_ai(int app_ai) {
                this.app_ai = app_ai;
        }
        public int getData_ai() {
                return data_ai;
        }
        public void setData_ai(int data_ai) {
                this.data_ai = data_ai;
        }
        public int getOnly_ai() {
                return only_ai;
        }
        public void setOnly_ai(int only_ai) {
                this.only_ai = only_ai;
        }
        public int getApp_mi() {
                return app_mi;
        }
        public void setApp_mi(int app_mi) {
                this.app_mi = app_mi;
        }
        public int getData_mi() {
                return data_mi;
        }
        public void setData_mi(int data_mi) {
                this.data_mi = data_mi;
        }
        public int getOnly_mi() {
                return only_mi;
        }
        public void setOnly_mi(int only_mi) {
                this.only_mi = only_mi;
        }
        public int getApp_server() {
                return app_server;
        }
        public void setApp_server(int app_server) {
                this.app_server = app_server;
        }
        public int getData_server() {
                return data_server;
        }
        public void setData_server(int data_server) {
                this.data_server = data_server;
        }
        public int getOnly_server() {
                return only_server;
        }
        public void setOnly_server(int only_server) {
                this.only_server = only_server;
        }
        
}
