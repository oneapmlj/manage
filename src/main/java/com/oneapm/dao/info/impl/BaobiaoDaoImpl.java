package com.oneapm.dao.info.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.info.Baobiao;

public class BaobiaoDaoImpl extends DaoImplBase<Baobiao>{
	protected static final Logger LOG = LoggerFactory.getLogger(BaobiaoDaoImpl.class);
	protected final String TABLE_NAME_DAY = "baobiao_day";
	protected final String TABLE_NAME_WEEK = "baobiao_week";
	protected final String TABLE_NAME_MONTH = "baobiao_month";
	
	static {
		Instance = new BaobiaoDaoImpl();
	}
	
	private final static BaobiaoDaoImpl Instance;
	
	public static BaobiaoDaoImpl getInstance(){
		return Instance;
	}
	
	public List<Baobiao> findByDay(){
	    List<Baobiao> baobiaos = null;
	    try{
	        DBObject object = new BasicDBObject();
	        object.put("type", 1);
	        DBObject sort = new BasicDBObject();
	        sort.put("data_time", -1);
	        DBCursor cursor = getDBCollection(TABLE_NAME_DAY).find(object).sort(sort).limit(30);
	        baobiaos = new ArrayList<Baobiao>();
	        while(cursor.hasNext()){
	            baobiaos.add(getBaobiaoFromObject(cursor.next()));
	        }
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return baobiaos;
	}
	
	public List<Baobiao> findByWeek(){
        List<Baobiao> baobiaos = null;
        try{
            DBObject object = new BasicDBObject();
            object.put("type", 2);
            DBObject sort = new BasicDBObject();
            sort.put("data_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME_DAY).find(object).sort(sort).limit(20);
            baobiaos = new ArrayList<Baobiao>();
            while(cursor.hasNext()){
                baobiaos.add(getBaobiaoFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return baobiaos;
    }
	
	public List<Baobiao> findByMonth(){
        List<Baobiao> baobiaos = null;
        try{
            DBObject object = new BasicDBObject();
            object.put("type", 3);
            DBObject sort = new BasicDBObject();
            sort.put("data_time", -1);
            DBCursor cursor = getDBCollection(TABLE_NAME_DAY).find(object).sort(sort).limit(12);
            baobiaos = new ArrayList<Baobiao>();
            while(cursor.hasNext()){
                baobiaos.add(getBaobiaoFromObject(cursor.next()));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        return baobiaos;
    }
	private Baobiao getBaobiaoFromObject(DBObject object){
	    Baobiao baobiao = null;
	    try{
	        int sign = Integer.parseInt(object.get("sign").toString().trim());
	        int sign_up = Integer.parseInt(object.get("sign_up").toString().trim());
	        //登录（增量/总数）
	        int login = Integer.parseInt(object.get("login").toString().trim());
	        int login_up = Integer.parseInt(object.get("login_up").toString().trim());
	        //非当天注册的登录（增量/总数）
	        int login_first = Integer.parseInt(object.get("login_first").toString().trim());
	        int login_first_up = Integer.parseInt(object.get("login_first_up").toString().trim());
	        //下载（增量/总数）
	        int download = Integer.parseInt(object.get("download").toString().trim());
	        int download_up = Integer.parseInt(object.get("download_up").toString().trim());
	        //第一次下载（增量/总数）
	        int download_first = Integer.parseInt(object.get("download_first").toString().trim());
	        int download_first_up = Integer.parseInt(object.get("download_first_up").toString().trim());
	        //添加应用（增量/总数）
	        int app = Integer.parseInt(object.get("app").toString().trim());
	        int app_up = Integer.parseInt(object.get("app_up").toString().trim());
	        //第一次添加应用（增量/总数）
	        int app_first = Integer.parseInt(object.get("app_first").toString().trim());
	        int app_first_up = Integer.parseInt(object.get("app_first_up").toString().trim());
	        //有数据用户（增量/总数）
	        int data = Integer.parseInt(object.get("data").toString().trim());
	        int data_up = Integer.parseInt(object.get("data_up").toString().trim());
	        //第一次有数据用户（增量/总数）
	        int data_first = Integer.parseInt(object.get("data_first").toString().trim());
	        int data_first_up = Integer.parseInt(object.get("data_first_up").toString().trim());
	        //有数据应用（增量/总数）
	        int data_app = Integer.parseInt(object.get("data_app").toString().trim());
	        int data_app_up = Integer.parseInt(object.get("data_app_up").toString().trim());
	        //第一次有数据应用（增量/总数）
	        int data_app_first = Integer.parseInt(object.get("data_app_first").toString().trim());
	        int data_app_first_up = Integer.parseInt(object.get("data_app_first_up").toString().trim());
	        //企业数（增量/总数）
	        int value = Integer.parseInt(object.get("value").toString().trim());
	        int value_up = Integer.parseInt(object.get("value_up").toString().trim());
	        //新标记企业（增量/总数）
	        int value_first = Integer.parseInt(object.get("value_first").toString().trim());
	        int value_first_up = Integer.parseInt(object.get("value_first_up").toString().trim());
	        //开发者数（增量/总数）
	        int normal = Integer.parseInt(object.get("normal").toString().trim());
	        int normal_up = Integer.parseInt(object.get("normal_up").toString().trim());
	        //新标记开发者（增量/总数）
	        int normal_first = Integer.parseInt(object.get("normal_first").toString().trim());
	        int normal_first_up = Integer.parseInt(object.get("normal_first_up").toString().trim());
	        //未定义数（增量/总数）
	        int unknow = Integer.parseInt(object.get("sign").toString().trim());
	        int unknow_up = Integer.parseInt(object.get("sign").toString().trim());
	        //新标记未定义（增量/总数）
	        int unknow_first = Integer.parseInt(object.get("unknow_first").toString().trim());
	        int unknow_first_up = Integer.parseInt(object.get("unknow_first_up").toString().trim());
	        //关闭数（增量/总数）
	        int close = Integer.parseInt(object.get("close").toString().trim());
	        int close_up = Integer.parseInt(object.get("close_up").toString().trim());
	        //新标记关闭（增量/总数）
	        int close_first = Integer.parseInt(object.get("close_first").toString().trim());
	        int close_first_up = Integer.parseInt(object.get("close_first_up").toString().trim());
//	        //销售重点数（增量/总数）
	        int saleimport = Integer.parseInt(object.get("saleimport").toString().trim());
//	        int saleimport_up = Integer.parseInt(object.get("saleimport_up").toString().trim());
//	        //销售新重点数（增量/总数）
//	        int saleimport_first = Integer.parseInt(object.get("saleimport_first").toString().trim());
//	        int saleimport_first_up = Integer.parseInt(object.get("saleimport_first_up").toString().trim());
//	        //销售普通数（增量/总数）
	        int salenormal = Integer.parseInt(object.get("salenormal").toString().trim());
//	        int salenormal_up = Integer.parseInt(object.get("salenormal_up").toString().trim());
//	        //销售新普通数（增量/总数）
//	        int salenormal_first = Integer.parseInt(object.get("salenormal_first").toString().trim());
//	        int salenormal_first_up = Integer.parseInt(object.get("salenormal_first_up").toString().trim());
	        //添加记录（增量/总数）
	        int record = Integer.parseInt(object.get("record").toString().trim());
	        int record_up = Integer.parseInt(object.get("record_up").toString().trim());
	        //分配到销售（增量/总数）
	        int tosale = Integer.parseInt(object.get("tosale").toString().trim());
	        int tosale_up = Integer.parseInt(object.get("tosale_up").toString().trim());
	        //新分配到销售（增量/总数）
	        int tosale_first = Integer.parseInt(object.get("tosale_first").toString().trim());
	        int tosale_first_up = Integer.parseInt(object.get("tosale_first_up").toString().trim());
	        //交流（增量/总数）
            int tojiao = Integer.parseInt(object.get("tojiao").toString().trim());
            int tojiao_up = Integer.parseInt(object.get("tojiao_up").toString().trim());
            //测试（增量/总数）
            int toce = Integer.parseInt(object.get("toce").toString().trim());
            int toce_up = Integer.parseInt(object.get("toce_up").toString().trim());
	        //进入商务流程（增量/总数）
	        int tobu = Integer.parseInt(object.get("tobu").toString().trim());
	        int tobu_up = Integer.parseInt(object.get("tobu_up").toString().trim());
	        //完成（增量/总数）
	        int finish = Integer.parseInt(object.get("finish").toString().trim());
	        int finish_up = Integer.parseInt(object.get("finish_up").toString().trim());
	        //成交（增量/总数）
	        int success = Integer.parseInt(object.get("success").toString().trim());
	        int success_up = Integer.parseInt(object.get("success_up").toString().trim());
	        //失败（增量/总数）
	        int fail = Integer.parseInt(object.get("fail").toString().trim());
	        int fail_up = Integer.parseInt(object.get("fail_up").toString().trim());
	        baobiao = new Baobiao(sign,sign_up,login,login_up,login_first,login_first_up,
	                download, download_up, download_first, download_first_up, app,
	                app_up,app_first,app_first_up,data,data_up,data_first,
	                data_first_up, data_app, data_app_up,data_app_first,data_app_first_up,
	                value, value_up,value_first,value_first_up,normal,normal_up,
	                normal_first,normal_first_up, unknow, unknow_up,unknow_first, unknow_first_up,
	                close, close_up,close_first, close_first_up, record, record_up,tosale,tosale_up,
	                tosale_first, tosale_first_up, tojiao, tojiao_up,  toce,  toce_up,  tobu,
	                tobu_up, finish,finish_up, success,success_up,fail,fail_up, saleimport, salenormal);
//	        baobiao = new Baobiao(sign, sign_up, login, login_up, login_first, login_first_up, download, 
//	                download_up, download_first, download_first_up, app, app_up, app_first, app_first_up, 
//	                data, data_up, data_first, data_first_up, data_app, data_app_up, data_app_first, 
//	                data_app_first_up, value, value_up, value_first, value_first_up, normal, normal_up, 
//	                normal_first, normal_first_up, unknow, unknow_up, unknow_first, unknow_first_up, close, 
//	                close_up, close_first, close_first_up,  
//	                record, record_up, tosale, tosale_up, tosale_first, tosale_first_up, tobu, tobu_up, finish, 
//	                finish_up, success, success_up, fail, fail_up);
	        String dataTime = object.get("data_time").toString();
	        baobiao.setDataTime(dataTime);
	    }catch(Exception e){
	        LOG.error(e.getMessage(), e);
	    }
	    return baobiao;
	}
}
