package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Mark;

public class MarkDaoImpl extends DaoImplBase<Mark> {
        protected static final Logger LOG = LoggerFactory.getLogger(MarkDaoImpl.class);
        protected final String TABLE_NAME = "mark";

        static {
                Instance = new MarkDaoImpl();
        }

        private final static MarkDaoImpl Instance;

        public static MarkDaoImpl getInstance() {
                return Instance;
        }

        public List<Mark> findByInfoId(Long infoId) {
                List<Mark> marks = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        marks = new ArrayList<Mark>();
                        while (cursor.hasNext()) {
                                marks.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return marks;
        }

        public List<Mark> findByAdminId(Long adminId) {
                List<Mark> marks = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("admin_id", adminId);
                        object.put("status", 0);
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        marks = new ArrayList<Mark>();
                        while (cursor.hasNext()) {
                                marks.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return marks;
        }

        public Mark findAdminIdAndInfoId(Long adminId, Long infoId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("admin_id", adminId);
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTagFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        

        public Mark findAdminIdAndGroupId(Long adminId, Long groupId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("admin_id", adminId);
                        object.put("group_id", groupId);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTagFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Mark findById(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTagFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Long getIdest() {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(object).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }

        public Mark insert(Mark mark) {
                try {
                        DBObject object = new BasicDBObject();
                        mark.setId(getIdest() + 1);
                        object.put("id", mark.getId());
                        object.put("admin_id", mark.getAdminId());
                        object.put("info_id", mark.getInfoId());
                        object.put("status", mark.getStatus());
                        object.put("create_time", mark.getCreateTime());
                        object.put("group_id", mark.getGroupId());
                        if (getDBCollection(TABLE_NAME).insert(object).getN() > -1) {
                                return mark;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean update(Mark mark) {
                try {
                        DBObject object = new BasicDBObject("id", mark.getId());
                        DBObject value = new BasicDBObject();
                        value.put("status", mark.getStatus());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public Mark getTagFromResult(DBObject object) {
                Mark mark = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        Long infoId = null;
                        try{
                        	 infoId = Long.parseLong(object.get("info_id").toString().trim());
                        }catch(Exception e){
                        }
                        String createTime = object.get("create_time").toString();
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        Long groupId = null;
                        try{
                        groupId = Long.parseLong(object.get("group_id").toString().trim());
                       }catch(Exception e){
                       }
                        mark = new Mark(id, infoId, createTime, status, adminId);
                        mark.setGroupId(groupId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return mark;
        }
}
