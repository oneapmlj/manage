package com.oneapm.dao.info.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Active;
import com.oneapm.dto.info.Guanlian;

public class GuanlianDaoImpl extends DaoImplBase<Active>{
	protected static final Logger LOG = LoggerFactory.getLogger(GuanlianDaoImpl.class);
	protected static final String TABLE_NAME = "guanlian";
	
	static {
		Instance = new GuanlianDaoImpl();
	}
	
	private final static GuanlianDaoImpl Instance;
	
	public static GuanlianDaoImpl getInstance(){
		return Instance;
	}
	
	public Long getIdest(){
	        try{
	                DBObject sort = new BasicDBObject();
	                sort.put("id", -1);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
	                if(cursor.hasNext()){
	                        return Long.parseLong(cursor.next().get("id").toString());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return 10000L;
	}
	public boolean insert(Long id, Long userId, Long guanlianId, int role, String createTime){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("id", id);
	                object.put("user_id", userId);
	                object.put("guanlian_id", guanlianId);
	                object.put("role", role);
	                object.put("create_time", createTime);
	                return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public boolean exist(Long userId, Long guanlianId){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                object.put("guanlian_id", guanlianId);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                return cursor.hasNext();
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public List<Guanlian> findByUserId(Long userId){
	        List<Guanlian> guanlians = new ArrayList<Guanlian>();
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){
	                        guanlians.add(getGuanlianFromJson(cursor.next()));
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return guanlians;
	}
	
	public List<Guanlian> findByGuanlianId(Long guanlianId){
                List<Guanlian> guanlians = new ArrayList<Guanlian>();
                try{
                        DBObject object = new BasicDBObject();
                        object.put("guanlian_id", guanlianId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                guanlians.add(getGuanlianFromJson(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return guanlians;
        }
	
	public int findUserRole(Long userId){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                object.put("guanlian_id", userId);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return Integer.parseInt(cursor.next().get("role").toString());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return -1;
	}
	
	public boolean exist(Long userId){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                object.put("guanlian_id", new BasicDBObject("$ne", userId));
	                return getDBCollection(TABLE_NAME).find(object).hasNext();
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	public Long findRole(Long userId, int role){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                object.put("role", role);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return Long.parseLong(cursor.next().get("guanlian_id").toString());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public boolean drop(Long guanlianId){
	        try{
	                BasicDBList list = new BasicDBList();
	                list.add(new BasicDBObject("guanlian_id", guanlianId));
	                list.add(new BasicDBObject("user_id", guanlianId));
	                DBObject object = new BasicDBObject();
	                object.put("$or", object);
	                return getDBCollection(TABLE_NAME).remove(object).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public boolean remove(Long userId){
	        try{
	                DBObject object = new BasicDBObject("guanlian_id", userId);
	                DBObject value = new BasicDBObject("$set", new BasicDBObject("role", 0));
	                return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public boolean add(Long userId){
	        try{
	                DBObject object = new BasicDBObject("guanlian_id", userId);
                        DBObject value = new BasicDBObject("$set", new BasicDBObject("role", 1));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	private Guanlian getGuanlianFromJson(DBObject object){
	        try{
	                Long id = Long.parseLong(object.get("id").toString());
	                Long userId = Long.parseLong(object.get("user_id").toString());
	                Long guanlianId = Long.parseLong(object.get("guanlian_id").toString());
	                int role = Integer.parseInt(object.get("role").toString());
	                return new Guanlian(id, userId, guanlianId, role);
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
}
