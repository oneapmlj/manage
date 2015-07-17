package com.oneapm.service.account;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.Call;
import com.oneapm.dto.Account.Account;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TagService;
import com.oneapm.service.show.CallService;
import com.oneapm.vo.Quanxian;
import com.oneapm.web.SupportAction;

public class AccountRecordService {

        protected static final Logger LOG = LoggerFactory.getLogger(AccountRecordService.class);

        public static Account findByAdmin(Admin admin, int type, boolean self) {
                if (admin == null)
                        return null;
//                List<Card> cards = null;
                List<Call> calls = null;
//                List<Mail> mails = null;
//                int mailSize = 0;
                int callSize = 0;
//                int cardSize = 0;
                int infoSize = 0;
                if (self) {
//                        cards = CardService.findByAccountId(admin.getId());
                        calls = CallService.findByAccountId(admin.getId());
//                        mails = MailService.findByAccountId(admin.getId());
//                        cardSize = cards.size();
                        callSize = calls.size();
//                        mailSize = mails.size();
                }
                List<Info> infos = InfoService.findInfosByAdminId(admin.getId());
                if (type > 0) {
                        if (self) {
//                                if (cards != null && cards.size() > 0) {
//                                        for (int i = 0; i < cards.size(); i++) {
//                                                if (!match(type, cards.get(i).getInfoId())) {
//                                                        cards.remove(i);
//                                                        i--;
//                                                }
//                                        }
//                                }
                                if (calls != null && calls.size() > 0) {
                                        for (int i = 0; i < calls.size(); i++) {
                                                if (!match(type, calls.get(i).getInfoId())) {
                                                        calls.remove(i);
                                                        i--;
                                                }
                                        }
                                }
//                                if (mails != null && mails.size() > 0) {
//                                        for (int i = 0; i < mails.size(); i++) {
//                                                if (!match(type, mails.get(i).getInfoId())) {
//                                                        mails.remove(i);
//                                                        i--;
//                                                }
//                                        }
//                                }
                        }
                        if (infos != null && infos.size() > 0) {
                                for (int i = 0; i < infos.size(); i++) {
                                        if (!match(type, infos.get(i).getId())) {
                                                infos.remove(i);
                                                i--;
                                        }
                                }
                        }
                }
                infoSize = infos.size();
                Account account = new Account(admin, null, null, calls, infos);
                // Account account = new Account(admin, null, null, null,
                // infos);
                List<Quanxian> quanxians = new ArrayList<Quanxian>();
                for (Quanxian quanxian : SupportAction.getGRADE().getList()) {
                        quanxians.add(new Quanxian(quanxian.getId(), quanxian.getQuanxian(), quanxian.getName(), quanxian.getAll()));
                }
                if (admin.getGrades() != null && admin.getGrades().trim().length() > 0) {
                        for (int i = 0; i < quanxians.size(); i++) {
                                if (admin.getGrades().indexOf(quanxians.get(i).getQuanxian()) > -1) {
                                        quanxians.get(i).setStatus(1);
                                } else {
                                        if (admin.getGrades().indexOf(SupportAction.getGRADE().getMap().get(999).getQuanxian()) > -1 && quanxians.get(i).getAll() == 1) {
                                                quanxians.get(i).setStatus(1);
                                        }
                                }
                        }
                }
                account.setQuanxians(quanxians);
                account.setCallSize(callSize);
//                account.setCardSize(cardSize);
                account.setInfoSize(infoSize);
//                account.setMailSize(mailSize);
                return account;
        }

        public static boolean match(int type, Long infoId) {
                switch (type) {
                case 1:
                        return TagService.findByInfoId(infoId).getMetric() == 1;
                case 2:
                        return TagService.findByInfoId(infoId).getMetric() == 2;
                case 3:
                        return TagService.findByInfoId(infoId).getMetric() == 3;
                case 4:
                        return TagService.findByInfoId(infoId).getMetric() == 4;
                case 5:
                        return TagService.findByInfoId(infoId).getLoudou() == 0;
                case 6:
                        return TagService.findByInfoId(infoId).getLoudou() == 1;
                case 7:
                        return TagService.findByInfoId(infoId).getLoudou() == 2;
                case 8:
                        return TagService.findByInfoId(infoId).getLoudou() == 3;
                case 9:
                        return TagService.findByInfoId(infoId).getLoudou() == 4;
                case 10:
                        return TagService.findByInfoId(infoId).getLoudou() == 5;
                default:
                        break;
                }
                return false;
        }
}
