package com.oneapm.web.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dto.UserGroups;
import com.oneapm.dto.UserGroup;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.dto.info.Info;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.GuanlianService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.record.Xiaoshouyi;
import com.oneapm.service.show.CallService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
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
     private List<UserGroups> userGroupsList;
     private UserGroups userGroups;
     private List<UserGroup> userGroupList;
     private UserGroup userGroup;
     private String mark;
     private String putTime;
     private String add_time;
     private boolean add_call_point;
     private Long cardId;
     private Long recordType;
     private String project;
     private int downloadsNum;
     public String view() {
         if (!isLogin()) {
                 return "login";
         }
         //info = InfoService.findUserId(id);
        // userGroups = UserGroupService.findByGroupId(info.getUserId(),getAdmin());
         System.out.println(id);
         userGroups = UserGroupService.findByGroupId(id,getAdmin());
         if(userGroups!=null){
         userGroupList = UserGroupService.findUsersByGroupId(userGroups.getGroupId());
         }
         if(userGroupList!=null){
         for(UserGroup userGroup : userGroupList ){
         info = InfoService.findByUserId(userGroup.getUserId(), getAdmin());
         if (info != null) {
             if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(105))) {
                     info.setAssign(1);
             }
         }
         downloadsNum = downloadsNum + info.getDownloads().size();
         userGroup.setInfo(info);
         }
         }
         if(userGroups!=null){
         UserGroupService.initUserGroups(userGroups);
         userGroups.setUserGroups(userGroupList);
         }
         return "view";
 }
     private String license;
     private int pay_level;
     public void edit() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
//                 if (qq != null) {
//                         qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
//                 }
                /* if (project != null) {
                         project = new String(project.getBytes("ISO8859-1"), "UTF-8");
                 }
                 if (name != null) {
                         name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                 }*/
//                 if (phone != null) {
//                         phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (email != null) {
//                         email = new String(email.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (license != null) {
//                         license = new String(license.getBytes("ISO8859-1"), "UTF-8");
//                 }
                 String result = UserGroupService.edit(groupId, project, email,  getAdmin(), license, pay_level);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }
     
     public String onlianxi(){
         if (!isLogin()) {
                 return "login";
         }
         try{
                 userGroupsList = UserGroupService.onlianxi(getAdmin());
         }catch(Exception e){
                 LOG.error(e.getMessage(), e);
         }
         return "onlianxi";
 }
     
     
     public void add_call() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(103))) {
                 try {
                         getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                         return;
                 } catch (Exception e) {
                         LOG.error(e.getMessage(), e);
                 }
         }
         try {
  //              mark = new String(mark.getBytes("ISO8859-1"), "UTF-8");
//                 if (phone != null) {
//                         phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (email != null) {
//                         email = new String(email.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (qq != null) {
//                         qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (name != null) {
//                         name = new String(name.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (position != null) {
//                         position = new String(position.getBytes("ISO8859-1"), "UTF-8");
//                 }
//                 if (branch != null) {
//                         branch = new String(branch.getBytes("ISO8859-1"), "UTF-8");
//                 }
                 if (add_time == null || add_time.trim().length() < 1) {
                         putTime = TimeTools.format();
                 } else {
                         putTime = add_time;
                 }
                 LOG.info("ADD CALL : "+mark);
                 String result = CallService.insertWithGroupId(groupId, cardId,  mark, recordType, getAdmin(), putTime, add_call_point);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }
     
     private String guanlianId;
     private Long guanlian;
     public void guanlian_view() throws IOException{
		if (!isLogin()) {
			getServletResponse().sendRedirect("/login.action");
			return;
		}
		try {
			List<Long> userIdList = new ArrayList<Long>();
			List<Guanlian> guanlianList = GuanlianService.findByUserId(groupId);
			for (int i = 0; i < guanlianList.size(); i++) {
				userIdList.add(guanlianList.get(i).getGuanlianId());
			}
			String result = GuanlianService.findAllGuanlian(userIdList);
			getServletResponse().getWriter().print(result);
       }catch(Exception e){
               getServletResponse().getWriter().print(OneTools.getResult(0, "显示错误"));
       }
     }
     
     public void guanlian_add() throws IOException{
             if (!isLogin()) {
                     getServletResponse().sendRedirect("/login.action");
                     return;
             }
             try{
                     guanlian = Long.parseLong(guanlianId);
                     String result = GuanlianService.add(groupId, guanlian);
                     getServletResponse().getWriter().print(result);
             }catch(Exception e){
                     getServletResponse().getWriter().print(OneTools.getResult(0, "请输入userId"));
             }
     }
     public void guanlian_remove() throws IOException{
             if (!isLogin()) {
                     getServletResponse().sendRedirect("/login.action");
                     return;
             }
             try{
             		guanlian = Long.parseLong(guanlianId);
                     String result = GuanlianService.remove(groupId, guanlian);
                     getServletResponse().getWriter().print(result);
             }catch(Exception e){
                     LOG.error(e.getMessage(), e);
             }
     }
     public void guanlian_change() throws IOException{
             if (!isLogin()) {
                     getServletResponse().sendRedirect("/login.action");
                     return;
             }
             try{
             		guanlian = Long.parseLong(guanlianId);
                     String result = GuanlianService.change(groupId, guanlian);
                     getServletResponse().getWriter().print(result);
             }catch(Exception e){
                     LOG.error(e.getMessage(), e);
             }
     }
     
     public void sale() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
                 String result = UserGroupService.assign(getAdmin(), 1, groupId, adminId);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }	
     public void presale() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
                 String result = UserGroupService.assign(getAdmin(), 3, groupId, adminId);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }
     
     public void support() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
                 String result = UserGroupService.assign(getAdmin(), 2, groupId, adminId);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }
     private Long from;
     private Long messageId;
     private int type;
     public void change() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         String result = UserGroupService.change(getAdmin(), from, groupId, type, messageId);
         getServletResponse().getWriter().print(result);
 }
     
     public void back() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
                 String result = UserGroupService.back(getAdmin(), type, groupId);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
 }
     private String xiaoshou;
     public void xiaoshouyi() throws IOException {
             if (!isLogin()) {
                     getServletResponse().sendRedirect("/login.action");
                     return;
             }
             try{
                     if (xiaoshou != null) {
                             /*xiaoshou = new String(xiaoshou.getBytes("ISO8859-1"), "UTF-8");*/
                     }
                     if(!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(116))){
                             getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                             return;
                     }
                     String result = Xiaoshouyi.xiaoshouyiWithGroupId(getAdmin(), groupId, xiaoshou);
                     getServletResponse().getWriter().print(result);
             }catch(Exception e){
                     LOG.error(e.getMessage(), e);
             }
     }
     
     private String qq;
     private boolean in;
     private String phone;
     private String name;
     private String company;
     public void searchOut() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
