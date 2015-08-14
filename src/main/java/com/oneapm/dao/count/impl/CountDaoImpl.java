package com.oneapm.dao.count.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.dto.info.Info;


public class CountDaoImpl extends DaoImplBase<Info>{
	protected static final Logger LOG = LoggerFactory.getLogger(CountDaoImpl.class);
	protected final String TABLE_NAME = "count";
	private final static CountDaoImpl Instance;
	static {
		Instance = new CountDaoImpl();
	}
	public static CountDaoImpl getInstance() {
        return Instance;
}
	public String insertCount(CountDto dto){
	    try{
/*	    	ApiData data = new ApiData();		
			List<String> email;	
			email = data.httpURLConectionGET(url);
			for(int i = 0; i < email.size(); i ++){
			CountDto entity = UserIdDaoImpl.getInstance().findByEmail(email.get(i));
			System.out.println(entity.getUserId());	
			}*/
	    	long userId = UserIdDaoImpl.getInstance().findByEmail(dto.getEmail());
            DBObject value = new BasicDBObject();
            value.put("email", dto.getEmail());
            value.put("userId", userId);
            value.put("number", dto.getNumber());
            value.put("event", dto.getEvent());
            value.put("create_time", dto.getCreate_time());
            if(getDBCollection(TABLE_NAME).insert(value).getN() > -1){
                return "success";
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	    return null;
	}
	public List<CountDto> getCountData(){
		List<CountDto> dtoList = new ArrayList<CountDto>();
	    try{
	    	
	    	
            if(getDBCollection(TABLE_NAME).find().count()!=0){
             DBCursor cursor = getDBCollection(TABLE_NAME).find();
             while(cursor.hasNext()){
            	 dtoList.add((CountDto)cursor.next()) ;
             }

            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	    return dtoList;
	}
	public String insertCountList(List<CountDto> dtolist){
	    try{
	    	CountDto dto = new CountDto();
	    	List<DBObject> valuelist = new ArrayList<DBObject>();
	    	for(int i = 0; i < dtolist.size(); i++){
	    	long userId = UserIdDaoImpl.getInstance().findByEmail(dtolist.get(i).getEmail());
	    	DBObject value = new BasicDBObject();
	    	value.put("email", dtolist.get(i).getEmail());
            value.put("userId", userId);
            value.put("number", dtolist.get(i).getNumber());
            value.put("event", dtolist.get(i).getEvent());
            value.put("create_time", dtolist.get(i).getCreate_time());
            valuelist.add(value);
	    	}
	    	 if(getDBCollection(TABLE_NAME).insert(valuelist).getN() > -1){
	                return "success";
	            }
	    
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	    return null;
	}

}
