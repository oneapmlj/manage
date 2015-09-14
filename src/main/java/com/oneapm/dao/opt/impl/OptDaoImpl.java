package com.oneapm.dao.opt.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;

public class OptDaoImpl extends DaoImplBase<Admin>{
	
	protected static final Logger LOG = LoggerFactory.getLogger(OptDaoImpl.class);
	
	protected final String TABLE_NAME = "oneapmmanage";
	static {
		Instance = new OptDaoImpl();
	}
	
	private final static OptDaoImpl Instance;
	public static OptDaoImpl getInstance(){
		return Instance;
	}
	
	public int findLength(int id){
	    int total = 0;
	    try{
            BasicDBObject object = new BasicDBObject();
            object.put("id", id);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
            while(cursor.hasNext()){
                cursor.next();
                total ++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	    return total;
	}
	public List<Info> findById(int id, int start, int end){
		List<Info> infos = null;
		try{
			BasicDBObject object = new BasicDBObject();
			object.put("id", id);
			object.put("status", 1);
			BasicDBObject sort = new BasicDBObject();
			sort.put("user_id", -1);
			DBCursor cursor = null;
			if(end > 0){
			    cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(start).limit(end);
			}else{
			    cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(start);
			}
			infos = new ArrayList<Info>();
			while(cursor.hasNext()){
			    infos.add(getInfoFromResult(cursor.next()));
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return infos;
	}
	
	public String findUpdateTime(int id){
		try{
		    BasicDBObject object = new BasicDBObject();
		    object.put("id", id);
		    DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
		    if(cursor.hasNext()){
		        return cursor.next().get("data_time").toString();
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean delete(int id){
		try{
			BasicDBObject object = new BasicDBObject();
			object.put("id", id);
			return getDBCollection(TABLE_NAME).remove(object).getN() > -1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteById(int id, Long userId){
		try{
		    BasicDBObject object = new BasicDBObject();
		    object.put("id", id);
		    object.put("user_id", userId);
		    return getDBCollection(TABLE_NAME).remove(object).getN() > -1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	public Info getInfoFromResult(DBObject object) {
        Info info = null;
        try{
            Long userId = Long.parseLong(object.get("user_id").toString());
            String name = object.get("name").toString();
            String email = object.get("email").toString();
            String dataTime = object.get("data_time").toString();
            String loginTime = object.get("login_time").toString();
            String createTime = object.get("create_time").toString();
            String phone = object.get("phone").toString();
            String company = object.get("company").toString();
            String language = null;
            try{
                language = object.get("language").toString();
            }catch(Exception e){}
            String version = null;
            try{
                version = object.get("version").toString().trim();
            }catch(Exception e){}
            info = new Info(userId, name, email, company, phone, loginTime, createTime,language, null, null, null, null, null, null, 0, null, null,0);
            info.setDataTime(dataTime);
            info.setVersion(version);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return info;
    }
}
