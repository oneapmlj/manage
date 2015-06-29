package com.oneapm.dao.opt.impl;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Gongdan;

public class GongdanDaoImpl extends DaoImplBase<Gongdan>{
	protected static final Logger LOG = LoggerFactory.getLogger(GongdanDaoImpl.class);
	protected final String TABLE_NAME = "gongdan";
	
	static {
		Instance = new GongdanDaoImpl();
	}
	
	private final static GongdanDaoImpl Instance;
	
	public static GongdanDaoImpl getInstance(){
		return Instance;
	}
	
	public Gongdan findById(Long id){
		try{
			DBObject object = new BasicDBObject();
			object.put("id", id);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			if(cursor.hasNext()){
			    return getGongdanFromResult(cursor.next());
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<Gongdan> findByUserId(Long userId){
	    List<Gongdan> gongdans = null;
	    try{
	        DBObject object = new BasicDBObject("user_id", userId);
	        DBObject sort = new BasicDBObject("create_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
	        gongdans = new ArrayList<Gongdan>();
	        while(cursor.hasNext()){
	            gongdans.add(getGongdanFromResult(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return gongdans;
	}
	
	public boolean update(Gongdan gongdan){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", gongdan.getId());
	        DBObject value = new BasicDBObject();
            value.put("$set", new BasicDBObject("title", gongdan.getTitle()));
            value.put("$set", new BasicDBObject("due_time", gongdan.getDueTime()));
            value.put("$set", new BasicDBObject("status", gongdan.getStatus()));
            value.put("$set", new BasicDBObject("assignee_id", gongdan.getAssigneeId()));
            value.put("$set", new BasicDBObject("assigne_time", gongdan.getAssigneTime()));
            value.put("$set", new BasicDBObject("resolved_time", gongdan.getResolvedTime()));
            value.put("$set", new BasicDBObject("closed_time", gongdan.getClosedTime()));
            value.put("$set", new BasicDBObject("source", gongdan.getSource()));
            return getDBCollection(TABLE_NAME).update(object,value).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public boolean insert(Gongdan gongdan){
	    try{
            DBObject value = new BasicDBObject();
            value.put("id", gongdan.getId());
            value.put("title", gongdan.getTitle());
            value.put("email", gongdan.getEmail());
            try{
                value.put("due_time", gongdan.getDueTime());
            }catch(Exception e){}
            value.put("status", gongdan.getStatus());
            try{
                value.put("assignee_id", gongdan.getAssigneeId());
            }catch(Exception e){}
            value.put("create_time", gongdan.getCreateTime());
            try{
                value.put("assigne_time", gongdan.getAssigneTime());
            }catch(Exception e){}
            try{
                value.put("resolved_time", gongdan.getResolvedTime());
            }catch(Exception e){}
            try{
                value.put("closed_time", gongdan.getClosedTime());
            }catch(Exception e){}
            value.put("source", gongdan.getSource());
            value.put("user_id", gongdan.getUserId());
            return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
	}
	
	
	public Gongdan getGongdanFromResult(DBObject obj) {
	    Gongdan gongdan = null;
		try{
		    Long id = Long.parseLong(obj.get("id").toString().trim());
            String title = obj.get("title").toString();
            String email = obj.get("email").toString();
            String dueTime = null;
            try{
                dueTime = obj.get("due_time").toString();
            }catch(Exception e){}
            int status = Integer.parseInt(obj.get("status").toString().trim());
            Long assigneeId = null;
            try{
                assigneeId = Long.parseLong(obj.get("assignee_id").toString().trim());
            }catch(Exception e){}
            String createTime = obj.get("create_time").toString();
            String assigneTime = null;
            try{
                assigneTime = obj.get("assigne_time").toString();
            }catch(Exception e){}
            String resolvedTime = null;
            try{
                resolvedTime = obj.get("resolved_time").toString();
            }catch(Exception e){}
            String closedTime = null;
            try{
                closedTime = obj.get("closed_time").toString();
            }catch(Exception e){}
            String source = obj.get("source").toString();
            Long userId = null;
            try{
                userId = Long.parseLong(obj.get("user_id").toString().trim());
            }catch(Exception e){}
            gongdan = new Gongdan(id, title, email, dueTime,
                    status, assigneeId, createTime, assigneTime, resolvedTime,
                    closedTime, source);
            gongdan.setUserId(userId);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return gongdan;
	}
}
