package com.oneapm.web.card;

import java.util.List;

import com.oneapm.dto.card.Card;
import com.oneapm.dto.card.CardRecord;
import com.oneapm.service.card.CardService;
import com.oneapm.web.SupportAction;


public class CardAction extends SupportAction{

    /**
     * 
     */
    private static final long serialVersionUID = -221095790153864272L;
    
    private Long id;

    
    private String company;
    
    private Long from;
    
    private String fromName;
    
    private String changeTime;
    
    private List<CardRecord> records;
    private Card card;
    public String view(){
        if(!isLogin()){
            return "login";
        }
        try{
            card = CardService.findById(id);
            records = CardService.getRecoardById(id, getAdmin().getName());
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return "view";
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<CardRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CardRecord> records) {
        this.records = records;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
