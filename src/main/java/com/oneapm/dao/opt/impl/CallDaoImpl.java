package com.oneapm.dao.opt.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Call;

public class CallDaoImpl extends DaoImplBase<Call> {
        protected static final Logger LOG = LoggerFactory.getLogger(CallDaoImpl.class);
        protected final String TABLE_NAME = "call";

        static {
                Instance = new CallDaoImpl();
        }

        private final static CallDaoImpl Instance;

        public static CallDaoImpl getInstance() {
                return Instance;
        }

        public long countByAdminId(Long accountId){
                try {
                        DBObject object = new BasicDBObject("admin_id", accountId);
                        return getDBCollection(TABLE_NAME).count(object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }
        public List<Call> findByAccountId(Long accountId, int number, int skip) {
                List<Call> calls = new ArrayList<Call>();
                try {
                        DBObject object = new BasicDBObject("admin_id", accountId);
                        DBObject sort = new BasicDBObject("call_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(skip).limit(number);
                        while (cursor.hasNext()) {
                                calls.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return calls;
        }

        public Call findById(Long callId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("call_id", callId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public boolean exit(Long infoId, Long adminId, String time){
                try{
                      DBObject object = new BasicDBObject();
                      object.put("info_id", infoId);
                      object.put("admin_id", adminId);
                      object.put("call_time", new BasicDBObject("$gte",time));
                      DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                      return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return true;
        }

        public Call insertAndGet(Call call) {
                try {
                        Long id = getIdes();
                        if (id == null) {
                                return null;
                        }
                        call.setCallId(id);
                        DBObject value = new BasicDBObject();
                        value.put("call_id", id);
                        value.put("info_id", call.getInfoId());
                        value.put("card_id", call.getCardId());
                        value.put("mark", call.getMark());
                        value.put("call_time", call.getCallTime());
                        value.put("admin_id", call.getAdminId());
                        value.put("company", call.getCompany());
                        value.put("type", call.getType());
                        value.put("gongdan", call.getGongdan());
                        value.put("time", call.getTime());
                        value.put("todu", call.getTodu());
                        if (getDBCollection(TABLE_NAME).insert(value).getN() > -1) {
                                return call;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Long getIdes() {
                Long id = 100L;
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("call_id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("call_id").toString()) + 1;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return id;
        }

        public List<Call> findByInfoId(Long infoId) {
                List<Call> calls = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        DBObject sort = new BasicDBObject();
                        sort.put("call_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        calls = new ArrayList<Call>();
                        while (cursor.hasNext()) {
                                calls.add(getInfoFromResult(cursor.next()));
                        }

                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return calls;
        }

        public Call getInfoFromResult(DBObject object) {
                Call call = null;
                try {
                        Long infoId = null;
                        try {
                                infoId = Long.parseLong(object.get("info_id").toString().trim());
                        } catch (Exception e) {
                        }
                        Long callId = Long.parseLong(object.get("call_id").toString().trim());
                        Long cardId = null;
                        try {
                                cardId = Long.parseLong(object.get("card_id").toString().trim());
                        } catch (Exception e) {
                        }
                        String callTime = object.get("call_time").toString();
                        String mark = object.get("mark").toString();
                        Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        Long type = null;
                        try {
                                type = Long.parseLong(object.get("type").toString().trim());
                        } catch (Exception e) {
                        }
                        String company = null;
                        try {
                                company = object.get("company").toString();
                        } catch (Exception e) {
                        }
                        String time = null;
                        try {
                                time = object.get("time").toString();
                        } catch (Exception e) {
                        }
                        Long gongdan = null;
                        try {
                                gongdan = Long.parseLong(object.get("gongdan").toString().trim());
                        } catch (Exception e) {
                        }
                        int todu = 0;
                        try {
                                todu = Integer.parseInt(object.get("todu").toString().trim());
                        } catch (Exception e) {
                        }
                        call = new Call(infoId, callId, cardId, callTime, mark, adminId, company, type, time, gongdan);
                        call.setTodu(todu);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return call;
        }
}
