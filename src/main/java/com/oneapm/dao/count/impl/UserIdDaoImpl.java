package com.oneapm.dao.count.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.dto.info.Info;

public class UserIdDaoImpl extends DaoImplBase<Info>{
	protected static final Logger LOG = LoggerFactory.getLogger(UserIdDaoImpl.class);
	protected final String TABLE_NAME = "info";
	private CountDto count;
	private final static UserIdDaoImpl Instance;
	static {
		Instance = new UserIdDaoImpl();
	}
	public static UserIdDaoImpl getInstance() {
        return Instance;
}
	public long findByEmail(String email){
        try{
                DBObject object = new BasicDBObject();
                object.put("email", email);
                DBCursor cursor= getDBCollection(TABLE_NAME).find(object).limit(1);
                if(cursor.hasNext()){
    	            return Long.parseLong(cursor.next().get("user_id").toString().trim());
    	        }
          
                
                
        }catch(Exception e){
                LOG.error(e.getMessage(), e);
        }
        return 0l;
}


}
