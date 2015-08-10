package com.oneapm.service.count;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.oneapm.dao.count.impl.CountDaoImpl;
import com.oneapm.dto.Count.CountDto;



public class CountService {
	 protected static final Logger LOG = LoggerFactory.getLogger(CountService.class);
	 public static List<CountDto> findAllCountDto() {
         List<CountDto> countDtoList = null;
         try {
        	 countDtoList = CountDaoImpl.getInstance().getCountData();
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return countDtoList;
 }
	 public static void insertCountDto(CountDto dto) {
         String url = "http://localhost:8081/wh/apitest?date=2015-8-5";
         try {
        	 CountDaoImpl.getInstance().insertCount(dto, url);
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         
 }
}
