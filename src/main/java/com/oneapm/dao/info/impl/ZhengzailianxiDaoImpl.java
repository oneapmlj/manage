package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Zhengzailianxi;

public class ZhengzailianxiDaoImpl extends DaoImplBase<Zhengzailianxi> {
        protected static final Logger LOG = LoggerFactory.getLogger(ZhengzailianxiDaoImpl.class);
        protected static final String TABLE_NAME = "zhengzailianxi";

        static {
                Instance = new ZhengzailianxiDaoImpl();
        }

        private final static ZhengzailianxiDaoImpl Instance;

        public static ZhengzailianxiDaoImpl getInstance() {
                return Instance;
        }

        public List<Zhengzailianxi> findAll(){
                List<Zhengzailianxi> zhengzailianxis = new ArrayList<Zhengzailianxi>();
                try{
                      DBObject object = new BasicDBObject();
                      object.put("status", 0);
                      DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                      while(cursor.hasNext()){
                              zhengzailianxis.add(getZhengzailianxiFromObject(cursor.next()));
                      }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return zhengzailianxis;
        }
        public Zhengzailianxi findByInfoId(Long infoId){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return getZhengzailianxiFromObject(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public List<Zhengzailianxi> findByAdminId(Long adminId){
                List<Zhengzailianxi> zhengzailianxis = new ArrayList<Zhengzailianxi>();
                try{
                      DBObject object = new BasicDBObject();
                      object.put("admin_id", adminId);
                      object.put("status", 0);
                      DBObject sort= new BasicDBObject();
                      sort.put("stay", -1);
                      DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                      while(cursor.hasNext()){
                              zhengzailianxis.add(getZhengzailianxiFromObject(cursor.next()));
                      }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return zhengzailianxis;
        }
        
        public Zhengzailianxi findById(Long id){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        object.put("status", 0);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return getZhengzailianxiFromObject(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Long getIdest(){
                try{
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if(cursor.hasNext()){
                                return getZhengzailianxiFromObject(cursor.next()).getId()+1L;
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }
        
        public boolean insert(Zhengzailianxi zhengzailianxi){
                try{
                        DBObject value = new BasicDBObject();
                        value.put("id", zhengzailianxi.getId());
                        value.put("admin_id", zhengzailianxi.getAdminId());
                        value.put("start_time", zhengzailianxi.getStartTime());
                        value.put("status", zhengzailianxi.getStatus());
                        value.put("stay", zhengzailianxi.getStay());
                        value.put("info_id", zhengzailianxi.getInfoId());
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean update(Zhengzailianxi zhengzailianxi){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", zhengzailianxi.getId());
                        DBObject value = new BasicDBObject();
                        value.put("status", zhengzailianxi.getStatus());
                        value.put("end_time", zhengzailianxi.getEndTime());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        public boolean updateStay(Zhengzailianxi zhengzailianxi){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", zhengzailianxi.getId());
                        DBObject value = new BasicDBObject();
                        value.put("stay", zhengzailianxi.getStay());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        private Zhengzailianxi getZhengzailianxiFromObject(DBObject object) {
                Zhengzailianxi zhengzailianxi = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        String startTime = object.get("start_time").toString().trim();
                        String endTime = null;
                        try{
                                endTime = object.get("end_time").toString().trim();
                        }catch(Exception e){}
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        int stay = Integer.parseInt(object.get("stay").toString().trim());
                        Long infoId = Long.parseLong(object.get("info_id").toString().trim());
                        zhengzailianxi = new Zhengzailianxi(id, adminId, startTime, endTime, status, stay, infoId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return zhengzailianxi;
        }
}
