package com.oneapm.dto.info;

public class DataLiucunShow {

        private Long id;

        private int type;

        private int count;

        private int percent;

        private int time;

        private String dataTimeStart;

        private String dataTimeEnd;

        private int number;
        
        private String fromTime;
        
        private int agent;

        public DataLiucunShow(Long id, int type, int count, int percent, int time, String dataTimeStart, String dataTimeEnd,
                        int number, int agent, String fromTime) {
                setId(id);
                setCount(count);
                setTime(time);
                setDataTimeEnd(dataTimeEnd);
                setDataTimeStart(dataTimeStart);
                setPercent(percent);
                setType(type);
                setNumber(number);
                setAgent(agent);
                setFromTime(fromTime);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public int getCount() {
                return count;
        }

        public void setCount(int count) {
                this.count = count;
        }

        public int getTime() {
                return time;
        }

        public void setTime(int time) {
                this.time = time;
        }

        public String getDataTimeStart() {
                return dataTimeStart;
        }

        public void setDataTimeStart(String dataTimeStart) {
                this.dataTimeStart = dataTimeStart;
        }

        public String getDataTimeEnd() {
                return dataTimeEnd;
        }

        public void setDataTimeEnd(String dataTimeEnd) {
                this.dataTimeEnd = dataTimeEnd;
        }

        public int getPercent() {
                return percent;
        }

        public void setPercent(int percent) {
                this.percent = percent;
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

        public String getFromTime() {
                return fromTime;
        }

        public void setFromTime(String fromTime) {
                this.fromTime = fromTime;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

}
