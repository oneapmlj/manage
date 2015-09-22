package com.oneapm.service.show;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.account.impl.AdminDaoImpl;
import com.oneapm.dao.card.impl.CardDaoImpl;
import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dao.opt.impl.CallDaoImpl;
import com.oneapm.dto.Call;
import com.oneapm.dto.NoteType;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.card.Card;
import com.oneapm.dto.info.Info;
import com.oneapm.record.MessageType;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.card.CardService;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TaskService;
import com.oneapm.service.message.MessageService;
import com.oneapm.service.note.NoteService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.util.exception.DataException;
import com.oneapm.util.exception.PramaException;

public class CallService {

        protected static final Logger LOG = LoggerFactory.getLogger(CallService.class);

        public static Call findById(Long callId) {
                Call call = CallDaoImpl.getInstance().findById(callId);
                addAmin(call);
                return call;
        }
        public static boolean exit(Long infoId, Long adminId, String time){
                return CallDaoImpl.getInstance().exit(infoId, adminId, time);
        }

        public static List<Call> findByAccountId(Long accountId, int skip, int number) {
                List<Call> calls = CallDaoImpl.getInstance().findByAccountId(accountId, number, skip);
                for(int i=0;i<calls.size();i++){
                        UserGroups userGroups = UserGroupService.findByGroupIdSimple(calls.get(i).getGroupId());
                        if(userGroups == null){
                                calls.remove(i);
                                i--;
                                continue;
                        }
                        String name = userGroups.getGroupName();
                        
                        calls.get(i).setCompany(name);
                }
                return calls;
        }

