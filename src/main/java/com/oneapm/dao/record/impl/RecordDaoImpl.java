package com.oneapm.dao.record.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.record.Record;

public class RecordDaoImpl extends DaoImplBase<Record> {

    protected static final Logger LOG = LoggerFactory
            .getLogger(RecordDaoImpl.class);

    protected final static String TABLE_NAME = "record";

    private final static RecordDaoImpl Instance;

    static {
        Instance = new RecordDaoImpl();
    }

    public static RecordDaoImpl getInstance() {
        return Instance;
    }
    
    public Record findById(Long id){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", id);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
            if(cursor.hasNext()){
                return getRecorFromObject(cursor.next());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    
    public List<Record> findRecords(int start, int number){
        List<Record> records = null;
        try{
            DBObject sort = new BasicDBObject();
            sort.put("create_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).skip(start).limit(number);
            records = new ArrayList<Record>();
            while(cursor.hasNext()){
                records.add(getRecorFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return records;
    }
    
    public List<Record> findByAdminId(Long adminId){
        List<Record> records = null;
        try{
            DBObject object = new BasicDBObject();
            object.put("admin_id", adminId);
            DBObject sort = new BasicDBObject();
            sort.put("create_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
            records = new ArrayList<Record>();
            while(cursor.hasNext()){
                records.add(getRecorFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return records;
    }
    
    public Long getIdest(){
        try{
            DBObject sort = new BasicDBObject();
            sort.put("id", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
            if(cursor.hasNext()){
                return Long.parseLong(cursor.next().get("id").toString().trim());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return 100L;
    }
    
    public boolean insert(Record record){
        if(record == null)return false;
        try{
            DBObject value = new BasicDBObject();
            record.setId(getIdest()+1L);
            value.put("id", record.getId());
            value.put("admin_id", record.getAdminId());
            value.put("info_id", record.getInfoId());
            value.put("type", record.getType());
            value.put("create_time", record.getCreateTime());
            value.put("from_id", record.getFromId());
            value.put("status", record.getStatus());
            value.put("metric", record.getMetric());
            value.put("loudou", record.getLoudou());
            value.put("me", record.getMe());
            value.put("lou", record.getLou());
            return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    private Record getRecorFromObject(DBObject object){
        Record record = null;
        try{
            Long id = Long.parseLong(object.get("id").toString().trim());
            Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
            Long infoId = Long.parseLong(object.get("info_id").toString().trim());
            int type = Integer.parseInt(object.get("type").toString().trim());
            String createTime = object.get("create_time").toString();
            Long fromId = Long.parseLong(object.get("from_id").toString().trim());
            int status = Integer.parseInt(object.get("status").toString().trim());
            Integer metric = 0;
            try{
                metric = Integer.parseInt(object.get("metric").toString().trim());
            }catch(Exception e){}
            Integer loudou = 0;
            try{
                loudou = Integer.parseInt(object.get("loudou").toString().trim());
            }catch(Exception e){}
            Integer me = 0;
            try{
                me = Integer.parseInt(object.get("me").toString().trim());
            }catch(Exception e){}
            Integer lou = 0;
            try{
                lou = Integer.parseInt(object.get("lou").toString().trim());
            }catch(Exception e){}
            record = new Record(id, adminId, infoId, type, createTime, fromId, status,metric, loudou, me, lou);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return record;
    }
}
