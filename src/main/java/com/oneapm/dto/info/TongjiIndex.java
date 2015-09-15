package com.oneapm.dto.info;

import java.util.List;

import com.oneapm.dto.UserGroups;

public class TongjiIndex {

        private List<Tongji> tongjis;
        private Long id;
        private boolean isNext;

        private List<Info> today;
        private List<Info> yestoday;
        private List<Info> todayUp;
        private List<Info> todayDown;
        private String name;
        private String date;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public boolean isNext() {
                return isNext;
        }

        public void setNext(boolean isNext) {
                this.isNext = isNext;
        }

        public List<Tongji> getTongjis() {
                return tongjis;
        }

        public void setTongjis(List<Tongji> tongjis) {
                this.tongjis = tongjis;
        }


        public List<Info> getToday() {
			return today;
		}

		public void setToday(List<Info> today) {
			this.today = today;
		}

		public List<Info> getYestoday() {
			return yestoday;
		}

		public void setYestoday(List<Info> yestoday) {
			this.yestoday = yestoday;
		}

		public List<Info> getTodayUp() {
			return todayUp;
		}

		public void setTodayUp(List<Info> todayUp) {
			this.todayUp = todayUp;
		}

		public List<Info> getTodayDown() {
			return todayDown;
		}

		public void setTodayDown(List<Info> todayDown) {
			this.todayDown = todayDown;
		}

		public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }
}
