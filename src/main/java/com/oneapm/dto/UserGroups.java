package com.oneapm.dto;

import java.util.List;

import com.oneapm.dto.card.Card;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.dto.info.Mark;
import com.oneapm.dto.tag.Tag;
import com.oneapm.record.MailPush;

public class UserGroups {

        private Long groupId;
        private Long adminId;
        private String groupName;
        private Long parentId;
        private int deleted;
        
        private String createTime;
        private String language;
        private int level;
        
        private Long sale;
        private Long support;
        private Long preSale;
        private int payLevel;
        private String payTime;
        private String comming;
        private int emailStatus;
        private String contectTime;
        private List<UserGroup> userGroups;
        private List<Mail> mails;
        private List<App> apps;
        private List<Gongdan> gongdans;
        private Tag tag;
        private Group group;
        private Group groupFather;
        private String saleName;
        private String supportName;
        private String preSaleName;
        private List<Call> calls;
        private List<Card> cards;
        private List<Guanlian> guanlians;
        private List<MailPush> pushs;
        private Zhengzailianxi zhengzailianxi;
        private String expireTime;
        private int daoqi;
        private String pay_level;
        private Mark mark;
        private String project;

        public UserGroups(Long groupId, Long adminId, String groupName, Long parentId, int deleted){
                setGroupId(groupId);
                setAdminId(adminId);
                setGroupName(groupName);
                setParentId(parentId);
                setDeleted(deleted);
        }
        public UserGroups(Long groupId, Long adminId, String groupName, Long parentId, int deleted, Long sale, Long support,
        		Long preSale, int payLevel, String payTime, String comming, int emailStatus, String contectTime){
            setGroupId(groupId);
            setAdminId(adminId);
            setGroupName(groupName);
            setParentId(parentId);
            setDeleted(deleted);
            setSale(sale);
            setSupport(support);
            setPreSale(preSale);
            setPayLevel(payLevel);
            setPayTime(payTime);
            setComming(comming);
            setEmailStatus(emailStatus);
            setContectTime(contectTime);
    }
        
       
        
		public List<UserGroup> getUserGroups() {
			return userGroups;
		}
		public void setUserGroups(List<UserGroup> userGroups) {
			this.userGroups = userGroups;
		}
		public Long getGroupId() {
                return groupId;
        }

        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public String getGroupName() {
                return groupName;
        }

        public void setGroupName(String groupName) {
                this.groupName = groupName;
        }

        public Long getParentId() {
                return parentId;
        }

        public void setParentId(Long parentId) {
                this.parentId = parentId;
        }

        public int getDeleted() {
                return deleted;
        }

        public void setDeleted(int deleted) {
                this.deleted = deleted;
        }

        public Long getSale() {
                return sale;
        }

        public void setSale(Long sale) {
                this.sale = sale;
        }

        public Long getSupport() {
                return support;
        }

        public void setSupport(Long support) {
                this.support = support;
        }

        public Long getPreSale() {
                return preSale;
        }

        public void setPreSale(Long preSale) {
                this.preSale = preSale;
        }

        public int getPayLevel() {
                return payLevel;
        }

        public void setPayLevel(int payLevel) {
                this.payLevel = payLevel;
        }

        public String getPayTime() {
                return payTime;
        }

        public void setPayTime(String payTime) {
                this.payTime = payTime;
        }

        public String getComming() {
                return comming;
        }

        public void setComming(String comming) {
                this.comming = comming;
        }

        public int getEmailStatus() {
                return emailStatus;
        }

        public void setEmailStatus(int emailStatus) {
                this.emailStatus = emailStatus;
        }

        public String getContectTime() {
                return contectTime;
        }

        public void setContectTime(String contectTime) {
                this.contectTime = contectTime;
        }
		public List<Mail> getMails() {
			return mails;
		}
		public void setMails(List<Mail> mails) {
			this.mails = mails;
		}
		public List<App> getApps() {
			return apps;
		}
		public void setApps(List<App> apps) {
			this.apps = apps;
		}
		public List<Gongdan> getGongdans() {
			return gongdans;
		}
		public void setGongdans(List<Gongdan> gongdans) {
			this.gongdans = gongdans;
		}
		public Tag getTag() {
			return tag;
		}
		public void setTag(Tag tag) {
			this.tag = tag;
		}
		public Group getGroup() {
			return group;
		}
		public void setGroup(Group group) {
			this.group = group;
		}
		public Group getGroupFather() {
			return groupFather;
		}
		public void setGroupFather(Group groupFather) {
			this.groupFather = groupFather;
		}
		public String getSaleName() {
			return saleName;
		}
		public void setSaleName(String saleName) {
			this.saleName = saleName;
		}
		public String getSupportName() {
			return supportName;
		}
		public void setSupportName(String supportName) {
			this.supportName = supportName;
		}
		public String getPreSaleName() {
			return preSaleName;
		}
		public void setPreSaleName(String preSaleName) {
			this.preSaleName = preSaleName;
		}
		public List<Call> getCalls() {
			return calls;
		}
		public void setCalls(List<Call> calls) {
			this.calls = calls;
		}
		public List<Card> getCards() {
			return cards;
		}
		public void setCards(List<Card> cards) {
			this.cards = cards;
		}
		public List<Guanlian> getGuanlians() {
			return guanlians;
		}
		public void setGuanlians(List<Guanlian> guanlians) {
			this.guanlians = guanlians;
		}
		public List<MailPush> getPushs() {
			return pushs;
		}
		public void setPushs(List<MailPush> pushs) {
			this.pushs = pushs;
		}
		public Zhengzailianxi getZhengzailianxi() {
			return zhengzailianxi;
		}
		public void setZhengzailianxi(Zhengzailianxi zhengzailianxi) {
			this.zhengzailianxi = zhengzailianxi;
		}
		public String getExpireTime() {
			return expireTime;
		}
		public void setExpireTime(String expireTime) {
			this.expireTime = expireTime;
		}
		public int getDaoqi() {
			return daoqi;
		}
		public void setDaoqi(int daoqi) {
			this.daoqi = daoqi;
		}
		public String getPay_level() {
			return pay_level;
		}
		public void setPay_level(String pay_level) {
			this.pay_level = pay_level;
		}
		public Mark getMark() {
			return mark;
		}
		public void setMark(Mark mark) {
			this.mark = mark;
		}
                public String getCreateTime() {
                        return createTime;
                }
                public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                }
                public String getProject() {
                        return project;
                }
                public void setProject(String project) {
                        this.project = project;
                }
                public String getLanguage() {
                        return language;
                }
                public void setLanguage(String language) {
                        this.language = language;
                }
                public int getLevel() {
                        return level;
                }
                public void setLevel(int level) {
                        this.level = level;
                }
		
        
}
