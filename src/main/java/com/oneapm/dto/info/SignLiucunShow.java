package com.oneapm.dto.info;

public class SignLiucunShow {

        private Long id;

        private int type;

        private int number_download;

        private int number_app;

        private String signTimeStart;

        private String signTimeEnd;

        private int count_download;

        private int percent_download;

        private int count_app;

        private int percent_app;

        private String fromTime;

        private int agent;

        public SignLiucunShow(Long id, int type, int number_download, int number_app, String signTimeStart, String signTimeEnd,
                        int count_download, int percent_download, int count_app, int percent_app, int agent, String fromTime) {
                setId(id);
                setType(type);
                setNumber_download(number_download);
                setNumber_app(number_app);
                setSignTimeStart(signTimeStart);
                setSignTimeEnd(signTimeEnd);
                setCount_app(count_app);
                setCount_download(count_download);
                setPercent_app(percent_app);
                setPercent_download(percent_download);
                setAgent(agent);
                setFromTime(fromTime);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getCount_download() {
                return count_download;
        }

        public void setCount_download(int count_download) {
                this.count_download = count_download;
        }

        public int getPercent_download() {
                return percent_download;
        }

        public void setPercent_download(int percent_download) {
                this.percent_download = percent_download;
        }

        public int getCount_app() {
                return count_app;
        }

        public void setCount_app(int count_app) {
                this.count_app = count_app;
        }

        public int getPercent_app() {
                return percent_app;
        }

        public void setPercent_app(int percent_app) {
                this.percent_app = percent_app;
        }

        public String getSignTimeStart() {
                return signTimeStart;
        }

        public void setSignTimeStart(String signTimeStart) {
                this.signTimeStart = signTimeStart;
        }

        public String getSignTimeEnd() {
                return signTimeEnd;
        }

        public void setSignTimeEnd(String signTimeEnd) {
                this.signTimeEnd = signTimeEnd;
        }

        public int getNumber_download() {
                return number_download;
        }

        public void setNumber_download(int number_download) {
                this.number_download = number_download;
        }

        public int getNumber_app() {
                return number_app;
        }

        public void setNumber_app(int number_app) {
                this.number_app = number_app;
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
