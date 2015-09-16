package com.oneapm.dto.tag;

/**
 * 用户标签
 * 
 * @author abc
 *
 */
public class Tag {
        private Long id;
        private Long infoId;
        private Integer category;
        private int from;
        private Integer metric;
        private Integer loudou;
        private int status;
        private String description;
        private Loudou lou;
        private Metric me;
        private Integer province;
        private String provinceName;
        private Integer person;
        private String personName;
//        private Integer pv;
//        private String pvName;
//        private Integer uv;
//        private String uvName;
        private String categoryName;
        private String language;
        private int rongzi;
        private String rongziName;
        private int fuwuqi;
        private String fuwuqiName;
        private Long groupId;
        public Tag() {
        };

        public Tag(Long id, Long infoId, Integer category, int from, Integer metric, Integer loudou, String description,
                        int status, Integer province, Integer person, String language, Integer rongzi, Integer fuwuqi) {
                setId(id);
                setInfoId(infoId);
                setCategory(category);
                setFrom(from);
                setLoudou(loudou);
                setMetric(metric);
                setDescription(description);
                setStatus(status);
                setProvince(province);
                setPerson(person);
//                setPv(pv);
//                setUv(uv);
                setLanguage(language);
                setRongzi(rongzi);
                setFuwuqi(fuwuqi);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public int getCategory() {
                return category;
        }

        public void setCategory(int category) {
                this.category = category;
        }

        public int getFrom() {
                return from;
        }

        public void setFrom(int from) {
                this.from = from;
        }

        public Integer getLoudou() {
                return loudou;
        }

        public void setLoudou(Integer loudou) {
                this.loudou = loudou;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Integer getMetric() {
                return metric;
        }

        public void setMetric(Integer metric) {
                this.metric = metric;
        }

        public Loudou getLou() {
                return lou;
        }

        public void setLou(Loudou lou) {
                this.lou = lou;
        }

        public Metric getMe() {
                return me;
        }

        public void setMe(Metric me) {
                this.me = me;
        }

        public Integer getProvince() {
                return province;
        }

        public void setProvince(Integer province) {
                this.province = province;
        }

        public Integer getPerson() {
                return person;
        }

        public void setPerson(Integer person) {
                this.person = person;
        }

//        public Integer getPv() {
//                return pv;
//        }
//
//        public void setPv(Integer pv) {
//                this.pv = pv;
//        }
//
//        public Integer getUv() {
//                return uv;
//        }
//
//        public void setUv(Integer uv) {
//                this.uv = uv;
//        }

        public String getProvinceName() {
                return provinceName;
        }

        public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
        }

        public String getPersonName() {
                return personName;
        }

        public void setPersonName(String personName) {
                this.personName = personName;
        }

//        public String getPvName() {
//                return pvName;
//        }
//
//        public void setPvName(String pvName) {
//                this.pvName = pvName;
//        }
//
//        public String getUvName() {
//                return uvName;
//        }
//
//        public void setUvName(String uvName) {
//                this.uvName = uvName;
//        }

        public String getCategoryName() {
                return categoryName;
        }

        public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }

        public int getRongzi() {
                return rongzi;
        }

        public void setRongzi(int rongzi) {
                this.rongzi = rongzi;
        }

        public String getRongziName() {
                return rongziName;
        }

        public void setRongziName(String rongziName) {
                this.rongziName = rongziName;
        }

        public int getFuwuqi() {
                return fuwuqi;
        }

        public void setFuwuqi(int fuwuqi) {
                this.fuwuqi = fuwuqi;
        }

        public String getFuwuqiName() {
                return fuwuqiName;
        }

        public void setFuwuqiName(String fuwuqiName) {
                this.fuwuqiName = fuwuqiName;
        }

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
        
}
