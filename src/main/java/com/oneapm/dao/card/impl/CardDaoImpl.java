package com.oneapm.dao.card.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.card.Card;

public class CardDaoImpl extends DaoImplBase<Card>{
	protected static final Logger LOG = LoggerFactory.getLogger(CardDaoImpl.class);
	protected final String TABLE_NAME = "card";
	
	static {
		Instance = new CardDaoImpl();
	}
	
	private final static CardDaoImpl Instance;
	
	public static CardDaoImpl getInstance(){
		return Instance;
	}
	
	public Card findById(Long cardId){
		try{
			DBObject object = new BasicDBObject();
			object.put("id", cardId);
			DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
			if(cursor.hasNext()){
			    return getInfoFromResult(cursor.next());
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	public boolean update(Card card){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", card.getId());
	        DBObject value = new BasicDBObject();
            value.put("name", card.getName());
            value.put("branch", card.getBranch());
            value.put("position", card.getPosition());
            value.put("phone", card.getPhone());
            value.put("email", card.getEmail());
            value.put("company", card.getCompany());
            value.put("change_time", card.getChangeTime());
            value.put("gender", card.getGender());
            value.put("qq", card.getQq());
            DBObject obj = new BasicDBObject("$set", value);
            return getDBCollection(TABLE_NAME).update(object,obj).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public Card insertAndGet(Card card){
	    try{
            Long id = getIdes();
            if(id == null){
                return null;
            }
            card.setId(id);
            DBObject value = new BasicDBObject();
            value.put("id", id);
            value.put("name", card.getName());
            value.put("branch", card.getBranch());
            value.put("position", card.getPosition());
            value.put("phone", card.getPhone());
            value.put("email", card.getEmail());
            value.put("create_time", card.getCreateTime());
            value.put("from", card.getFrom());
            value.put("info_id", card.getInfoId());
            value.put("change_time", card.getChangeTime());
            value.put("gender", card.getGender());
            value.put("qq", card.getQq());
            value.put("group_id", card.getGroupId());
            if(getDBCollection(TABLE_NAME).insert(value).getN() > -1){
                return card;
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	    return null;
	}
	
	public Long getIdes(){
	    Long id = 100L;
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
	    return id;
	}
	
	public List<Card> findByInfoId(Long infoId){
	    List<Card> cards = null;
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("info_id", infoId);
	        DBObject sort = new BasicDBObject();
	        sort.put("change_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
	        cards = new ArrayList<Card>();
	        while(cursor.hasNext()){
	            cards.add(getInfoFromResult(cursor.next()));
	        }
	        
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return cards;
	}
	
	public List<Card> findByAccountId(Long accountId, int number){
	    List<Card> cards = new ArrayList<Card>();;
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("from", accountId);
	        DBObject sort = new BasicDBObject();
	        sort.put("create_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(number);
	        while(cursor.hasNext()){
	            cards.add(getInfoFromResult(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return cards;
	}
	
	public Card getInfoFromResult(DBObject object) {
		Card card = null;
		try{
		    Long id = Long.parseLong(object.get("id").toString().trim());
		    String name = null;
		    try{
		        name = object.get("name").toString();
		    }catch(Exception e){}
		    String branch = null;
		    try{
		        branch = object.get("branch").toString();
		    }catch(Exception e){}
		    String position = null;
		    try{
		        position = object.get("position").toString();
		    }catch(Exception e){}
		    String phone = null;
		    try{
		        phone = object.get("phone").toString();
		    }catch(Exception e){}
		    String email = null;
		    try{
		        email = object.get("email").toString();
		    }catch(Exception e){}
		    String createTime = object.get("create_time").toString();
		    Long from = null;
		    try{
		        from = Long.parseLong(object.get("from").toString().trim());
		    }catch(Exception e){}
		    Long infoId = null;
		    try{
		    	infoId = Long.parseLong(object.get("info_id").toString().trim());
		    }catch(Exception e){}
		    String changeTime = object.get("change_time").toString();
		    int gender = Integer.parseInt(object.get("gender").toString().trim());
		    String qq = null;
		    try{
		        qq = object.get("qq").toString();
		    }catch(Exception e){}
		    Long groupId = null;
		    try{
		    	groupId = Long.parseLong(object.get("group_id").toString().trim());
		    }catch(Exception e){}
		    card = new Card(id, name, branch, position, 
		            phone, email, createTime, from, infoId, changeTime, gender, qq);
		    card.setGroupId(groupId);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return card;
	}
}
