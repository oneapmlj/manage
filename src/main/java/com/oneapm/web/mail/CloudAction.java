package com.oneapm.web.mail;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.MailDto;
import com.oneapm.service.mail.CloudService;
import com.oneapm.web.SupportAction;


public class CloudAction extends SupportAction{

    private static final long serialVersionUID = -8352411904406746148L;

    protected static final Logger LOG = LoggerFactory.getLogger(CloudAction.class);
    
    private MailDto mail;
    private List<MailDto> mails;
    private int id;
    private String description;
    private int timeType;
    private String title;
    private int status;
    private String content;
    
    public String add(){
        if(!isLogin()){
            return "login";
        }
        return "add";
    }
    public void insert() throws IOException{
        if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
//        description = new String(description.getBytes("ISO8859-1"),"UTF-8"); 
        String result = CloudService.insert(description, timeType, title, status, content);
        getServletResponse().getWriter().print(result);
    }
    
    public MailDto getMail() {
        return mail;
    }
    public void setMail(MailDto mail) {
        this.mail = mail;
    }
    public List<MailDto> getMails() {
        return mails;
    }
    public void setMails(List<MailDto> mails) {
        this.mails = mails;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTimeType() {
        return timeType;
    }
    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }
}
