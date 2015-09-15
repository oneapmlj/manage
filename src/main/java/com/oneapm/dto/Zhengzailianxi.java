package com.oneapm.dto;

public class Zhengzailianxi {

        private Long id;
        private Long adminId;
        private String startTime;
        private String endTime;
        private int status;
        private int stay;
        private String adminName;
        private Long infoId;
        private Long groupId;
        public Zhengzailianxi(){}
        public Zhengzailianxi(Long id, Long adminId, String startTime, String endTime, int status, int stay, Long infoId){
                setId(id);
                setAdminId(adminId);
                setStartTime(startTime);
                setEndTime(endTime);
                setStartTime(startTime);
                setStay(stay);
                setInfoId(infoId);
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public int getStatus() {
                return status;
        }
        public void setStatus(int status) {
                this.status = status;
        }
        public String getEndTime() {
                return endTime;
        }
        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }
        public String getStartTime() {
                return startTime;
        }
        public void setStartTime(String startTime) {
                this.startTime = startTime;
        }
        public Long getAdminId() {
                return adminId;
        }
        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }
        public int getStay() {
                return stay;
        }
        public void setStay(int stay) {
                this.stay = stay;
        }
        public String getAdminName() {
                return adminName;
        }
        public void setAdminName(String adminName) {
                this.adminName = adminName;
        }
        public Long getInfoId() {
                return infoId;
        }
        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
        
}
