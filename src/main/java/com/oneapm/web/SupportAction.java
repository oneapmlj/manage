package com.oneapm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.oneapm.dto.Contact;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.MailMode;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.tag.Category;
import com.oneapm.dto.tag.From;
import com.oneapm.dto.tag.Language;
import com.oneapm.dto.tag.Loudou;
import com.oneapm.dto.tag.Metric;
import com.oneapm.dto.tag.Person;
import com.oneapm.dto.tag.Province;
import com.oneapm.dto.tag.Rongzi;
import com.oneapm.dto.tag.TagBase;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.mail.CloudService;
import com.oneapm.vo.Grade;
import com.oneapm.vo.Quanxian;
import com.opensymphony.xwork2.ActionSupport;

public class SupportAction extends ActionSupport implements ServletRequestAware, SessionAware, ApplicationAware, ServletResponseAware {

        private final static Grade GRADE = new Grade();
        private static List<Contact> todus;

        public static boolean quanxian(String grades, Quanxian quanxian) {
                if (grades == null)
                        return false;
                if (quanxian.getAll() == 1 && grades.indexOf(GRADE.getMap().get(999).getQuanxian()) >= 0)
                        return true;
                return grades.indexOf(quanxian.getQuanxian()) >= 0;
        }

        protected static List<MailMode> MAIL_MODE;
        protected static List<Menu> menu;
        protected static List<TagBase> LOUDOU;
        protected static List<TagBase> METRIC;
        protected static List<TagBase> LANGUAGE;
        protected static List<TagBase> PERSON;
        protected static List<TagBase> PROVINCE;
//        protected static List<TagBase> PV;
//        protected static List<TagBase> UV;
        protected static List<TagBase> RONGZI;
        protected static List<TagBase> CATEGORY;
        protected static List<TagBase> FROM;

        public static void tagInit() {
                LOUDOU = new ArrayList<TagBase>();
                for (Loudou in : Loudou.values()) {
                        LOUDOU.add(new TagBase(in.getId(), in.getName()));
                }
                METRIC = new ArrayList<TagBase>();
                for (Metric in : Metric.values()) {
                        METRIC.add(new TagBase(in.getId(), in.getName()));
                }
                LANGUAGE = new ArrayList<TagBase>();
                for (Language in : Language.values()) {
                        LANGUAGE.add(new TagBase(in.getId(), in.getName()));
                }
                PERSON = new ArrayList<TagBase>();
                for (Person in : Person.values()) {
                        PERSON.add(new TagBase(in.getId(), in.getName()));
                }
                PROVINCE = new ArrayList<TagBase>();
                for (Province in : Province.values()) {
                        PROVINCE.add(new TagBase(in.getId(), in.getName()));
                }
//                PV = new ArrayList<TagBase>();
//                for (Pv in : Pv.values()) {
//                        PV.add(new TagBase(in.getId(), in.getName()));
//                }
//                UV = new ArrayList<TagBase>();
//                for (Uv in : Uv.values()) {
//                        UV.add(new TagBase(in.getId(), in.getName()));
//                }
                RONGZI = new ArrayList<TagBase>();
                for (Rongzi in : Rongzi.values()) {
                        RONGZI.add(new TagBase(in.getId(), in.getName()));
                }
                CATEGORY = new ArrayList<TagBase>();
                for (Category in : Category.values()) {
                        CATEGORY.add(new TagBase(in.getId(), in.getName()));
                }
                FROM = new ArrayList<TagBase>();
                for (From from : From.values()) {
                        FROM.add(new TagBase(from.getId(), from.getName()));
                }
        }

        public static boolean mailModeInit() {
                MAIL_MODE = new ArrayList<MailMode>();
                List<MailDto> mails = CloudService.findMails();
                if (mails == null)
                        return false;
                for (MailDto mail : mails) {
                        addMailMode(new MailMode(mail.getId(), mail.getDescription()));
                }
                return false;
        }

        public static void addMailMode(MailMode mailMode) {
                MAIL_MODE.add(mailMode);
        }

        static {
                MAIL_MODE = new ArrayList<MailMode>();
                menu = new ArrayList<Menu>();
                menu.add(new Menu("今日数据(TPM)", 1));
                menu.add(new Menu("未通过验证", 2));
                menu.add(new Menu("注册成功三天内未登录", 3));
                menu.add(new Menu("今日登录未下载", 4));
                menu.add(new Menu("今日下载未添加应用(TPM)", 5));
                menu.add(new Menu("注册成功未下载", 6));
                menu.add(new Menu("下载未添加应用(TPM)", 7));
                menu.add(new Menu("下载有应用今日无数据(TPM)", 8));
                menu.add(new Menu("昨日有数据今日无数据(TPM)", 9));
                menu.add(new Menu("今日有数据三天未登陆(TPM)", 10));
                menu.add(new Menu("注册两天后有登陆未下载", 11));
                menu.add(new Menu("今日有数据(M)", 12));
                menu.add(new Menu("今日下载探针未添加应用(M)", 13));
                menu.add(new Menu("下载探针未添加应用(M)", 14));
                menu.add(new Menu("下载探针有应用今日无数据(M)", 15));
                menu.add(new Menu("昨日有数据今日无数据(M)", 16));

                todus = new ArrayList<Contact>();
                todus.add(new Contact(1000, "推送销售"));
                todus.add(new Contact(1001, "技术支持"));
                todus.add(new Contact(1002, "再联系"));
                todus.add(new Contact(1003, "关闭"));
        }

        /**
	 * 
	 */
        private static final long serialVersionUID = 1L;

        public static ServletContext systemApplication;

