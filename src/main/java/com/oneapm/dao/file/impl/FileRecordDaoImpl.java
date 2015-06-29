package com.oneapm.dao.file.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.file.FileRecord;

public class FileRecordDaoImpl extends DaoImplBase<FileRecord>{
	protected static final Logger LOG = LoggerFactory.getLogger(FileRecordDaoImpl.class);
	protected final String TABLE_NAME = "file_record";
	
	static {
		Instance = new FileRecordDaoImpl();
	}
	
	private final static FileRecordDaoImpl Instance;
	
	public static FileRecordDaoImpl getInstance(){
		return Instance;
	}
	
	public boolean insert(FileRecord record){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", record.getId());
	        object.put("file_id", record.getFileId());
	        object.put("type", record.getType());
	        object.put("admin_id", record.getAdminId());
	        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
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
	
}
