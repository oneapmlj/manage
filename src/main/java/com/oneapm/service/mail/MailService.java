package com.oneapm.service.mail;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.account.impl.AdminDaoImpl;
import com.oneapm.dao.mail.impl.MailDaoImpl;
import com.oneapm.dao.mail.impl.MailModeDaoImpl;
import com.oneapm.dto.Mail;
import com.oneapm.service.info.InfoService;

public class MailService {

        protected static final Logger LOG = LoggerFactory.getLogger(MailService.class);

        @SuppressWarnings("unchecked")
        public static JSONArray getJSONArrayFromMails(List<Mail> mails) {
                if (mails == null || mails.size() <= 0) {
                        return null;
                }
                JSONArray array = new JSONArray();
                for (Mail mail : mails) {
                        array.add(getJSONFromMail(mail));
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromMail(Mail mail) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", mail.getId());
                        object.put("infoId", mail.getInfoId());
                        object.put("sendTime", mail.getSendTime());
                        object.put("mailMode", mail.getMailMode());
                        object.put("adminId", mail.getAdminId());
                        object.put("adminName", mail.getAdminName());
                        object.put("modeName", mail.getModeName());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }

        public static List<Mail> findMailsById(Long infoId) {
                List<Mail> mails = MailDaoImpl.getInstance().findById(infoId);
                if (mails != null) {
                        for (Mail mail : mails) {
                                mail.setAdminName(AdminDaoImpl.getInstance().findById(mail.getAdminId()).getName());
                                mail.setModeName(MailModeDaoImpl.getInstance().findById(mail.getMailMode()).getDescription());
                        }
                }
                return mails;
        }

        public static List<Mail> findMailsByGroupId(Long groupId) {
            List<Mail> mails = MailDaoImpl.getInstance().findByGroupId(groupId);
            if (mails != null) {
                    for (Mail mail : mails) {
                            mail.setAdminName(AdminDaoImpl.getInstance().findById(mail.getAdminId()).getName());
                            mail.setModeName(MailModeDaoImpl.getInstance().findById(mail.getMailMode()).getDescription());
                    }
            }
            return mails;
    }
        public static List<Mail> findByAccountId(Long accountId) {
                List<Mail> mails = new ArrayList<Mail>();
//                List<Mail> mails = MailDaoImpl.getInstance().findByAccountId(accountId);
//                try{
//                        if (mails != null) {
//                                for (Mail mail : mails) {
//                                        mail.setModeName(MailModeDaoImpl.getInstance().findById(mail.getMailMode()).getDescription());
//                                        mail.setCompanyName(InfoService.findByIdSimple(mail.getInfoId()).getCompany());
//                                }
//                        }
//                }catch(Exception e){
//                        LOG.error(e.getMessage(), e);
//                }
                return mails;
        }

        public static Long insert(Mail mail) {
                return MailDaoImpl.getInstance().insert(mail);
        }
        
        public static boolean insertAll(String content, Long adminId, String from, int number, String time){
                return MailDaoImpl.getInstance().insertAll(content, adminId, from, number, time);
        }
}
