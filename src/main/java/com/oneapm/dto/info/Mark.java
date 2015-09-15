package com.oneapm.dto.info;

public class Mark {

        private Long id;

        private Long infoId;

        private String createTime;

        private int status;

        private Long adminId;
        
        private String projectName;
        
        private Long groupId;

        public Mark(Long id, Long infoId, String createTime, int status,Long adminId) {
                setId(id);
                setInfoId(infoId);
                setCreateTime(createTime);
                setStatus(status);
                setAdminId(adminId);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public String getCreateTime() {
                return createTime;
        }

        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public String getProjectName() {
                return projectName;
        }

        public void setProjectName(String projectName) {
                this.projectName = projectName;
        }

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
        

}
