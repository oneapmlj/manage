package com.oneapm.service.count;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.oneapm.dao.count.impl.CountDaoImpl;
import com.oneapm.dao.count.impl.UserIdDaoImpl;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.util.ApiData;
import com.oneapm.util.OneTools;



public class CountService {
	 protected static final Logger LOG = LoggerFactory.getLogger(CountService.class);
	 private static final String GET_URL = "http://localhost:8081/wh/apitest?date=2015-8-5";
	 public static List<CountDto> findAllCountDto() {
         List<CountDto> countDtoList = null;
         try {
        	 countDtoList = CountDaoImpl.getInstance().getCountData();
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return countDtoList;
 }
	 public static String findByEmail(CountDto dto) throws IOException {
         long userId = 0l;
         List<String> key = new ArrayList<String>();
     	 List<Object> value = new ArrayList<Object>();
     	 String jsonString = null;
     	ApiData data = new ApiData();
     	List<String> emailList = data.httpURLConectionGET(GET_URL);
     	for(int i = 0; i < emailList.size(); i++){
     	if(emailList.get(i) !=null && emailList.get(i) != ""){
     		CountDto dto1 = new CountDto();    
     		dto1.setEmail(emailList.get(i));
     	    userId = UserIdDaoImpl.getInstance().findByEmail(dto.getEmail());
     		key.add(emailList.get(i));
     		value.add(userId);
     		if(userId!=0l){
     		jsonString = OneTools.getResult(1, key, value);	
     		
     		}else{
     		
     		jsonString = OneTools.getResult(0, "未找到用户id");
     		}
     	}
     }
		return jsonString;
		
 }
	 public static void insertCountDto(CountDto dto) {
         
         try {
        	 CountDaoImpl.getInstance().insertCount(dto);
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         
 }
}
