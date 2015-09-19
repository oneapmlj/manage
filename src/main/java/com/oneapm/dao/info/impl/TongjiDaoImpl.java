package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Tongji;

public class TongjiDaoImpl extends DaoImplBase<Tongji> {
        protected static final Logger LOG = LoggerFactory.getLogger(TongjiDaoImpl.class);
        protected final String TABLE_NAME = "tongji";

        static {
                Instance = new TongjiDaoImpl();
        }

        private final static TongjiDaoImpl Instance;

        public static TongjiDaoImpl getInstance() {
                return Instance;
        }
        

        public Long getIdest() {
                try {
                        DBObject sort = new BasicDBObject("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 1L;
        }

        public Tongji findByTime(String time) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$gte", time));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTongjiFromJSON(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Tongji findById(Long id) {
                try {
                        DBObject object = new BasicDBObject("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTongjiFromJSON(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Tongji> index(Long start, int number) {
                List<Tongji> tongjis = null;
                try {
                        DBObject object = new BasicDBObject();
                        if (start >= 0L) {
                                object.put("id", new BasicDBObject("$lte", start));
                        }
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(number);
                        tongjis = new ArrayList<Tongji>();
                        while (cursor.hasNext()) {
                                tongjis.add(getTongjiFromJSON(cursor.next()));
                        }
                        return tongjis;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Tongji findLast() {
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return getTongjiFromJSON(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }


        private Tongji getTongjiFromJSON(DBObject object) {
                Tongji tongji = null;
                try {
                        long total = Long.parseLong(object.get("total").toString());
                        long sign = Long.parseLong(object.get("sign").toString());
                        long login = Long.parseLong(object.get("login").toString());
                        long download = Long.parseLong(object.get("download").toString());
                        long app = Long.parseLong(object.get("app").toString());
                        long data = Long.parseLong(object.get("data").toString());
                        String data_time = object.get("data_time").toString();
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        long appData = 0;
                        try {
                                appData = Long.parseLong(object.get("app_data").toString().trim());
                        } catch (Exception e) {
                        }
                        long group = 0L;
                        try {
                             group = Long.parseLong(object.get("group").toString());
                        }  catch (Exception e) {
                        }
                        tongji = new Tongji(group, id, total, sign, login, download, app, data, appData);
                        tongji.setData_time(data_time);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tongji;
        }
}
