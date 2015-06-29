package com.oneapm.dao.mail.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.MailDto;

public class MailModeDaoImpl extends DaoImplBase<MailDto>{
	protected static final Logger LOG = LoggerFactory.getLogger(MailModeDaoImpl.class);
	protected final String TABLE_NAME = "mail_mode";
	
	static {
		Instance = new MailModeDaoImpl();
	}
	
	private final static MailModeDaoImpl Instance;
	
	public static MailModeDaoImpl getInstance(){
		return Instance;
	}
	
	public MailDto findById(int id){
		try{
		    DBObject object = new BasicDBObject();
		    object.put("id", id);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			if(cursor.hasNext()){
			    return getMailFromResult(cursor.next());
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	public boolean update(MailDto mail){
	    DBObject object = new BasicDBObject();
        object.put("id", mail.getId());
        DBObject value = new BasicDBObject();
        value.put("title", mail.getTitle());
        value.put("content", mail.getContent());
        value.put("status", mail.getStatus());
        value.put("last_use_time", mail.getLastUseTime());
        value.put("use", mail.getUse());
        value.put("time_type", mail.getTimeType());
        value.put("description", mail.getDescription());
        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN()>-1;
	}
	
	public List<MailDto> findMails(){
	    List<MailDto> modes = null;
	    try{
	        DBObject sort = new BasicDBObject("create_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort);
	        modes = new ArrayList<MailDto>();
	        while(cursor.hasNext()){
	            modes.add(getMailFromResult(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return modes;
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
	public boolean insert(MailDto mail){
		try{
		    DBObject object = new BasicDBObject();
		    object.put("id", getIdest()+1L);
		    object.put("title", mail.getTitle());
		    object.put("content", mail.getContent());
		    object.put("status", mail.getStatus());
		    object.put("create_time", mail.getCreateTime());
		    object.put("last_use_time", mail.getLastUseTime());
		    object.put("use", mail.getUse());
		    object.put("time_type", mail.getTimeType());
		    object.put("description", mail.getDescription());
		    return getDBCollection(TABLE_NAME).insert(object).getN()>-1;
		}catch(Exception e){
		    LOG.error(e.getMessage(), e);
		}
	    return false;
	}
	
	public MailDto getMailFromResult(DBObject object) {
		MailDto dto = null;
		try{
		    int id = Integer.parseInt(object.get("id").toString());
		    int timeType = Integer.parseInt(object.get("time_type").toString());
		    String title = object.get("title").toString();
		    String content = object.get("content").toString();
		    int status = Integer.parseInt(object.get("status").toString());
		    String createTime = object.get("create_time").toString();
		    String lastUseTime = null;
		    try{
		        lastUseTime = object.get("last_use_time").toString();
		    }catch(Exception e){}
		    String description = object.get("description").toString();
		    dto = new MailDto(id, timeType, title, content, status, createTime, lastUseTime, description);
		}catch(Exception e){
		    LOG.error(e.getMessage(), e);
		}
		return dto;
	}
}
