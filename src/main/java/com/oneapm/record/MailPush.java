package com.oneapm.record;

public class MailPush {

        private Long id;
        private String createTime;
        private int status;
        private Long adminId;
        private int type;
        private Long infoId;
        private String putTime;
        private String name;
        private int from;// 0.系统推送1.转交2.手动生成3系统回收
        private int number;
        private String touchTime;
        private Long fromId;
        private String fromName;
        private int warming;
        private String warmingTime;
        private String adminName;
        private boolean remove;

        private boolean point;

        public MailPush(Long id, String createTime, int status, Long adminId, int type, Long infoId, String putTime, int number, String touchTime, int from, int warming, String warmingTime, boolean point) {
                setId(id);
                setCreateTime(createTime);
                setStatus(status);
                setAdminId(adminId);
                setType(type);
                setInfoId(infoId);
                setPutTime(putTime);
                setNumber(number);
                setTouchTime(touchTime);
                setFrom(from);
                setWarmingTime(warmingTime);
                setPoint(point);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public String getPutTime() {
                return putTime;
        }

        public void setPutTime(String putTime) {
                this.putTime = putTime;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getFrom() {
                return from;
        }

        public void setFrom(int from) {
                this.from = from;
        }

        public int getNumber() {
                return number;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public String getTouchTime() {
                return touchTime;
        }

        public void setTouchTime(String touchTime) {
                this.touchTime = touchTime;
        }

        public Long getFromId() {
                return fromId;
        }

        public void setFromId(Long fromId) {
                this.fromId = fromId;
        }

        public int getWarming() {
                return warming;
        }

        public void setWarming(int warming) {
                this.warming = warming;
        }

        public String getWarmingTime() {
                return warmingTime;
        }

        public void setWarmingTime(String warmingTime) {
                this.warmingTime = warmingTime;
        }

        public String getFromName() {
                return fromName;
        }

        public void setFromName(String fromName) {
                this.fromName = fromName;
        }

        public boolean isPoint() {
                return point;
        }

        public void setPoint(boolean point) {
                this.point = point;
        }

        public String getAdminName() {
                return adminName;
        }

        public void setAdminName(String adminName) {
                this.adminName = adminName;
        }

        public boolean isRemove() {
                return remove;
        }

        public void setRemove(boolean remove) {
                this.remove = remove;
        }
}
