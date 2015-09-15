package com.oneapm.dto;

import com.oneapm.dto.info.Info;

public class UserGroup {

        private Long groupId;
        private Long userId;
        private String email;
        private String role;
        private int defaultGroup;
        private int deleted;
        private int status;
        private Info info;
        public UserGroup(Long groupId, Long userId, String email, String role, int defaultGroup, int deleted, int status){
                setGroupId(groupId);
                setUserId(userId);
                setEmail(email);
                setRole(role);
                setDefaultGroup(defaultGroup);
                setDeleted(deleted);
                setStatus(status);
        }
        
        public Info getInfo() {
			return info;
		}

		public void setInfo(Info info) {
			this.info = info;
		}

		public Long getGroupId() {
                return groupId;
        }

        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }



        public int getDeleted() {
                return deleted;
        }

        public void setDeleted(int deleted) {
                this.deleted = deleted;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public int getDefaultGroup() {
                return defaultGroup;
        }

        public void setDefaultGroup(int defaultGroup) {
                this.defaultGroup = defaultGroup;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }


        public String getRole() {
                return role;
        }

        public void setRole(String role) {
                this.role = role;
        }
}
