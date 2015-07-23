package com.oneapm.dto.Account;

import java.util.List;

import com.oneapm.dto.Call;
import com.oneapm.dto.Mail;
import com.oneapm.dto.card.Card;
import com.oneapm.dto.info.Info;
import com.oneapm.vo.Quanxian;

public class Account {

        private Admin admin;

        private List<Mail> mails;

        private List<Call> calls;

        private List<Card> cards;

        private List<Info> infos;

        private int size;
        private int sizeTotal;
        private int pageNow;
        private int pageTotal;
        private List<Quanxian> quanxians;

        public Account() {
        }

        public Account(Admin admin, List<Mail> mails, List<Card> cards, List<Call> calls, List<Info> infos) {
                setAdmin(admin);
                setMails(mails);
                setCalls(calls);
                setCards(cards);
                setInfos(infos);
        }

        public List<Mail> getMails() {
                return mails;
        }

        public void setMails(List<Mail> mails) {
                this.mails = mails;
        }

        public List<Card> getCards() {
                return cards;
        }

        public void setCards(List<Card> cards) {
                this.cards = cards;
        }

        public List<Call> getCalls() {
                return calls;
        }

        public void setCalls(List<Call> calls) {
                this.calls = calls;
        }

        public Admin getAdmin() {
                return admin;
        }

        public void setAdmin(Admin admin) {
                this.admin = admin;
        }

        public List<Info> getInfos() {
                return infos;
        }

        public void setInfos(List<Info> infos) {
                this.infos = infos;
        }

        public List<Quanxian> getQuanxians() {
                return quanxians;
        }

        public void setQuanxians(List<Quanxian> quanxians) {
                this.quanxians = quanxians;
        }

        public int getPageNow() {
                return pageNow;
        }

        public void setPageNow(int pageNow) {
                this.pageNow = pageNow;
        }

        public int getPageTotal() {
                return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
                this.pageTotal = pageTotal;
        }

        public int getSize() {
                return size;
        }

        public void setSize(int size) {
                this.size = size;
        }

        public int getSizeTotal() {
                return sizeTotal;
        }

        public void setSizeTotal(int sizeTotal) {
                this.sizeTotal = sizeTotal;
        }
}
