package com.oneapm.dao.file.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.file.Wendang;

public class WendangDaoImpl extends DaoImplBase<Wendang>{
	protected static final Logger LOG = LoggerFactory.getLogger(WendangDaoImpl.class);
	protected final String TABLE_NAME = "wendang";
	
	static {
		Instance = new WendangDaoImpl();
	}
	
	private final static WendangDaoImpl Instance;
	
	public static WendangDaoImpl getInstance(){
		return Instance;
	}
	
	public boolean insert(Wendang wendang){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", wendang.getId());
	        object.put("status", wendang.getStatus());
	        object.put("code", wendang.getCode());
	        object.put("create_time", wendang.getCreateTime());
	        object.put("name", wendang.getName());
	        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public boolean update(Wendang wendang){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", wendang.getId());
	        DBObject value = new BasicDBObject();
	        value.put("status", wendang.getStatus());
	        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public Long getIdest(){
	    try{
	        DBObject sort = new BasicDBObject();
	        sort.put("id", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
	        if(cursor.hasNext()){
	            return Long.parseLong(cursor.next().get("id").toString().trim())+1L;
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return 100L;
	}
	
	public Wendang findById(Long id){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", id);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	        if(cursor.hasNext()){
	            return getWendangFromObject(cursor.next());
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return null;
	}
	
	public Wendang findByCode(String code){
	    try{
	        DBObject object = new BasicDBObject();
            object.put("code", code);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
            if(cursor.hasNext()){
                return getWendangFromObject(cursor.next());
            }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return null;
	}
	
	private Wendang getWendangFromObject(DBObject object){
	    Wendang wendang = null;
	    try{
	        Long id = Long.parseLong(object.get("id").toString().trim());
	        String createTime = object.get("create_time").toString();
	        int status = Integer.parseInt(object.get("status").toString().trim());
	        String code = object.get("code").toString();
	        String name = object.get("name").toString();
	        wendang = new Wendang(id, code, status, createTime, name);
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return wendang;
	}
}
