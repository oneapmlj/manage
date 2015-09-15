package com.oneapm.dao.group.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.group.Group;

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
	              return new UserGroups( groupId,  adminId,  groupName,  parentId,  deleted,  sale,  support,
	              		 preSale,  payLevel,  payTime,  comming,  emailStatus,  contectTime);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
