package com.oneapm.dao.mail.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.record.PushDeal;

public class PushDealDaoImpl extends DaoImplBase<PushDeal>{
    protected static final Logger LOG = LoggerFactory.getLogger(PushDealDaoImpl.class);
    protected final String TABLE_NAME = "push_deal";
    
    static {
        Instance = new PushDealDaoImpl();
    }
    
    private final static PushDealDaoImpl Instance;
    
    public static PushDealDaoImpl getInstance(){
        return Instance;
    }
    public Long getIdest(){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(object).limit(1);
            if(cursor.hasNext()){
                return Long.parseLong(cursor.next().get("id").toString().trim());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return 100L;
    }
    public boolean insert(PushDeal deal){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", deal.getId());
            object.put("push_id", deal.getPushId());
            object.put("admin_id", deal.getAdminId());
            object.put("type", deal.getType());
            object.put("create_time", deal.getCreateTime());
            object.put("pass_id", deal.getPassId());
            object.put("mode", deal.getMode());
            object.put("call_id", deal.getCallId());
            object.put("put_time", deal.getPutTime());
            return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    
}
