package com.oneapm.dao.info.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Tip;

public class TipDaoImpl extends DaoImplBase<Tip>{
    protected static final Logger LOG = LoggerFactory.getLogger(TipDaoImpl.class);
    protected final String TABLE_NAME = "tip";
    
    static {
        Instance = new TipDaoImpl();
    }
    
    private final static TipDaoImpl Instance;
    
    public static TipDaoImpl getInstance(){
        return Instance;
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
    
    public Tip findByInfoId(Long infoId){
        try{
            DBObject object = new BasicDBObject();
            object.put("info_id", infoId);
            DBObject sort = new BasicDBObject();
            sort.put("data_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
            if(cursor.hasNext()){
                return getTipFromObject(cursor.next());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    public boolean insert(Tip tip){
        try{
            DBObject value = new BasicDBObject();
            value.put("from", tip.getFrom());
            value.put("to", tip.getTo());
            value.put("data_time", tip.getDataTime());
            value.put("id", tip.getId());
            value.put("info_id", tip.getInfoId());
            return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    
    private Tip getTipFromObject(DBObject object){
        Tip tip = null;
        try{
            Long id = Long.parseLong(object.get("id").toString().trim());
            Long infoId = Long.parseLong(object.get("info_id").toString().trim());
            Long from = Long.parseLong(object.get("from").toString().trim());
            Long to = Long.parseLong(object.get("to").toString().trim());
            String dataTime = object.get("data_time").toString();
            tip = new Tip(id, from, to, dataTime, infoId);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return tip;
    }
}
