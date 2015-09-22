package com.oneapm.service.message;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.record.impl.MessageDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.record.Message;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TaskService;
import com.oneapm.service.record.RecordService;
import com.oneapm.util.TimeTools;
import com.oneapm.vo.MessageVo;

public class MessageService {
        protected static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

        private static String warming = null;

        public static void setWarming(String warming) {
                if (warming.trim().equals("0")) {
                        MessageService.warming = null;
                } else {
                        MessageService.warming = warming;
                }
        }

        @SuppressWarnings("unchecked")
        public static String getWarming() {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        if (warming != null && warming.length() > 0) {
                                object.put("warming", warming);
                                object.put("status", 1);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object.toJSONString();
        }

        public static List<Message> findByAdminId(Long adminId) {
                if (adminId == null)
                        return null;
                List<Message> messages = MessageDaoImpl.getInstance().findByAdminIdUnView(adminId);
                return messages;
        }
        
        public static List<Message> findByAdmin(Long adminId, int number ,int skip){
                if (adminId == null)
                        return null;
                List<Message> messages = MessageDaoImpl.getInstance().findByAdminId(adminId, number , skip);
                return messages;
        }


        public static List<Message> findUnCloseByAdminId(Long adminId) {
                if (adminId == null)
                        return null;
                List<Message> messages = MessageDaoImpl.getInstance().findByAdminIdUnClose(adminId);
                return messages;
        }

        public static Message findById(Long id) {
                return MessageDaoImpl.getInstance().findById(id);
        }
        public static Message findByGroupId(Long groupId) {
            return MessageDaoImpl.getInstance().findByGroupId(groupId);
    }

        public static MessageVo findVoById(Long id) {
                Message message = findById(id);
                if (message == null)
                        return null;
                return getMessageVoFromMessage(message);
        }

        @SuppressWarnings("unchecked")
        public static String check(Long messageId) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        Long groupId = close(messageId);
//                        if (infoId != null) {
                                object.put("status", 1);
                                object.put("id", messageId);
                                object.put("groupId", groupId);
//                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("msg", "服务器内部错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String findVosJSONByAdminId(Long adminId) {
                JSONObject object = new JSONObject();
                try {
                        List<MessageVo> vos = findVosByAdminId(adminId);
                        if (vos == null || vos.size() <= 0) {
                                object.put("length", 0);
                        } else {
                                object.put("vos", getArrayFromMessageVo(vos));
                                object.put("length", vos.size());
                        }
                        int task = TaskService.findLength(adminId);
                        object.put("task", task);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object.toJSONString();
        }

        public static List<MessageVo> findMessage(Long adminId, int number, int skip) {
                List<MessageVo> vos = new ArrayList<MessageVo>();
                try {
                        vos = findVosAllByAdminId(adminId, number, skip);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return vos;
        }
        
        public static int findCount(Long adminId){
                return MessageDaoImpl.getInstance().findCount(adminId);
        }

        public static List<MessageVo> findVosByAdminId(Long adminId) {
                List<Message> messages = findByAdminId(adminId);
                if (messages == null || messages.size() <= 0)
                        return null;
                List<MessageVo> vos = new ArrayList<MessageVo>();
                for (Message message : messages) {
                        vos.add(getMessageVoFromMessage(message));
                }
                return vos;
        }

        public static List<MessageVo> findVosAllByAdminId(Long adminId, int number, int skip) {
                List<Message> messages = findByAdmin(adminId, number, skip);
                if (messages == null || messages.size() <= 0)
                        return null;
                List<MessageVo> vos = new ArrayList<MessageVo>();
                for (Message message : messages) {
                        vos.add(getMessageVoFromMessage(message));
                }
                return vos;
        }

        public static List<MessageVo> findVosUnCloseByAdminId(Long adminId) {
                List<Message> messages = findUnCloseByAdminId(adminId);
                if (messages == null || messages.size() <= 0)
                        return null;
                List<MessageVo> vos = new ArrayList<MessageVo>();
                for (Message message : messages) {
                        vos.add(getMessageVoFromMessage(message));
                }
                return vos;
        }

        @SuppressWarnings("unchecked")
        public static String change(Admin admin, int type, Long infoId, Long messageId, int status) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        Info info = InfoService.findByIdSingle(infoId);
                        Message message = findById(messageId);
                        switch (type) {
                        case 1:
                                if (status == 1) {
                                        info.setSale(message.getFrom());
                                        InfoService.update(info);
                                        insert(admin.getId(), message.getFrom(), 0, infoId, 3);
                                        close(messageId);
                                }
                                if (status == 2) {
                                        insert(admin.getId(), message.getFrom(), 0, infoId, 5);
                                        close(messageId);
                                }
                                RecordService.insert(admin.getId(), type, infoId, message.getFrom(), status, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        case 2:
                                if (status == 1) {
                                        info.setSupport(message.getFrom());
                                        InfoService.update(info);
                                        insert(admin.getId(), message.getFrom(), 0, infoId, 4);
                                        close(messageId);
                                }
                                if (status == 2) {
                                        insert(admin.getId(), message.getFrom(), 0, infoId, 6);
                                        close(messageId);
                                }
                                RecordService.insert(admin.getId(), type, infoId, message.getFrom(), status, info.getTag().getMetric(), info.getTag().getLoudou(), 0, 0);
                                break;
                        default:
                                break;
                        }
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("msg", "服务器内部错误！");
                }
                return object.toJSONString();
        }

        public static Long close(Long id) {
                Message message = findById(id);
                try{
                        message.setStatus(3);
                        if(MessageDaoImpl.getInstance().update(message)){
                                return message.getGroupId();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static boolean view(Long id) {
                Message message = findById(id);
                message.setStatus(2);
                return MessageDaoImpl.getInstance().update(message);
        }

        public static boolean agree(Long id) {
                Message message = findById(id);
                message.setStatus(1);
                return MessageDaoImpl.getInstance().update(message);
        }

        public static boolean insert(Long from, Long to, int status, Long infoId, int type) {
                Message message = new Message(null, from, to, status, infoId, TimeTools.format(), null, null, type);
                return MessageDaoImpl.getInstance().insert(message);
        }
        public static boolean insertWithGroupId(Long from, Long to, int status, Long groupId, int type) {
            Message message = new Message(null, from, to, status, groupId, TimeTools.format(), null, null, type);
            message.setGroupId(groupId);
            return MessageDaoImpl.getInstance().insert(message);
    }



        public static Message findApplyByGroupId(Long groupId, int type, int status) {
            return MessageDaoImpl.getInstance().findApplyByGroupId(groupId, type, status);
    }

        public static MessageVo getMessageVoFromMessage(Message message) {
                MessageVo vo = null;
                try {
                        String fromName = AccountService.findById(message.getFrom()).getName();
                        String toName = AccountService.findById(message.getTo()).getName();
                        String company = null;
                        if (message.getGroupId() != null) {
                                company = UserGroupService.findByGroupIdSingle(message.getGroupId()).getGroupName();
                        }
                        vo = new MessageVo(message, fromName, toName, company);
                        vo.setGroupId(message.getGroupId());
                } catch (Exception e) {
                }
                return vo;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromMessageVo(List<MessageVo> vos) {
                JSONArray array = new JSONArray();
                try {
                        for (MessageVo vo : vos) {
                                array.add(getJSONFromMessageVo(vo));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromMessageVo(MessageVo vo) {
                JSONObject object = new JSONObject();
                try {
                        object.put("message", getJSONFromMessage(vo.getMessage()));
                        object.put("fromName", vo.getFromName());
                        object.put("toName", vo.getToName());
                        object.put("company", vo.getCompany());
                        object.put("infoId", vo.getInfoId());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromMessage(Message message) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", message.getId());
                        object.put("from", message.getFrom());
                        object.put("to", message.getTo());
                        object.put("status", message.getStatus());
                        object.put("infoId", message.getInfoId());
                        object.put("createTime", message.getCreateTime());
                        object.put("viewTime", message.getViewTime());
                        object.put("closeTime", message.getCloseTime());
                        object.put("type", message.getType());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
