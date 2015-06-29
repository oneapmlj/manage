package com.oneapm.dto.info;

public class DataLiucun {

        private Long userId;

        private Long id;

        private int type;

        private String dataTime;

        private int number;

        private int agent;

        private String fromTime;

        public DataLiucun(Long userId, Long id, String dataTime, int type, int number, int agent, String fromTime) {
                setUserId(userId);
                setId(id);
                setDataTime(dataTime);
                setType(type);
                setNumber(number);
                setAgent(agent);
                setFromTime(fromTime);
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

        public String getDataTime() {
                return dataTime;
        }

        public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getNumber() {
                return number;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

        public String getFromTime() {
                return fromTime;
        }

        public void setFromTime(String fromTime) {
                this.fromTime = fromTime;
        }

}
