package com.oneapm.dao.mail.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.record.MailPush;

public class MailPushDaoImpl extends DaoImplBase<MailPush> {
        protected static final Logger LOG = LoggerFactory.getLogger(MailPushDaoImpl.class);
        protected final String TABLE_NAME = "mail_push";

        static {
                Instance = new MailPushDaoImpl();
        }

        private final static MailPushDaoImpl Instance;

        public static MailPushDaoImpl getInstance() {
                return Instance;
        }

        public Long getIdest() {
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim()) + 1;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }

        public List<MailPush> findByAdmin(Long adminId, String time, int type) {
                List<MailPush> pushs = new ArrayList<MailPush>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("admin_id", adminId);
                        object.put("status", 0);
                        object.put("put_time", new BasicDBObject("$lte", time));
                        DBObject sort = new BasicDBObject();
                        if (type <= 0) {
                                sort.put("type", -1);
                        } else {
                                if (type == 4) {
                                        sort.put("put_time", -1);
                                }
                        }
                        sort.put("warming", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                pushs.add(getPushFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return pushs;
        }

        public int length(Long adminId, String time) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("admin_id", adminId);
                        object.put("status", 0);
                        object.put("put_time", new BasicDBObject("$lte", time));
                        return (int) getDBCollection(TABLE_NAME).count(object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public MailPush findById(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getPushFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean touch(Long infoId, Long adminId, String touchTime) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("admin_id", adminId);
                        object.put("status", 0);
                        DBObject value = new BasicDBObject();
                        value.put("touch_time", touchTime);
                        value.put("warming", 0);
                        value.put("warming_time", null);
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value), false, true).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean touchByGroupId(Long groupId, Long adminId, String touchTime) {
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    object.put("admin_id", adminId);
                    object.put("status", 0);
                    DBObject value = new BasicDBObject();
                    value.put("touch_time", touchTime);
                    value.put("warming", 0);
                    value.put("warming_time", null);
                    return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value), false, true).getN() > -1;
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return false;
    }

        public List<MailPush> findByInfoIdAndAdmin(Long infoId, Long adminId) {
                List<MailPush> pushs = new ArrayList<MailPush>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        object.put("admin_id", adminId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                pushs.add(getPushFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return pushs;
        }
        
        public List<MailPush> findByInfoId(Long infoId) {
                List<MailPush> pushs = new ArrayList<MailPush>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                pushs.add(getPushFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return pushs;
        }
        public List<MailPush> findByGroupId(Long groupId) {
            List<MailPush> pushs = new ArrayList<MailPush>();
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    object.put("status", 0);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                    while (cursor.hasNext()) {
                            pushs.add(getPushFromObject(cursor.next()));
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return pushs;
    }

        public MailPush findByInfoIdAdminType(Long infoId, Long adminId, int type) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        object.put("admin_id", adminId);
                        object.put("type", type);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getPushFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean insert(MailPush push) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", push.getId());
                        object.put("create_time", push.getCreateTime());
                        object.put("status", push.getStatus());
                        object.put("admin_id", push.getAdminId());
                        object.put("type", push.getType());
                        object.put("info_id", push.getInfoId());
                        object.put("put_time", push.getPutTime());
                        object.put("touch_time", push.getTouchTime());
                        object.put("from", push.getFrom());
                        object.put("from_id", push.getFromId());
                        object.put("warmin", push.getWarming());
                        object.put("point", push.isPoint());
                        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean update(MailPush push) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", push.getId());
                        DBObject value = new BasicDBObject();
                        value.put("status", push.getStatus());
                        value.put("put_time", push.getPutTime());
                        value.put("admin_id", push.getAdminId());
                        value.put("number", push.getNumber());
                        value.put("touch_time", push.getTouchTime());
                        value.put("from", push.getFrom());
                        value.put("from_id", push.getFromId());
                        value.put("warming", push.getWarming());
                        value.put("point", push.isPoint());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        private MailPush getPushFromObject(DBObject object) {
                MailPush push = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        String createTime = object.get("create_time").toString();
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        Long adminId = null;
                        try {
                                adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        } catch (Exception e) {
                        }
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        Long infoId = Long.parseLong(object.get("info_id").toString().trim());
                        String putTime = object.get("put_time").toString();
                        int number = 0;
                        try {
                                number = Integer.parseInt(object.get("number").toString().trim());
                        } catch (Exception e) {
                        }
                        String touchTime = null;
                        try {
                                touchTime = object.get("touch_time").toString();
                        } catch (Exception e) {
                        }
                        int from = 0;
                        try {
                                from = Integer.parseInt(object.get("from").toString().trim());
                        } catch (Exception e) {
                        }
                        Long fromId = null;
                        try {
                                fromId = Long.parseLong(object.get("from_id").toString().trim());
                        } catch (Exception e) {
                        }
                        int warming = 0;
                        try {
                                warming = Integer.parseInt(object.get("warming").toString().trim());
                        } catch (Exception e) {
                        }
                        String warmingTime = null;
                        try {
                                warmingTime = object.get("warming_time").toString();
                        } catch (Exception e) {
                        }
                        boolean point = false;
                        try{
                                point = Boolean.parseBoolean(object.get("point").toString().trim());
                        }catch(Exception e){}
                        push = new MailPush(id, createTime, status, adminId, type, infoId, putTime, number, touchTime, from, warming, warmingTime, point);
                        push.setFromId(fromId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return push;
        }
}
