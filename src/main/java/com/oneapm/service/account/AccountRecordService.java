package com.oneapm.service.account;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.Call;
import com.oneapm.dto.Account.Account;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TagService;
import com.oneapm.service.show.CallService;
import com.oneapm.util.OneTools;

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
        public static Account findByAdmin(Admin admin, int type, boolean self, int page, int nowPage) {
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
                                        List<Info> infos = InfoService.findInfosByAdminId(admin.getId(), 50, (50*nowPage));
                                        if (type > 0) {
                                                if (infos != null && infos.size() > 0) {
                                                        for (int i = 0; i < infos.size(); i++) {
                                                                if (!match(type, infos.get(i).getId())) {
                                                                        infos.remove(i);
                                                                        i--;
                                                                }
                                                        }
                                                }
                                        }
                                        account = new Account(admin, null, null, null, infos);
                                        account.setSize(infos.size());
                                        account.setPageNow(nowPage+1);
                                        if(infoSize%50 == 0){
                                                account.setPageTotal(infoSize/50);
                                        }else{
                                                account.setPageTotal(infoSize/50+1);
                                        }
                                        account.setSizeTotal(infoSize);
                                }
                                break;
                        case 1:
                                int callSize = (int) CallService.countByAdminId(admin.getId());
                                List<Call> calls = null;
                                if (self) {
                                        calls = CallService.findByAccountId(admin.getId(), (50*nowPage), 50);
                                }
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
                                if(callSize%50 == 0){
                                        account.setPageTotal(callSize/50);
                                }else{
                                        account.setPageTotal(callSize/50+1);
                                }
                                account.setSizeTotal(callSize);
                                break;
                        default:
                                break;
                }
                return account;
        }

        public static boolean match(int type, Long infoId) {
                switch (type) {
                case 1:
                        return TagService.findByInfoId(infoId).getMetric() == 1;
                case 2:
                        return TagService.findByInfoId(infoId).getMetric() == 2;
                case 3:
                        return TagService.findByInfoId(infoId).getMetric() == 3;
                case 4:
                        return TagService.findByInfoId(infoId).getMetric() == 4;
                case 5:
                        return TagService.findByInfoId(infoId).getLoudou() == 0;
                case 6:
                        return TagService.findByInfoId(infoId).getLoudou() == 1;
                case 7:
                        return TagService.findByInfoId(infoId).getLoudou() == 2;
                case 8:
                        return TagService.findByInfoId(infoId).getLoudou() == 3;
                case 9:
                        return TagService.findByInfoId(infoId).getLoudou() == 4;
                case 10:
                        return TagService.findByInfoId(infoId).getLoudou() == 5;
                default:
                        break;
                }
                return false;
        }
}
