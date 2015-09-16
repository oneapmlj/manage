package com.oneapm.dto;

public class Call {

        private Long infoId;

        private Long callId;

        private Long cardId;

        private String callTime;

        private String mark;

        private Long adminId;

        private String company;

        private String adminName;

        private String cardName;

        private Long type;

        private String typeName;

        private String time;

        private Long gongdan;

        private int todu;
        
        private Long groupId;

        public Call(Long infoId, Long callId, Long cardId, String callTime, String mark, Long adminId, String company, Long type, String time, Long gongdan) {
                setInfoId(infoId);
                setCardId(cardId);
                setCallTime(callTime);
                setMark(mark);
                setAdminId(adminId);
                setCompany(company);
                setCallId(callId);
                setType(type);
                setGongdan(gongdan);
                setTime(time);
        }

        public Call() {

        }

        public Long getCardId() {
                return cardId;
        }

        public void setCardId(Long cardId) {
                this.cardId = cardId;
        }

        public String getCallTime() {
                return callTime;
        }

        public void setCallTime(String callTime) {
                this.callTime = callTime;
        }

        public String getMark() {
                return mark;
        }

        public void setMark(String mark) {
                this.mark = mark;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }

        public Long getCallId() {
                return callId;
        }

        public void setCallId(Long callId) {
                this.callId = callId;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public String getAdminName() {
                return adminName;
        }

        public void setAdminName(String adminName) {
                this.adminName = adminName;
        }

        public String getCardName() {
                return cardName;
        }

        public void setCardName(String cardName) {
                this.cardName = cardName;
        }

        public Long getType() {
                return type;
        }

        public void setType(Long type) {
                this.type = type;
        }

        public Long getGongdan() {
                return gongdan;
        }

        public void setGongdan(Long gongdan) {
                this.gongdan = gongdan;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public int getTodu() {
                return todu;
        }

        public void setTodu(int todu) {
                this.todu = todu;
        }

        public String getTypeName() {
                return typeName;
        }

        public void setTypeName(String typeName) {
                this.typeName = typeName;
        }

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
        
        
}
