package com.oneapm.dao.opt.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Download;

public class DownDaoImpl extends DaoImplBase<Download> {
        protected static final Logger LOG = LoggerFactory.getLogger(DownDaoImpl.class);
        protected static final String TABLE_NAME = "download";

        static {
                Instance = new DownDaoImpl();
        }

        private final static DownDaoImpl Instance;

        public static DownDaoImpl getInstance() {
                return Instance;
        }
        
        public List<String> findVersions(int agent){
                List<String> versions = new ArrayList<String>();
                try{
                        DBObject object = new BasicDBObject();
                        object.put("agent", agent);
                        DBObject sort = new BasicDBObject();
                        sort.put("vesion", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while(cursor.hasNext()){
                                versions.add(cursor.next().get("vesion").toString().trim());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return versions;
        }
        
        public List<Download> findByAgent(int agent, String banben){
                List<Download> downloads = new ArrayList<Download>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        if(banben != null){
                                object.put("vesion", banben);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                downloads.add(getInfoFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return downloads;
        }

        public List<Download> findByTime(String downloadTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("create_time", new BasicDBObject("$gte", downloadTime));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        List<Download> downloads = new ArrayList<Download>();
                        while (cursor.hasNext()) {
                                downloads.add(getInfoFromResult(cursor.next()));
                        }
                        return downloads;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Download> findByUserId(Long userId) {
                List<Download> downloads = new ArrayList<Download>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                downloads.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return downloads;
        }

        public Download findById(Long downloadId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("download_id", downloadId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Download findDonwTime(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
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

        public boolean insert(Download download) {
                try {
                        DBObject value = new BasicDBObject();
                        value.put("user_id", download.getUserId());
                        value.put("agentName", download.getAgentName());
                        value.put("create_time", download.getDownloadTime());
                        value.put("download_id", download.getId());
                        value.put("vesion", download.getVesion());
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public Download getInfoFromResult(DBObject object) {
                Download download = null;
                try {
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        String createTime = object.get("create_time").toString();
                        Long downloadId = Long.parseLong(object.get("download_id").toString().trim());
                        String vesion = object.get("vesion").toString().trim();
                        String language = object.get("agentName").toString();
                        download = new Download(downloadId, userId, createTime, language, vesion);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return download;
        }
}
