package com.oneapm.dao.mail.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DBManager;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Mail;
import com.oneapm.dto.mail.SendCloudDto;

public class MailDaoImpl extends DaoImplBase<Mail> {
        protected static final Logger LOG = LoggerFactory.getLogger(MailDaoImpl.class);
        protected final String TABLE_NAME = "mail";
        protected final String TABLE_NAME_ALL = "mail_all";

        static {
                Instance = new MailDaoImpl();
        }

        private final static MailDaoImpl Instance;

        public static MailDaoImpl getInstance() {
                return Instance;
        }

        public List<Mail> findById(Long infoId) {
                List<Mail> mails = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        DBObject sort = new BasicDBObject();
                        sort.put("send_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        mails = new ArrayList<Mail>();
                        while (cursor.hasNext()) {
                                mails.add(getRecordFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return mails;
        }
        public List<Mail> findByGroupId(Long groupId) {
            List<Mail> mails = null;
            try {
                    DBObject object = new BasicDBObject();
                    object.put("group_id", groupId);
                    DBObject sort = new BasicDBObject();
                    sort.put("send_time", -1);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                    mails = new ArrayList<Mail>();
                    while (cursor.hasNext()) {
                            mails.add(getRecordFromResult(cursor.next()));
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return mails;
    }

        public Mail findByMailId(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getRecordFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Mail> findByAccountId(Long accountId) {
                List<Mail> mails = new ArrayList<Mail>();
                ;
                try {
                        DBObject object = new BasicDBObject("admin_id", accountId);
                        DBObject sort = new BasicDBObject("send_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        while (cursor.hasNext()) {
                                mails.add(getRecordFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return mails;
        }

        public Long insert(Mail mail) {
                try {
                        DBObject object = new BasicDBObject();
                        Long id = getIdest() + 1;
                        object.put("id", id);
                        object.put("info_id", mail.getInfoId());
                        object.put("admin_id", mail.getAdminId());
                        object.put("send_time", mail.getSendTime());
                        object.put("mail_mode", mail.getMailMode());
                        object.put("mail_content", mail.getMailContent());
                        object.put("group_id", mail.getGroupId());
                        if (getDBCollection(TABLE_NAME).insert(object).getN() > -1) {
                                return id;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean insertAll(String content, Long adminId, String from, int number, String time) {
                try {
                        DBObject object = new BasicDBObject();
                        Long id = getAllIdest() + 1;
                        object.put("id", id);
                        object.put("content", content);
                        object.put("admin_id", adminId);
                        object.put("from", from);
                        object.put("number", number);
                        object.put("time", time);
                        return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public Long getIdest() {
                try {
                        DBObject sort = new BasicDBObject("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }
        
        public Long getAllIdest() {
                try {
                        DBObject sort = new BasicDBObject("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME_ALL).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }

        public Mail getRecordFromResult(DBObject object) {
                Mail mail = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        Long infoId = 0L;
                        try{
                         infoId = Long.parseLong(object.get("info_id").toString());
                        }catch(Exception e){}
                        Long groupId = 0L;
                        try{
                        	groupId = Long.parseLong(object.get("group_id").toString());
                        }catch(Exception e){}
                        int mailMode = Integer.parseInt(object.get("mail_mode").toString());
                        String sendTime = object.get("send_time").toString();
                        Long adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        String mailContent = object.get("mail_content").toString();
                        mail = new Mail(id, infoId, sendTime, mailMode, adminId, mailContent,groupId);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return mail;
        }
    	public List<SendCloudDto> findSendCloudByEmail(String email){
    		Connection conn=null;
    		PreparedStatement pst=null;
    		List<SendCloudDto> list = new ArrayList<SendCloudDto>();
    		try {
    			conn=DBManager.getConncection();
    			String sql="select * from sendcloud_new where email='"+email+"' and date_sub(curdate(), INTERVAL 14 DAY) <= date";
    			pst=conn.prepareStatement(sql);
    			ResultSet rs=pst.executeQuery(sql);
    			
    			 while(rs.next()){                      
    				 SendCloudDto sd = new SendCloudDto();
    				 sd.setId(rs.getString("id"));
    				 sd.setEvent(rs.getString("event"));
    				 sd.setEmail(rs.getString("email"));
    				 sd.setDate(rs.getString("date"));
    				 sd.setLabelId(rs.getString("labelid"));
    				 sd.setUrl(rs.getString("url"));
    	             list.add(sd);       
    		}
    			 return list;
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			
    		}finally{
    			try {
    				pst.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
			return list;
    	}
    	
    	public List<SendCloudDto> findBadEventDto(){
    		Connection conn=null;
    		PreparedStatement pst=null;
    		List<SendCloudDto> list = new ArrayList<SendCloudDto>();
    		try {
    			conn=DBManager.getConncection();
    			String sql="select * from sendcloud_new where  event='report_spam' or event='invalid' or event='bounce' or event='unsubcribes'";
    			System.out.println("QUERY_SQL:"+sql);
    			pst=conn.prepareStatement(sql);
    			ResultSet rs=pst.executeQuery(sql);
    			
    			 while(rs.next()){                      
    				 SendCloudDto sd = new SendCloudDto();
    				 sd.setId(rs.getString("id"));
    				 sd.setEvent(rs.getString("event"));
    				 sd.setEmail(rs.getString("email"));
    				 sd.setDate(rs.getString("date"));
    				 sd.setLabelId(rs.getString("labelid"));
    				 sd.setUrl(rs.getString("url"));
    	             list.add(sd);       
    		}
    			 return list;
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			
    		}finally{
    			try {
    				pst.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
			return list;
    	}
    	
    	
    	
}