        public static long countByAdminId(Long adminId){
                return CallDaoImpl.getInstance().countByAdminId(adminId);
        }
        public static List<Call> findByInfoId(Long infoId) {
                List<Call> calls = null;
                try {
                        calls = CallDaoImpl.getInstance().findByInfoId(infoId);
                        for (Call call : calls) {
                                addAmin(call);
                                if (call.getCardId() != null && call.getCardId() >= 100) {
                                        Card card = CardService.findById(call.getCardId());
                                        if (card != null) {
                                                call.setCardName(card.getName());
                                        }
                                } else {
                                        call.setCardName("注册");
                                }
                                if (call.getType() != null && call.getType() > 0) {
                                        NoteType note = NoteService.findTypeById(call.getType());
                                        if (note != null) {
                                                call.setTypeName(note.getName());
                                        }
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return calls;
        }
        
        
        public static List<Call> findByGroupId(Long groupId) {
            List<Call> calls = null;
            try {
                    calls = CallDaoImpl.getInstance().findByGroupId(groupId);
                    for (Call call : calls) {
                            addAmin(call);
                            if (call.getCardId() != null && call.getCardId() >= 100) {
                                    Card card = CardService.findById(call.getCardId());
                                    if (card != null) {
                                            call.setCardName(card.getName());
                                    }
                            } else {
                                    call.setCardName("注册");
                            }
                            if (call.getType() != null && call.getType() > 0) {
                                    NoteType note = NoteService.findTypeById(call.getType());
                                    if (note != null) {
                                            call.setTypeName(note.getName());
                                    }
                            }
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return calls;
    }
        public static void addAmin(Call call) {
                if (call.getAdminId() == null || call.getAdminId() <= 0)
                        return;
                Admin admin = AdminDaoImpl.getInstance().findById(call.getAdminId());
                if (admin == null)
                        return;
                call.setAdminName(admin.getName());
        }

        @SuppressWarnings("unchecked")
        public static String insert(Long infoId, Long cardId, String qq, String time, Long gongdan, String mark, Long type, Admin admin, 
                        String putTime, String name, String phone, String email, int gender, String position, String branch, boolean point) {
                JSONObject object = new JSONObject();
                try {
                        Info info = InfoService.findByIdSimple(infoId);
                        if (info == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        Call call = new Call(infoId, null, cardId, TimeTools.format(), mark, admin.getId(), info.getCompany(), type, time, gongdan);
                        call.setGongdan(null);
                        if (cardId == null || cardId <= 0) {
                                if ((qq == null || qq.trim().length() <= 0) && (name == null || name.trim().length() <= 0) && (phone == null || phone.trim().length() <= 0) && (email == null || email.trim().length() <= 0) && (gongdan == null || gongdan <= 0) && call.getType() <= 0) {
                                        return OneTools.getResult(0, "至少填一项信息!");
                                }
                                if ((qq != null && qq.trim().length() > 0) || (name != null && name.trim().length() > 0) || (phone != null && phone.trim().length() > 0) || (email != null && email.trim().length() > 0)) {
                                        Card card = new Card();
                                        card.setId(CardDaoImpl.getInstance().getIdes());
                                        card.setCreateTime(TimeTools.format());
                                        card.setFrom(admin.getId());
                                        card.setInfoId(infoId);
                                        card.setGender(gender);
                                        card.setQq(qq);
                                        card.setName(name);
                                        card.setPhone(phone);
                                        card.setEmail(email);
                                        card.setPosition(position);
                                        card.setBranch(branch);
                                        card.setFrom(admin.getId());
                                        CardService.insertAndGet(card);
                                        call.setCardId(card.getId());
                                        card.setFromName(admin.getName());
                                        object.put("card", CardService.getJSONFromCard(card));
                                }
                                if (gongdan != null && gongdan > 0) {
                                        call.setGongdan(gongdan);
                                }
                        }
                        call.setAdminName(admin.getName());
                        if (call.getType() > 0) {
                                NoteType note = NoteService.findTypeById(type);
                                if (note != null) {
                                        if (NoteService.todu(note.getTodu(), infoId, admin, putTime, point)) {
                                                call.setTodu(note.getTodu());
                                                if (note.getTodu() == 1002) {
                                                        if (call.getMark() == null) {
                                                                call.setMark(putTime + "再联系");
                                                        } else {
                                                                call.setMark(putTime + "再联系<br/>" + call.getMark());
                                                        }
                                                }
                                        }
                                        if (note != null) {
                                                call.setTypeName(note.getName());
                                        }
                                }
                        }
                        call = insertAndGet(call);
                        if (call == null) {
                                throw new DataException();
                        }
                        info.setContectTime(TimeTools.format());
                        InfoDaoImpl.getInstance().update_contectTime(info);
                        TaskService.deal(call.getInfoId(), admin.getId(), call.getCallId());
                        JSONObject value = getJSONFromCall(call);
                        while(mark != null && mark.indexOf("@") > -1){
                                if(mark.length() > mark.indexOf("@")+1){
                                        mark = mark.substring(mark.indexOf("@")+1, mark.length());
                                        if(mark.indexOf(" ") > -1){
                                                String at = mark.substring(0, mark.indexOf(" ") +1);
                                                Admin ad = AccountService.findByRealName(at.trim());
                                                if(ad != null){
                                                        MessageService.insert(admin.getId(), ad.getId(), 0, infoId, 21);
                                                }
                                        }
                                }
                        }
                        object.put("status", 1);
                        object.put("call", value);
                } catch (PramaException e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "公司信息不存在！");
                } catch (DataException e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误！");
                }
                return object.toJSONString();
        }
        
        @SuppressWarnings("unchecked")
        public static String insertWithGroupId(Long groupId, Long cardId,  String mark, Long type, Admin admin, 
                        String putTime, boolean point) {
                JSONObject object = new JSONObject();
                try {
                        UserGroups userGroups = UserGroupService.findByGroupIdSimple(groupId);
                        if (userGroups == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        Long infoId = 0L;
                        Long gongdan = null;
                        Call call = new Call(infoId, null, cardId, TimeTools.format(), mark, admin.getId(), userGroups.getGroupName(), type, putTime, gongdan);
                        call.setUserId(cardId);
                        call.setCardId(0L);
                        call.setGongdan(null);
                        call.setGroupId(groupId);
                        if (cardId == null || cardId <= 0) {
                                if ( call.getType() <= 0) {
                                        return OneTools.getResult(0, "至少填一项信息!");
                                }
                                        Card card = new Card();
                                        card.setId(CardDaoImpl.getInstance().getIdes());
                                        card.setCreateTime(TimeTools.format());
                                        card.setFrom(admin.getId());
                                        CardService.insertAndGet(card);
                                        call.setCardId(card.getId());
                                        card.setFromName(admin.getName());
                                        object.put("card", CardService.getJSONFromCard(card));
                                }
                        call.setAdminName(admin.getName());
                        if (call.getType() > 0) {
                                NoteType note = NoteService.findTypeById(type);
                                if (note != null) {
                                        if (NoteService.toduWithGroupId(note.getTodu(), userGroups.getGroupId(), admin, putTime, point)) {
                                                call.setTodu(note.getTodu());
                                                if (note.getTodu() == 1002) {
                                                        if (call.getMark() == null) {
                                                                call.setMark(putTime + "再联系");
                                                        } else {
                                                                call.setMark(putTime + "再联系<br/>" + call.getMark());
                                                        }
                                                }
                                        }
                                        if (note != null) {
                                                call.setTypeName(note.getName());
                                        }
                                }
                        }
                        call = insertAndGetWithGroupId(call);
                        if (call == null) {
                                throw new DataException();
                        }
                        userGroups.setContectTime(putTime);
                        UserGroupsDaoImpl.getInstance().update_contectTime(userGroups);
                        TaskService.dealWithGroupId(userGroups.getGroupId(), admin.getId(), call.getCallId());
                        JSONObject value = getJSONFromCall(call);
                        while(mark != null && mark.indexOf("@") > -1){
                                if(mark.length() > mark.indexOf("@")+1){
                                        mark = mark.substring(mark.indexOf("@")+1, mark.length());
                                        if(mark.indexOf(" ") > -1){
                                                String at = mark.substring(0, mark.indexOf(" ") +1);
                                                Admin ad = AccountService.findByRealName(at.trim());
                                                if(ad != null){
                                                        MessageService.insertWithGroupId(admin.getId(), ad.getId(), 0, userGroups.getGroupId(), 21);
                                                }
                                        }
                                }
                        }
                        object.put("status", 1);
                        object.put("call", value);
                } catch (PramaException e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "公司信息不存在！");
                } catch (DataException e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误！");
                }
                return object.toJSONString();
        }
        
        public static Call insertAndGet(Call call) throws PramaException {
                Info info = InfoService.findByIdSimple(call.getInfoId());
                if (info == null) {
                        throw new PramaException();
                }
                if (call.getCardId() != null && call.getCardId() >= 100) {
                        Card card = CardService.findById(call.getCardId());
                        if (card != null) {
                                call.setCardName(card.getName());
                        }
                } else {
                        call.setCardName("注册");
                }
                call.setCompany(info.getCompany());
                call.setCallTime(TimeTools.format());
                call = CallDaoImpl.getInstance().insertAndGet(call);
                if (!call.getAdminId().equals(info.getSupport())) {
                        MessageService.insert(call.getAdminId(), info.getSupport(), 0, info.getId(), MessageType.ADD_RECORD);
                }
                if (!call.getAdminId().equals(info.getSale())) {
                        MessageService.insert(call.getAdminId(), info.getSale(), 0, info.getId(), MessageType.ADD_RECORD);
                }
                return call;
        }
        
        public static Call insertAndGetWithGroupId(Call call) throws PramaException {
            UserGroups userGroups = UserGroupService.findByGroupIdSimple(call.getGroupId());
            if (userGroups == null) {
                    throw new PramaException();
            }
            if (call.getCardId() != null && call.getCardId() >= 100) {
                    Card card = CardService.findById(call.getCardId());
                    if (card != null) {
                            call.setCardName(card.getName());
                    }
            } else {
                    call.setCardName("注册");
            }
            call.setCompany(userGroups.getGroupName());
            call.setCallTime(TimeTools.format());
            call = CallDaoImpl.getInstance().insertAndGet(call);
            if (!call.getAdminId().equals(userGroups.getSupport())) {
                    MessageService.insertWithGroupId(call.getAdminId(), userGroups.getSupport(), 0, userGroups.getGroupId(), MessageType.ADD_RECORD);
            }
            if (!call.getAdminId().equals(userGroups.getSale())) {
                    MessageService.insertWithGroupId(call.getAdminId(), userGroups.getSale(), 0, userGroups.getGroupId(), MessageType.ADD_RECORD);
            }
            return call;
    }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromCall(List<Call> calls) {
                if (calls == null || calls.size() <= 0) {
                        return null;
                }
                JSONArray array = new JSONArray();
                for (Call call : calls) {
                        array.add(getJSONFromCall(call));
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromCall(Call call) {
                JSONObject object = new JSONObject();
                try {
                        object.put("callId", call.getCallId());
                        object.put("infoId", call.getInfoId());
                        object.put("cardId", call.getCardId());
                        object.put("callTime", call.getCallTime());
                        object.put("mark", call.getMark());
                        object.put("adminId", call.getAdminId());
                        object.put("company", call.getCompany());
                        object.put("adminName", call.getAdminName());
                        object.put("cardName", call.getCardName());
                        object.put("gongdan", call.getGongdan());
                        object.put("todu", call.getTodu());
                        object.put("typeName", call.getTypeName());
                        object.put("group_id", call.getGroupId());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
