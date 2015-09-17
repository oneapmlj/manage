package com.oneapm.record;

import com.oneapm.dto.MailDto;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.info.Info;

public class Task {

        private MailPush push;

        private MailDto mode;

        private Info info;

        private int warmingHour;
        private boolean point;
        private UserGroups userGroups;
        public Task(MailPush push, MailDto mode, Info info, boolean point) {
                setPush(push);
                setMode(mode);
                setInfo(info);
                setPoint(point);
        }
        public Task(MailPush push, MailDto mode, UserGroups userGroups, boolean point) {
            setPush(push);
            setMode(mode);
            setUserGroups(userGroups);
            setPoint(point);
    }

        public MailPush getPush() {
                return push;
        }

        public void setPush(MailPush push) {
                this.push = push;
        }

        public MailDto getMode() {
                return mode;
        }

        public void setMode(MailDto mode) {
                this.mode = mode;
        }

        public Info getInfo() {
                return info;
        }

        public void setInfo(Info info) {
                this.info = info;
        }

        public int getWarmingHour() {
                return warmingHour;
        }

        public void setWarmingHour(int warmingHour) {
                this.warmingHour = warmingHour;
        }

        public boolean isPoint() {
                return point;
        }

        public void setPoint(boolean point) {
                this.point = point;
        }

		public UserGroups getUserGroups() {
			return userGroups;
		}

		public void setUserGroups(UserGroups userGroups) {
			this.userGroups = userGroups;
		}
        
}
