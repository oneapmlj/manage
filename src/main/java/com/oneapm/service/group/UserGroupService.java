package com.oneapm.service.group;

import java.util.List;

import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dao.group.impl.UserGroupDaoImpl;
import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Zhengzailianxi;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupView;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.dto.info.Info;
import com.oneapm.record.MailPush;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.card.CardService;
import com.oneapm.service.info.AppService;
import com.oneapm.service.info.GuanlianService;
import com.oneapm.service.info.KFService;
import com.oneapm.service.info.MarkService;
import com.oneapm.service.info.TagService;
import com.oneapm.service.info.TaskService;
import com.oneapm.service.info.ZhengzailianxiService;
import com.oneapm.service.mail.MailService;
import com.oneapm.service.show.CallService;
import com.oneapm.util.TimeTools;
import com.oneapm.dto.UserGroup;

public class UserGroupService {
	public static UserGroups findGroupsById(Long admin_id){
		return UserGroupsDaoImpl.getInstance().findByAdminId(admin_id);
	}
	public static List<UserGroup> findUsersByGroupId(Long groupId){
		return UserGroupDaoImpl.getInstance().findUsersByGroupId(groupId);
	}
	
    public static UserGroups findByGroupId(Long groupId, Admin admin) {
    	UserGroups userGroups = UserGroupsDaoImpl.getInstance().findByAdminId(groupId);
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
        if(userGroups.getGroupId() != null){
                List<Guanlian> guanlians = GuanlianService.findByUserId(userGroups.getGroupId());
                userGroups.setGuanlians(guanlians);
        }
        return userGroups;
}
    
    
    
    
    public static UserGroups findByGroupIdSingle(Long groupId) {
    	UserGroups userGroups = UserGroupsDaoImpl.getInstance().findByAdminId(groupId);
        initTag(userGroups);
        return userGroups;
    }
    
    
    
	public static void initUserGroups(UserGroups userGroups) {
		userGroups.setMails(MailService.findMailsById(userGroups.getGroupId()));
		userGroups.setCalls(CallService.findByGroupId(userGroups.getGroupId()));
		userGroups.setApps(AppService.findByUserId(userGroups.getGroupId()));
		//userGroups.setCards(CardService.findByInfoId(userGroups.getGroupId()));
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
}
