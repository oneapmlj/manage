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
    
    private int mailSize;
    
    private int callSize;
    
    private int cardSize;
    
    private int infoSize;
    
    private List<Quanxian> quanxians;
    
    public Account(){}
    public Account(Admin admin, List<Mail> mails, List<Card> cards, List<Call> calls, List<Info> infos){
        setAdmin(admin);
        setMails(mails);
        setCalls(calls);
        setCards(cards);
        setInfos(infos);
        if(mails != null){
            setMailSize(mails.size());
        }
        if(cards != null){
            setCardSize(cards.size());
        }
        if(calls != null){
            setCallSize(calls.size());
        }
        if(infos != null){
            setInfoSize(infos.size());
        }
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
    public int getCallSize() {
        return callSize;
    }
    public void setCallSize(int callSize) {
        this.callSize = callSize;
    }
    public int getCardSize() {
        return cardSize;
    }
    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }
    public int getMailSize() {
        return mailSize;
    }
    public void setMailSize(int mailSize) {
        this.mailSize = mailSize;
    }
    public List<Info> getInfos() {
        return infos;
    }
    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }
    public int getInfoSize() {
        return infoSize;
    }
    public void setInfoSize(int infoSize) {
        this.infoSize = infoSize;
    }
    public List<Quanxian> getQuanxians() {
        return quanxians;
    }
    public void setQuanxians(List<Quanxian> quanxians) {
        this.quanxians = quanxians;
    }
}
