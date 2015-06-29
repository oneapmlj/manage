package com.oneapm.dao.lable.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.lable.Lable;

public class LableDaoImpl extends DaoImplBase<Lable>{
	protected static final Logger LOG = LoggerFactory.getLogger(LableDaoImpl.class);
	protected final String TABLE_NAME = "lable";
	
	static {
		Instance = new LableDaoImpl();
	}
	
	private final static LableDaoImpl Instance;
	
	public static LableDaoImpl getInstance(){
		return Instance;
	}
	
	public Lable findById(Long id){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("id", id);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return findLableByObject(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public List<Lable> findByFather(Long id){
	        List<Lable> lables = new ArrayList<Lable>();
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("father", id);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){
	                        lables.add(findLableByObject(cursor.next()));
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return lables;
	}
	
	public Long getIdest(){
	        try{
	                DBObject sort = new BasicDBObject();
	                sort.put("id", -1);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
	                if(cursor.hasNext()){
	                        return Long.parseLong(cursor.next().get("id").toString())+1;
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return 10000000L;
	}
	public Long insert(Lable lable){
	        try{
	                Long id = getIdest();
	                DBObject value = new BasicDBObject();
	                value.put("id", id);
	                value.put("father", lable.getFather());
	                value.put("child", lable.isChild());
	                value.put("grade", lable.getGrade());
	                value.put("name", lable.getName());
	                value.put("description", lable.getDescription());
	                value.put("key", lable.getKey());
	                value.put("from", lable.getFrom());
	                if(getDBCollection(TABLE_NAME).insert(value).getN() > -1){
	                        return id;
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public boolean update(Lable lable){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", lable.getId());
                        DBObject value = new BasicDBObject();
                        value.put("father", lable.getFather());
                        value.put("child", lable.isChild());
                        value.put("grade", lable.getGrade());
                        value.put("name", lable.getName());
                        value.put("description", lable.getDescription());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
	
	public boolean findByKey(String key, Long fatherId){
	        try{
                        DBObject object = new BasicDBObject();
                        object.put("key", key);
                        object.put("father", fatherId);
                        return getDBCollection(TABLE_NAME).find(object).hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
	}
	public boolean findByName(String name, Long fatherId){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("name", name);
                        object.put("father", fatherId);
                        return getDBCollection(TABLE_NAME).find(object).hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
	
	public boolean findByFrom(String from){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("from", from);
                        return getDBCollection(TABLE_NAME).find(object).hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
	
	private Lable findLableByObject(DBObject object){
	      try{
	              Long id = Long.parseLong(object.get("id").toString());
	              Long father = Long.parseLong(object.get("father").toString());
	              boolean child = Boolean.parseBoolean(object.get("child").toString());
	              int grade = Integer.parseInt(object.get("grade").toString());
	              String name = object.get("name").toString();
	              String description = object.get("description").toString();
	              String key = object.get("key").toString();
	              String from = object.get("from").toString();
	              return new Lable(id, father, child, grade, name, description, key, from);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
