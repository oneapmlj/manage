package com.oneapm.dao.group.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.info.Info;

public class UserGroupsDaoImpl extends DaoImplBase<Group>{
	protected static final Logger LOG = LoggerFactory.getLogger(UserGroupsDaoImpl.class);
	protected final String TABLE_NAME = "groups";
	
	static {
		Instance = new UserGroupsDaoImpl();
	}
	
	private final static UserGroupsDaoImpl Instance;
	
	public static UserGroupsDaoImpl getInstance(){
		return Instance;
	}
	
	public UserGroups findByAdminId(Long admin_id){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("admin_id", admin_id);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return findUserGroupsByObject(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public UserGroups findById(Long groupId){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("group_id", groupId);
                        DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return findComplicatedGroupsByObject(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
	public List<Long> findByTime(String start, String end){
	        try{
	                DBObject object = new BasicDBObject();
	                BasicDBList list = new BasicDBList();
	                list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
	                list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
	                object.put("$and", list);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                List<Long> ids = new ArrayList<Long>();
	                while(cursor.hasNext()){
	                        ids.add(Long.parseLong(cursor.next().get("group_id").toString()));
	                }
	                return ids;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	
	private UserGroups findUserGroupsByObject(DBObject object){
	      try{
	              Long groupId = Long.parseLong(object.get("group_id").toString());
	              Long parentId = Long.parseLong(object.get("parent_id").toString());
	              int deleted = Integer.parseInt(object.get("deleted").toString());
	              String groupName = object.get("group_name").toString();
	              Long adminId = Long.parseLong(object.get("admin_id").toString());
	              return new UserGroups(groupId, adminId, groupName, parentId, deleted);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	 public boolean update_contectTime(UserGroups userGroups) {
         try {
                 DBObject object = new BasicDBObject();
                 object.put("group_id", userGroups.getGroupId());
                 BasicDBObject value = new BasicDBObject();
                 value.put("$set", new BasicDBObject("contect_time", userGroups.getContectTime()));
                 return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return false;
 }
	
	private UserGroups findComplicatedGroupsByObject(DBObject object){
	      try{
	              Long groupId = Long.parseLong(object.get("group_id").toString());
	              Long parentId = Long.parseLong(object.get("parent_id").toString());
	              int deleted = Integer.parseInt(object.get("deleted").toString());
	              String groupName = object.get("group_name").toString();
	              Long adminId = Long.parseLong(object.get("admin_id").toString());
	              Long sale = Long.parseLong(object.get("sale").toString());
	              Long support = Long.parseLong(object.get("support").toString());
	              Long preSale = Long.parseLong(object.get("preSale").toString());
	              int payLevel = Integer.parseInt(object.get("payLevel").toString());
	              String payTime = object.get("payTime").toString();
	              String comming = object.get("comming").toString();
	              int emailStatus = Integer.parseInt(object.get("emailStatus").toString());
	              String contectTime = object.get("contectTime").toString();
	              String createTime = object.get("create_time").toString();
	              UserGroups userGroups = new UserGroups( groupId,  adminId,  groupName,  parentId,  deleted,  sale,  support,
	                                 preSale,  payLevel,  payTime,  comming,  emailStatus,  contectTime);
	              userGroups.setCreateTime(createTime);
	              return userGroups;
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
