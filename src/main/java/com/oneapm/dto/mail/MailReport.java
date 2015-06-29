package com.oneapm.dto.mail;

import java.util.Map;

public class MailReport {

        private Long userId;
        
        private boolean active;
        
        private Map<Long, AppReport> apps;
        
        public MailReport(Long userId, Map<Long, AppReport> apps){
                setUserId(userId);
                setApps(apps);
                setActive(false);
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public boolean isActive() {
                return active;
        }

        public void setActive(boolean active) {
                this.active = active;
        }

        public Map<Long, AppReport> getApps() {
                return apps;
        }

        public void setApps(Map<Long, AppReport> apps) {
                this.apps = apps;
        }

}
