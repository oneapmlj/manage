package com.oneapm.web;

public interface SessionKeys {
	
	String ID = "id";
   /**
    * 密码
    */
   String PASSWORD = "xx";
   
   String USER_NAME = "username";
   /**
    * 权限等级
    */
   String GRADE = "grade";

   String NAME = "name";
   
   
   /**
    * 用户验证
    * {@link Integer}
    */
   String STATUS = "status";
   /**
    * 返回消息
    */
   String BACK_MESSAGE = "backmessage";
   
   String LOGIN_ERROR = "login_error";
   
}