//         if (name != null) {
//                 name = new String(name.getBytes("ISO8859-1"), "UTF-8").trim();
//         }
//         if (company != null) {
//                 company = new String(company.getBytes("ISO8859-1"), "UTF-8");
//         }
//         if (qq != null) {
//                 qq = new String(qq.getBytes("ISO8859-1"), "UTF-8").trim();
//         }
         String result = UserGroupService.searchOut(email, name, phone, company, in, getAdmin(), qq);
         getServletResponse().getWriter().print(result);
 }
     
     
     public void delete() throws IOException {
         if (!isLogin()) {
                 getServletResponse().sendRedirect("/login.action");
                 return;
         }
         try {
                 String result = UserGroupService.delete(getAdmin(), groupId, type);
                 getServletResponse().getWriter().print(result);
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
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


	public List<UserGroups> getUserGroupsList() {
		return userGroupsList;
	}


	public void setUserGroupsList(List<UserGroups> userGroupsList) {
		this.userGroupsList = userGroupsList;
	}


	public String getMark() {
		return mark;
	}


	public void setMark(String mark) {
		this.mark = mark;
	}


	public String getAdd_time() {
		return add_time;
	}


	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}


	public String getPutTime() {
		return putTime;
	}


	public void setPutTime(String putTime) {
		this.putTime = putTime;
	}


	public boolean isAdd_call_point() {
		return add_call_point;
	}


	public void setAdd_call_point(boolean add_call_point) {
		this.add_call_point = add_call_point;
	}


	public Long getCardId() {
		return cardId;
	}


	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}


	public Long getRecordType() {
		return recordType;
	}


	public void setRecordType(Long recordType) {
		this.recordType = recordType;
	}


	public String getGuanlianId() {
		return guanlianId;
	}


	public void setGuanlianId(String guanlianId) {
		this.guanlianId = guanlianId;
	}


	public Long getGuanlian() {
		return guanlian;
	}


	public void setGuanlian(Long guanlian) {
		this.guanlian = guanlian;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getXiaoshou() {
		return xiaoshou;
	}
	public void setXiaoshou(String xiaoshou) {
		this.xiaoshou = xiaoshou;
	}
	public int getDownloadsNum() {
		return downloadsNum;
	}
	public void setDownloadsNum(int downloadsNum) {
		this.downloadsNum = downloadsNum;
	}

        public String getLicense() {
                return license;
        }

        public void setLicense(String license) {
                this.license = license;
        }

        public int getPay_level() {
                return pay_level;
        }

        public void setPay_level(int pay_level) {
                this.pay_level = pay_level;
        }
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public boolean isIn() {
		return in;
	}
	public void setIn(boolean in) {
		this.in = in;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
     
}
