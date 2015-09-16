package com.oneapm.service.card;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.account.impl.AdminDaoImpl;
import com.oneapm.dao.card.impl.CardDaoImpl;
import com.oneapm.dao.card.impl.CardRecordDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.card.Card;
import com.oneapm.dto.card.CardRecord;
import com.oneapm.dto.info.Info;
import com.oneapm.service.info.InfoService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.util.exception.DataException;
import com.oneapm.util.exception.PramaException;

public class CardService {

        protected static final Logger LOG = LoggerFactory.getLogger(CardService.class);

        public static Card findById(Long cardId) {
                Card card = CardDaoImpl.getInstance().findById(cardId);
                addAmin(card);
                return card;
        }

        public static List<Card> findByInfoId(Long infoId) {
                List<Card> cards = null;
                try {
                        cards = CardDaoImpl.getInstance().findByInfoId(infoId);
                        for (Card card : cards) {
                                addAmin(card);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return cards;
        }

        public static String update(Long cardId, String name, String branch, String phone, String qq, int gender, String email, String position) {
                try {
                        if (cardId == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        Card card = findById(cardId);
                        if (card == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        if (name != null && name.trim().length() > 0) {
                                card.setName(name);
                        }
                        if (branch != null && branch.trim().length() > 0) {
                                card.setBranch(branch);
                        }
                        if (phone != null && phone.trim().length() > 0) {
                                card.setPhone(phone);
                        }
                        if (qq != null && qq.trim().length() > 0) {
                                card.setQq(qq);
                        }
                        card.setGender(gender);

                        if (email != null && email.trim().length() > 0) {
                                card.setEmail(email);
                        }
                        if (position != null && position.trim().length() > 0) {
                                card.setPosition(position);
                        }
                        card.setChangeTime(TimeTools.format());
                        if (update(card)) {
                                List<String> args1 = new ArrayList<String>();
                                List<Object> args2 = new ArrayList<Object>();
                                args1.add("name");
                                args1.add("branch");
                                args1.add("phone");
                                args1.add("qq");
                                args1.add("gender");
                                args1.add("position");
                                args1.add("email");
                                args1.add("id");
                                args2.add(card.getName());
                                args2.add(card.getBranch());
                                args2.add(card.getPhone());
                                args2.add(card.getQq());
                                args2.add(card.getGender());
                                args2.add(card.getPosition());
                                args2.add(card.getEmail());
                                args2.add(card.getId());
                                return OneTools.getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static boolean update(Card card) {
                return CardDaoImpl.getInstance().update(card);
        }

        public static List<Card> findByAccountId(Long accountId) {
                List<Card> cards = CardDaoImpl.getInstance().findByAccountId(accountId, 30);
                return cards;
        }

        public static void addAmin(Card card) {
                if (card.getFrom() == null || card.getFrom() <= 0)
                        return;
                Admin admin = AdminDaoImpl.getInstance().findById(card.getFrom());
                if (admin == null)
                        return;
                card.setFromName(admin.getName());
        }

        @SuppressWarnings("unchecked")
        public static String insert(Card card, String fromName) {
                JSONObject object = new JSONObject();
                try {
                        Info info = InfoService.findByIdSimple(card.getInfoId());
                        card = insertAndGet(card);
                        if (card == null) {
                                throw new DataException();
                        }
                        card.setFromName(fromName);
                        JSONObject value = getJSONFromCard(card);
                        object.put("status", 1);
                        object.put("card", value);
                } catch (PramaException e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "公司信息不存在！");
                } catch (DataException e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误！");
                }
                return object.toJSONString();
        }

        /**
         * 更新名片信息
         * 
         * @param id
         * @param name
         * @param branch
         * @param position
         * @param phone
         * @param email
         * @param infoId
         * @param gender
         * @param mark
         * @param adminId
         * @return
         */
        // public static String update(Long id, String name, String branch,
        // String position, String phone,
        // String email, Long infoId, int gender, String mark, Long adminId){
        // JSONObject object = new JSONObject();
        // try{
        // object.put("status", 0);
        // Info info = InfoService.findById(infoId);
        // Card card = new Card(id, name, branch, position, phone,
        // email, null, info.getCompany(), null, infoId, TimeTools.format(),
        // gender);
        // CardRecord record = new CardRecord(null, id, adminId, 20,
        // TimeTools.format(), mark);
        // if(CardDaoImpl.getInstance().update(card)){
        // CardRecordDaoImpl.getInstance().insert(record);
        // object.put("status", 1);
        // }
        // }catch(Exception e){
        // LOG.error(e.getMessage(), e);
        // object.put("msg", "服务器内部错误！");
        // }
        // return object.toJSONString();
        // }

        public static List<CardRecord> getRecoardById(Long cardId, String adminName) {
                List<CardRecord> records = CardRecordDaoImpl.getInstance().findByCardId(cardId);
                if (records != null) {
                        for (CardRecord record : records) {
                                record.setAdminName(adminName);
                                record.setChangeName(getChangeName(record.getChangeType()));
                        }
                }
                return records;
        }

        public static String getChangeName(int type) {
                switch (type) {
                case 1:
                        return "新建";
                case 2:
                        return "修改";
                default:
                        break;
                }
                return "编辑";
        }

        public static Card insertAndGet(Card card) throws PramaException {
                card.setCreateTime(TimeTools.format());
                card.setChangeTime(TimeTools.format());
                card = CardDaoImpl.getInstance().insertAndGet(card);
                return card;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromCard(Card card) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", card.getId());
                        object.put("name", card.getName());
                        object.put("branch", card.getBranch());
                        object.put("position", card.getPosition());
                        object.put("phone", card.getPhone());
                        object.put("email", card.getEmail());
                        object.put("createTime", card.getCreateTime());
                        object.put("company", card.getCompany());
                        object.put("from", card.getFrom());
                        object.put("infoId", card.getInfoId());
                        object.put("changeTime", card.getChangeTime());
                        object.put("gender", card.getGender());
                        object.put("qq", card.getQq());
                        object.put("fromName", card.getFromName());
                        object.put("group_id", card.getGroupId());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
