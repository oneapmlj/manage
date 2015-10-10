package com.oneapm.dao.group.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.UserGroup;
import com.oneapm.dto.group.Group;

public class UserGroupDaoImpl extends DaoImplBase<UserGroup>{
		protected static final Logger LOG = LoggerFactory.getLogger(UserGroupDaoImpl.class);
		protected final String TABLE_NAME = "user_group";
		
		static {
			Instance = new UserGroupDaoImpl();
		}
		
		private final static UserGroupDaoImpl Instance;
		
		public static UserGroupDaoImpl getInstance(){
			return Instance;
		}
		

		
		public List<UserGroup> findUsersByGroupId(Long groupId){
			List<UserGroup> list = new ArrayList<UserGroup>();
	        try{	
	        		
	                DBObject object = new BasicDBObject();
	                object.put("group_id", groupId);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){	                	
	                	UserGroup userGroups = findUserGroupsByObject(cursor.next());
	                	list.add(userGroups);
	               
	                }
	               
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return list;
		}
		
		public UserGroup findUsersByUserId(Long userId){
			UserGroup userGroup = null;
	        try{	
	        		
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){	                	
	                	 userGroup = findUserGroupsByObject(cursor.next());
	                }
	               
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return userGroup;
		}
		
		public List<UserGroup> findUsersListByUserId(Long userId){
			List<UserGroup> list = new ArrayList<UserGroup>();
	        try{	
	        		
	                DBObject object = new BasicDBObject();
	                object.put("user_id", userId);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){	                	
	                	UserGroup userGroups = findUserGroupsByObject(cursor.next());
	                	list.add(userGroups);
	               
	                }
	               
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return list;
		}
		
		private UserGroup findUserGroupsByObject(DBObject object){
		      try{
		              Long groupId = Long.parseLong(object.get("group_id").toString());
		              int deleted = Integer.parseInt(object.get("deleted").toString());
		              int status = Integer.parseInt(object.get("status").toString());
		              int defaultGroup = Integer.parseInt(object.get("default_group").toString());
		              String email = object.get("email").toString();
		              String role = object.get("role").toString();
		              Long userId = Long.parseLong(object.get("user_id").toString());
		              return new UserGroup(groupId, userId, email, role, defaultGroup,deleted,status);
		      }catch(Exception e){
		              LOG.error(e.getMessage(), e);
		      }
		      return null;
		}
		
	}