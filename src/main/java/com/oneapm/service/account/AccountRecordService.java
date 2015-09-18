package com.oneapm.service.account;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.Call;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Account.Account;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TagService;
import com.oneapm.service.show.CallService;
import com.oneapm.util.OneTools;
import com.oneapm.vo.Quanxian;
import com.oneapm.web.SupportAction;

public class AccountRecordService {

        protected static final Logger LOG = LoggerFactory.getLogger(AccountRecordService.class);

        @SuppressWarnings("unchecked")
        public static String findByAdminId(Admin admin, int type, boolean self, int page, int nowPage){
                try{
                        Account account = findByAdmin(admin, type, self, page, nowPage);
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("page", page);
                        object.put("tytpe", type);
                        object.put("pageNow", account.getPageNow());
                        object.put("pageTotal", account.getPageTotal());
                        object.put("size", account.getSize());
                        object.put("sizeTotal", account.getSizeTotal());
                        if(account.getInfos() != null){
                                object.put("infos", InfoService.getArrayFromInfos(account.getInfos()));
                        }
                        if(account.getCalls() != null){
                                object.put("calls", CallService.getArrayFromCall(account.getCalls()));
                        }
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage() ,e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        @SuppressWarnings("unchecked")
        public static String findGroupsByAdminId(Admin admin, int type, boolean self, int page, int nowPage){
                try{
                        Account account = findGroupsByAdmin(admin, type, self, page, nowPage);
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("page", page);
                        object.put("tytpe", type);
                        object.put("pageNow", account.getPageNow());
                        object.put("pageTotal", account.getPageTotal());
                        object.put("size", account.getSize());
                        object.put("sizeTotal", account.getSizeTotal());
                        if(account.getUserGroupsList() != null){
                                object.put("userGroupsList", UserGroupService.getArrayFromUserGroupsList(account.getUserGroupsList()));
                        }
                        if(account.getCalls() != null){
                                object.put("calls", CallService.getArrayFromCall(account.getCalls()));
                        }
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage() ,e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        public static String Admin(Admin admin){
                try{
                        JSONObject object = new JSONObject();
                        object.put("admin_id", admin.getId());
                        object.put("name", admin.getName());
                        object.put("username", admin.getUsername());
                        object.put("email", admin.getEmail());
                        object.put("status", 1);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage() ,e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        public static Account findByAdmin(Admin admin, int type, boolean self, int page, int nowPage) {
                int number = 30;
                if (admin == null)
                        return null;
                Account account = null;
                switch (page) {
                        case 0:
                                int infoSize = (int) InfoService.countAdminId(admin.getId());
                                if(infoSize <= 0){
                                        account = new Account(admin, null, null, null, null);
                                        account.setSize(0);
                                        account.setPageNow(1);
                                        account.setPageTotal(1);
                                        account.setSizeTotal(0);
                                }else{
                                        List<Info> infos = InfoService.findInfosByAdminId(admin.getId(), number, (number*nowPage));
                                        if (type > 0) {
                                                if (infos != null && infos.size() > 0) {
                                                        for (int i = 0; i < infos.size(); i++) {
                                                                if (!match(type, infos.get(i))) {
                                                                        infos.remove(i);
                                                                        i--;
                                                                }
                                                        }
                                                }
                                        }
                                        account = new Account(admin, null, null, null, infos);
                                        account.setSize(infos.size());
                                        account.setPageNow(nowPage+1);
                                        if(infoSize%number == 0){
                                                account.setPageTotal(infoSize/number);
                                        }else{
                                                account.setPageTotal(infoSize/number+1);
                                        }
                                        account.setSizeTotal(infoSize);
                                }
                                break;
                        case 1:
                                int callSize = (int) CallService.countByAdminId(admin.getId());
                                if(callSize <= 0){
                                        account = new Account(admin, null, null, null, null);
                                        account.setSize(0);
                                        account.setPageNow(1);
                                        account.setPageTotal(1);
                                        account.setSizeTotal(0);
                                }else{
                                        List<Call> calls = CallService.findByAccountId(admin.getId(), (number*nowPage), number);
                                        if (type > 0) {
                                                if (self) {
                                                        if (calls != null && calls.size() > 0) {
                                                                for (int i = 0; i < calls.size(); i++) {
                                                                        if (!match(type, calls.get(i).getInfoId())) {
                                                                                calls.remove(i);
                                                                                i--;
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                        account = new Account(admin, null, null, calls, null);
                                        account.setSize(calls.size());
                                        
                                        account.setSize(calls.size());
                                        account.setPageNow(nowPage+1);
                                        if(callSize%number == 0){
                                                account.setPageTotal(callSize/number);
                                        }else{
                                                account.setPageTotal(callSize/number+1);
                                        }
                                        account.setSizeTotal(callSize);
                                }
                                break;
                        default:
                                break;
                }
                try{
                        List<Quanxian> quanxians = new ArrayList<Quanxian>();
                        for (Quanxian quanxian : SupportAction.getGRADE().getList()) {
                                quanxians.add(new Quanxian(quanxian.getId(), quanxian.getQuanxian(), quanxian.getName(), quanxian.getAll()));
                        }
                        if (admin.getGrades() != null && admin.getGrades().trim().length() > 0) {
                                for (int i = 0; i < quanxians.size(); i++) {
                                        if (admin.getGrades().indexOf(quanxians.get(i).getQuanxian()) > -1) {
                                                quanxians.get(i).setStatus(1);
                                        } else {
                                                if (admin.getGrades().indexOf(SupportAction.getGRADE().getMap().get(999).getQuanxian()) > -1 && quanxians.get(i).getAll() == 1) {
                                                        quanxians.get(i).setStatus(1);
                                                }
                                        }
                                }
                        }
                        account.setQuanxians(quanxians);
                }catch(Exception e){
                        LOG.error(e.getMessage() ,e);
                }
                return account;
        }
        

        
        public static Account findGroupsByAdmin(Admin admin, int type, boolean self, int page, int nowPage) {
            int number = 30;
            if (admin == null)
                    return null;
            Account account = null;
            switch (page) {
                    case 0:
                            int userGroupsSize = (int) UserGroupService.countAdminId(admin.getId());
                            if(userGroupsSize <= 0){
                                    account = new Account(admin, null, null, null, null);
                                    account.setSize(0);
                                    account.setPageNow(1);
                                    account.setPageTotal(1);
                                    account.setSizeTotal(0);
                            }else{
                                    List<UserGroups> userGroupsList = UserGroupService.findUserGroupsListByAdminId(admin.getId(), number, (number*nowPage));
                                    if (type > 0) {
                                            if (userGroupsList != null && userGroupsList.size() > 0) {
                                                    for (int i = 0; i < userGroupsList.size(); i++) {
                                                            if (!matchGroups(type, userGroupsList.get(i))) {
                                                            	userGroupsList.remove(i);
                                                                    i--;
                                                            }
                                                    }
                                            }
                                    }
                                    account = new Account(admin, null, null, null);
                                    account.setUserGroupsList(userGroupsList);
                                    account.setSize(userGroupsList.size());
                                    account.setPageNow(nowPage+1);
                                    if(userGroupsSize%number == 0){
                                            account.setPageTotal(userGroupsSize/number);
                                    }else{
                                            account.setPageTotal(userGroupsSize/number+1);
                                    }
                                    account.setSizeTotal(userGroupsSize);
                            }
                            break;
                    case 1:
                            int callSize = (int) CallService.countByAdminId(admin.getId());
                            if(callSize <= 0){
                                    account = new Account(admin, null, null, null);
                                    account.setSize(0);
                                    account.setPageNow(1);
                                    account.setPageTotal(1);
                                    account.setSizeTotal(0);
                            }else{
                                    List<Call> calls = CallService.findByAccountId(admin.getId(), (number*nowPage), number);
                                    if (type > 0) {
                                            if (self) {
                                                    if (calls != null && calls.size() > 0) {
                                                            for (int i = 0; i < calls.size(); i++) {
                                                            	if(calls.get(i).getGroupId()!=null){
                                                                    if (!matchGroupId(type, calls.get(i).getGroupId())) {
                                                                            calls.remove(i);
                                                                            i--;
                                                                    }
                                                            	}
                                                            }
                                                    }
                                            }
                                    }
                                    account = new Account(admin, null, null, calls, null);
                                    account.setSize(calls.size());
                                    
                                    account.setSize(calls.size());
                                    account.setPageNow(nowPage+1);
                                    if(callSize%number == 0){
                                            account.setPageTotal(callSize/number);
                                    }else{
                                            account.setPageTotal(callSize/number+1);
                                    }
                                    account.setSizeTotal(callSize);
                            }
                            break;
                    default:
                            break;
            }
            try{
                    List<Quanxian> quanxians = new ArrayList<Quanxian>();
                    for (Quanxian quanxian : SupportAction.getGRADE().getList()) {
                            quanxians.add(new Quanxian(quanxian.getId(), quanxian.getQuanxian(), quanxian.getName(), quanxian.getAll()));
                    }
                    if (admin.getGrades() != null && admin.getGrades().trim().length() > 0) {
                            for (int i = 0; i < quanxians.size(); i++) {
                                    if (admin.getGrades().indexOf(quanxians.get(i).getQuanxian()) > -1) {
                                            quanxians.get(i).setStatus(1);
                                    } else {
                                            if (admin.getGrades().indexOf(SupportAction.getGRADE().getMap().get(999).getQuanxian()) > -1 && quanxians.get(i).getAll() == 1) {
                                                    quanxians.get(i).setStatus(1);
                                            }
                                    }
                            }
                    }
                    account.setQuanxians(quanxians);
            }catch(Exception e){
                    LOG.error(e.getMessage() ,e);
            }
            return account;
    }
    
        public static boolean match(int type, Info info) {
                switch (type) {
                case 1:
                        return info.getTag().getMetric() == 1;
                case 2:
                        return info.getTag().getMetric() == 2;
                case 3:
                        return info.getTag().getMetric() == 3;
                default:
                        break;
                }
                return false;
        }
        
        public static boolean matchGroups(int type, UserGroups userGroups) {
            switch (type) {
            case 1:
                    return userGroups.getTag().getMetric() == 1;
            case 2:
                    return userGroups.getTag().getMetric() == 2;
            case 3:
                    return userGroups.getTag().getMetric() == 3;
            default:
                    break;
            }
            return false;
    }
        
        public static boolean match(int type, Long infoId) {
                switch (type) {
                case 1:
                        return TagService.findByInfoId(infoId).getMetric() == 1;
                case 2:
                        return TagService.findByInfoId(infoId).getMetric() == 2;
                case 3:
                        return TagService.findByInfoId(infoId).getMetric() == 3;
                default:
                        break;
                }
                return false;
        }
        
        public static boolean matchGroupId(int type, Long groupId) {
            switch (type) {
            case 1:
                    return TagService.findByGroupId(groupId).getMetric() == 1;
            case 2:
                    return TagService.findByGroupId(groupId).getMetric() == 2;
            case 3:
                    return TagService.findByGroupId(groupId).getMetric() == 3;
            default:
                    break;
            }
            return false;
    }
}
