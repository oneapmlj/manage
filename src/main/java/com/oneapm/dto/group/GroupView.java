package com.oneapm.dto.group;

public class GroupView {
        private Long infoId;
        private Long groupId;
        private int score;
        private String typeTime;
        private String changeTime;
        private Long userGroupId;
        public GroupView(Long infoId, Long groupId, int score, String typeTime, String changeTime){
                setInfoId(infoId);
                setGroupId(groupId);
                setScore(score);
                setTypeTime(typeTime);
                setChangeTime(changeTime);
        }
        public GroupView(Long infoId, Long groupId, int score, String typeTime, String changeTime, Long userGroupId){
            setInfoId(infoId);
            setGroupId(groupId);
            setScore(score);
            setTypeTime(typeTime);
            setChangeTime(changeTime);
            setUserGroupId(userGroupId);
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
        public int getScore() {
                return score;
        }
        public void setScore(int score) {
                this.score = score;
        }
        public String getTypeTime() {
                return typeTime;
        }
        public void setTypeTime(String typeTime) {
                this.typeTime = typeTime;
        }
        public String getChangeTime() {
                return changeTime;
        }
        public void setChangeTime(String changeTime) {
                this.changeTime = changeTime;
        }
		public Long getUserGroupId() {
			return userGroupId;
		}
		public void setUserGroupId(Long userGroupId) {
			this.userGroupId = userGroupId;
		}
        
}