        protected Map<String, Object> session;

        public static String ERROR = "ERROR";

        private HttpServletRequest request;

        protected HttpServletResponse response;

        private Map<String, String> application;

        public void setServletRequest(HttpServletRequest request) {
                this.request = request;
        }

        public HttpServletRequest getRequest() {
                return request;
        }

        private String url;

        protected String errorMessage = ErrorMsg.SERVERERROR;
        private Admin admin;

        protected boolean isLogin() {
                try {
                        String username = UserCookieHelper.getCookieValue(SessionKeys.USER_NAME, request);
                        String password = UserCookieHelper.getCookieValue(SessionKeys.PASSWORD, request);
                        setAdmin(AccountService.verify(username, password));
                        if (admin != null) {
                                if (admin.getGrades() != null) {
                                        admin.setAssign(admin.getGrades().indexOf(getGRADE().getMap().get(105).getQuanxian()) > -1 ? 1 : admin.getGrades().indexOf(getGRADE().getMap().get(999).getQuanxian()) > -1 ? 1 : 0);
                                        admin.setManage(admin.getGrades().indexOf(getGRADE().getMap().get(109).getQuanxian()) > -1 ? 1 : admin.getGrades().indexOf(getGRADE().getMap().get(999).getQuanxian()) > -1 ? 1 : 0);
                                        admin.setReview(admin.getGrades().indexOf(getGRADE().getMap().get(110).getQuanxian()) > -1 ? 1 : admin.getGrades().indexOf(getGRADE().getMap().get(999).getQuanxian()) > -1 ? 1 : 0);
                                        admin.setPay(admin.getGrades().indexOf(getGRADE().getMap().get(112).getQuanxian()) > -1 ? 1 : 0);
                                        admin.setDuandian(admin.getGrades().indexOf(getGRADE().getMap().get(113).getQuanxian()) > -1 ? 1 : 0);
                                }
                                if (admin.getStatus() == 2) {
                                        return false;
                                }
                                getRequest().getSession().setAttribute("grade", admin.getGrade());
                                AccountService.updateLogin(admin.getId());
                                return true;
                        }
                } catch (Exception e) {
                }
                return false;
        }

        protected String isLogin(boolean update) {
                try {
                        String username = UserCookieHelper.getCookieValue(SessionKeys.USER_NAME, request);
                        String password = UserCookieHelper.getCookieValue(SessionKeys.PASSWORD, request);
                        setAdmin(AccountService.verify(username, password));
                        if (admin != null) {
                                if (admin.getStatus() == 2) {
                                        return null;
                                }
                                getRequest().getSession().setAttribute("grade", admin.getGrade());
                                if (update) {
                                        AccountService.updateLogin(admin.getId());
                                }
                                return admin.getGrades();
                        }
                } catch (Exception e) {
                }
                return null;
        }

        @SuppressWarnings("unchecked")
        protected <T> T getSessionOf(String key) {
                Map<String, Object> s = getSession();
                System.out.println(request.getSession(true).getAttribute(key));
                return (T) s.get(key);
        }

        public static void loginEvent(HttpServletResponse response, HttpServletRequest request, Admin admin) {
                HttpSession session = request.getSession(true);
                // session.setAttribute(SessionKeys.ID, admin.getId());
                session.setAttribute(SessionKeys.USER_NAME, admin.getUsername());
                session.setAttribute(SessionKeys.PASSWORD, admin.getPassword());
                // session.setAttribute(SessionKeys.NAME, admin.getName());
                // session.setAttribute(SessionKeys.GRADE, admin.getGrade());

                String token = getToken(String.valueOf(admin.getId()));
                UserCookieHelper.saveUserCookie(response, String.valueOf(admin.getId()), String.valueOf(admin.getUsername()), admin.getPassword(), token);
                AccountService.updateLogin(admin.getId());
        }

        public void loginOutEvent() {
                getSession().clear();
                UserCookieHelper.removeUserCookies();
        }

        public static String getToken(String accountId) {
                StringBuilder builder = new StringBuilder();
                builder.append(accountId).append(new Date().getTime());
                return builder.toString();
        }

        protected Map<String, Object> getSession() {
                return session;
        }

        @SuppressWarnings("unchecked")
        public void setSession(Map session) {
                this.session = session;
        }

        public void setServletResponse(HttpServletResponse response) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                this.response = response;
        }

        public HttpServletResponse getServletResponse() {
                return this.response;
        }

        @SuppressWarnings("unchecked")
        public void setApplication(Map application) {
                this.application = application;
        }

        protected Map<String, String> getApplication() {
                return application;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public Admin getAdmin() {
                return admin;
        }

        public void setAdmin(Admin admin) {
                this.admin = admin;
        }

        public List<TagBase> getLOUDOU() {
                return LOUDOU;
        }

        public List<TagBase> getLANGUAGE() {
                return LANGUAGE;
        }

        public List<TagBase> getMETRIC() {
                return METRIC;
        }

        public List<TagBase> getPERSON() {
                return PERSON;
        }

        public List<TagBase> getPROVINCE() {
                return PROVINCE;
        }

//        public List<TagBase> getPV() {
//                return PV;
//        }
//
//        public List<TagBase> getUV() {
//                return UV;
//        }
        public List<TagBase> getRONGZI() {
                return RONGZI;
        }

        public List<TagBase> getCATEGORY() {
                return CATEGORY;
        }

        public List<TagBase> getFROM() {
                return FROM;
        }

        public static Grade getGRADE() {
                return GRADE;
        }

        public List<Contact> getTodus() {
                return todus;
        }
}
