package com.oneapm.dto.mail;

public class AppReport {

        private Long userId;
        
        private Long appId;
        
        private float cpm;
        
        private float responsetime;
        
        private double apdex;
        
        public AppReport(Long userId, Long appId, float cpm, float responsetime, double apdex){
                setUserId(userId);
                setApdex(apdex);
                setAppId(appId);
                setCpm(cpm);
                setResponsetime(responsetime);
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public Long getAppId() {
                return appId;
        }

        public void setAppId(Long appId) {
                this.appId = appId;
        }

        public float getResponsetime() {
                return responsetime;
        }

        public void setResponsetime(float responsetime) {
                this.responsetime = responsetime;
        }

        public float getCpm() {
                return cpm;
        }

        public void setCpm(float cpm) {
                this.cpm = cpm;
        }

        public double getApdex() {
                return apdex;
        }

        public void setApdex(double apdex) {
                this.apdex = apdex;
        }
        
        
}
