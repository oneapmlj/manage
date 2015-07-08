package com.oneapm.service.account;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.account.impl.AdminDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.service.mail.SendCloudService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.vo.Grade;
import com.oneapm.web.SupportAction;

@SuppressWarnings("unchecked")
public class AccountService {

        protected static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

        public static boolean password_set(Long id, String token) {
                if (token == null) {
                        return false;
                }
                String t = AdminDaoImpl.getInstance().getToken(id);
                if (t == null) {
                        return false;
                }
                return t.trim().equals(token.trim());
        }
        
        public static String at(String name){
                try{
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        List<Admin> admins = new ArrayList<Admin>();
                        if(name.length() <= 0){
                                admins = AdminDaoImpl.getInstance().atAdmin();
                        }else{
                                admins = AdminDaoImpl.getInstance().at(name);
                        }
                        if(admins.size() > 0){
                                args1.add("admins");
                                args2.add(getArrayFromAdminList(admins));
                                return OneTools.getResult(1, args1, args2);
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "");
        }
        
        public static Admin findByRealName(String name){
                Admin admin = AdminDaoImpl.getInstance().findByRealName(name);
                return admin;
        }

        public static String findName(Long id) {
                Admin admin = findById(id);
                if (admin != null) {
                        return admin.getName();
                }
                return null;
        }

        public static String updateQuanxian(Admin admin, Long id, String quanxian) {
                try {
                        Admin a = findById(id);
                        if (a == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        a.setGrades(quanxian);
                        if (AdminDaoImpl.getInstance().update(a)) {
                                return OneTools.getResult(1, "");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String password_update(Long id, String token, String password) {
                try {
                        if (id == null || id < 0 || token == null || token.length() < 0 || password == null || password.length() < 6) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        Admin admin = AdminDaoImpl.getInstance().findById(id);
                        if (admin == null) {
                                return OneTools.getResult(0, "用户不存在");
                        }
                        String t = AdminDaoImpl.getInstance().getToken(id);
                        if (t == null) {
                                return OneTools.getResult(0, "不允许重置密码");
                        }
                        if (t.equals(token)) {
                                admin.setPassword(string2MD5(password));
                                AdminDaoImpl.getInstance().update(admin);
                                return OneTools.getResult(1, null);
                        }
                        return OneTools.getResult(0, "密码重置失败");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static boolean updateLogin(Long adminId) {
                String loginTime = TimeTools.format();
                return AdminDaoImpl.getInstance().updateLogin(adminId, loginTime);
        }

        public static String password_apply(String email) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        Admin admin = AdminDaoImpl.getInstance().findByEmail(email);
                        if (admin == null) {
                                object.put("msg", "此邮箱用户不存在");
                                return object.toJSONString();
                        }
                        // String token =
                        // AdminDaoImpl.getInstance().getToken(admin.getId());
                        // if(token != null && token.length() > 0){
                        // object.put("status", 1);
                        // return object.toJSONString();
                        // }
                        String token = string2MD5(email + TimeTools.format() + new Random().nextInt());
                        String html = "<div style='width:400px;height:300px;margin: 30px 0 0 40px;'>" + "<div>您已经申请重置运营系统密码。</div>" + "<div><a href='http://manage.oneapm.com/account_password_set.action?" + "id=" + admin.getId() + "&token=" + token + "'>点击重置密码</a>" + "</div>" + "<div style='color:red;'>" + "<div>(运营系统支持chrome,firefox,IE9以上浏览器)</div>" + "<div>如果遇到使用问题，请联系@李江</div>" + "</div>" + "<div>" + "<div style='margin:10px 0 0 0;'>李江</div>" + "<div>Email:lijiang@oneapm.com</div>" + "<div>tel:13311393672</div>" + "<div>qq:695419811</div>" + "</div>" + "</div>";
                        if (AdminDaoImpl.getInstance().addToken(admin.getId(), token)) {
                                if (SendCloudService.sendMail("lijiang@oneapm.com", admin.getEmail(), html, "运营系统帐号密码重置")) {
                                        object.put("status", 1);
                                } else {
                                        object.put("msg", "重置邮件发送失败");
                                }
                        } else {
                                object.put("msg", "生成token失败");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("msg", "服务器内部错误");
                }
                return object.toJSONString();
        }

        public static String ID(int group) {
                JSONObject object = new JSONObject();
                Long ids = null;
                Long ide = null;
                switch (group) {
                case 1:
                        ids = 80000000L;
                        ide = 90000000L;
                        break;
                case 2:
                        ids = 40000000L;
                        ide = 50000000L;
                        break;
                case 3:
                        ids = 40000000L;
                        ide = 50000000L;
                        break;
                case 4:
                        ids = 80000000L;
                        ide = 90000000L;
                        break;
                case 5:
                        ids = 40000000L;
                        ide = 50000000L;
                        break;
                case 6:
                        ids = 40000000L;
                        ide = 50000000L;
                        break;
                case 7:
                        ids = 10000000L;
                        ide = 20000000L;
                        break;
                }
                Long id = AdminDaoImpl.getInstance().getIdest(ids, ide);
                if (id != null) {
                        id++;
                        object.put("status", 1);
                        object.put("id", id);
                } else {
                        object.put("status", 0);
                }
                return object.toJSONString();
        }

        public static String list(int group, Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        if (admin.getGroup() == 1) {
                                object.put("status", 0);
                                object.put("msg", "权限不足");
                                return object.toJSONString();
                        }
                        List<Admin> admins = new ArrayList<Admin>();
                        List<Admin> ads = null;
                        switch (group) {
                        case 0:
                                if (admin.getGroup() < 7) {
                                        object.put("status", 0);
                                        object.put("msg", "权限不足");
                                        return object.toJSONString();
                                }
                                admins = AdminDaoImpl.getInstance().list(group);
                                for (int i = 0; i < admins.size(); i++) {
                                        if (admins.get(i).getId().equals(99999999L)) {
                                                admins.remove(i);
                                                i--;
                                        } else {
                                                if (admins.get(i).getGroup() < 4) {
                                                        admins.remove(i);
                                                        i--;
                                                } else {
                                                        if (admins.get(i).getId().equals(admin.getId())) {
                                                                admins.remove(i);
                                                                i--;
                                                        }
                                                }
                                        }
                                }
                                break;
                        case 1:
                                if (admin.getGroup() < 7 && admin.getGroup() != 4) {
                                        object.put("status", 0);
                                        object.put("msg", "权限不足");
                                        return object.toJSONString();
                                }
                                admins = AdminDaoImpl.getInstance().list(1);
                                ads = AdminDaoImpl.getInstance().list(4);
                                for (Admin ad : ads) {
                                        admins.add(ad);
                                }
                                break;
                        case 2:
                                if (admin.getGroup() < 7 && admin.getGroup() != 5 && admin.getGroup() != 6 && admin.getGroup() != 3 && admin.getGroup() != 2) {
                                        object.put("status", 0);
                                        object.put("msg", "权限不足");
                                        return object.toJSONString();
                                }
                                admins = AdminDaoImpl.getInstance().list(2);
                                ads = AdminDaoImpl.getInstance().list(5);
                                for (Admin ad : ads) {
                                        admins.add(ad);
                                }
                                break;
                        case 4:
                                break;
                        case 5:
                                break;
                        case 6:
                                break;
                        case 7:
                                break;
                        }
                        List<Admin> as = new ArrayList<Admin>();
                        for (Admin a : admins) {
                                if (SupportAction.quanxian(a.getGrades(), new Grade().getMap().get(114))) {
                                        as.add(a);
                                }
                        }
                        for (Admin a : admins) {
                                if (!SupportAction.quanxian(a.getGrades(), new Grade().getMap().get(114))) {
                                        as.add(a);
                                }
                        }
                        object.put("admins", getArrayFromAdminList(as));
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误");
                }
                return object.toJSONString();
        }

        public static String task_pass(Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        List<Admin> admins = new ArrayList<Admin>();
                        admins = AdminDaoImpl.getInstance().findAdmins(0);
                        for (int i = 0; i < admins.size(); i++) {
                                Admin ad = admins.get(i);
                                if (admin.getGrades().indexOf(SupportAction.getGRADE().getMap().get(111).getQuanxian()) < 0) {
                                        admins.remove(i);
                                        i--;
                                        continue;
                                }
                                if (admin.getId().equals(ad.getId())) {
                                        admins.remove(i);
                                        i--;
                                        continue;
                                }
                        }
                        object.put("admins", getArrayFromAdminList(admins));
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误");
                }
                return object.toJSONString();
        }

        public static Admin findById(Long adminId) {
                return AdminDaoImpl.getInstance().findById(adminId);
        }

        public static Admin findByEmail(String email) {
                return AdminDaoImpl.getInstance().findByEmail(email);
        }

        public static Admin login(String username, String password) {
                try {
                        if (password == null) {
                                return null;
                        }
                        password = string2MD5(password);
                        Admin admin = verify(username, password);
                        return admin;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        public static List<Admin> findByGroup(int group) {
                return AdminDaoImpl.getInstance().findByGroup(group);
        }

        public static List<Admin> findAdmins() {
                return AdminDaoImpl.getInstance().findAdmins(0);
        }

        public static List<Admin> findSales() {
                return AdminDaoImpl.getInstance().findSales();
        }

        public static List<Admin> findSupports() {
                return AdminDaoImpl.getInstance().findSupports();
        }

        public static void main(String[] args) {
                String str = string2MD5("wkqurfkj@#92dsnl");
                System.out.println(str);
                // System.out.println(string2MD5(str));
                // try {
                // DBConnectionManager.getInstance().init();
                // } catch (Exception e) {
                // e.printStackTrace();
                // }
                // String password = MethodUtil.getMD5("111111", "UTF-8", 0);
                // int emailbase = 2980;
                // Long phonebase = 13323122240L;
                // String[] company_name =
                // {"company_test1","company_test2","company_test3","company_test4","company_test5"};
                // String active_code = "kkkkkkkk";
                // String create_date = "2014-09-20 19:12:23";
                // String lastLoginTime = "2014-10-20 19:12:23";
                // for(int i=0;i<2000;i++){
                // try{
                // UserDaoImpl.getInstance().insert((emailbase+"@test.com"),
                // password,
                // phonebase, 1, company_name[new
                // Random().nextInt(company_name.length)],
                // active_code, create_date, lastLoginTime);
                // System.out.println(emailbase);
                // emailbase++;
                // phonebase++;
                // }catch(Exception e){
                // e.printStackTrace();
                // }
                // }
        }

        public static String string2MD5(String inStr) {
                MessageDigest md5 = null;
                try {
                        md5 = MessageDigest.getInstance("MD5");
                } catch (Exception e) {
                        return "";
                }
                char[] charArray = inStr.toCharArray();
                byte[] byteArray = new byte[charArray.length];

                for (int i = 0; i < charArray.length; i++)
                        byteArray[i] = (byte) charArray[i];
                byte[] md5Bytes = md5.digest(byteArray);
                StringBuffer hexValue = new StringBuffer();
                for (int i = 0; i < md5Bytes.length; i++) {
                        int val = ((int) md5Bytes[i]) & 0xff;
                        if (val < 16)
                                hexValue.append("0");
                        hexValue.append(Integer.toHexString(val));
                }
                return hexValue.toString();

        }

        public static Admin verify(String username, String password) {
                if (username == null)
                        return null;
                Admin admin = AdminDaoImpl.getInstance().findByName(username);
                if (admin == null) {
                        return null;
                }
                if (admin.getStatus() <= 0)
                        return null;
                if (password.equals(admin.getPassword())) {
                        return admin;
                } else {
                        return null;
                }
        }

        public static String insert(String email, String name, String grades, Long id, Long adminId, int group) {
                try {
                        Admin admin = findById(id);
                        if (admin != null) {
                                return OneTools.getResult(0, "id已经存在");
                        }
                        admin = findByEmail(email);
                        if (admin != null) {
                                return OneTools.getResult(0, "email已经存在");
                        }
                        String token = string2MD5(email + TimeTools.format() + new Random().nextInt());
                        String html = "<div style='width:400px;height:300px;margin: 30px 0 0 40px;'>" + "<div>运营系统为你分配了一个帐号，请尽快激活。</div>" + "<div><a href='http://manage.oneapm.com/account_sign.action?" + "id=" + id + "&token=" + token + "'>点击激活帐号</a>" + "</div>" + "<div style='color:red;'>" + "<div>(运营系统支持chrome,firefox,IE9以上浏览器)</div>" + "<div>如果遇到使用问题，请联系@李江</div>" + "</div>" + "<div>" + "<div style='margin:10px 0 0 0;'>李江</div>" + "<div>Email:lijiang@oneapm.com</div>" + "<div>tel:13311393672</div>" + "<div>qq:695419811</div>" + "</div>" + "</div>";
                        if (AdminDaoImpl.getInstance().insert(id, name, email, grades, 0, 0, token, adminId, group)) {
                                if (SendCloudService.sendMail("lijiang@oneapm.com", email, html, "运营系统帐号激活")) {
                                        return OneTools.getResult(1, "");
                                } else {
                                        return OneTools.getResult(0, "邮箱信息有误");
                                }
                        }
                } catch (Exception e) {
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String update(Long id, String token, String phone, String position, String nickName, String password, String username) {
                JSONObject object = new JSONObject();
                try {
                        password = string2MD5(password);
                        String t = AdminDaoImpl.getInstance().getToken(id);
                        if (t == null || token == null || !t.equals(token)) {
                                object.put("status", 0);
                                object.put("msg", "验证失败");
                                return object.toJSONString();
                        }
                        if (AdminDaoImpl.getInstance().update(id, phone, nickName, position, password, username)) {
                                object.put("status", 1);
                        }
                } catch (Exception e) {
                        object.put("status", 0);
                        object.put("msg", "验证失败");
                }
                return object.toJSONString();
        }

        public static JSONArray getArrayFromAdminList(List<Admin> admins) {
                JSONArray array = new JSONArray();
                for (Admin admin : admins) {
                        array.add(getJSONFromAdmin(admin));
                }
                return array;
        }

        public static JSONObject getJSONFromAdmin(Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", admin.getId());
                        object.put("name", admin.getName());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
