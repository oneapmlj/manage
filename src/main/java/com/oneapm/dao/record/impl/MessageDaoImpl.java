package com.oneapm.dao.record.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Account.Admin;
import com.oneapm.record.Message;

public class MessageDaoImpl extends DaoImplBase<Admin> {

        protected static final Logger LOG = LoggerFactory.getLogger(MessageDaoImpl.class);

        protected final static String TABLE_NAME = "message";

        private final static MessageDaoImpl Instance;

        static {
                Instance = new MessageDaoImpl();
        }

        public static MessageDaoImpl getInstance() {
                return Instance;
        }

        
        public Message findApplyByGroupId(Long groupId, int type, int status) {
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    object.put("status", status);
                    object.put("type", type);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                    if (cursor.hasNext()) {
                            return getMessageFromObject(cursor.next());
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return null;
        }
        public Message findById(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getMessageFromObject(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Message findByGroupId(Long groupId) {
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                    if (cursor.hasNext()) {
                            return getMessageFromObject(cursor.next());
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return null;
    }

        public List<Message> findByAdminId(Long adminId, int number, int skip) {
                List<Message> messages = null;
                try {
                        DBObject object = new BasicDBObject("to", adminId);
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(skip).limit(number);
                        messages = new ArrayList<Message>();
                        while (cursor.hasNext()) {
                                messages.add(getMessageFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return messages;
        }
        public int findCount(Long adminId){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("to", adminId);
                        return (int) getDBCollection(TABLE_NAME).count(object);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public List<Message> findByAdminIdUnView(Long adminId) {
                List<Message> messages = null;
                try {
                        DBObject object = new BasicDBObject("to", adminId);
                        object.put("status", 0);
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        messages = new ArrayList<Message>();
                        while (cursor.hasNext()) {
                                messages.add(getMessageFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return messages;
        }
        

        public List<Message> findByAdminIdUnClose(Long adminId) {
                List<Message> messages = null;
                try {
                        DBObject object = new BasicDBObject("to", adminId);
                        object.put("status", new BasicDBObject("$lte", 1));
                        DBObject sort = new BasicDBObject();
                        sort.put("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        messages = new ArrayList<Message>();
                        while (cursor.hasNext()) {
                                messages.add(getMessageFromObject(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return messages;
        }

        public Long getIdest() {
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }

        public boolean update(Message message) {
                try {
                        DBObject object = new BasicDBObject("id", message.getId());
                        DBObject value = new BasicDBObject();
                        value.put("status", message.getStatus());
                        value.put("view_time", message.getViewTime());
                        value.put("close_time", message.getCloseTime());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean updateByGroupId(Message message) {
            try {
                    DBObject object = new BasicDBObject("group_id", message.getGroupId());
                    DBObject value = new BasicDBObject();
                    value.put("status", message.getStatus());
                    value.put("view_time", message.getViewTime());
                    value.put("close_time", message.getCloseTime());
                    return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return false;
    }

        public boolean insert(Message message) {
                try {
                        message.setId(getIdest() + 1L);
                        DBObject value = new BasicDBObject();
                        value.put("id", message.getId());
                        value.put("from", message.getFrom());
                        value.put("to", message.getTo());
                        value.put("info_id", message.getInfoId());
                        value.put("status", message.getStatus());
                        value.put("create_time", message.getCreateTime());
                        value.put("type", message.getType());
                        value.put("group_id", message.getGroupId());
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        private Message getMessageFromObject(DBObject object) {
                Message message = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        Long from = Long.parseLong(object.get("from").toString().trim());
                        Long to = Long.parseLong(object.get("to").toString().trim());
                        Long infoId = null;
                        try {
                        	infoId = Long.parseLong(object.get("info_id").toString().trim());
	                    } catch (Exception e) {
	                    }
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        Long groupId = null;
                        try {
                         groupId = Long.parseLong(object.get("group_id").toString().trim());
                        }catch(Exception e){}
                        String viewTime = null;
                        try {
                                viewTime = object.get("view_time").toString().trim();
                        } catch (Exception e) {
                        }
                        String closeTime = null;
                        try {
                                closeTime = object.get("close_time").toString().trim();
                        } catch (Exception e) {
                        }
                        int type = Integer.parseInt(object.get("type").toString().trim());
                        String createTime = object.get("create_time").toString();
                        message = new Message(id, from, to, status, infoId, createTime, viewTime, closeTime, type);
                        message.setGroupId(groupId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return message;
        }
}
