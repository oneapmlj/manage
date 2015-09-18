package com.oneapm.web.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dao.account.impl.ControlDaoImpl;
import com.oneapm.dto.Account.Account;
import com.oneapm.service.account.AccountRecordService;
import com.oneapm.service.account.AccountService;
import com.oneapm.util.OneTools;
import com.oneapm.vo.Quanxian;
import com.oneapm.web.SessionKeys;
import com.oneapm.web.SupportAction;

public class AccountAction extends SupportAction {

        /**
	 * 
	 */
        private static final long serialVersionUID = 1L;
        private String username;
        private String password;
        private int group;
        private String quanxian;
        private String name;
        private String email;
        private Long id;
        private Account account;
        private int type;
        private List<Quanxian> quanxians;

        public String password() {
                return "password";
        }
        
        public void at() throws IOException{
//                if(name != null){
//                        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
//                }
                if(name == null || name.indexOf(" ") > -1){
                        getServletResponse().getWriter().print(OneTools.getResult(0, ""));
                        return;
                }
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = AccountService.at(name);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, ""));
                return;
        }

        public void password_update() throws IOException {
                if (password == null || password.length() < 6) {
                        getServletResponse().getWriter().print("{'status':0,'msg':'至少输入6位密码'}");
                        return;
                }
                try {
                        String result = AccountService.password_update(id, token, password);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void password_apply() throws IOException {
                if (email == null || email.length() <= 0) {
                        getServletResponse().getWriter().print("{'status':0,'msg':'不存在此邮箱帐号'}");
                        return;
                }
                try {
                        String result = AccountService.password_apply(email);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public String password_set() {
                if (AccountService.password_set(id, token)) {
                        return "password_set";
                }
                return ERROR;
        }

        public void grade() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(type))) {
                                List<String> args1 = new ArrayList<String>();
                                List<Object> args2 = new ArrayList<Object>();
                                args1.add("id");
                                args2.add(id);
                                getServletResponse().getWriter().print(OneTools.getResult(1, args1, args2));
                                return;
                        }
                        getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足，请联系管理员"));
                        return;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "服务器错误"));
        }

        public void ID() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!getAdmin().getId().equals(99999999L)) {
                        getServletResponse().getWriter().print("{'status':0,'msg':'登录超时，请重新登录'}");
                        return;
                }
                String result = AccountService.ID(group);
                getServletResponse().getWriter().print(result);
        }

        private List<Control> controls;
        private int control;
        private int page;
        private int nowPage;
        public String account() {
                if (!isLogin()) {
                        return "login";
                }
                account = AccountRecordService.findGroupsByAdmin(getAdmin(), type, true, page, nowPage);
                if (getAdmin().getId().equals(99999999L)) {
                        controls = ControlDaoImpl.getInstance().list();
                }
                return "account";
        }
        public void view() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = AccountRecordService.findGroupsByAdminId(getAdmin(), type, true, page, nowPage);
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public void admin() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = AccountRecordService.Admin(getAdmin());
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }

        public void update_control() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!getAdmin().getId().equals(99999999L)) {
                        getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                        return;
                }
                Control control = ControlDaoImpl.getInstance().findById(id);
                if (control == null) {
                        getServletResponse().getWriter().print(OneTools.getResult(0, "数据错误"));
                        return;
                }
                control.setControl(control.getControl() == 0 ? 1 : 0);
                if (ControlDaoImpl.getInstance().update(id, control.getControl())) {
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("control");
                        args2.add(control.getControl());
                        args1.add("id");
                        args2.add(id);
                        getServletResponse().getWriter().print(OneTools.getResult(1, args1, args2));
                        return;
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "修改失败"));
                return;
        }

        public void quanxian() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!getAdmin().getId().equals(99999999L)) {
                        getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                        return;
                }
                try {
                        String result = AccountService.updateQuanxian(getAdmin(), id, quanxian);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                return;
        }

        public String add() {
                if (!isLogin()) {
                        return "login";
                }
                if (!getAdmin().getId().equals(99999999L)) {
                        session.put(SessionKeys.BACK_MESSAGE, "权限不够!");
                        return "account";
                }
                setQuanxians(getGRADE().getList());
                return "add";
        }

        public void list() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = AccountService.list(group, getAdmin());
                getServletResponse().getWriter().print(result);
        }

        public void task_pass() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = AccountService.task_pass(getAdmin());
                getServletResponse().getWriter().print(result);
        }

        private String token;

        public String sign() {
                return "sign";
        }

        private String nickName;
        private String phone;
        private String position;

        public void update() throws IOException {
                if (token == null) {
                        getServletResponse().getWriter().print("{'status':0,'msg':'激活失败'}");
                        return;
                }
//                nickName = new String(nickName.getBytes("ISO8859-1"), "UTF-8");
//                position = new String(position.getBytes("ISO8859-1"), "UTF-8");
                String result = AccountService.update(id, token, phone, position, nickName, password, username);
                getServletResponse().getWriter().print(result);
        }

        public void insert() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!getAdmin().getId().equals(99999999L)) {
                        session.put(SessionKeys.BACK_MESSAGE, "权限不够!");
                        getServletResponse().getWriter().print("{'status':0,'msg':'权限不足'}");
                        return;
                }
//                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                String result = AccountService.insert(email, name, quanxian, id, getAdmin().getId(), group);
                getServletResponse().getWriter().print(result);
        }

        public String login() {
                if (username == null || password == null) {
                        session.put(SessionKeys.LOGIN_ERROR, "1");
                        session.put(SessionKeys.BACK_MESSAGE, "用户名和密码不能为空!");
                        return "loginIndex";
                }
                try {
                        setAdmin(AccountService.login(username, password));
                        if (getAdmin() == null) {
                                session.put(SessionKeys.LOGIN_ERROR, "1");
                                session.put(SessionKeys.BACK_MESSAGE, "用户名或密码错误");
                                return "loginIndex";
                        }
                        if (getAdmin().getStatus() == 2) {
                                session.put(SessionKeys.LOGIN_ERROR, "1");
                                session.put(SessionKeys.BACK_MESSAGE, "帐号被冻结，请联系管理员");
                                return "loginIndex";
                        }
                        loginEvent(getServletResponse(), getRequest(), getAdmin());
                } catch (Exception e) {
                        e.printStackTrace();
                        return "loginIndex";
                }
                return "show";
        }

        public String loginOut() {
                try {
                        loginOutEvent();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return "loginIndex";
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getQuanxian() {
                return quanxian;
        }

        public void setQuanxian(String quanxian) {
                this.quanxian = quanxian;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }

        public String getNickName() {
                return nickName;
        }

        public void setNickName(String nickName) {
                this.nickName = nickName;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public Account getAccount() {
                return account;
        }

        public void setAccount(Account account) {
                this.account = account;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getGroup() {
                return group;
        }

        public void setGroup(int group) {
                this.group = group;
        }

        public List<Quanxian> getQuanxians() {
                return quanxians;
        }

        public void setQuanxians(List<Quanxian> quanxians) {
                this.quanxians = quanxians;
        }

        public List<Control> getControls() {
                return controls;
        }

        public void setControls(List<Control> controls) {
                this.controls = controls;
        }

        public int getControl() {
                return control;
        }

        public void setControl(int control) {
                this.control = control;
        }

        public int getPage() {
                return page;
        }

        public void setPage(int page) {
                this.page = page;
        }



        public int getNowPage() {
                return nowPage;
        }

        public void setNowPage(int nowPage) {
                this.nowPage = nowPage;
        }

}
