package com.oneapm.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.web.account.Control;

public class ControlDaoImpl extends DaoImplBase<Control> {

    protected static final Logger LOG = LoggerFactory
            .getLogger(ControlDaoImpl.class);

    protected final static String TABLE_NAME = "control";

    private final static ControlDaoImpl Instance;

    static {
        Instance = new ControlDaoImpl();
    }

    public static ControlDaoImpl getInstance() {
        return Instance;
    }
    
    public List<Control> list(){
        List<Control> controls = new ArrayList<Control>();
        try{
            DBObject sort = new BasicDBObject();
            sort.put("id", 1);
            DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort);
            while(cursor.hasNext()){
                controls.add(getControlFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return controls;
    }
    
    public boolean update(Long id, int control){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", id);
            DBObject value = new BasicDBObject("$set", new BasicDBObject("control", control));
            return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    
    public Control findById(Long id){
        try{
            DBObject object = new BasicDBObject();
            object.put("id", id);
            DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
            if(cursor.hasNext()){
                return getControlFromObject(cursor.next());
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    
    private Control getControlFromObject(DBObject object){
        Control control = new Control();
        try{
            control.setId(Long.parseLong(object.get("id").toString().trim()));
            control.setControl(Integer.parseInt(object.get("control").toString().trim()));
            control.setName(object.get("name").toString());
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return control;
    }
    
}
