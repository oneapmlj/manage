package com.oneapm.dao.file.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.file.FileDto;

public class FileDaoImpl extends DaoImplBase<FileDto>{
	protected static final Logger LOG = LoggerFactory.getLogger(FileDaoImpl.class);
	protected final String TABLE_NAME = "file";
	
	static {
		Instance = new FileDaoImpl();
	}
	
	private final static FileDaoImpl Instance;
	
	public static FileDaoImpl getInstance(){
		return Instance;
	}
	
	public boolean insert(FileDto dto){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", dto.getId());
	        object.put("name", dto.getName());
	        object.put("code", dto.getCode());
	        object.put("grade", dto.getGrade());
	        object.put("type", dto.getType());
	        object.put("ext", dto.getExt());
	        object.put("father", dto.getFather());
	        object.put("create_time", dto.getCreateTime());
	        object.put("download_time", dto.getDownloadTime());
	        object.put("view_time", dto.getViewTime());
	        object.put("status", dto.getStatus());
	        object.put("password", dto.getPassword());
	        object.put("admin_id", dto.getAdminId());
	        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public boolean remove(Long id){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", id);
	        DBObject value = new BasicDBObject("status", 0);
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
	
	public int total(Long father){
	    try{
	       DBObject object = new BasicDBObject();
	       object.put("father", father);
           object.put("status", 1);
	       DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	       int i=0;
	       while(cursor.hasNext()){
	           i++;
	           cursor.next();
	       }
	       return i;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return 0;
	}
	
	public FileDto findById(Long id){
	    FileDto dto = null;
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", id);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	        if(cursor.hasNext()){
	            return getFileFromObject(cursor.next());
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return dto;
	}
	
	public boolean update(FileDto dto){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", dto.getId());
	        DBObject value = new BasicDBObject();
	        value.put("name", dto.getName());
	        value.put("grade", dto.getGrade());
	        value.put("type", dto.getType());
	        value.put("father", dto.getFather());
	        value.put("download_time", dto.getDownloadTime());
	        value.put("view_time", dto.getViewTime());
	        value.put("status", dto.getStatus());
	        value.put("password", dto.getPassword());
	        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public List<FileDto> findByFather(Long father){
	    List<FileDto> dtos = new ArrayList<FileDto>();
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("father", father);
	        object.put("status", 1);
	        DBObject sort = new BasicDBObject("create_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
	        while(cursor.hasNext()){
	            dtos.add(getFileFromObject(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return dtos;
	}
	
	public List<FileDto> findByFather(Long father, int type){
        List<FileDto> dtos = new ArrayList<FileDto>();
        try{
            DBObject object = new BasicDBObject();
            object.put("father", father);
            object.put("status", 1);
            object.put("type", 1);
            DBObject sort = new BasicDBObject("create_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
            while(cursor.hasNext()){
                dtos.add(getFileFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return dtos;
    }
	
	public FileDto getFileFromObject(DBObject object){
	    FileDto dto = null;
	    try{
	        Long id = Long.parseLong(object.get("id").toString().trim());
	        String name = object.get("name").toString();
	        String code = null;
	        try{
	            code = object.get("code").toString();
	        }catch(Exception e){}
	        int grade = Integer.parseInt(object.get("grade").toString().trim());
	        int type = Integer.parseInt(object.get("type").toString().trim());
	        String ext = null;
	        try{
	            ext = object.get("ext").toString();
	        }catch(Exception e){}
	        Long father = Long.parseLong(object.get("father").toString().trim());
	        String createTime = object.get("create_time").toString();
	        String downloadTime = null;
	        try{
	            downloadTime = object.get("download_time").toString();
	        }catch(Exception e){}
	        String viewTime = null;
	        try{
	            viewTime = object.get("view_time").toString();
	        }catch(Exception e){}
	        int status = Integer.parseInt(object.get("status").toString().trim());
	        String password = null;
	        try{
	            password = object.get("password").toString();
	        }catch(Exception e){}
	        Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
	        dto = new FileDto(id, name, code, grade, type, ext, father, createTime,
	                downloadTime, viewTime, status, password, adminId);
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return dto;
	}
}
