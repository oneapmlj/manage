package com.oneapm.service.mail;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dao.info.impl.AppDaoImpl;
import com.oneapm.dao.mail.impl.MailDaoImpl;
import com.oneapm.dao.mail.impl.MailModeDaoImpl;
import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.Mail;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.service.FileSystem;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TaskService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.util.exception.PramaException;
import com.oneapm.util.exception.TimeException;

public class SendMailService extends OneTools {

        protected static final Logger LOG = LoggerFactory.getLogger(SendMailService.class);

        private static Set<String> stopSend;
        public static Long send(Info info, MailDto mail, String mailContent, Admin admin, String title, String from) throws PramaException, ParseException, TimeException {
                if (info == null || admin == null) {
                        return null;
                }
                List<Mail> mails = MailService.findMailsById(info.getId());
                if (mails != null && mails.size() > 0) {
                        if (TimeTools.fromHour(mails.get(0).getSendTime()) <= 5) {
                                throw new TimeException();
                        }
                }
                try {
                        mail.setLastUseTime(TimeTools.format());
                        mail.setUse(mail.getUse() + 1);
                        MailModeDaoImpl.getInstance().update(mail);
                        if(title == null || title.length() <= 0){
                                title = mail.getTitle();
                        }
                        if (SendCloudService.sendCloud(info.getEmail(), mailContent, title, from)) {
                                return MailService.insert(new Mail(null, info.getId(), TimeTools.format(), mail.getId(), admin.getId(), mailContent,info.getUserId()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        /**
         * 群发邮件，不考虑多次发送
         * @param infos
         * @param mail
         * @param mailContent
         * @param admin
         * @param title
         * @param from
         * @return
         * @throws PramaException
         * @throws ParseException
         * @throws TimeException
         */
        public static void push(Set<String> emails, MailDto mail, String mailContent, Admin admin, String title, String from, Long lable) throws PramaException, ParseException, TimeException {
                if (emails == null || emails.size() <= 0 || admin == null) {
                        return;
                }
                try {
                        mail.setLastUseTime(TimeTools.format());
                        mail.setUse(mail.getUse() + 1);
                        MailModeDaoImpl.getInstance().update(mail);
                        if(title == null || title.length() <= 0){
                                title = mail.getTitle();
                        }
                        int i=0;
                        int j= 0;
                        for(String email : emails){
                                if(i%500 == 0){
                                        LOG.info("send email step :"+j+"/"+i);
                                }
                                try{
                                        i++;
                                        boolean send = SendCloudService2.sendMail(email, mailContent, title, lable);
                                        if(!send){
                                                LOG.info("send email faile:"+email);
                                        }else{
                                                j++;
                                        }
                                }catch(Exception e){}
                        }
//                        SendCloudService2.sendMail("lijiang1225@qq.com", mailContent, title, lable);
//                        SendCloudService2.sendMail("lijiang@oneapm.com", mailContent, title, lable);
//                        SendCloudService2.sendMail("jiang1li@126.com", mailContent, title, lable);
                        MailService.insertAll(mailContent, admin.getId(), from, emails.size(), TimeTools.format());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }
        
        
        public static String findByModeId(int mode) throws IOException{
                MailDto mail = MailModeDaoImpl.getInstance().findById(mode);
                String html = FileSystem.read(mail.getContent());
                return html;
        }
        public static String findTitle(int mode){
                MailDto mail = MailModeDaoImpl.getInstance().findById(mode);
                if(mail != null){
                        return mail.getTitle();
                }
                return "";
        }
        public static String findByMailId(Long id) throws IOException{
                Mail mail = MailDaoImpl.getInstance().findByMailId(id);
                return mail.getMailContent();
        }

//        public static String previewPush(Long id, Admin admin) {
//                try {
//                        if (id == null || id <= 0) {
//                                return getResult(0, "参数错误！");
//                        }
//                        MailPush push = TaskService.findById(id);
//                        if (push == null) {
//                                return getResult(0, "参数错误！");
//                        }
//                        MailDto mail = MailModeDaoImpl.getInstance().findById(TaskService.mailType(push));
//                        if (mail == null) {
//                                return getResult(0, "邮件模板不存在");
//                        }
//                        if (admin == null) {
//                                return getResult(0, "发送邮件帐号不存在");
//                        }
//                        Info info = InfoService.findByIdSingle(push.getInfoId());
//                        return modeToMail(info, mail, admin);
//                } catch (Exception e) {
//                        LOG.error(e.getMessage(), e);
//                }
//                return null;
//        }

        @SuppressWarnings("unchecked")
        public static String sendSingle(Long infoId, int mode, Admin admin, String mailContent, String title, int to, int baocun, Long lable, String from) {
                JSONObject object = new JSONObject();
                try {
                        if (infoId == null || infoId <= 0) {
                                if(to == 0){
                                        return getResult(0, "请选择要发送邮件的用户！");
                                }
                        }
                        MailDto mail = MailModeDaoImpl.getInstance().findById(mode);
                        if (mail == null) {
                                return getResult(0, "邮件模板不存在");
                        }
                        if (admin == null) {
                                return getResult(0, "帐号未登录");
                        }
                        if(from == null || from.trim().length() <= 1){
                                from = admin.getEmail();
                        }
                        Info info = InfoService.findByUserIdSimple(infoId);
                        if(info!=null){
                        TaskService.mail(info.getId(), admin.getId(), mode);
                        }
                        if(to == 0){
                                Long id = send(info, mail, mailContent, admin, title, from);
                                if (id != null) {
                                        object.put("status", 1);
                                        object.put("to", to);
                                        object.put("adminName", admin.getName());
                                        object.put("time", TimeTools.format());
                                        object.put("description", mail.getDescription());
                                        object.put("adminId", admin.getId());
                                        object.put("id", id);
                                }
                        }else{
                                List<Integer> agents = new ArrayList<Integer>();
                                List<App> apps = null;
                                List<Download> downloads = null;
                                Set<Long> groupIds = null;
                                Set<Long> userIds = null;
                                List<UserGroups> userGroups = new ArrayList<UserGroups>();
                                switch (to) {
                                        case 2:
                                                userGroups = UserGroupsDaoImpl.getInstance().findAll();
                                                for(int i=0;i<userGroups.size();i++){
                                                        if(userGroups.get(i).getEmailStatus() == 1){
                                                                userGroups.remove(i);
                                                                i--;
                                                        }
                                                }
                                                break;
                                        case 3:
                                                agents.add(7);
                                                agents.add(8);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 4:
                                                agents.add(7);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 5:
                                                agents.add(8);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 6:
                                                agents.add(1);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 7:
                                                agents.add(2);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 8:
                                                agents.add(5);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 9:
                                                agents.add(4);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 10:
                                                agents.add(6);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 11:
                                                agents.add(3);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 12:
                                                agents.add(9);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 13:
                                                agents.add(10);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        case 14:
                                                agents.add(11);
                                                apps = AppDaoImpl.getInstance().find(agents);
                                                groupIds = new HashSet<Long>();
                                                for(App app : apps){
                                                        groupIds.add(app.getUserId());
                                                }
                                                userIds = new HashSet<Long>();
                                                downloads = DownDaoImpl.getInstance().find(agents);
                                                for(Download download : downloads){
                                                        userIds.add(download.getUserId());
                                                }
                                                for(Long groupId:groupIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findById(groupId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                for(Long userId : userIds){
                                                        UserGroups userGroups2 = UserGroupsDaoImpl.getInstance().findByAdminId(userId);
                                                        if(userGroups2 != null && userGroups2.getEmailStatus() != 1){
                                                                userGroups.add(userGroups2);
                                                        }
                                                }
                                                break;
                                        default:
                                                break;
                                }
                                Set<String> emails = new HashSet<String>();
                                List<String> emailStrings = new ArrayList<String>();
                                if(userGroups != null && userGroups.size() > 0){
                                        for(UserGroups userGroups2 : userGroups){
                                                Info info2 = InfoService.findByUserIdSimple(userGroups2.getAdminId());
                                                if(info2 != null){
                                                        emails.add(info2.getEmail());
                                                }
                                        }
                                }
                                for(String email : emails){
                                        emailStrings.add(email);
                                }
                                if(stopSend == null){
                                        stopSend = new HashSet<String>();
                                }
                                switch (baocun) {
                                        case 1:
                                                for(String email : emails){
                                                        stopSend.add(email);
                                                }
                                                break;
                                        case 2:
                                                for(int i=0;i<emailStrings.size();i++){
                                                        int j = stopSend.size();
                                                        stopSend.add(emailStrings.get(i));
                                                        if(stopSend.size() == j){
                                                                emailStrings.remove(i);
                                                                i--;
                                                        }
                                                }
                                                emails = new HashSet<String>();
                                                for(String email:emailStrings){
                                                        emails.add(email);
                                                }
                                                break;
                                        case 3:
                                                stopSend = new HashSet<String>();
                                                return getResult(0, "清零完成");
                                        default:
                                                break;
                                }
                                push(emails, mail, mailContent, admin, title, from, lable);
                        }
                        return getResult(1, "发送成功：");
                } catch (TimeException e) {
                        return getResult(0, "该用户在6小时内已经收到过邮件，不可频繁发送");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }


}
