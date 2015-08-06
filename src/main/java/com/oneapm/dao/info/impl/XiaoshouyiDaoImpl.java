package com.oneapm.dao.info.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;

public class XiaoshouyiDaoImpl extends DaoImplBase<Long> {
        protected static final Logger LOG = LoggerFactory.getLogger(XiaoshouyiDaoImpl.class);
        protected static final String TABLE_NAME = "xiaoshouyi";

        static {
                Instance = new XiaoshouyiDaoImpl();
        }

        private final static XiaoshouyiDaoImpl Instance;

        public static XiaoshouyiDaoImpl getInstance() {
                return Instance;
        }
        
        public Long getIdest(){
                try{
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if(cursor.hasNext()){
                                return Long.parseLong(cursor.next().get("id").toString())+1L;
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return 10000L;
        }
        /**
         * 
         * @param id
         * @param createTime
         * @param userId
         * @param adminId
         * @param type 1:推送，2:收回
         * @param sale
         */
        public void insert(Long id, String createTime, Long userId, Long adminId, int type, Long sale, String description){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        object.put("create_time", createTime);
                        object.put("user_id", userId);
                        object.put("admin_id", adminId);
                        object.put("type", type);
                        object.put("sale", sale);
                        object.put("description", description);
                        getDBCollection(TABLE_NAME).insert(object);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
}
