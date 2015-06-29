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
import com.oneapm.dto.card.CardRecord;

public class CardRecordDaoImpl extends DaoImplBase<CardRecord>{
	protected static final Logger LOG = LoggerFactory.getLogger(CardRecordDaoImpl.class);
	protected final String TABLE_NAME = "card_record";
	
	static {
		Instance = new CardRecordDaoImpl();
	}
	
	private final static CardRecordDaoImpl Instance;
	
	public static CardRecordDaoImpl getInstance(){
		return Instance;
	}
	
	public Long getIdest(){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("id", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(object).limit(1);
	        if(cursor.hasNext()){
	            return Long.parseLong(cursor.next().get("id").toString().trim());
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return 100L;
	}
	public boolean insert(CardRecord record){
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("card_id", record.getCardId());
	        object.put("id", getIdest());
	        object.put("admin_id", record.getAdminId());
	        object.put("change_type", record.getChangeName());
	        object.put("create_time", record.getCreateTime());
	        object.put("mark", record.getMark());
	        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return false;
	}
	
	public List<CardRecord> findByCardId(Long cardId){
	    List<CardRecord> records = null;
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("card_id", cardId);
	        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(new BasicDBObject("create_time", -1));
	        records = new ArrayList<CardRecord>();
	        while(cursor.hasNext()){
	            records.add(getInfoFromResult(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return records;
	}
	
	
	public CardRecord getInfoFromResult(DBObject object) {
	    CardRecord record = null;
		try{
		    Long id = Long.parseLong(object.get("id").toString().trim());
		    Long cardId = Long.parseLong(object.get("card_id").toString().trim());
		    Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
		    int changeType = Integer.parseInt(object.get("change_type").toString().trim());
		    String createTime = object.get("create_time").toString().trim();
		    String mark = object.get("mark").toString().trim();
		    record = new CardRecord(id, cardId, adminId, changeType, createTime, mark);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return record;
	}
}
