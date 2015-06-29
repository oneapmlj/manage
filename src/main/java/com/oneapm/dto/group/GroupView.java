package com.oneapm.dto.group;

public class GroupView {
        private Long infoId;
        private Long groupId;
        private int score;
        private String typeTime;
        private String changeTime;
        public GroupView(Long infoId, Long groupId, int score, String typeTime, String changeTime){
                setInfoId(infoId);
                setGroupId(groupId);
                setScore(score);
                setTypeTime(typeTime);
                setChangeTime(changeTime);
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
}
