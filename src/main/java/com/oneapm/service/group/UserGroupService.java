package com.oneapm.service.group;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.group.impl.UserGroupDaoImpl;
import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dto.UserGroup;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Zhengzailianxi;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupView;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.dto.tag.Language;
import com.oneapm.record.MailPush;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.AppService;
import com.oneapm.service.info.GuanlianService;
import com.oneapm.service.info.KFService;
import com.oneapm.service.info.MarkService;
import com.oneapm.service.info.TagService;
import com.oneapm.service.info.TaskService;
import com.oneapm.service.info.ZhengzailianxiService;
import com.oneapm.service.mail.MailService;
import com.oneapm.service.show.CallService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class UserGroupService extends OneTools{
	protected static final Logger LOG = LoggerFactory.getLogger(UserGroupService.class);
	public static UserGroups findGroupsById(Long admin_id){
		return UserGroupsDaoImpl.getInstance().findByAdminId(admin_id);
	}
	public static List<UserGroup> findUsersByGroupId(Long groupId){
		return UserGroupDaoImpl.getInstance().findUsersByGroupId(groupId);
	}
	
    public static UserGroups findByGroupId(Long groupId, Admin admin) {
    	UserGroups userGroups = UserGroupsDaoImpl.getInstance().findById(groupId);
        if (userGroups != null) {
                try{
                        Zhengzailianxi zhengzailianxi = ZhengzailianxiService.findByGroupId(userGroups.getGroupId());
                        if(zhengzailianxi != null){
                                zhengzailianxi.setAdminName(AccountService.findName(zhengzailianxi.getAdminId()));
                        }
                        userGroups.setZhengzailianxi(zhengzailianxi);
                        try{
                                TimeTools.formatTime.parse(userGroups.getExpireTime());
                                int day = TimeTools.apartDay(userGroups.getExpireTime(), TimeTools.format());
                                day = day >= 0 ? day : -1;
                                userGroups.setDaoqi(day);
                                if(userGroups.getPayLevel() == 0){
                                        if(day < 0){
                                        	userGroups.setPayLevel(40);
                                        }else{
                                        	userGroups.setPayLevel(30);
                                        }
                                        userGroups.setPay_level("线下用户");
                                }else{
                                        switch (userGroups.getPayLevel()) {
                                        case 10:userGroups.setPay_level("免费用户");break;
                                        case 20:userGroups.setPay_level("免费到期");break;
                                        case 30:userGroups.setPay_level("付费用户");break;
                                        case 40:userGroups.setPay_level("付费到期");break;
                                        default:userGroups.setPay_level("未知");break;
                                        }
                                }
                                userGroups.setExpireTime(userGroups.getExpireTime().substring(0, 19));
                        }catch(Exception e){
                        	userGroups.setExpireTime("未添加");
                        	userGroups.setDaoqi(-2);
                        	userGroups.setPay_level("未知");
                        }
                }catch(Exception e){}
                initTag(userGroups);
                initUserGroups(userGroups);
                userGroups.setMark(MarkService.findByAdminIdAndGroupId(admin.getId(), userGroups.getGroupId()));
//                info.setTip(TipService.findByInfoId(info.getId()));
                List<MailPush> pushs = TaskService.findByGroupId(userGroups.getGroupId());
                if (pushs.size() > 0) {
                        for(MailPush push : pushs){
                                try{
                                        if(push.getFromId() != null && push.getFromId() > 0){
                                                push.setFromName(AccountService.findById(push.getFromId()).getName());
                                        }
                                        push.setAdminName(AccountService.findById(push.getAdminId()).getName());
                                        push.setRemove(push.getAdminId().equals(admin.getId()) || push.getAdminId().equals(10000005L));
                                }catch(Exception e){}
                        }
                        userGroups.setPushs(pushs);
                        TaskService.touchByGroupId(userGroups.getGroupId(), admin.getId(), TimeTools.format());
                }
        }
        //获取关联帐号
        if(userGroups.getGroupId() != null&& userGroups != null){
                List<Guanlian> guanlians = GuanlianService.findByUserId(userGroups.getGroupId());
                userGroups.setGuanlians(guanlians);
        }
        return userGroups;
}
    
    
    
    
    public static UserGroups findByGroupIdSingle(Long groupId) {
    	UserGroups userGroups = UserGroupsDaoImpl.getInstance().findByAdminId(groupId);
        initUserGroups(userGroups);
        return userGroups;
    }
    public static UserGroups findByGroupIdSimple(Long groupId) {
    	UserGroups userGroups = UserGroupsDaoImpl.getInstance().findByAdminId(groupId);
        return userGroups;
    }
    
    public static List<UserGroups> onlianxi(Admin admin){
        List<UserGroups> UserGroupsList = null;
        try{
                List<Zhengzailianxi> zhengzailianxis = ZhengzailianxiService.findByAdminId(admin.getId());
                if(zhengzailianxis != null && zhengzailianxis.size() > 0){
                	UserGroupsList = new ArrayList<UserGroups>();
                        for(Zhengzailianxi zhengzailianxi : zhengzailianxis){
                                UserGroups userGroups = findByGroupIdSingle(zhengzailianxi.getGroupId());
                                userGroups.setZhengzailianxi(zhengzailianxi);
                                UserGroupsList.add(userGroups);
                        }
                }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return UserGroupsList;
}
    
	public static void initUserGroups(UserGroups userGroups) {
		//userGroups.setMails(MailService.findMailsById(userGroups.getGroupId()));
		userGroups.setMails(MailService.findMailsByGroupId(userGroups.getGroupId()));
		userGroups.setCalls(CallService.findByGroupId(userGroups.getGroupId()));
		userGroups.setApps(AppService.findByUserId(userGroups.getGroupId()));
		//userGroups.setApps(AppService.findByGroupId(userGroups.getGroupId()));
		userGroups.setGongdans(KFService.findByUserId(userGroups.getGroupId()));
                initTag(userGroups);
                initSupport(userGroups);
}
	
	
	
	
	 public static void initTag(UserGroups userGroups) {
		 userGroups.setTag(TagService.findByGroupId(userGroups.getGroupId()));
         if(userGroups.getGroupId() != null){
                 GroupView view = GroupService.findByUserGroupId(userGroups.getGroupId());
                 if(view == null){
                         return;
                 }
                 Group group = GroupService.findById(view.getGroupId());
                 userGroups.setGroup(group);
                 if(group.getFather() > 0){
                         Group father = GroupService.findById(group.getFather());
                         userGroups.setGroupFather(father);
                 }
         }
 }
	 
	 
	
	  
	  
	  public static void initSupport(UserGroups userGroups){
          if (userGroups.getSale() != null && userGroups.getSale() > 0L) {
        	  userGroups.setSaleName(AccountService.findById(userGroups.getSale()).getName());
          }
          if (userGroups.getSupport() != null && userGroups.getSupport() > 0L) {
        	  userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
          }
          if (userGroups.getPreSale() != null && userGroups.getPreSale() > 0L) {
        	  userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
          }
  }
	  @SuppressWarnings("unchecked")
	public static JSONArray getArrayFromUserGroups(List<UserGroups> groups) {
	                JSONArray array = new JSONArray();
	                if (groups == null || groups.size() <= 0) {
	                        return array;
	                }
	                for (UserGroups userGroups : groups) {
	                        array.add(getJSONFromUserGroups(userGroups));
	                }
	                return array;
	        }

	        @SuppressWarnings("unchecked")
	        public static JSONObject getJSONFromUserGroups(UserGroups userGroups) {
	                JSONObject value = new JSONObject();
	                if (userGroups == null)
	                        return value;
	                try {
	                        value.put("groupId", userGroups.getGroupId());
	                        value.put("company", userGroups.getGroupName());
	                        value.put("createTime", userGroups.getCreateTime());
	                        value.put("project", userGroups.getProject());
	                        value.put("saleName", userGroups.getSaleName());
	                        value.put("supportName", userGroups.getSupportName());
	                        value.put("preSaleName", userGroups.getPreSaleName());
	                        value.put("comming", userGroups.getComming());
	                        value.put("contectTime", userGroups.getContectTime());
	                        if (userGroups.getCalls() != null) {
	                                value.put("calls", CallService.getArrayFromCall(userGroups.getCalls()));
	                        }
	                        if (userGroups.getMails() != null) {
	                                value.put("mails", MailService.getJSONArrayFromMails(userGroups.getMails()).toString());
	                        }
	                        if (userGroups.getTag() != null) {
	                                value.put("tag", TagService.getJSONFromTagName(userGroups.getTag()));
	                        }
	                } catch (Exception e) {
	                }
	                return value;
	        }
	        public static String edit(Long groupId, String project,  String email,  Admin admin) {
                try {
                        if (groupId == null || groupId <= 0) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        UserGroups userGroups = findByGroupIdSimple(groupId);
                        if (project != null && project.trim().length() > 0) {
                        	userGroups.setProject(project);
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("project");
                        args2.add(userGroups.getProject());
                        if (update(userGroups)) {
                                return getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }
	        public static boolean update(UserGroups userGroups) {
                return UserGroupsDaoImpl.getInstance().update(userGroups);
        }
	        public static List<UserGroups> findByComming(String comming){
	                return UserGroupsDaoImpl.getInstance().findByComming(comming);
	        }
	        
	        public static void initLanguage(UserGroups userGroups){
	                if (userGroups.getTag().getLanguage() != null && userGroups.getTag().getLanguage().trim().length() > 0) {
	                        String[] languages = userGroups.getTag().getLanguage().split(OneTools.sp);
	                        if (languages != null && languages.length > 0) {
	                                StringBuilder builder = new StringBuilder();
	                                for (String l : languages) {
	                                        try {
	                                                int k = Integer.parseInt(l.trim());
	                                                if (k > 0) {
	                                                        builder.append(Language.getName(k)).append(OneTools.sp);
	                                                }
	                                        } catch (Exception e) {
	                                        }
	                                }
	                                userGroups.setLanguage(builder.toString());
	                        }
	                }
	        }
	        
	        public static int power(Long adminId, int group, UserGroups userGroups) {
	                if (userGroups != null && adminId != null) {
	                        initLanguage(userGroups);
	                        switch (group) {
	                        case 1:
	                                if (userGroups.getSale() == null || userGroups.getSale() <= 0L) {
	                                        userGroups.setLevel(0);
	                                        break;
	                                }
	                                userGroups.setSaleName(AccountService.findById(userGroups.getSale()).getName());
	                                userGroups.setLevel(adminId.equals(userGroups.getSale()) ? 1 : 0);
	                                break;
	                        case 2:
	                                if ((userGroups.getSupport() == null || userGroups.getSupport() <= 0) && (userGroups.getPreSale() == null || userGroups.getPreSale() <= 0)) {
	                                        userGroups.setLevel(2);
	                                        break;
	                                }
	                                if(userGroups.getSupport() != null && userGroups.getSupport() > 0L){
	                                        userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
	                                }
	                                if(userGroups.getPreSale() != null && userGroups.getPreSale() > 0){
	                                        userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
	                                }
	                                // info.setLevel(adminId.equals(info.getSupport())
	                                // ? 1 : 0);
	                                userGroups.setLevel(1);
	                                break;
	                        case 3:
	                                if ((userGroups.getSupport() == null || userGroups.getSupport() <= 0) && (userGroups.getPreSale() == null || userGroups.getPreSale() <= 0)) {
	                                        userGroups.setLevel(2);
	                                        break;
	                                }
	                                if(userGroups.getSupport() != null && userGroups.getSupport() > 0L){
	                                        userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
	                                }
	                                if(userGroups.getPreSale() != null || userGroups.getPreSale() > 0){
	                                        userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
	                                }
	                                // info.setLevel(adminId.equals(info.getPreSale())
	                                // ? 1 : 0);
	                                userGroups.setLevel(1);
	                                break;
	                        case 4:
	                                if (userGroups.getSale() != null && userGroups.getSale() > 0) {
	                                        userGroups.setSaleName(AccountService.findById(userGroups.getSale()).getName());
	                                }
	                                userGroups.setLevel(1);
	                                break;
	                        case 5:
	                                if (userGroups.getSupport() != null && userGroups.getSupport() > 0) {
	                                        userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
	                                }
	                                if (userGroups.getPreSale() != null && userGroups.getPreSale() > 0) {
	                                        userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
	                                }
	                                userGroups.setLevel(1);
	                                break;
	                        case 6:
	                                if (userGroups.getPreSale() != null && userGroups.getPreSale() > 0) {
	                                        userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
	                                }
	                                if (userGroups.getSupport() != null && userGroups.getSupport() > 0) {
	                                        userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
	                                }
	                                userGroups.setLevel(1);
	                                break;
	                        case 7:
	                                if (userGroups.getSale() != null && userGroups.getSale() > 0) {
	                                        userGroups.setSaleName(AccountService.findById(userGroups.getSale()).getName());
	                                }
	                                if (userGroups.getSupport() != null && userGroups.getSupport() > 0) {
	                                        userGroups.setSupportName(AccountService.findById(userGroups.getSupport()).getName());
	                                }
	                                if (userGroups.getPreSale() != null && userGroups.getPreSale() > 0) {
	                                        userGroups.setPreSaleName(AccountService.findById(userGroups.getPreSale()).getName());
	                                }
	                                userGroups.setLevel(1);
	                                break;
	                        default:
	                                userGroups.setLevel(0);
	                                break;
	                        }
	                }
	                return userGroups.getLevel();
	        }
}
