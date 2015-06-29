package com.oneapm.record;

public class Message {
        private Long id;
        private Long from;
        private Long to;
        private int status;
        private Long infoId;
        private String createTime;
        private String viewTime;
        private String closeTime;
        /**
         * 1.申请销售负责人 2.申请运营负责人 3.
         */
        private int type;

        public Message() {
        }

        public Message(Long id, Long from, Long to, int status, Long infoId, String createTime, String viewTime, String closeTime, int type) {
                setId(id);
                setFrom(from);
                setTo(to);
                setStatus(status);
                setInfoId(infoId);
                setCreateTime(createTime);
                setViewTime(viewTime);
                setCloseTime(closeTime);
                setType(type);
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

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
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

        public Long getTo() {
                return to;
        }

        public void setTo(Long to) {
                this.to = to;
        }

        public String getViewTime() {
                return viewTime;
        }

        public void setViewTime(String viewTime) {
                this.viewTime = viewTime;
        }

        public String getCloseTime() {
                return closeTime;
        }

        public void setCloseTime(String closeTime) {
                this.closeTime = closeTime;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

}
