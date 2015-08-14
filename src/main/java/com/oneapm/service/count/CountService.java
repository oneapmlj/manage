package com.oneapm.service.count;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.util.JSON;
import com.oneapm.dao.count.impl.CountDaoImpl;
import com.oneapm.dao.count.impl.UserIdDaoImpl;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.util.ApiData;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;



public class CountService {
	 protected static final Logger LOG = LoggerFactory.getLogger(CountService.class);
	 private static  StringBuffer GET_URL_NOPARAM = new StringBuffer("http://localhost:8081/wh/apitest?");
	 private static  StringBuffer JSON_URL = new StringBuffer("http://localhost:8081/wh/sendcloud?");
	 public static List<CountDto> findAllCountDto() {
         List<CountDto> countDtoList = null;
         try {
        	 countDtoList = CountDaoImpl.getInstance().getCountData();
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return countDtoList;
 }
	 public static List<String> findByEmail(String date) throws IOException {
         long userId = 0l;
       /*  List<String> key = new ArrayList<String>();
     	 List<Object> value = new ArrayList<Object>();*/
     	 String jsonString = null;
     	 List<String> jsonList = new ArrayList<>();
     	ApiData data = new ApiData();
     	int start = GET_URL_NOPARAM.indexOf("?")+1;
     	/*System.out.println(start);*/
     	StringBuffer sb = GET_URL_NOPARAM.delete(start, GET_URL_NOPARAM.length());
     	String GET_URL = sb.append("date="+date).toString();
     	List<String> emailList = data.httpURLConectionGET(GET_URL);
     	for(int i = 0; i < emailList.size(); i++){
     	if(emailList.get(i) !=null && emailList.get(i) != ""){
     		CountDto dto1 = new CountDto();    
     		dto1.setEmail(emailList.get(i));
     	    userId = UserIdDaoImpl.getInstance().findByEmail(dto1.getEmail());
     		/*key.add(emailList.get(i));
     		value.add(userId);*/
     		if(userId!=0l){
     		jsonString = OneTools.getResult(1, emailList.get(i), userId);
     		
     		}else{     		
     		jsonString = OneTools.getResult(0, "not exists userId");
     		
     		}
     	}
     	jsonList.add(jsonString);
    /* 	key.clear();
     	value.clear();*/
     }
 		return jsonList;
		
 }
	 public static void insertCountDto(CountDto dto) {
       
         try {
        	 TimeTools t = new TimeTools();
        	 String strDate = t.format(new Date());
        	 dto.setCreate_time(strDate);
        	 CountDaoImpl.getInstance().insertCount(dto);
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         
 }
	 public static void insertCountDtoList(List<CountDto> dtolist) {
	     
         try {
        	 TimeTools t = new TimeTools();
        	 String strDate =  "yyyy-mm-dd".format(dtolist.get(0).getCreate_time());
        	 List<String> jsonList = CountService.getJson(strDate);
        	
        	 for(int i = 0; i < jsonList.size(); i++){
                 JSONObject jo = (JSONObject)JSONValue.parse(jsonList.get(i)); 
                 if((long)jo.get("status")==1l){
                 dtolist.get(i).setUserId((long)jo.get("userId"));
                 dtolist.get(i).setEmail(jo.get("email").toString());
                 dtolist.get(i).setCreate_time(strDate);
                 dtolist.get(i).setEvent((String)jo.get("event").toString());
                 
        	 }
        	 }
        	  	
        	String flag = CountDaoImpl.getInstance().insertCountList(dtolist);
        	System.out.println(flag);
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         
 }
	 public static List<String> getJson(String date) throws IOException {
        long userId = 0l;
		CountDto dto1 = new CountDto();
     	ApiData data = new ApiData();
     	String jsonString = null;
    	List<String> jsonList = new ArrayList<>();
     	int start = JSON_URL.indexOf("?")+1;
     	/*System.out.println(start);*/
     	StringBuffer sb = JSON_URL.delete(start, JSON_URL.length());
     	String GET_URL = sb.append("date="+date).toString();
     	List<String> ja = data.httpURLConectionGETJsonData(GET_URL);
       
         for(int i = 0; i < ja.size(); i++){  
        	 JSONObject o=(JSONObject) JSONValue.parse(ja.get(i));
        	      	 
        	 dto1.setEmail((String)o.get("email"));
        	 userId = UserIdDaoImpl.getInstance().findByEmail(dto1.getEmail());
        	 if(userId!=0l){
          		jsonString = OneTools.getResult(1, (String)o.get("email"),
          				userId, (String)o.get("event"), 
          				(String)o.get("date"), 
          				(String)o.get("url"));
          		
          		}else{     		
          		jsonString = OneTools.getResult(0, "not exists userId");
          		
          		}
        	 jsonList.add(jsonString);
         }  
 		return jsonList;
		
 }
}
