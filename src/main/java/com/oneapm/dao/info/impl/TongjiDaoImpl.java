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

        public boolean update(Tongji tongji) {
                try {
                        DBObject object = new BasicDBObject("id", tongji.getId());
                        DBObject value = new BasicDBObject();
                        value.put("total", tongji.getTotal());
                        value.put("jiaoliu", tongji.getJiaoliu());
                        value.put("ceshi", tongji.getCeshi());
                        value.put("caigou", tongji.getCaigou());
                        value.put("wancheng", tongji.getWancheng());
                        value.put("wancheng_success", tongji.getWancheng_success());
                        value.put("wancheng_fail", tongji.getWancheng_fail());
                        value.put("point", tongji.getPoint());
                        value.put("common", tongji.getCommon());
                        value.put("unbin", tongji.getUnbin());
                        value.put("unuse", tongji.getUnuse());
                        value.put("sign", tongji.getSign());
                        value.put("login", tongji.getLogin());
                        value.put("download", tongji.getDownload());
                        value.put("app", tongji.getApp());
                        value.put("data", tongji.getData());
                        value.put("app_data", tongji.getAppData());
                        value.put("data_time", tongji.getData_time());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean insert(Tongji tongji) {
                try {
                        tongji.setId(getIdest() + 1L);
                        DBObject value = new BasicDBObject();
                        value.put("id", tongji.getId());
                        value.put("total", tongji.getTotal());
                        value.put("jiaoliu", tongji.getJiaoliu());
                        value.put("ceshi", tongji.getCeshi());
                        value.put("caigou", tongji.getCaigou());
                        value.put("wancheng", tongji.getWancheng());
                        value.put("wancheng_success", tongji.getWancheng_success());
                        value.put("wancheng_fail", tongji.getWancheng_fail());
                        value.put("point", tongji.getPoint());
                        value.put("common", tongji.getCommon());
                        value.put("unbin", tongji.getUnbin());
                        value.put("unuse", tongji.getUnuse());
                        value.put("sign", tongji.getSign());
                        value.put("login", tongji.getLogin());
                        value.put("download", tongji.getDownload());
                        value.put("app", tongji.getApp());
                        value.put("data", tongji.getData());
                        value.put("app_data", tongji.getAppData());
                        value.put("data_time", tongji.getData_time());
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        private Tongji getTongjiFromJSON(DBObject object) {
                Tongji tongji = null;
                try {
                        long total = Long.parseLong(object.get("total").toString());
                        long jiaoliu = Long.parseLong(object.get("jiaoliu").toString());
                        long ceshi = Long.parseLong(object.get("ceshi").toString());
                        long caigou = Long.parseLong(object.get("caigou").toString());
                        // long wancheng =
                        // Long.parseLong(object.get("wancheng").toString());
                        long wancheng_success = Long.parseLong(object.get("finish_success").toString());
                        long wancheng_fail = Long.parseLong(object.get("finish_fail").toString());
                        long point = Long.parseLong(object.get("point").toString());
                        long common = Long.parseLong(object.get("common").toString());
                        long unbin = Long.parseLong(object.get("unbin").toString());
                        long unuse = Long.parseLong(object.get("unuse").toString());
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
                        tongji = new Tongji(id, total, jiaoliu, ceshi, caigou, 0, wancheng_success, wancheng_fail, point, common, unbin, unuse, sign, login, download, app, data, appData);
                        tongji.setData_time(data_time);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tongji;
        }
}
