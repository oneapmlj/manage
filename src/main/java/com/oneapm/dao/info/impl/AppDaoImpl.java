package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.App;

public class AppDaoImpl extends DaoImplBase<App> {
        protected static final Logger LOG = LoggerFactory.getLogger(AppDaoImpl.class);
        protected final String TABLE_NAME = "app";

        static {
                Instance = new AppDaoImpl();
        }

        private final static AppDaoImpl Instance;

        public static AppDaoImpl getInstance() {
                return Instance;
        }
        public List<App> findByAgent(int agent, String banben, boolean dataTime){
                List<App> apps = new ArrayList<App>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                                if(agent < 7){
                                        object.put("version", banben);
                                }
                        }
                        if(dataTime){
                                object.put("data_time", new BasicDBObject("$gte","2014-01-01 00:00:00"));
                        }
                        object.put("parent_id", 0L);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getAppFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public boolean findByAgentAndUserId(Long userId, int agent, String banben){
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                                if(agent < 7){
                                        object.put("version", banben);
                                }
                        }
                        object.put("user_id", userId);
                        object.put("parent_id", 0L);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        
        public App getAppFromResult(DBObject object) {
                App ap = null;
                try {
                        Long appId = Long.parseLong(object.get("app_id").toString().trim());
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String dataTime = object.get("data_time").toString();
                        Long agentId = 0L;
                        String createTime = object.get("create_time").toString();
                        String language = null;
                        try{
                                language = object.get("language").toString();
                        }catch(Exception e){}
                        try{
                                agentId = Long.parseLong(object.get("agent_id").toString());
                        }catch(Exception e){}
                        String appName = object.get("name").toString();
                        String version = object.get("version").toString();
                        ap = new App(userId, createTime, language, appId, appName, dataTime, agent, agentId);
                        ap.setVersion(version);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return ap;
        }

}
