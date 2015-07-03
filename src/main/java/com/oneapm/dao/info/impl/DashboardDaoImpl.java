package com.oneapm.dao.info.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Active;
import com.oneapm.dto.info.Dashboard;

public class DashboardDaoImpl extends DaoImplBase<Active> {
        protected static final Logger LOG = LoggerFactory.getLogger(DashboardDaoImpl.class);
        protected static final String TABLE_NAME = "dashboard2";

        static {
                Instance = new DashboardDaoImpl();
        }

        private final static DashboardDaoImpl Instance;

        public static DashboardDaoImpl getInstance() {
                return Instance;
        }

        public Dashboard findLast() {
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return getDashboardFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Dashboard find(String start, String end) {
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                        list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(new BasicDBObject("$and", list)).sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return getDashboardFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        private Dashboard getDashboardFromObject(DBObject object) {
                Dashboard dashboard = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        int sign = Integer.parseInt(object.get("sign").toString());
                        int downloadNew = Integer.parseInt(object.get("download_new").toString());
                        int appNew = Integer.parseInt(object.get("app_new").toString());
                        int download= Integer.parseInt(object.get("download").toString());
                        int app= Integer.parseInt(object.get("app").toString());
                        String dataTime = object.get("data_time").toString();
                        int uv = Integer.parseInt(object.get("uv").toString());
                        int newUv = Integer.parseInt(object.get("new_uv").toString());
                        int data_all = Integer.parseInt(object.get("data_all").toString());
                        int app_all = Integer.parseInt(object.get("app_all").toString());
                        int data_ai= Integer.parseInt(object.get("data_ai").toString());
                        int app_ai = Integer.parseInt(object.get("app_ai").toString());
                        int only_ai = Integer.parseInt(object.get("only_ai").toString());
                        int data_mi = Integer.parseInt(object.get("data_mi").toString());
                        int app_mi = Integer.parseInt(object.get("app_mi").toString());
                        int only_mi = Integer.parseInt(object.get("only_mi").toString());
                        int data_server= Integer.parseInt(object.get("data_server").toString());
                        int app_server= Integer.parseInt(object.get("app_server").toString());
                        int only_server= Integer.parseInt(object.get("only_server").toString());
                        dashboard = new Dashboard(id, sign, download, downloadNew, app, appNew, dataTime, uv, newUv, data_all, app_all, data_ai, app_ai, only_ai, data_mi, app_mi, only_mi, data_server, app_server, only_server);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return dashboard;
        }
}
