package com.oneapm.dao.group.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.group.Group;

public class UserGroupsDaoImpl extends DaoImplBase<Group> {
	protected static final Logger LOG = LoggerFactory.getLogger(UserGroupsDaoImpl.class);
	protected final String TABLE_NAME = "groups";

	static {
		Instance = new UserGroupsDaoImpl();
	}

	private final static UserGroupsDaoImpl Instance;

	public static UserGroupsDaoImpl getInstance() {
		return Instance;
	}
	
	public long countAdminId(Long adminId){
        try {
                DBObject object = new BasicDBObject();
                BasicDBList list = new BasicDBList();
                list.add(new BasicDBObject("admin_id", adminId));
                list.add(new BasicDBObject("sale", adminId));
                list.add(new BasicDBObject("support", adminId));
                list.add(new BasicDBObject("preSale", adminId));
                object.put("$or", list);
                DBObject sort = new BasicDBObject();
                sort.put("contectTime", 1);
                return getDBCollection(TABLE_NAME).count(object);
        } catch (Exception e) {
                LOG.error(e.getMessage(), e);
        }
        return 0;
}
	
	public List<UserGroups> findByAdminId(Long adminId, int number, int skip) {
        List<UserGroups> userGroups = new ArrayList<UserGroups>();
        try {
                DBObject object = new BasicDBObject();
                BasicDBList list = new BasicDBList();
                list.add(new BasicDBObject("admin_id", adminId));
                list.add(new BasicDBObject("sale", adminId));
                list.add(new BasicDBObject("support", adminId));
                list.add(new BasicDBObject("preSale", adminId));
                object.put("$or", list);
                DBObject sort = new BasicDBObject();
                sort.put("contectTime", 1);
                DBCursor cursor = null;
                if(number == 0){
                        cursor = getDBCollection(TABLE_NAME).find(object);
                }else{
                        cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(skip).limit(number);
                }
                userGroups = new ArrayList<UserGroups>();
                while (cursor.hasNext()) {
                	userGroups.add(findComplicatedGroupsByObject(cursor.next()));
                }
                return userGroups;
        } catch (Exception e) {
                LOG.error(e.getMessage(), e);
        }
        return null;
}
	public boolean update_xiaoshouyi(UserGroups userGroups, String lableId) {
		try {
			DBObject object = new BasicDBObject();
			DBObject value = new BasicDBObject();
			value.put("xiaoshouyi", userGroups.getXiaoshouyi());
			if (lableId != null && lableId.length() > 0) {
				value.put("xiaoshouyi_lable_id", lableId);
			}
			object.put("group_id", userGroups.getGroupId());
			return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	public UserGroups findByAdminId(Long admin_id) {
		try {
			DBObject object = new BasicDBObject();
			object.put("admin_id", admin_id);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			if (cursor.hasNext()) {
				return findComplicatedGroupsByObject(cursor.next());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public List<UserGroups> findByComming(String comming, String start, String end) {
	        List<UserGroups> groups = new ArrayList<UserGroups>();
		try {
			DBObject object = new BasicDBObject("comming", comming);
			if(start != null ){
			        BasicDBList list = new BasicDBList();
			        list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
			        list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
			        object.put("$and", list);
			}
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			while (cursor.hasNext()) {
				groups.add(findComplicatedGroupsByObject(cursor.next()));
			}
			return groups;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return groups;
	}

	public UserGroups findById(Long groupId) {
		try {
			DBObject object = new BasicDBObject();
			object.put("group_id", groupId);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			if (cursor.hasNext()) {
				return findComplicatedGroupsByObject(cursor.next());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public List<Long> findByTime(String start, String end) {
		try {
			DBObject object = new BasicDBObject();
			BasicDBList list = new BasicDBList();
			list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
			if(end != null){
			        list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
			}
			object.put("$and", list);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			List<Long> ids = new ArrayList<Long>();
			while (cursor.hasNext()) {
				ids.add(Long.parseLong(cursor.next().get("group_id").toString()));
			}
			return ids;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public boolean update_contectTime(UserGroups userGroups) {
		try {
			DBObject object = new BasicDBObject();
			object.put("group_id", userGroups.getGroupId());
			BasicDBObject value = new BasicDBObject();
			value.put("$set", new BasicDBObject("contectTime", userGroups.getContectTime()));
			return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	private UserGroups findComplicatedGroupsByObject(DBObject object) {
		UserGroups userGroups = null;
		try {
			Long groupId = null;
			try {
				groupId = Long.parseLong(object.get("group_id").toString());
			} catch (Exception e) {
			}
			Long parentId = null;
			try {
				parentId = Long.parseLong(object.get("parent_id").toString());
			} catch (Exception e) {
			}
			Long adminId = null;
			try {
				adminId = Long.parseLong(object.get("admin_id").toString());
			} catch (Exception e) {
			}
			Long sale = null;
			try {
				sale = Long.parseLong(object.get("sale").toString());
			} catch (Exception e) {
			}
			Long support = null;
			try {
				support = Long.parseLong(object.get("support").toString());
			} catch (Exception e) {
			}
			Long preSale = null;
			try {
				preSale = Long.parseLong(object.get("preSale").toString());
			} catch (Exception e) {
			}
			int deleted = 0;
			try {
				deleted = Integer.parseInt(object.get("deleted").toString());
			} catch (Exception e) {
			}
			String groupName = null;
			try {
				groupName = object.get("group_name").toString();
			} catch (Exception e) {
			}
			int payLevel = 0;
			try {
				payLevel = Integer.parseInt(object.get("payLevel").toString());
			} catch (Exception e) {
			}
			String payTime = null;
			try {
				payTime = object.get("payTime").toString();
			} catch (Exception e) {
			}
			String comming = null;
			try {
				comming = object.get("comming").toString();
			} catch (Exception e) {
			}
			int emailStatus = 0;
			try {
				emailStatus = Integer.parseInt(object.get("emailStatus").toString());
			} catch (Exception e) {
			}
			String expireTime = null;
                        try{
                                expireTime = object.get("pay_expire_time").toString();
                        }catch(Exception e){}
			String contectTime = null;
			try {
				contectTime = object.get("contectTime").toString();
			} catch (Exception e) {
			}
			String createTime = null;
			try {
				createTime = object.get("create_time").toString();
			} catch (Exception e) {
			}
			String project = null;
			try {
				project = object.get("project").toString();
			} catch (Exception e) {
			}
			Long xiaoshouyi = null;
			try {
				xiaoshouyi = Long.parseLong(object.get("xiaoshouyi").toString());
			} catch (Exception e) {
			}
			userGroups = new UserGroups(groupId, adminId, groupName, parentId, deleted, sale, support, preSale,
					payLevel, payTime, comming, emailStatus, contectTime);
			userGroups.setCreateTime(createTime);
			userGroups.setProject(project);
			userGroups.setXiaoshouyi(xiaoshouyi);
			userGroups.setExpireTime(expireTime);
			return userGroups;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return userGroups;
	}

	public boolean update(UserGroups userGroups) {
		try {
			DBObject object = new BasicDBObject();
			object.put("group_id", userGroups.getGroupId());
			DBObject value = new BasicDBObject();
			if (userGroups.getGroupId() != null && userGroups.getGroupId() > 0) {
				value.put("group_id", userGroups.getGroupId());
			}
			value.put("sale", userGroups.getSale());
			value.put("project", userGroups.getProject());
			value.put("support", userGroups.getSupport());
			value.put("preSale", userGroups.getPreSale());
			if(userGroups.getExpireTime() !=null && userGroups.getExpireTime().trim().length() > 0){
                                value.put("pay_level", userGroups.getPayLevel());
                                value.put("pay_expire_time", userGroups.getExpireTime());
                        }
			return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	public boolean updateOwner(UserGroups userGroups) {
		try {
			DBObject object = new BasicDBObject();
			object.put("group_id", userGroups.getGroupId());
			DBObject value = new BasicDBObject();
			value.put("support", userGroups.getSupport());
			value.put("sale", userGroups.getSale());
			value.put("preSale", userGroups.getPreSale());
			return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	public List<UserGroups> countSign(String start, String end) {
		try {
			DBObject object = new BasicDBObject();
			object.put("user_id", new BasicDBObject("$gte", 1L));
			BasicDBList list = new BasicDBList();
			list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
			list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
			object.put("$and", list);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			List<UserGroups> groups = new ArrayList<UserGroups>();
			while (cursor.hasNext()) {
				groups.add(findComplicatedGroupsByObject(cursor.next()));
			}
			return groups;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	  public List<UserGroups> search(String email, String name, String phone, String company, String qq, boolean in) {
          List<UserGroups> userGroups = null;
          try {
                  DBObject object = new BasicDBObject();
                  BasicDBList list = new BasicDBList();
                  if (phone != null && !phone.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + phone.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("phone", pattern));
                  }
                  if (name != null && !name.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + name.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("name", pattern));
                  }
                  if (company != null && !company.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("group_name", pattern));
                  }
                  if (company != null && !company.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("project", pattern));
                  }
                  if (email != null && !email.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + email.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("email", pattern));
                  }
                  if (qq != null && !qq.trim().equals("")) {
                          list.add(new BasicDBObject("qq", qq));
                  }
                  if (list.size() <= 0) {
                          return null;
                  }
                  object.put("$or", list);
                  DBObject sort = new BasicDBObject("create_time", -1);
                  DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                  userGroups = new ArrayList<UserGroups>();
                  while (cursor.hasNext()) {
                	  userGroups.add(findComplicatedGroupsByObject(cursor.next()));
                  }
          } catch (Exception e) {
                  LOG.error(e.getMessage(), e);
          }
          return userGroups;
  }
	  
	  
	  public List<UserGroups> searchByCompany(String company) {
          List<UserGroups> userGroups = null;
          try {
                  DBObject object = new BasicDBObject();
                  BasicDBList list = new BasicDBList();
                  if (company != null && !company.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("group_name", pattern));
                  }
                  if (company != null && !company.trim().equals("")) {
                          Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                          list.add(new BasicDBObject("project", pattern));
                  }
                  if (list.size() <= 0) {
                          return null;
                  }
                  object.put("$or", list);
                  DBObject sort = new BasicDBObject("create_time", -1);
                  DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                  userGroups = new ArrayList<UserGroups>();
                  while (cursor.hasNext()) {
                	  userGroups.add(findComplicatedGroupsByObject(cursor.next()));
                  }
          } catch (Exception e) {
                  LOG.error(e.getMessage(), e);
          }
          return userGroups;
  }
}
