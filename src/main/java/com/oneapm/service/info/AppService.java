package com.oneapm.service.info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oneapm.dao.info.impl.AppDaoImpl;
import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.opt.impl.AddDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class AppService {

        protected static final Logger LOG = LoggerFactory.getLogger(AppService.class);


        public static List<App> findAppByAgent(int agent, String start, String end, String banben){
                List<App> apps = new ArrayList<App>();
                try{
                        if(agent <= 0){
                                apps = AddDaoImpl.getInstance().findByAgent(start, end);
                        }else{
                                apps = AddDaoImpl.getInstance().findByAgent(agent, start, end);
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public static List<Aplication> findAplicationByAgent(int agent, String start, String banben, String end){
                List<Aplication> apps = new ArrayList<Aplication>();
                try{
                        apps = AppDataDaoImpl.getInstance().findByAgent(agent, start, end);
                        if(banben != null && banben.trim().length() > 0 && !banben.equals("null") && (agent <= 6)){
                                for(int i=0;i<apps.size();i++){
                                        App app = findById(apps.get(i).getAppId(), apps.get(i).getAgent(), apps.get(i).getAgentId());
                                        if(app != null){
                                                if(app.getVersion() != null && !banben.equals(app.getVersion())){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                        }else{
                                                apps.remove(i);
                                                i--;
                                        }
                                }
                        }
                        if(agent == 9 && !banben.equals("0")){
                                int number = Integer.parseInt(banben);
                                for(int i=0;i<apps.size();i++){
                                        switch (number) {
                                        case 100:
                                                if(AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 0, true)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 101:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 0, true)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 1:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 1, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 2:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 2, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 3:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 3, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 4:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 4, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 5:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 5, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        case 6:
                                                if(!AppDaoImpl.getInstance().exist(apps.get(i).getAppId(), 6, false)){
                                                        apps.remove(i);
                                                        i--;
                                                }
                                                break;
                                        default:
                                                break;
                                        }
                                }
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        

        public static List<App> countData() {
                return AddDaoImpl.getInstance().countData(TimeTools.getDateTime(0));
        }

        
        
        public static String appMap(Long appId, int agent, Long agentId){
                try{
                        App app = null;
                        app = findById(appId, agent, agentId);
                        if(app == null){
                                LOG.info(appId+"_"+agent+"_"+agentId);
                                return OneTools.getResult(0, "参数错误");
                        }
                        String time = TimeTools.getDateTime(30);
                        List<Aplication> apps = null;
                        apps = AppDataDaoImpl.getInstance().findByTimeList(time, appId, agent, agentId);
                        if(apps == null || apps.size() <= 0){
                                return OneTools.getResult(0, "无数据连续");
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        JSONArray array = new JSONArray();
                        for(Aplication aplication : apps){
                                JSONObject object = new JSONObject();
                                object.put("time", aplication.getDataTime().substring(0, 10));
                                object.put("total", aplication.getTotal());
                                object.put("agent_number", aplication.getAgentNumber());
                                array.add(object);
                        }
                        args1.add("datas");
                        args2.add(array);
                        args1.add("appId");
                        args2.add(appId);
                        args1.add("agentId");
                        args2.add(agentId);
                        args1.add("agent");
                        args2.add(agent);
                        return OneTools.getResult(1, args1, args2);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static List<App> findByUserId(Long userId) {
                List<App> apps = null;
                if (userId == null || userId <= 0)
                        return null;
                apps = AddDaoImpl.getInstance().findByUserId(userId);
                long dataTime = 0;
                try{
                        dataTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(TimeTools.getDateTime(0)).getTime();
                }catch(Exception e){
                        LOG.error(e.getMessage() ,e);
                }
                for (App app : apps) {
                        try {
                                if (app.getDataTime() == null) {
                                        app.setDataTime("未记录");
                                } else {
                                        if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(app.getDataTime()).getTime() >= dataTime){
                                                app.setDataTime("今日");
                                        }else{
                                                app.setDataTime(app.getDataTime().substring(0,10));
                                        }
                                }
                                if(app.getAppName().length() > 20){
                                        app.setAppName(app.getAppName().substring(0, 17)+"…");
                                }
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                return apps;
        }
        
        public static List<App> findByGroupId(Long groupId) {
            List<App> apps = null;
            if (groupId == null || groupId <= 0)
                    return null;
            apps = AddDaoImpl.getInstance().findByGroupId(groupId);
            long dataTime = 0;
            try{
                    dataTime = TimeTools.formatTime.parse(TimeTools.getDateTime(0)).getTime();
            }catch(Exception e){
                    LOG.error(e.getMessage() ,e);
            }
            for (App app : apps) {
                    try {
                            if (app.getDataTime() == null) {
                                    app.setDataTime("未记录");
                            } else {
                                    if(TimeTools.formatTime.parse(app.getDataTime()).getTime() >= dataTime){
                                            app.setDataTime("今日");
                                    }else{
                                            app.setDataTime(app.getDataTime().substring(0,10));
                                    }
                            }
                            if(app.getAppName().length() > 20){
                                    app.setAppName(app.getAppName().substring(0, 17)+"…");
                            }
                    } catch (Exception e) {
                            LOG.error(e.getMessage(), e);
                    }
            }
            return apps;
    }
//        public static List<App> findByUserIdM(Long userId) {
//                List<App> apps = null;
//                if (userId == null || userId <= 0)
//                        return null;
//                apps = AddMDaoImpl.getInstance().findByUserId(userId);
//                String dataTime = TimeTools.getDateTime(0);
//                for (App app : apps) {
//                        try {
////                                app.setActive(ActiveDaoImpl.getInstance().findByIdAndDataTime(app.getAppId(), dataTime, 2).getActive());
//                                if (app.getDataTime() == null) {
//                                        app.setDataTime("未记录");
//                                } else {
//                                        if(TimeTools.formatTime.parse(app.getDataTime()).getTime() > TimeTools.formatTime.parse(dataTime).getTime()){
//                                                app.setDataTime("今日");
//                                        }
////                                        if (TimeTools.fromToday(app.getDataTime(), false) <= 0) {
////                                                app.setDataTime("今日");
////                                        }
//                                }
//                        } catch (Exception e) {
//                        }
//                }
//                return apps;
//        }

        public static App findById(Long appId, int agent, Long agentId) {
                if (appId == null || appId <= 0)
                        return null;
                return AddDaoImpl.getInstance().findById(appId, agentId, agent);
        }

//        public static App findByIdM(Long appId) {
//                if (appId == null || appId <= 0)
//                        return null;
//                return AddMDaoImpl.getInstance().findById(appId);
//        }

        public static App findAddTime(Long userId) {
                return AddDaoImpl.getInstance().findAddTime(userId);
        }

//        public static boolean update(Long appId, String dataTime) {
//                return AddDaoImpl.getInstance().update(appId, dataTime);
//        }

//        public static boolean updateM(Long appId, String dataTime) {
//                return AddMDaoImpl.getInstance().update(appId, dataTime);
//        }
}
