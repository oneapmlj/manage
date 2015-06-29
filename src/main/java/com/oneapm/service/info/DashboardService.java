package com.oneapm.service.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oneapm.dao.info.impl.DashboardDaoImpl;
import com.oneapm.dto.info.Dashboard;
import com.oneapm.dto.info.Tongji;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class DashboardService {

        protected static final Logger LOG = LoggerFactory.getLogger(DashboardService.class);
        public static String dashboard(){
                try{
                        Dashboard dashboard = DashboardDaoImpl.getInstance().findLast();
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("data", dashboard.getData());
                        object.put("sign", dashboard.getSign());
                        object.put("downloadNew", dashboard.getDowloadNew());
                        object.put("appNew", dashboard.getAppNew());
                        object.put("uv", dashboard.getUv());
                        object.put("new_uv", dashboard.getNewUv());
                        if(dashboard.getSign() > 0){
                                int number_download_sign = dashboard.getDowloadNew()*1000/dashboard.getSign();
                                number_download_sign = number_download_sign > 1000 ? 1000 : number_download_sign;
                                object.put("download_sign", number_download_sign);
                                int number_app_sign = dashboard.getAppNew()*1000/dashboard.getSign();
                                number_app_sign = number_app_sign > 1000 ? 1000 : number_app_sign;
                                object.put("app_sign", number_app_sign);
                        }else{
                                object.put("download_sign", 0);
                                object.put("app_sign", 0);
                        }
                        if(dashboard.getDowloadNew() > 0){
                                int number_app_download = dashboard.getAppNew()*1000/dashboard.getDowloadNew();
                                number_app_download = number_app_download > 1000 ? 1000 : number_app_download;
                                object.put("app_download", number_app_download);
                        }else{
                                object.put("app_download", 0);
                        }
                        Tongji tongji = TongjiService.findLast();
                        object.put("download", tongji.getDownload());
                        object.put("app", tongji.getApp());
                        object.put("total_app", tongji.getAppData());
                        String start = TimeTools.getDateTime(29);
                        String end = TimeTools.getDateTime(28);
                        JSONArray date = new JSONArray();
                        JSONArray date_data = new JSONArray();
                        JSONArray date_uv= new JSONArray();
                        JSONArray date_app_new= new JSONArray();
                        JSONArray date_download_new= new JSONArray();
                        
                        JSONArray date_data2 = new JSONArray();
                        
                        for(int i=0;i<30;i++){
                                date.add(start.substring(8, 10));
                                Dashboard dashboard2 = DashboardDaoImpl.getInstance().find(start, end);
                                if(dashboard2 == null){
                                        date_data.add(0);
                                        date_app_new.add(0);
                                        date_download_new.add(0);
                                        date_uv.add(0);
                                        
                                        date_data2.add(0);
                                }else{
                                        date_data.add(dashboard2.getData());
                                        date_data2.add(dashboard2.getData()+50);
                                        if(dashboard2.getDowloadNew() > 0){
                                                int number_app_new = dashboard2.getAppNew()*1000/dashboard2.getDowloadNew();
                                                number_app_new = number_app_new > 1000 ? 1000 : number_app_new;
                                                date_app_new.add(number_app_new);
                                        }else{
                                                date_app_new.add(0);
                                        }
                                        if(dashboard2.getSign() > 0){
                                                date_download_new.add(dashboard2.getDowloadNew()*1000/dashboard2.getSign());
                                        }else{
                                                date_download_new.add(0);   
                                        }
                                        date_uv.add(dashboard2.getUv());
                                }
                                start = TimeTools.next(start, 1);
                                end = TimeTools.next(end, 1);
                        }
                        object.put("date", date);
                        object.put("date_data", date_data);
                        object.put("date_download_new", date_download_new);
                        object.put("date_app_new", date_app_new);
                        object.put("date_uv", date_uv);
                        object.put("date_data2", date_data2);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }
}
