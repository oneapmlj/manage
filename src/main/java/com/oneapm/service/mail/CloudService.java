package com.oneapm.service.mail;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.GuanlianDaoImpl;
import com.oneapm.dao.mail.impl.MailDaoImpl;
import com.oneapm.dao.mail.impl.MailModeDaoImpl;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.MailMode;
import com.oneapm.dto.info.DataLiucunShow;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.dto.mail.SendCloudDto;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.web.SupportAction;

public class CloudService {

        protected static final Logger LOG = LoggerFactory.getLogger(CloudService.class);

        @SuppressWarnings("unchecked")
        public static String insert(String description, int timeType, String title, int status, String content) {
                JSONObject object = new JSONObject();
                try {
                        if (title == null || title.equals("")) {
                                object.put("status", 0);
                                object.put("msg", "主题不能为空！");
                                return object.toJSONString();
                        }
                        if (content == null || content.equals("")) {
                                object.put("status", 0);
                                object.put("msg", "内容不能为空！");
                                return object.toJSONString();
                        }
                        MailDto dto = new MailDto(0, timeType, title, content, status, TimeTools.format(), null, description);
                        dto.setUse(0);
                        if (MailModeDaoImpl.getInstance().insert(dto)) {
                                SupportAction.addMailMode(new MailMode(dto.getId(), dto.getDescription()));
                                object.put("status", 1);
                                return object.toJSONString();
                        }
                        object.put("status", 0);
                        object.put("msg", "添加失败！");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误！");
                }
                return object.toJSONString();
        }

        public static List<MailDto> findMails() {
                return MailModeDaoImpl.getInstance().findMails();
        }
        
        public static  String findSendCloudByEmail(String email) {
         	JSONObject object = new JSONObject();
         	JSONArray jsonlist = new JSONArray();
         	List<SendCloudDto> sdList = MailDaoImpl.getInstance().findSendCloudByEmail(email);
         	 List<String> args1 = new ArrayList<String>();
             List<Object> args2 = new ArrayList<Object>();
             args1.add("sdList");
             args2.add(getArrayFromList(sdList));
             return OneTools.getResult(1, args1, args2);
    }
        
        public static  List<SendCloudDto> findSendCloudBeanByEmail(String email) {
         	List<SendCloudDto> sdList = MailDaoImpl.getInstance().findSendCloudByEmail(email);
             return sdList;
    }
        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromList(List<SendCloudDto> list) {
                JSONArray array = new JSONArray();
                try {
                        for (SendCloudDto dto : list) {
                                array.put(getJSONFromSendCloud(dto));
                                
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }
        
        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromSendCloud(SendCloudDto dto) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", dto.getId());
                        object.put("date", dto.getDate());
                        object.put("event", dto.getEvent());
                        object.put("email", dto.getEmail());
                        object.put("url", dto.getUrl());
                        object.put("labelId", dto.getLabelId());
                   
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
     /*   public static  List<SendCloudDto> findSendCloudByEmail(String email) {
         	List<SendCloudDto> sdList = MailDaoImpl.getInstance().findSendCloudByEmail(email);
           return sdList;
    }*/

}
