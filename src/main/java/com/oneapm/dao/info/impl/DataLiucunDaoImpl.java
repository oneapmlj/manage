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
import com.oneapm.dto.info.DataLiucunShow;

public class DataLiucunDaoImpl extends DaoImplBase<DataLiucun> {
        protected static final Logger LOG = LoggerFactory.getLogger(DataLiucunDaoImpl.class);
        protected final String TABLE_NAME = "data_liucun_week_show";
        protected final String TABLE_NAME_Data = "data_liucun_week";

        static {
                Instance = new DataLiucunDaoImpl();
        }

        private final static DataLiucunDaoImpl Instance;

        public static DataLiucunDaoImpl getInstance() {
                return Instance;
        }

        public List<DataLiucunShow> findByType(int agent) {
                List<DataLiucunShow> shows = new ArrayList<DataLiucunShow>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("type", 1);
                        object.put("agent", agent);
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time_start", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                shows.add(getShowFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return shows;
        }

        public List<DataLiucunShow> findByFromTime(String fromTime, int agent) {
                List<DataLiucunShow> shows = new ArrayList<DataLiucunShow>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 2);
                        object.put("agent", agent);
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time_start", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                shows.add(getShowFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return shows;
        }

        public List<DataLiucun> findDataByIdAndNumber(Long id, int number) {
                List<DataLiucun> liucuns = new ArrayList<DataLiucun>();
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

        public List<DataLiucun> findDataByIdAndDataTime(String fromTime, String dataTime, int agent) {
                List<DataLiucun> liucuns = new ArrayList<DataLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 2);
                        object.put("data_time", dataTime);
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

        public List<DataLiucun> findDataByFromTime(String fromTime, int agent) {
                List<DataLiucun> liucuns = new ArrayList<DataLiucun>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("from_time", fromTime);
                        object.put("type", 1);
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

        public DataLiucunShow findByTime(Long id, String dataTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time_start", dataTime);
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

        private DataLiucunShow getShowFromObject(DBObject object) {
                DataLiucunShow show = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        int count = Integer.parseInt(object.get("count").toString().trim());
                        int percent = Integer.parseInt(object.get("percent").toString().trim());
                        String dataTimeStart = object.get("data_time_start").toString();
                        String dataTimeEnd = object.get("data_time_end").toString();
                        int number = Integer.parseInt(object.get("number").toString().trim());
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String fromTime = object.get("from_time").toString();
                        show = new DataLiucunShow(id, type, count, percent, 0, dataTimeStart, dataTimeEnd, number, agent, fromTime);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return show;
        }

        private DataLiucun getLiucunFromObject(DBObject object) {
                DataLiucun liucun = null;
                try {
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        String dataTime = object.get("data_time").toString();
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String fromTime = object.get("from_time").toString();
                        liucun = new DataLiucun(userId, id, dataTime, type, 0, agent, fromTime);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return liucun;
        }
}
