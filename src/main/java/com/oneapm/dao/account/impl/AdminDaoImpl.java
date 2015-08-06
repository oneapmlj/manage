package com.oneapm.dao.account.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Account.Admin;
import com.oneapm.util.TimeTools;

public class AdminDaoImpl extends DaoImplBase<Admin> {

        protected static final Logger LOG = LoggerFactory.getLogger(AdminDaoImpl.class);

        protected final static String TABLE_NAME = "admin";

        private final static AdminDaoImpl Instance;

        static {
                Instance = new AdminDaoImpl();
        }

        public static AdminDaoImpl getInstance() {
                return Instance;
        }
        
        public List<Admin> atAdmin(){
                List<Admin> admins = new ArrayList<Admin>();
                try{
                        DBObject object = new BasicDBObject();
                        object.put("at", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public List<Admin> at(String name){
                List<Admin> admins = new ArrayList<Admin>();
                try{
                        DBObject object = new BasicDBObject();
                        Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);
                        object.put("name", pattern);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }
        public Admin findByEmail(String email) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("email", email.trim());
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getAdminFromResultSet(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Admin> list(int group) {
                List<Admin> admins = null;
                DBCursor cursor = null;
                try {
                        DBObject object = new BasicDBObject();
                        if (group > 0) {
                                object.put("group", group);
                        }
                        DBObject sort = new BasicDBObject();
                        sort.put("group", -1);
                        object.put("status", new BasicDBObject("$gte", 1));
                        cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        admins = new ArrayList<Admin>();
                        while (cursor.hasNext()) {
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public Long getIdest(Long ids, Long ide) {
                try {
                        DBObject object = new BasicDBObject();
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("id", new BasicDBObject("$gte", ids)));
                        list.add(new BasicDBObject("id", new BasicDBObject("$lt", ide)));
                        object.put("$and", list);
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public String getToken(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return cursor.next().get("token").toString();
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean addToken(Long id, String token) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBObject value = new BasicDBObject();
                        value.put("token", token);
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public List<Admin> findSales() {
                List<Admin> admins = null;
                try {
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("id", new BasicDBObject("$gte", 80000000L)));
                        list.add(new BasicDBObject("id", new BasicDBObject("$lt", 90000000L)));
                        DBObject object = new BasicDBObject();
                        object.put("$and", list);
                        object.put("status", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        admins = new ArrayList<Admin>();
                        while (cursor.hasNext()) {
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public List<Admin> findSupports() {
                List<Admin> admins = null;
                try {
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("id", new BasicDBObject("$gte", 40000000L)));
                        list.add(new BasicDBObject("id", new BasicDBObject("$lt", 50000000L)));
                        DBObject object = new BasicDBObject();
                        object.put("$and", list);
                        object.put("status", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        admins = new ArrayList<Admin>();
                        while (cursor.hasNext()) {
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public List<Admin> findAdmins(int adminType) {
                List<Admin> admins = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("status", 1);
                        if(adminType > 0){
                                object.put("group", adminType);
                        }
                        DBObject sort = new BasicDBObject("id", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        admins = new ArrayList<Admin>();
                        while (cursor.hasNext()) {
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public Admin findById(Long id) {
                try {
                        BasicDBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getAdminFromResultSet(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Admin findByRealName(String name){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("name", name);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return getAdminFromResultSet(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Admin> findByGroup(int group) {
                List<Admin> admins = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("group", group);
                        object.put("status", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        admins = new ArrayList<Admin>();
                        while (cursor.hasNext()) {
                                Admin admin = getAdminFromResultSet(cursor.next());
                                if(admin != null){
                                        admins.add(admin);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return admins;
        }

        public Admin findByName(String username) {
                DBCollection coll = getDBCollection(TABLE_NAME);
                DBCursor cursor = null;
                try {
                        BasicDBObject object = new BasicDBObject();
                        object.put("username", username);
                        cursor = coll.find(object);
                        if (cursor.hasNext()) {
                                return getAdminFromResultSet(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean update(Long id, String phone, String nickName, String position, String password, String username) {
                try {
                        DBObject object = new BasicDBObject("id", id);
                        DBObject value = new BasicDBObject();
                        DBObject v = new BasicDBObject();
                        v.put("status", 1);
                        v.put("phone", phone);
                        v.put("nick_name", nickName);
                        v.put("position", position);
                        v.put("login_time", TimeTools.format());
                        v.put("password", password);
                        v.put("token", null);
                        v.put("username", username);
                        value.put("$set", v);
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean insert(Long id, String name, String email, String grades, int grade, int status, String token, Long adminId, int group) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("grade", grade);
                        object.put("status", status);
                        object.put("id", id);
                        object.put("create_id", adminId);
                        object.put("create_time", TimeTools.format());
                        object.put("name", name);
                        object.put("login_time", null);
                        object.put("group", 1);
                        object.put("grades", grades);
                        object.put("token", token);
                        object.put("email", email);
                        object.put("group", group);
                        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean update(Admin admin) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", admin.getId());
                        DBObject value = new BasicDBObject();
                        value.put("username", admin.getUsername());
                        value.put("password", admin.getPassword());
                        value.put("grade", new Integer(admin.getGrade()));
                        value.put("status", new Integer(admin.getStatus()));
                        value.put("create_id", admin.getCreateId());
                        value.put("create_time", admin.getCreateTime());
                        value.put("name", admin.getName());
                        value.put("login_time", admin.getLoginTime());
                        value.put("group", admin.getGroup());
                        value.put("grades", admin.getGrades());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean updateLogin(Long adminId, String loginTime) {
                try {
                        return getDBCollection(TABLE_NAME).update(new BasicDBObject("id", adminId), new BasicDBObject("$set", new BasicDBObject("login_time", loginTime))).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean updateXiaoshouyiId(Long adminId, Long xiaoshouyiId){
                try{
                        return getDBCollection(TABLE_NAME).update(new BasicDBObject("id", adminId), new BasicDBObject("$set",new BasicDBObject("xiaoshouyi_id", xiaoshouyiId))).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        private Admin getAdminFromResultSet(DBObject object) {
                Admin admin = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString());
                        Long createId = Long.parseLong(object.get("create_id").toString());
                        String username = object.get("username").toString();
                        String password = object.get("password").toString();
                        int grade = Integer.parseInt(object.get("grade").toString().trim());
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        String createTime = object.get("create_time").toString();
                        int group = Integer.parseInt(object.get("group").toString().trim());
                        String position = object.get("position").toString().trim();
                        String phone = object.get("phone").toString().trim();
                        String email = object.get("email").toString().trim();
                        String nickName = object.get("nick_name").toString();
                        String grades = object.get("grades").toString();
                        String loginTime = object.get("login_time").toString();
                        String name = object.get("name").toString();
                        Long xiaoshouyiId = null;
                        try{
                                xiaoshouyiId = Long.parseLong(object.get("xiaoshouyi_id").toString());
                        }catch(Exception e){}
                        admin = new Admin(group, id, username, password, grade, status, createTime, createId, position, phone, email, nickName, grades);
                        admin.setName(name);
                        admin.setLoginTime(loginTime);
                        admin.setXiaoshouyiId(xiaoshouyiId);
                } catch (Exception e) {}
                return admin;
        }
}
