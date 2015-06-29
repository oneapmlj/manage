package com.oneapm.dto.group;

public class GroupRecord {

        private Long id;
        private Long from;
        private Long to;
        private Long adminId;
        private String time;
        
        public GroupRecord(Long id, Long from, Long to, Long adminId, String time){
                setId(id);
                setFrom(from);
                setTo(to);
                setAdminId(adminId);
                setTime(time);
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public Long getFrom() {
                return from;
        }
        public void setFrom(Long from) {
                this.from = from;
        }
        public Long getTo() {
                return to;
        }
        public void setTo(Long to) {
                this.to = to;
        }
        public Long getAdminId() {
                return adminId;
        }
        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }
        public String getTime() {
                return time;
        }
        public void setTime(String time) {
                this.time = time;
        }
}
