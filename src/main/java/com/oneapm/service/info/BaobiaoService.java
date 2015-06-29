package com.oneapm.service.info;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.BaobiaoDaoImpl;
import com.oneapm.dto.info.Baobiao;

public class BaobiaoService {
    protected static final Logger LOG = LoggerFactory.getLogger(BaobiaoService.class);
    
    public static List<Baobiao> findByType(int type){
        if(type < 1){
            type = 1;
        }
        List<Baobiao> baobiaos = null;
        switch(type){
            case 1:baobiaos = BaobiaoDaoImpl.getInstance().findByDay();break;
            case 2:baobiaos = BaobiaoDaoImpl.getInstance().findByWeek();break;
            case 3:baobiaos = BaobiaoDaoImpl.getInstance().findByMonth();break;
            default:break;
        }
        if(baobiaos != null && baobiaos.size() > 0){
            for(Baobiao baobiao : baobiaos){
                baobiao.setDataTime(baobiao.getDataTime().substring(0, 10));
            }
        }
        return baobiaos;
    }
}
