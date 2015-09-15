package com.oneapm.web.group;

import java.util.ArrayList;
import java.util.List;

import com.oneapm.dto.UserGroups;
import com.oneapm.dto.UserGroup;
import com.oneapm.dto.info.Info;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.InfoService;
import com.oneapm.web.SupportAction;
public class UserGroupAction extends SupportAction{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1457049226760258345L;
	private Long id;
	private Long groupId;
     private Long userId;
     private String email;
     private String role;
     private int defaultGroup;
     private int deleted;
     private int status;
     
     private Long adminId;
     private String groupName;
     private Long parentId;
     
     private Long sale;
     private Long support;
     private Long preSale;
     private int payLevel;
     private String payTime;
     private String comming;
     private int emailStatus;
     private String contectTime;
     private Info info;
     private List<Info> infos;
     private UserGroups userGroups;
     private List<UserGroup> userGroupList;
     private UserGroup userGroup;
     public String view() {
         if (!isLogin()) {
                 return "login";
         }
         info = InfoService.findUserId(id);
         userGroups = UserGroupService.findByGroupId(info.getUserId(),getAdmin());
         infos = new ArrayList<Info>();
         userGroupList = UserGroupService.findUsersByGroupId(userGroups.getGroupId());
         for(UserGroup userGroup : userGroupList ){
         info = InfoService.findByUserId(userGroup.getUserId(), getAdmin());
        
         if (info != null) {
             if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(105))) {
                     info.setAssign(1);
                     
             }
         }
         userGroup.setInfo(info);
         }
         UserGroupService.initUserGroups(userGroups);
         userGroups.setUserGroups(userGroupList);
         return "view";
 }
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getDefaultGroup() {
		return defaultGroup;
	}
	public void setDefaultGroup(int defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Info> getInfos() {
		return infos;
	}
	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}
	public UserGroups getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(UserGroups userGroups) {
		this.userGroups = userGroups;
	}
	public List<UserGroup> getUserGroupList() {
		return userGroupList;
	}
	public void setUserGroupList(List<UserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
     
}
