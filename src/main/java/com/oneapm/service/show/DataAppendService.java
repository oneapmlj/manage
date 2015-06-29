package com.oneapm.service.show;

import com.oneapm.util.TimeTools;

public class DataAppendService {

	public static boolean isData(Long userId, String date, int days){
		if(userId == null)return false;
		if(date == null || date.equals("")){
			date = TimeTools.getDateTime(days);
		}
		
		return false;
	}
}
