package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.DataLiucun;
import com.oneapm.dto.info.SignLiucun;
import com.oneapm.dto.info.SignLiucunShow;

public class SignLiucunDaoImpl extends DaoImplBase<DataLiucun> {
        protected static final Logger LOG = LoggerFactory.getLogger(SignLiucunDaoImpl.class);
        protected final String TABLE_NAME = "sign_liucun_week_show";
        protected final String TABLE_NAME_Data = "sign_liucun_week";

        static {
                Instance = new SignLiucunDaoImpl();
        }

        private final static SignLiucunDaoImpl Instance;

        public static SignLiucunDaoImpl getInstance() {
                return Instance;
        }
        
        public List<SignLiucun> findDownloadByIdAndDataTime(String fromTime, String dataTime, int agent) {
                List<SignLiucun> liucuns = new ArrayList<SignLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 2);
                        object.put("data_time", new BasicDBObject("$lte", dataTime));
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME_Data).find(object);
                        while (cursor.hasNext()) {
                                liucuns.add(getLiucunFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucuns;
        }
        
        public List<SignLiucun> findAppByIdAndDataTime(String fromTime, String dataTime, int agent) {
                List<SignLiucun> liucuns = new ArrayList<SignLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 3);
                        object.put("data_time", new BasicDBObject("$lte", dataTime));
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME_Data).find(object);
                        while (cursor.hasNext()) {
                                liucuns.add(getLiucunFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucuns;
        }

        public List<SignLiucun> findSignByFromTime(String fromTime) {
                List<SignLiucun> liucuns = new ArrayList<SignLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME_Data).find(object);
                        while (cursor.hasNext()) {
                                liucuns.add(getLiucunFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucuns;
        }
        public List<SignLiucunShow> findByType() {
                List<SignLiucunShow> shows = new ArrayList<SignLiucunShow>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("type", 1);
                        DBObject sort = new BasicDBObject();
                        sort.put("sign_time_start", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                shows.add(getShowFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return shows;
        }

        public List<SignLiucunShow> findByFromTime(String fromTime, int agent) {
                List<SignLiucunShow> shows = new ArrayList<SignLiucunShow>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 2);
                        object.put("agent", agent);
                        DBObject sort = new BasicDBObject();
                        sort.put("sign_time_start", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                shows.add(getShowFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return shows;
        }

        public List<SignLiucun> findDataByIdAndNumber(Long id, int number) {
                List<SignLiucun> liucuns = new ArrayList<SignLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        object.put("type", 2);
                        object.put("number", number);
                        DBCursor cursor = getDBCollection(TABLE_NAME_Data).find(object);
                        while (cursor.hasNext()) {
                                liucuns.add(getLiucunFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucuns;
        }

        public List<SignLiucun> findDataById(Long id) {
                List<SignLiucun> liucuns = new ArrayList<SignLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        object.put("type", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME_Data).find(object);
                        while (cursor.hasNext()) {
                                liucuns.add(getLiucunFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucuns;
        }

        public SignLiucunShow findByTime(Long id, String dataTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("sign_time_start", dataTime);
                        object.put("id", id);
                        object.put("type", 2);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getShowFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        private SignLiucunShow getShowFromObject(DBObject object) {
                SignLiucunShow show = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        int count_download = Integer.parseInt(object.get("count_download").toString().trim());
                        int count_app = Integer.parseInt(object.get("count_app").toString().trim());
                        int percent_download = Integer.parseInt(object.get("percent_download").toString().trim());
                        int percent_app = Integer.parseInt(object.get("percent_app").toString().trim());
                        String signTimeStart = object.get("sign_time_start").toString();
                        String signTimeEnd = object.get("sign_time_end").toString();
                        int number_download = Integer.parseInt(object.get("number_download").toString().trim());
                        int number_app = Integer.parseInt(object.get("number_app").toString().trim());
                        String fromTime = object.get("from_time").toString();
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        show = new SignLiucunShow(id, type, number_download, number_app, signTimeStart, signTimeEnd,
                                        count_download, percent_download, count_app, percent_app, agent, fromTime);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return show;
        }

        private SignLiucun getLiucunFromObject(DBObject object) {
                SignLiucun liucun = null;
                try {
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        String dataTime = object.get("data_time").toString();
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        liucun = new SignLiucun(userId, id, dataTime, type, 0);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucun;
        }
}
