package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dto.Zhengzailianxi;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.card.Card;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupView;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.tag.Language;
import com.oneapm.dto.tag.Tag;
import com.oneapm.record.MailPush;
import com.oneapm.record.Message;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.account.PayService;
import com.oneapm.service.card.CardService;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.mail.DownloadService;
import com.oneapm.service.mail.MailService;
import com.oneapm.service.message.MessageService;
import com.oneapm.service.record.RecordService;
import com.oneapm.service.show.CallService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.web.SupportAction;

public class InfoService extends OneTools {

        protected static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

        public static Info findByEmail(String email) {
                return InfoDaoImpl.getInstance().findByEmail(email);
        }

        public static List<Info> countSign() {
                return InfoDaoImpl.getInstance().countSign();
        }
        
        public static List<Info> findAll(){
                return InfoDaoImpl.getInstance().findAll();
        }
        
        public static List<Info> findEmail(int emailStatus){
                return InfoDaoImpl.getInstance().findEmail(emailStatus);
        }

        public static String delete(Admin admin, Long id, int type) {
                try {
                        Info info = findByIdSimple(id);
                        switch (type) {
                        case 1:
                                if (info.getSale() != null && info.getSale().equals(admin.getId())) {
                                        info.setSale(null);
                                        InfoDaoImpl.getInstance().updateOwner(info);
                                        return OneTools.getResult(1, "");
                                } else {
                                        return OneTools.getResult(0, "参数错误");
                                }
                        case 2:
                                if (info.getSupport() != null && info.getSupport().equals(admin.getId())) {
                                        info.setSupport(null);
                                        InfoDaoImpl.getInstance().updateOwner(info);
                                        return OneTools.getResult(1, "");
                                } else {
                                        return OneTools.getResult(0, "参数错误");
                                }
                        case 3:
                                if (info.getPreSale() != null && info.getPreSale().equals(admin.getId())) {
                                        info.setPreSale(null);
                                        InfoDaoImpl.getInstance().updateOwner(info);
                                        return OneTools.getResult(1, "");
                                } else {
                                        return OneTools.getResult(0, "参数错误");
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String back(Admin admin, int type, Long id) {
                try {
                        Info info = null;
                        Long adminId = null;
                        switch (type) {
                        case 1:
                                if (admin.getGroup() != 4 && admin.getGroup() <= 6) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getSale() == null) {
                                        return OneTools.getResult(0, "负责人出错，请刷新重试");
                                }
                                adminId = info.getSale();
                                info.setSale(0L);
                                if (InfoDaoImpl.getInstance().updateOwner(info)) {
                                        MessageService.insert(admin.getId(), adminId, 0, id, 16);
                                        RecordService.insert(admin.getId(), 16, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        TaskService.close(id, admin.getId());
                                } else {
                                        return OneTools.getResult(0, "数据更新失败");
                                }
                                break;
                        case 2:
                                if (admin.getGroup() != 5 && admin.getGroup() != 6 && admin.getGroup() < 7) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getSupport() == null) {
                                        return OneTools.getResult(0, "负责人出错，请刷新重试");
                                }
                                adminId = info.getSupport();
                                info.setSupport(0L);
                                InfoDaoImpl.getInstance().updateOwner(info);
                                MessageService.insert(admin.getId(), adminId, 0, id, 17);
                                RecordService.insert(admin.getId(), 17, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        case 3:
                                if (admin.getGroup() != 5 && admin.getGroup() != 6 && admin.getGroup() < 7) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getPreSale() == null) {
                                        return OneTools.getResult(0, "负责人出错，请刷新重试");
                                }
                                adminId = info.getPreSale();
                                info.setPreSale(0L);
                                InfoDaoImpl.getInstance().updateOwner(info);
                                MessageService.insert(admin.getId(), adminId, 0, id, 18);
                                RecordService.insert(admin.getId(), 18, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        }
                        return OneTools.getResult(1, "");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String assign(Admin admin, int type, Long id, Long adminId) {
                try {
                        Info info = null;
                        switch (type) {
                        case 1:
                                if (admin.getGroup() != 4 && admin.getGroup() <= 6) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getSale() != null && info.getSale() > 0) {
                                        return OneTools.getResult(0, "已有有负责人");
                                }
                                info.setSale(adminId);
                                update(info);
                                MessageService.insert(admin.getId(), adminId, 0, id, 9);
                                RecordService.insert(admin.getId(), 9, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        case 2:
                                if (admin.getGroup() != 5 &&  admin.getGroup() != 6 && admin.getGroup() <= 6 && admin.getGroup() != 2 && admin.getGroup() != 3) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        return OneTools.getResult(0, "已有有负责人");
                                }
                                info.setSupport(adminId);
                                update(info);
                                MessageService.insert(admin.getId(), adminId, 0, id, 10);
                                RecordService.insert(admin.getId(), 10, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        case 3:
                                if (admin.getGroup() != 6 && admin.getGroup() != 5  && admin.getGroup() < 7  && admin.getGroup() != 2 && admin.getGroup() != 3) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                info = findByIdSingle(id);
                                if (info.getPreSale() != null && info.getPreSale() > 0) {
                                        return OneTools.getResult(0, "已有有负责人");
                                }
                                info.setPreSale(adminId);
                                update(info);
                                MessageService.insert(admin.getId(), adminId, 0, id, 11);
                                RecordService.insert(admin.getId(), 11, id, adminId, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        }
                        return OneTools.getResult(1, "");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        /**
         * 获取添加的信息
         * 
         * @param adminId
         * @return
         */
        public static List<Info> findInfosByAdminId(Long adminId) {
                List<Info> infos = InfoDaoImpl.getInstance().findByAdminId(adminId);
                if (infos != null) {
                        for (Info info : infos) {
                                initAdmin(info);
                                initTag(info);
                        }
                }
                return infos;
        }

        @SuppressWarnings("unchecked")
        public static String change(Admin admin, Long from, Long infoId, int type, Long messageId) {
                JSONObject object = new JSONObject();
                try {
                        Info info = findByIdSingle(infoId);
                        Message message = null;
                        List<Admin> admins = null;
                        object.put("status", 0);
                        object.put("id", messageId);
                        if (info == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        switch (type) {
                        case 1:
                                if (admin.getId() < 80000000L || admin.getId() >= 90000000L) {
                                        return OneTools.getResult(0, "无权限");
                                }
                                if (MessageService.insert(admin.getId(), info.getSale(), 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSale(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        return object.toJSONString();
                                }
                                break;
                        case 2:
                                if (admin.getId() >= 80000000L && admin.getId() < 90000000L) {
                                        return OneTools.getResult(0, "无权限");
                                }
                                if (MessageService.insert(admin.getId(), info.getSupport(), 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSale(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        return object.toJSONString();
                                }
                                break;
                        case 3:
                                if (!info.getSale().equals(admin.getId())) {
                                        return OneTools.getResult(0, "无权限");
                                }
                                info.setSale(from);
                                update(info);
                                MessageService.agree(messageId);
                                if (MessageService.insert(admin.getId(), from, 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSale(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        object.put("id", messageId);
                                        return object.toJSONString();
                                }
                                break;
                        case 4:
                                if (!info.getSupport().equals(admin.getId())) {
                                        return OneTools.getResult(0, "无权限");
                                }
                                info.setSupport(from);
                                update(info);
                                MessageService.agree(messageId);
                                if (MessageService.insert(admin.getId(), from, 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSupport(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        object.put("id", messageId);
                                        return object.toJSONString();
                                }
                                break;
                        case 5:
                                MessageService.close(messageId);
                                if (MessageService.insert(admin.getId(), from, 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSupport(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        object.put("id", messageId);
                                        return object.toJSONString();
                                }
                                break;
                        case 6:
                                MessageService.close(messageId);
                                if (MessageService.insert(admin.getId(), from, 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSupport(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        object.put("id", messageId);
                                        return object.toJSONString();
                                }
                                break;
                        case 7:
                                if (info.getSale() == null || info.getSale() <= 0) {
                                        return OneTools.getResult(0, "参数错误");
                                }
                                message = MessageService.findApplyByInfoId(infoId, type, 0);
                                if (message != null) {
                                        return OneTools.getResult(0, "已经提醒过了");
                                }
                                if (MessageService.insert(admin.getId(), info.getSale(), 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSale(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        return object.toJSONString();
                                }
                                break;
                        case 8:
                                if (info.getSupport() == null || info.getSupport() <= 0) {
                                        return OneTools.getResult(0, "参数错误");
                                }
                                message = MessageService.findApplyByInfoId(infoId, type, 0);
                                if (message != null) {
                                        return OneTools.getResult(0, "已经提醒过了");
                                }
                                if (MessageService.insert(admin.getId(), info.getSupport(), 0, infoId, type)) {
                                        RecordService.insert(admin.getId(), type, infoId, info.getSupport(), 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                        object.put("status", 1);
                                        return object.toJSONString();
                                }
                                break;
                        case 9:
                                if (!SupportAction.quanxian(admin.getGrades(), SupportAction.getGRADE().getMap().get(105))) {
                                        return OneTools.getResult(0, "权限不足");
                                }
                                if (info.getSale() != null && info.getSale() > 0) {
                                        return OneTools.getResult(0, "已有负责人");
                                }
                                message = MessageService.findApplyByInfoId(infoId, type, 0);
                                if (message != null) {
                                        return OneTools.getResult(0, "已经有人推荐");
                                }
                                // if(MessageService.insert(admin.getId(), from,
                                // 0, infoId, type)){
                                admins = AccountService.findSales();
                                if (admins == null) {
                                        return OneTools.getResult(0, "服务器错误");
                                }
                                for (Admin ad : admins) {
                                        MessageService.insert(admin.getId(), ad.getId(), 0, infoId, type);
                                }
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("status", 1);
                                return object.toJSONString();
                                // }
                                // break;
                        case 10:
                                if (!SupportAction.quanxian(admin.getGrades(), SupportAction.getGRADE().getMap().get(105))) {
                                        return OneTools.getResult(0, "无权限");
                                }
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        return OneTools.getResult(0, "已有负责人");
                                }
                                message = MessageService.findApplyByInfoId(infoId, type, 0);
                                if (message != null) {
                                        return OneTools.getResult(0, "已经有人推荐");
                                }
                                admins = AccountService.findSupports();
                                if (admins == null) {
                                        return OneTools.getResult(0, "服务器错误");
                                }
                                for (Admin ad : admins) {
                                        MessageService.insert(admin.getId(), ad.getId(), 0, infoId, type);
                                }
                                // if(MessageService.insert(admin.getId(), from,
                                // 0, infoId, type)){
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("status", 1);
                                return object.toJSONString();
                                // }
                                // break;
                        case 11:
                                if (info.getSale() != null && info.getSale() > 0) {
                                        MessageService.agree(messageId);
                                        return OneTools.getResult(0, "已有负责人");
                                }
                                MessageService.agree(messageId);
                                info.setSale(admin.getId());
                                update(info);
                                // if(MessageService.insert(admin.getId(), from,
                                // 0, infoId, type)){
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("status", 1);
                                object.put("id", messageId);
                                return object.toJSONString();
                                // }
                                // break;
                        case 12:
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        MessageService.agree(messageId);
                                        return OneTools.getResult(0, "已有负责人");
                                }
                                MessageService.agree(messageId);
                                info.setSupport(admin.getId());
                                update(info);
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("status", 1);
                                return object.toJSONString();
                        case 13:
                                MessageService.close(messageId);
                                // if(MessageService.insert(admin.getId(), from,
                                // 0, infoId, type)){
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("id", messageId);
                                object.put("status", 1);
                                return object.toJSONString();
                                // }
                                // break;
                        case 14:
                                MessageService.close(messageId);
                                // if(MessageService.insert(admin.getId(), from,
                                // 0, infoId, type)){
                                RecordService.insert(admin.getId(), type, infoId, from, 0, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                object.put("status", 1);
                                object.put("id", messageId);
                                return object.toJSONString();
                                // }
                                // break;
                        default:
                                break;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
                return OneTools.getResult(0, "操作失败");
        }

        public static Info findByIdSimple(Long id) {
                return InfoDaoImpl.getInstance().findById(id);
        }
        
        public static boolean update_xiaoshouyi(Info info, String lableId){
                return InfoDaoImpl.getInstance().update_xiaoshouyi(info, lableId);
        }

        public static Info findByUserIdSimple(Long userId) {
                return InfoDaoImpl.getInstance().findByUserId(userId);
        }
        
        public static List<Info> onlianxi(Admin admin){
                List<Info> infos = null;
                try{
                        List<Zhengzailianxi> zhengzailianxis = ZhengzailianxiService.findByAdminId(admin.getId());
                        if(zhengzailianxis != null && zhengzailianxis.size() > 0){
                                infos = new ArrayList<Info>();
                                for(Zhengzailianxi zhengzailianxi : zhengzailianxis){
                                        Info info = findByIdSimple(zhengzailianxi.getInfoId());
                                        info.setZhengzailianxi(zhengzailianxi);
                                        infos.add(info);
                                }
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }

        public static Info findById(Long id, Admin admin) {
                Info info = InfoDaoImpl.getInstance().findById(id);
                if (info != null) {
                        try{
                                Zhengzailianxi zhengzailianxi = ZhengzailianxiService.findByInfoId(info.getId());
                                if(zhengzailianxi != null){
                                        zhengzailianxi.setAdminName(AccountService.findName(zhengzailianxi.getAdminId()));
                                }
                                info.setZhengzailianxi(zhengzailianxi);
                                try{
                                        TimeTools.formatTime.parse(info.getExpireTime());
                                        int day = TimeTools.apartDay(info.getExpireTime(), TimeTools.format());
                                        day = day >= 0 ? day : -1;
                                        info.setDaoqi(day);
                                        if(info.getPayLevel() == 0){
                                                if(day < 0){
                                                        info.setPayLevel(40);
                                                }else{
                                                        info.setPayLevel(30);
                                                }
                                                info.setPay_level("线下用户");
                                        }else{
                                                switch (info.getPayLevel()) {
                                                case 10:info.setPay_level("免费用户");break;
                                                case 20:info.setPay_level("免费到期");break;
                                                case 30:info.setPay_level("付费用户");break;
                                                case 40:info.setPay_level("付费到期");break;
                                                default:info.setPay_level("未知");break;
                                                }
                                        }
                                        info.setExpireTime(info.getExpireTime().substring(0, 19));
                                }catch(Exception e){
                                        info.setExpireTime("未添加");
                                        info.setDaoqi(-2);
                                        info.setPay_level("未知");
                                }
                        }catch(Exception e){}
                        initTag(info);
                        int power = power(admin.getId(), admin.getGroup(), info);
                        if (power > 0) {
                                initInfo(info);
                        } else {
                                return null;
                        }
                        info.setMark(MarkService.findByAdminIdAndInfoId(admin.getId(), id));
//                        info.setTip(TipService.findByInfoId(info.getId()));
                        List<MailPush> pushs = TaskService.findByInfoId(id);
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
                                info.setPushs(pushs);
                                TaskService.touch(id, admin.getId(), TimeTools.format());
                        }
                }
                
                return info;
        }
        /**
         * 非显示查询
         * @param id
         * @return
         */
        public static Info findById(Long id) {
                Info info = InfoDaoImpl.getInstance().findById(id);
                if (info != null) {
                        initTag(info);
                        power(99999999L, 7, info);
                        initSupport(info);
                }
                return info;
        }

        public static String edit(Long id, String project, String qq, String name, String phone, String email, String expireTime, int gender, int pay_level, Admin admin) {
                try {
                        if (id == null || id <= 0) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        Info info = findByIdSimple(id);
                        if (project != null && project.trim().length() > 0) {
                                info.setProject(project);
                        }
                        if(gender >= 0 && gender <= 2){
                                info.setGender(gender);
                        }
                        if (qq != null && qq.trim().length() > 0) {
                                info.setQq(qq);
                        }
                        if (name != null && name.trim().length() > 0) {
                                info.setName(name);
                        }
                        if (phone != null && phone.trim().length() > 0) {
                                info.setPhone(phone);
                        }
                        if (email != null && email.trim().length() > 0) {
                                info.setEmail(email);
                        }
                        if (expireTime != null && expireTime.trim().length() > 0) {
                                if(!admin.getId().equals(99999999L)){
                                        return OneTools.getResult(0, "权限不足");
                                }
                                if(info.getUserId() != null){
                                        if(PayService.pay(info.getUserId(), expireTime, "运营系统管理员添加", admin, pay_level)){
                                                info.setExpireTime(expireTime);
                                                info.setPayLevel(pay_level);
                                        }else {
                                                return OneTools.getResult(0, "api更新失败");
                                        }
                                }else{
                                        info.setExpireTime(expireTime);  
                                        info.setPayLevel(pay_level);
                                }
                        }
                        try{
                                TimeTools.formatTime.parse(info.getExpireTime());
                                int day = TimeTools.apartDay(info.getExpireTime(), TimeTools.format());
                                day = day >= 0 ? day : -1;
                                info.setDaoqi(day);
                                if(info.getPayLevel() == 0){
                                        if(day < 0){
                                                info.setPayLevel(40);
                                        }else{
                                                info.setPayLevel(30);
                                        }
                                }
                        }catch(Exception e){
                                info.setExpireTime("未添加");
                                info.setDaoqi(-2);
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("qq");
                        args1.add("project");
                        args1.add("name");
                        args1.add("phone");
                        args1.add("email");
                        args1.add("expireTime");
                        args1.add("payLevel");
                        args1.add("daoqi");
                        args1.add("gender");
                        args2.add(info.getQq());
                        args2.add(info.getProject());
                        args2.add(info.getName());
                        args2.add(info.getPhone());
                        args2.add(info.getEmail());
                        args2.add(info.getExpireTime());
                        args2.add(info.getPayLevel());
                        args2.add(info.getDaoqi());
                        args2.add(info.getGender());
                        if (update(info)) {
                                return getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }

        public static List<Info> findByLanguage(int language) {
                List<Info> infos = new ArrayList<Info>();
                String lang = new String(language + "").trim();
                List<Tag> tags = TagService.findByLanguage(lang);
                if (tags == null)
                        return null;
                for (Tag tag : tags) {
                        infos.add(findByIdSimple(tag.getInfoId()));
                }
                return infos;
        }
        
        public static List<Info> findByLoginTime(String loginTime){
                List<Info> infos = new ArrayList<Info>();
                try{
                        infos = InfoDaoImpl.getInstance().findByLoginTime(loginTime);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }

        public static void initLanguage(Info info){
                if (info.getTag().getLanguage() != null && info.getTag().getLanguage().trim().length() > 0) {
                        String[] languages = info.getTag().getLanguage().split(OneTools.sp);
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
                                info.setLanguage(builder.toString());
                        }
                }
        }
        public static int power(Long adminId, int group, Info info) {
                if (info != null && adminId != null) {
                        initLanguage(info);
                        switch (group) {
                        case 1:
                                if (info.getSale() == null || info.getSale() <= 0L) {
                                        info.setLevel(0);
                                        break;
                                }
                                info.setSaleName(AccountService.findById(info.getSale()).getName());
                                info.setLevel(adminId.equals(info.getSale()) ? 1 : 0);
                                break;
                        case 2:
                                if (info.getSupport() == null || info.getSupport() <= 0) {
                                        info.setLevel(2);
                                        break;
                                }
                                info.setSupportName(AccountService.findById(info.getSupport()).getName());
                                // info.setLevel(adminId.equals(info.getSupport())
                                // ? 1 : 0);
                                info.setLevel(1);
                                break;
                        case 3:
                                if (info.getPreSale() == null || info.getPreSale() <= 0) {
                                        info.setLevel(2);
                                        break;
                                }
                                info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                                // info.setLevel(adminId.equals(info.getPreSale())
                                // ? 1 : 0);
                                info.setLevel(1);
                                break;
                        case 4:
                                if (info.getSale() != null && info.getSale() > 0) {
                                        info.setSaleName(AccountService.findById(info.getSale()).getName());
                                }
                                info.setLevel(1);
                                break;
                        case 5:
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        info.setSupportName(AccountService.findById(info.getSupport()).getName());
                                }
                                if (info.getPreSale() != null && info.getPreSale() > 0) {
                                        info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                                }
                                info.setLevel(1);
                                break;
                        case 6:
                                if (info.getPreSale() != null && info.getPreSale() > 0) {
                                        info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                                }
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        info.setSupportName(AccountService.findById(info.getSupport()).getName());
                                }
                                info.setLevel(1);
                                break;
                        case 7:
                                if (info.getSale() != null && info.getSale() > 0) {
                                        info.setSaleName(AccountService.findById(info.getSale()).getName());
                                }
                                if (info.getSupport() != null && info.getSupport() > 0) {
                                        info.setSupportName(AccountService.findById(info.getSupport()).getName());
                                }
                                if (info.getPreSale() != null && info.getPreSale() > 0) {
                                        info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                                }
                                info.setLevel(1);
                                break;
                        default:
                                info.setLevel(0);
                                break;
                        }
                }
                return info.getLevel();
        }

        public static Info findByIdSingle(Long id) {
                Info info = InfoDaoImpl.getInstance().findById(id);
                initTag(info);
                return info;
        }

        public static Info findByUserIdSingle(Long id) {
                Info info = InfoDaoImpl.getInstance().findByUserId(id);
                if(info == null)return null;
                initTag(info);
                return info;
        }

        // public static Info findInfoById(Long id){
        // Info info = InfoDaoImpl.getInstance().findById(id);
        // return info;
        // }

        public static Info findByUserId(Long userId, Admin admin) {
                Info info = InfoDaoImpl.getInstance().findByUserId(userId);
                return findById(info.getId(), admin);
        }
        /**
         * 非显示
         * @param userId
         * @return
         */
        public static Info findByUserId(Long userId) {
                Info info = InfoDaoImpl.getInstance().findByUserId(userId);
                if (info != null) {
                        initTag(info);
                        power(99999999L, 7, info);
                        initSupport(info);
                }
                return info;
        }

        public static void initInfo(Info info) {
                info.setMails(MailService.findMailsById(info.getId()));
                info.setCalls(CallService.findByInfoId(info.getId()));
                info.setApps(AppService.findByUserId(info.getUserId()));
                info.setDownloads(DownloadService.findByUserId(info.getUserId()));
                info.setCards(CardService.findByInfoId(info.getId()));
                info.setGongdans(KFService.findByUserId(info.getUserId()));
                initTag(info);
                initAdmin(info);
                initSupport(info);
        }
        
        public static void initSupport(Info info){
                if (info.getSale() != null && info.getSale() > 0L) {
                        info.setSaleName(AccountService.findById(info.getSale()).getName());
                }
                if (info.getSupport() != null && info.getSupport() > 0L) {
                        info.setSupportName(AccountService.findById(info.getSupport()).getName());
                }
                if (info.getPreSale() != null && info.getPreSale() > 0L) {
                        info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                }
        }

        public static void initTag(Info info) {
                info.setTag(TagService.findByInfoId(info.getId()));
                if(info.getUserId() != null){
                        GroupView view = GroupService.findByInfoId(info.getId());
                        if(view == null){
                                return;
                        }
                        Group group = GroupService.findById(view.getGroupId());
                        info.setGroup(group);
                        if(group.getFather() > 0){
                                Group father = GroupService.findById(group.getFather());
                                info.setGroupFather(father);
                        }
                }
        }

        /**
         * 初始化负责人信息
         * 
         * @param info
         */
        public static void initAdmin(Info info) {
                if (info == null)
                        return;
                if (info.getAdminId() != null) {
                        boolean update = false;
                        Admin admin = AccountService.findById(info.getAdminId());
                        if (admin.getGroup() == 1) {
                                if (info.getSale() == null) {
                                        info.setSale(info.getAdminId());
                                        update = true;
                                }
                        } else {
                                if (admin.getGroup() == 2) {
                                        info.setSupport(info.getAdminId());
                                        update = true;
                                }
                        }
                        if (update) {
                                update(info);
                        }
                }
        }

        @SuppressWarnings("unchecked")
        public static String findJsonByUserId(Long userId) {
                Info info = InfoDaoImpl.getInstance().findByUserId(userId);
                if (info != null) {
                        info.setMails(MailService.findMailsById(info.getId()));
                        info.setCalls(CallService.findByInfoId(info.getId()));
                }
                JSONObject object = new JSONObject();
                try {
                        object.put("info", getJSONFromInfo(info));
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误!");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String findJsonById(Long id) {
                Info info = InfoDaoImpl.getInstance().findById(id);
                if (info == null) {
                        return OneTools.getResult(0, "用户不存在");
                }
                JSONObject object = new JSONObject();
                try {
                        object.put("info", getJSONFromInfo(info));
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误!");
                }
                return object.toJSONString();
        }

        public static Long findIdest() {
                Long id = InfoDaoImpl.getInstance().findIdest();
                if (id == null || id <= 0) {
                        id = 100L;
                }
                return id;
        }

        public static boolean update(Info info) {
                return InfoDaoImpl.getInstance().update(info);
        }

        @SuppressWarnings("unchecked")
        public static String insertInfo(Card card, String project, String company) {
                JSONObject object = new JSONObject();
                try {
                        Info info = new Info(null, card.getName(), card.getEmail(), company, card.getPhone(), null, TimeTools.format(), null, null, card.getFrom(), null, null, null, null, 0, card.getQq(), company);
                        info = InfoService.insertAndGet(info);
                        if (info == null) {
                                return OneTools.getResult(0, "添加失败");
                        }
                        card.setInfoId(info.getId());
                        CardService.insertAndGet(card);
                        object.put("status", 1);
                        object.put("infoId", info.getId());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
                return object.toJSONString();
        }

        public static Info insertAndGet(Info info) {
                return InfoDaoImpl.getInstance().insertAndGet(info);
        }

        @SuppressWarnings("unchecked")
        public static String searchOut(String email, String name, String phone, String company, boolean in, Admin admin, String qq) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        List<Info> infos = null;
                        if ((email == null || email.trim().equals("")) && (name == null || name.trim().equals("")) && (phone == null || phone.trim().equals("")) && (company == null || company.trim().equals("")) && (qq == null || qq.trim().equals(""))) {
                                return object.toJSONString();
                        } else {
                                infos = search(email, name, phone, company, qq, in);
                                if (infos == null || infos.size() <= 0) {
                                        return object.toJSONString();
                                }
                        }
                        if (infos != null) {
                                for (Info info : infos) {
                                        power(admin.getId(), admin.getGroup(), info);
                                }
                        }
                        JSONArray array = getArrayFromInfos(infos);
                        object.put("infos", array);
                        object.put("status", 1);
                        object.put("size", infos.size());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object.toJSONString();
        }

        public static List<Info> addSearch(Admin admin, String email, String name, String phone, String company, String qq, boolean in) {
                List<Info> infos = search(email, name, phone, company, qq, in);
                if (infos != null && infos.size() > 0) {
                        for (Info info : infos) {
                                if (info.getSale() != null && info.getSale() > 0L) {
                                        info.setSaleName(AccountService.findById(info.getSale()).getName());
                                }
                                if (info.getSupport() != null && info.getSupport() > 0L) {
                                        info.setSupportName(AccountService.findById(info.getSupport()).getName());
                                }
                                if (info.getPreSale() != null && info.getPreSale() > 0L) {
                                        info.setPreSaleName(AccountService.findById(info.getPreSale()).getName());
                                }
                                power(admin.getId(), admin.getGroup(), info);
                        }
                }
                return infos;
        }

        public static List<Info> search(String email, String name, String phone, String company, String qq, boolean in) {
                List<Info> infos = InfoDaoImpl.getInstance().search(email, name, phone, company, qq, in);
                for (int i = 0; i < infos.size(); i++) {
                        initTag(infos.get(i));
                }
                return infos;
        }
        public static List<Info> findByFrom(String from){
                return InfoDaoImpl.getInstance().findByFrom(from);
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromInfos(List<Info> infos) {
                JSONArray array = new JSONArray();
                if (infos == null || infos.size() <= 0) {
                        return array;
                }
                for (Info info : infos) {
                        array.add(getJSONFromInfo(info));
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromInfo(Info info) {
                JSONObject value = new JSONObject();
                if (info == null)
                        return value;
                try {
                        value.put("id", info.getId());
                        value.put("userId", info.getUserId());
                        value.put("name", info.getName());
                        value.put("email", info.getEmail());
                        value.put("company", info.getCompany());
                        value.put("phone", info.getPhone());
                        value.put("loginTime", info.getLoginTime());
                        value.put("createTime", info.getCreateTime());
                        value.put("dataTime", info.getDataTime());
                        value.put("project", info.getProject());
                        value.put("level", info.getLevel());
                        value.put("language", info.getLanguage());
                        value.put("saleName", info.getSaleName());
                        value.put("supportName", info.getSupportName());
                        value.put("preSaleName", info.getPreSaleName());
                        value.put("comming", info.getFrom());
                        if (info.getCalls() != null) {
                                value.put("calls", CallService.getArrayFromCall(info.getCalls()));
                        }
                        if (info.getMails() != null) {
                                value.put("mails", MailService.getJSONArrayFromMails(info.getMails()).toString());
                        }
                        if (info.getTag() != null) {
                                value.put("tag", TagService.getJSONFromTag(info.getTag()));
                        }
                } catch (Exception e) {
                }
                return value;
        }

}
