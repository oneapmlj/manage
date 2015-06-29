package com.oneapm.web.mail;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.oneapm.dto.Mail;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.MailMode;
import com.oneapm.dto.info.Info;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.mail.MailService;
import com.oneapm.service.mail.SendMailService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class SendMailAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 1L;
        protected static final Logger LOG = LoggerFactory.getLogger(SendMailAction.class);
        private int mode;
        private int to;
        private String from;
        private Long infoId;
        private Long adminId;
        private MailDto mail;

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        private String preview;

        public String preview() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        if (adminId != null && adminId > 0) {
                                preview = SendMailService.preview(infoId, mode, AccountService.findById(adminId));
                        } else {
                                preview = SendMailService.preview(infoId, mode, getAdmin());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), 1);
                }
                return "preview";
        }
        
        private Info info;
        private String content;
        private String signTime;
        private List<Mail> mails;
        public String send(){
                if (!isLogin()) {
                        return "login";
                }
                try {
                        info = InfoService.findByIdSimple(infoId);
                        signTime = info.getCreateTime().substring(0, 10);
                        mails = MailService.findMailsById(info.getId());
//                        content = SendMailService.findByModeId(101);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), 1);
                }
                return "send";
        }
        
        public void view() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String content = SendMailService.findByModeId(mode);
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("content", content);
                        String title = SendMailService.findTitle(mode);
                        object.put("title", title);
                        getServletResponse().getWriter().print(object.toJSONString());
                        return;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "服务器内部错误"));
        }
        
        public void review() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String content = SendMailService.findByMailId(id);
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("content", content);
                        getServletResponse().getWriter().print(object.toJSONString());
                        return;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "服务器内部错误"));
        }

        private String mailContent;
        private String title;
        private Long lable;
        public void sendSingle() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(100))) {
                        try {
                                getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                                return;
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                try {
                        String result = SendMailService.sendSingle(infoId, mode, getAdmin(), mailContent,title, to, from, lable);
                        getServletResponse().getWriter().print(result);
                        return;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), 1);
                }
        }

        private Long id;

        public String previewPush() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        preview = SendMailService.previewPush(id, getAdmin());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), 1);
                }
                return "preview";
        }

        public String all() {
                return "all";
        }

        public int getTo() {
                return to;
        }

        public void setTo(int to) {
                this.to = to;
        }

        public String getFrom() {
                return from;
        }

        public void setFrom(String from) {
                this.from = from;
        }

        public int getMode() {
                return mode;
        }

        public void setMode(int mode) {
                this.mode = mode;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public String getPreview() {
                return preview;
        }

        public void setPreview(String preview) {
                this.preview = preview;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Info getInfo() {
                return info;
        }

        public void setInfo(Info info) {
                this.info = info;
        }
        public List<MailMode> getMAIL_MODE() {
                return MAIL_MODE;
        }

        public MailDto getMail() {
                return mail;
        }

        public void setMail(MailDto mail) {
                this.mail = mail;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getSignTime() {
                return signTime;
        }

        public void setSignTime(String signTime) {
                this.signTime = signTime;
        }

        public String getMailContent() {
                return mailContent;
        }

        public void setMailContent(String mailContent) {
                this.mailContent = mailContent;
        }

        public List<Mail> getMails() {
                return mails;
        }

        public void setMails(List<Mail> mails) {
                this.mails = mails;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public Long getLable() {
                return lable;
        }

        public void setLable(Long lable) {
                this.lable = lable;
        }

}
