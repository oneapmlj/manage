package com.oneapm.dao.group.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.group.GroupView;

public class GroupViewDaoImpl extends DaoImplBase<GroupView>{
	protected static final Logger LOG = LoggerFactory.getLogger(GroupViewDaoImpl.class);
	protected final String TABLE_NAME = "group_view";
	
	static {
		Instance = new GroupViewDaoImpl();
	}
	
	private final static GroupViewDaoImpl Instance;
	
	public static GroupViewDaoImpl getInstance(){
		return Instance;
	}
	
	public GroupView findByInfoId(Long infoId){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("info_id", infoId);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return findGroupViewByObject(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public GroupView findByUserGroupId(Long usergroupId){
        try{
                DBObject object = new BasicDBObject();
                object.put("user_group_id", usergroupId);
                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
                if(cursor.hasNext()){
                        return findGroupViewByObject(cursor.next());
                }
        }catch(Exception e){
                LOG.error(e.getMessage(), e);
        }
        return null;
}
	
	public List<GroupView> findById(Long id){
	        List<GroupView> groups = new ArrayList<GroupView>();
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("group_id", id);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){
	                        groups.add(findGroupViewByObject(cursor.next()));
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return groups;
	}
	
	public boolean insert(GroupView groupView){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("info_id", groupView.getInfoId());
	                object.put("group_id", groupView.getGroupId());  
	                object.put("score", groupView.getScore());
	                object.put("type_time", groupView.getTypeTime());
	                object.put("change_time", groupView.getChangeTime());
	                return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public boolean update(GroupView view){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("info_id", view.getInfoId());
	                DBObject value = new BasicDBObject();
	                value.put("group_id", view.getGroupId());  
	                value.put("score", view.getScore());
	                value.put("type_time", view.getTypeTime());
	                value.put("change_time", view.getChangeTime());
	                return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	
	private GroupView findGroupViewByObject(DBObject object){
	      try{
	              Long infoId = Long.parseLong(object.get("info_id").toString());
	              Long groupId = Long.parseLong(object.get("group_id").toString());
	              int score = Integer.parseInt(object.get("score").toString());
	              String typeTime = null;
	              try{
	                      typeTime = object.get("type_time").toString();
	              }catch(Exception e){}
	              String changeTime = object.get("change_time").toString();
	              return new GroupView(infoId, groupId, score, typeTime, changeTime);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
