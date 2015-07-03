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
                        object.put("sign", dashboard.getSign());
                        object.put("downloadNew", dashboard.getDowloadNew());
                        object.put("appNew", dashboard.getAppNew());
                        object.put("download", dashboard.getDownload());
                        object.put("app", dashboard.getApp());
                        object.put("uv", dashboard.getUv());
                        object.put("new_uv", dashboard.getNewUv());
                        object.put("data_all", dashboard.getData_all());
                        object.put("data_ai", dashboard.getData_ai());
                        object.put("data_mi", dashboard.getData_mi());
                        object.put("data_server", dashboard.getData_server());
                        object.put("app_all", dashboard.getApp_all());
                        object.put("app_ai", dashboard.getApp_ai());
                        object.put("app_mi", dashboard.getApp_mi());
                        object.put("app_server", dashboard.getApp_server());
                        object.put("only_ai", dashboard.getOnly_ai());
                        object.put("only_mi", dashboard.getOnly_mi());
                        object.put("only_server", dashboard.getOnly_server());
//                        if(dashboard.getSign() > 0){
//                                int number_download_sign = dashboard.getDowloadNew()*1000/dashboard.getSign();
//                                number_download_sign = number_download_sign > 1000 ? 1000 : number_download_sign;
//                                object.put("download_sign", number_download_sign);
//                                int number_app_sign = dashboard.getAppNew()*1000/dashboard.getSign();
//                                number_app_sign = number_app_sign > 1000 ? 1000 : number_app_sign;
//                                object.put("app_sign", number_app_sign);
//                        }else{
//                                object.put("download_sign", 0);
//                                object.put("app_sign", 0);
//                        }
//                        if(dashboard.getDowloadNew() > 0){
//                                int number_app_download = dashboard.getAppNew()*1000/dashboard.getDowloadNew();
//                                number_app_download = number_app_download > 1000 ? 1000 : number_app_download;
//                                object.put("app_download", number_app_download);
//                        }else{
//                                object.put("app_download", 0);
//                        }
                        String start = TimeTools.getDateTime(29);
                        String end = TimeTools.getDateTime(28);
                        JSONArray date = new JSONArray();
                        JSONArray date_data_all = new JSONArray();
                        JSONArray date_data_ai = new JSONArray();
                        JSONArray date_data_mi = new JSONArray();
                        JSONArray date_data_server = new JSONArray();
//                        JSONArray date_uv= new JSONArray();
                        JSONArray date_app_all= new JSONArray();
                        JSONArray date_app_ai= new JSONArray();
                        JSONArray date_app_mi= new JSONArray();
                        JSONArray date_app_server= new JSONArray();
                        
                        JSONArray date_only_ai = new JSONArray();
                        JSONArray date_only_mi = new JSONArray();
                        JSONArray date_only_server = new JSONArray();
//                        JSONArray date_download_new= new JSONArray();
                        
                        JSONArray date_data2 = new JSONArray();
                        
                        for(int i=0;i<30;i++){
                                date.add(start.substring(8, 10));
                                Dashboard dashboard2 = DashboardDaoImpl.getInstance().find(start, end);
                                if(dashboard2 == null){
                                        date_data_all.add(0);
                                        date_data_ai.add(0);
                                        date_data_mi.add(0);
                                        date_data_server.add(0);
                                        date_app_all.add(0);
                                        date_app_ai.add(0);
                                        date_app_mi.add(0);
                                        date_app_server.add(0);
                                        date_only_ai.add(0);
                                        date_only_mi.add(0);
                                        date_only_server.add(0);
                                }else{
                                        date_data_all.add(dashboard2.getData_all());
                                        date_data_ai.add(dashboard2.getData_ai());
                                        date_data_mi.add(dashboard2.getData_mi());
                                        date_data_server.add(dashboard2.getData_server());
                                        date_app_all.add(dashboard2.getApp_all());
                                        date_app_ai.add(dashboard2.getApp_ai());
                                        date_app_mi.add(dashboard2.getApp_mi());
                                        date_app_server.add(dashboard2.getApp_server());
                                        date_only_ai.add(dashboard2.getOnly_ai());
                                        date_only_mi.add(dashboard2.getOnly_mi());
                                        date_only_server.add(dashboard2.getOnly_server());
                                }
                                start = TimeTools.next(start, 1);
                                end = TimeTools.next(end, 1);
                        }
                        object.put("date", date);
                        object.put("date_data_all", date_data_all);
                        object.put("date_data_ai", date_data_ai);
                        object.put("date_data_mi", date_data_mi);
                        object.put("date_data_server", date_data_server);
                        object.put("date_app_all", date_app_all);
                        object.put("date_app_ai", date_app_ai);
                        object.put("date_app_mi", date_app_mi);
                        object.put("date_app_server", date_app_server);
                        object.put("date_only_ai", date_only_ai);
                        object.put("date_only_mi", date_only_mi);
                        object.put("date_only_server", date_only_server);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }
}
