package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.tag.Language;

public class AppDataDaoImpl extends DaoImplBase<Aplication> {
        protected static final Logger LOG = LoggerFactory.getLogger(AppDataDaoImpl.class);
        protected final String TABLE_NAME = "appData";

        static {
                Instance = new AppDataDaoImpl();
        }

        private final static AppDataDaoImpl Instance;

        public static AppDataDaoImpl getInstance() {
                return Instance;
        }
        public List<Long> test(JSONArray array) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", new BasicDBObject("$in", array));
                        return getDBCollection(TABLE_NAME).distinct("user_id", object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public List<Aplication> findByAgent(int agent, String start, String end){
                List<Aplication> apps = new ArrayList<Aplication>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        if(start != null){
                               BasicDBList list = new BasicDBList();
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                                object.put("$and", list);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public List<Long> findUserIdsByAgent(int agent, String start, String end){
                List<Long> userIds = new ArrayList<Long>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        if(start != null){
                               BasicDBList list = new BasicDBList();
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                                object.put("$and", list);
                        }
                        userIds = getDBCollection(TABLE_NAME).distinct("user_id", object);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return userIds;
        }
        
        public List<Long> exitBeforeTime(int agent, String dataTime, List<Long> USERIDS){
                List<Long> userIds = new ArrayList<Long>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        object.put("data_time", new BasicDBObject("$lt", dataTime));
                       JSONArray array = new JSONArray();
                       for(Long userId : USERIDS){
                               array.add(userId);
                       }
                        object.put("user_id", new BasicDBObject("$in",array));
                        userIds = getDBCollection(TABLE_NAME).distinct("user_id", object);
                        System.out.println(userIds.size());
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return userIds;
        }
        
        public List<Aplication> findByTime(String start, String end){
                List<Aplication> apps = new ArrayList<Aplication>();
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public boolean existByTimeAndUserId(String start, String end, Long userId){
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        /**
         * 一段时间内是否有数据
         * @param start
         * @param end
         * @param userId
         * @param agent
         * @return
         */
        public boolean existByTimeAndUserIdAndAgent(String start, String end, Long userId, int agent){
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean duli(String start, String end, Long userId, int agentStart, int agentEnd){
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", userId);
                        BasicDBList agents = new BasicDBList();
                        agents.add(new BasicDBObject("agent", new BasicDBObject("$gte", agentStart)));
                        agents.add(new BasicDBObject("agent", new BasicDBObject("$lte", agentEnd)));
                        if(agentEnd >= agentStart){
                                object.put("$and", agents);
                        }else{
                                object.put("$or", agents);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public List<Aplication> findByTimeAndUserId(String start, String end, Long userId){
                List<Aplication> aplications = new ArrayList<Aplication>();
                try{
                       DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                aplications.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return aplications;
        }
        
        public Aplication findByUserIdLast(Long userId){
                try{
                        DBObject object = new BasicDBObject("user_id", userId);
                        DBObject sort = new BasicDBObject("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
                        if(cursor.hasNext()){
                                return getAplicationFromResult(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Aplication findByUserIdfirst(Long userId){
                try{
                        DBObject object = new BasicDBObject("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(new BasicDBObject("data_time", 1)).limit(1);
                        if(cursor.hasNext()){
                                return getAplicationFromResult(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Aplication> findByTimeList(String time, Long appId, int agent, Long agentId) {
                List<Aplication> apps = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("data_time", new BasicDBObject("$gte", time));
                        object.put("agent", agent);
                        object.put("agent_id", agentId);
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        apps = new ArrayList<Aplication>();
                        while (cursor.hasNext()) {
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        

        public boolean exist(Long userId, String dataTime, int agent) {
                try {
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        object.put("user_id", userId);
                        object.put("data_time", new BasicDBObject("$gte", dataTime));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean exist(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }


        public Aplication getAplicationFromResult(DBObject object) {
                Aplication ap = null;
                try {
                        Long appId = Long.parseLong(object.get("app_id").toString().trim());
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String dataTime = object.get("data_time").toString();
                        Long agentId = 0L;
                        try{
                                agentId = Long.parseLong(object.get("agent_id").toString());
                        }catch(Exception e){}
                        ap = new Aplication(appId, agent, userId, dataTime, Language.getName(agent), agentId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return ap;
        }

}
