package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;

public class XiazaiDaoImpl extends DaoImplBase<Long> {
        protected static final Logger LOG = LoggerFactory.getLogger(XiazaiDaoImpl.class);
        protected final String TABLE_NAME = "xiazai";

        static {
                Instance = new XiazaiDaoImpl();
        }

        private final static XiazaiDaoImpl Instance;

        public static XiazaiDaoImpl getInstance() {
                return Instance;
        }

        public List<Long> findByTime(String dataTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$gte", dataTime));
                        List<Long> ids = new ArrayList<Long>();
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                ids.add(Long.parseLong(cursor.next().get("user_id").toString()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Long> findByTime(String start, String end) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("$gte", start);
                        if (end != null) {
                                object.put("$lt", end);
                        }
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(new BasicDBObject("data_time", object)).sort(sort);
                        List<Long> ids = new ArrayList<Long>();
                        while (cursor.hasNext()) {
                                ids.add(Long.parseLong(cursor.next().get("group_id").toString()));
                        }
                        return ids;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean findByIdAndTime(String dataTime, Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$gte", dataTime));
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean findBeforeByIdAndTime(String dataTime, Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$lt", dataTime));
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean insert(Long userId, String dataTime) {
                try {
                        DBObject value = new BasicDBObject();
                        value.put("user_id", userId);
                        value.put("data_time", dataTime);
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
}
