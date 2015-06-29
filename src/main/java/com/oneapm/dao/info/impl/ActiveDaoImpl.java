package com.oneapm.dao.info.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Active;

public class ActiveDaoImpl extends DaoImplBase<Active>{
	protected static final Logger LOG = LoggerFactory.getLogger(ActiveDaoImpl.class);
	protected static final String TABLE_NAME = "active";
	
	static {
		Instance = new ActiveDaoImpl();
	}
	
	private final static ActiveDaoImpl Instance;
	
	public static ActiveDaoImpl getInstance(){
		return Instance;
	}
	
	public Active findByIdAndDataTime(Long appId, String dataTime, int type){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("app_id", appId);
	        object.put("data_time", dataTime);
	        object.put("type", type);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	        if(cursor.hasNext()){
	            return getActiveFromObject(cursor.next());
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return null;
	}
	
	
	private Active getActiveFromObject(DBObject object){
	    Active active = null;
	    try{
	        Long id = Long.parseLong(object.get("id").toString().trim());
	        Long appId = Long.parseLong(object.get("app_id").toString().trim());
	        Long userId = Long.parseLong(object.get("user_id").toString().trim());
	        int type = Integer.parseInt(object.get("type").toString().trim());
	        String dataTime = object.get("data_time").toString();
	        Long activ = Long.parseLong(object.get("active").toString().trim());
	        active = new Active(id, appId, userId, type, dataTime, activ);
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return active;
	}
}
