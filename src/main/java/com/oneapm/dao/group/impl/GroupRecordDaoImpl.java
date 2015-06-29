package com.oneapm.dao.group.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.group.GroupRecord;

public class GroupRecordDaoImpl extends DaoImplBase<GroupRecord>{
	protected static final Logger LOG = LoggerFactory.getLogger(GroupRecordDaoImpl.class);
	protected final String TABLE_NAME = "group_record";
	
	static {
		Instance = new GroupRecordDaoImpl();
	}
	
	private final static GroupRecordDaoImpl Instance;
	
	public static GroupRecordDaoImpl getInstance(){
		return Instance;
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
	        return 100000000L;
	}
	public boolean insert(GroupRecord record){
	        try{
	                Long id = getIdest();
	                DBObject value = new BasicDBObject();
	                value.put("id", id);
	                value.put("from", record.getFrom());
	                value.put("to", record.getTo());
	                value.put("admin_id", record.getAdminId());
	                value.put("time", record.getTime());
	                return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
}
