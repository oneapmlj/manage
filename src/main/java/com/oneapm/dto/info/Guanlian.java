package com.oneapm.dto.info;

public class Guanlian {

        private Long id;
        private Long userId;
        private Long guanlianId;
        private int role;
        
        public Guanlian(Long id, Long userId, Long guanlianId, int role){
                setId(id);
                setUserId(userId);
                setGuanlianId(guanlianId);
                setRole(role);
        }
        public Long getGuanlianId() {
                return guanlianId;
        }
        public void setGuanlianId(Long guanlianId) {
                this.guanlianId = guanlianId;
        }
        public int getRole() {
                return role;
        }
        public void setRole(int role) {
                this.role = role;
        }
        public Long getUserId() {
                return userId;
        }
        public void setUserId(Long userId) {
                this.userId = userId;
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
}
