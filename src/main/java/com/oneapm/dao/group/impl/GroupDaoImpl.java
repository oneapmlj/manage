package com.oneapm.dao.group.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.group.Group;

public class GroupDaoImpl extends DaoImplBase<Group>{
	protected static final Logger LOG = LoggerFactory.getLogger(GroupDaoImpl.class);
	protected final String TABLE_NAME = "group1";
	
	static {
		Instance = new GroupDaoImpl();
	}
	
	private final static GroupDaoImpl Instance;
	
	public static GroupDaoImpl getInstance(){
		return Instance;
	}
	
	public Group findById(Long id){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("id", id);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return findGroupByObject(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public List<Group> findByFather(Long id){
	        List<Group> groups = new ArrayList<Group>();
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("father", id);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                while(cursor.hasNext()){
	                        groups.add(findGroupByObject(cursor.next()));
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return groups;
	}
	public Long getIdest(){
	        try{
	                DBObject sort = new BasicDBObject();
	                sort.put("id", -1);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
	                if(cursor.hasNext()){
	                        return Long.parseLong(cursor.next().get("id").toString())+1;
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return 1000000L;
	}
	public Long insert(Group group){
	        try{
	                Long id = getIdest();
	                DBObject value = new BasicDBObject();
	                value.put("id", id);
	                value.put("father", group.getFather());
	                value.put("child", group.isChild());
	                value.put("type", group.getType());
	                value.put("total", group.getTotal());
	                value.put("grade", group.getGrade());
	                value.put("name", group.getName());
	                value.put("description", group.getDescription());
	                if(getDBCollection(TABLE_NAME).insert(value).getN() > -1){
	                        return id;
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public boolean update(Group group){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("id", group.getId());
	                DBObject value = new BasicDBObject();
	                value.put("father", group.getFather());
	                value.put("child", group.isChild());
	                value.put("type", group.getType());
	                value.put("grade", group.getGrade());
	                value.put("total", group.getTotal());
	                value.put("name", group.getName());
                        value.put("description", group.getDescription());
	                return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	private Group findGroupByObject(DBObject object){
	      try{
	              Long id = Long.parseLong(object.get("id").toString());
	              Long father = Long.parseLong(object.get("father").toString());
	              boolean child = Boolean.parseBoolean(object.get("child").toString());
	              int type = Integer.parseInt(object.get("type").toString());
	              int total = Integer.parseInt(object.get("total").toString());
	              int grade = Integer.parseInt(object.get("grade").toString());
	              String name = object.get("name").toString();
	              String description = object.get("description").toString();
	              return new Group(id, father, child, type, total, grade, name, description);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
