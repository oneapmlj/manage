package com.oneapm.dao.opt.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.App;

public class AddDaoImpl extends DaoImplBase<App> {
        protected static final Logger LOG = LoggerFactory.getLogger(AddDaoImpl.class);
        protected static final String TABLE_NAME = "app";

        static {
                Instance = new AddDaoImpl();
        }

        private final static AddDaoImpl Instance;

        public static AddDaoImpl getInstance() {
                return Instance;
        }

        public List<App> findByAgent(int agent, String start, String end) {
                List<App> apps = new ArrayList<App>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("agent", agent);
                        if (start != null) {
                                BasicDBList list = new BasicDBList();
                                list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                                list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                                object.put("$and", list);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }

        public List<App> findByAgent(String start, String end) {
                List<App> apps = new ArrayList<App>();
                try {
                        DBObject object = new BasicDBObject();
                        if (start != null) {
                                BasicDBList list = new BasicDBList();
                                list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                                list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                                object.put("$and", list);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }

        public List<App> countData(String time) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$gte", time));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        List<App> apps = new ArrayList<App>();
                        while (cursor.hasNext()) {
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                        return apps;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<App> findByTime(String appTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("create_time", new BasicDBObject("$gte", appTime));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        List<App> apps = new ArrayList<App>();
                        while (cursor.hasNext()) {
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                        return apps;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<App> findByUserId(Long userId) {
                List<App> apps = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        object.put("parent_id", 0);
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        apps = new ArrayList<App>();
                        while (cursor.hasNext()) {
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public List<App> findByGroupId(Long groupId) {
            List<App> apps = null;
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    object.put("parent_id", 0);
                    DBObject sort = new BasicDBObject();
                    sort.put("create_time", -1);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                    apps = new ArrayList<App>();
                    while (cursor.hasNext()) {
                            apps.add(getInfoFromResult(cursor.next()));
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return apps;
    }
        public App findById(Long appId, Long agentId, int agent) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("agent_id", agentId);
                        object.put("agent", agent);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public App findById(Long appId, int agent) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("agent", agent);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public App findAddTime(Long userId) {
                try {
                        DBObject object = new BasicDBObject("user_id", userId);
                        DBObject sort = new BasicDBObject("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public List<App> findAll(){
                List<App> apps = new ArrayList<App>();
                try{
                        DBObject object = new BasicDBObject();
                        object.put("parent_id", 0L);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getInfoFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }

        public boolean update(Long appId, Long agentId, int agent, String dataTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("agent_id", agentId);
                        object.put("agent", agent);
                        DBObject value = new BasicDBObject();
                        value.put("$set", new BasicDBObject("data_time", dataTime));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }


        public App getInfoFromResult(DBObject object) {
                App app = null;
                try {
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        String createTime = object.get("create_time").toString();
                        String language = object.get("language").toString();
                        Long appId = Long.parseLong(object.get("app_id").toString().trim());
                        String appName = object.get("name").toString();
                        String dataTime = null;
                        try {
                                dataTime = object.get("data_time").toString();
                        } catch (Exception e) {
                        }
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String version = null;
                        try {
                                version = object.get("version").toString();
                        } catch (Exception e) {
                        }
                        Long agentId = 0L;
                        try{
                                agentId = Long.parseLong(object.get("agent_id").toString());
                        }catch(Exception e){}
                        Long groupId = 0L;
                        try{
                        	groupId = Long.parseLong(object.get("group_id").toString());
                        }catch(Exception e){}
                        app = new App(userId, createTime, language, appId, appName, dataTime, agent, agentId);
                        app.setVersion(version);
                        app.setGroupId(groupId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return app;
        }
}
