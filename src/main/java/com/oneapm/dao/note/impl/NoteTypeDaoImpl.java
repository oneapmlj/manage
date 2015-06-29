package com.oneapm.dao.note.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.NoteType;

public class NoteTypeDaoImpl extends DaoImplBase<NoteType>{
    protected static final Logger LOG = LoggerFactory.getLogger(NoteTypeDaoImpl.class);
    protected final String TABLE_NAME = "note_type";
    
    static {
        Instance = new NoteTypeDaoImpl();
    }
    
    private final static NoteTypeDaoImpl Instance;
    
    public static NoteTypeDaoImpl getInstance(){
        return Instance;
    }
    
    public Long getIdest(){
        try{
            DBObject sort = new BasicDBObject();
            sort.put("id", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
            if(cursor.hasNext()){
                return Long.parseLong(cursor.next().get("id").toString().trim())+1L;
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return 1000L;
    }
    public NoteType findById(Long id){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", id);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
            if(cursor.hasNext()){
                return getNoteFromObject(cursor.next());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    
    public List<NoteType> findByFather(Long father){
        List<NoteType> notes = new ArrayList<NoteType>();
        try{
            DBObject object = new BasicDBObject();
            object.put("father", father);
            object.put("status", 1);
            DBObject sort = new BasicDBObject();
            sort.put("id", 1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
            while(cursor.hasNext()){
                notes.add(getNoteFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return notes;
    }
    
    public boolean insert(NoteType note){
        try{
            DBObject value = new BasicDBObject();
            value.put("id", note.getId());
            value.put("father", note.getFather());
            value.put("name", note.getName());
            value.put("create_time", note.getCreateTime());
            value.put("status", note.getStatus());
            value.put("admin_id", note.getAdminId());
            value.put("todu", note.getTodu());
            return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    
    public boolean update(NoteType note){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", note.getId());
            DBObject value = new BasicDBObject();
            value.put("father", note.getFather());
            value.put("name", note.getName());
            value.put("status", note.getStatus());
            return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    
    private NoteType getNoteFromObject(DBObject object){
        NoteType note = null;
        try{
            Long id = Long.parseLong(object.get("id").toString().trim());
            Long father = Long.parseLong(object.get("father").toString().trim());
            String name = object.get("name").toString();
            String createTime = object.get("create_time").toString();
            int status = Integer.parseInt(object.get("status").toString().trim());
            Long adminId = null;
            try{
                adminId = Long.parseLong(object.get("admin_id").toString().trim());
            }catch(Exception e){}
            int todu = 0;
            try{
                todu = Integer.parseInt(object.get("todu").toString().trim());
            }catch(Exception e){}
            note = new NoteType(id, father, name, createTime, status, adminId, todu);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return note;
    }
}
