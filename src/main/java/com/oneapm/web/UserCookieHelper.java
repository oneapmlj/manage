package com.oneapm.web;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class UserCookieHelper extends SupportAction{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 加密的密码
    public static final String PASSWORD = "xx";
    
    public static final String ID = "id";
    
    // 自动登录
    public static final String AUTO = "_auto";

    // 临时cookie
    public static final String OTHER_TEMP = "temp";

    // email
    public static final String USER_NAME = "username";

    // 验证email操作
    public static final String ACTIVE_EMAIL = "active_email";
    
    // 验证手机操作
    public static final String ACTIVE_MOBILE = "active_mobile";

    private static final String COOKIE_PATH = "/";

    private static final int MAX_AGE = 24 * 3600 * 30;
    
    private static final String COOKIE_DOMAIN = ".oneapm.com";

    private UserCookieHelper() {}

    /**
     * 自动登录Cookie
     * @param longNO
     * @param password
     * @param autologin
     * @param token
     */
    public static void saveUserCookie(HttpServletResponse response, String id, String username, String password, String token) {
        Cookie cookie1 = new Cookie(USER_NAME, username);
        cookie1.setMaxAge(MAX_AGE);
        cookie1.setPath(COOKIE_PATH);
        cookie1.setDomain(COOKIE_DOMAIN);
        Cookie cookie2 = new Cookie(PASSWORD, password);
        cookie2.setMaxAge(MAX_AGE);
        cookie2.setPath(COOKIE_PATH);
        cookie2.setDomain(COOKIE_DOMAIN);
        Cookie cookie4 = new Cookie(ID, id);
        cookie4.setMaxAge(MAX_AGE);
        cookie4.setPath(COOKIE_PATH);
        cookie4.setDomain(COOKIE_DOMAIN);
        
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie4);
    }
    

    /**
     * 清除所有cookie
     *
     */
    public static void removeUserCookies() {
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
        	if(cookie.getName().equals(USER_NAME) || cookie.getName().startsWith(OTHER_TEMP))continue;
            Cookie newCookie = new Cookie(cookie.getName(), null);
            newCookie.setMaxAge(0);
            newCookie.setPath(COOKIE_PATH);
            newCookie.setDomain(COOKIE_DOMAIN);
            ServletActionContext.getResponse().addCookie(newCookie);
        }
    }

    /**
     * 获得指定cookie
     * @param key
     * @param request
     * @return
     */
    public static String getCookieValue(String key, HttpServletRequest request){
    	  Cookie[] cookies = request.getCookies();
          if (cookies == null) {
              return null;
          }
          String returnValue = null;
          for (Cookie cookie : cookies) {
              if (key.equals(cookie.getName())) {
            	  returnValue = cookie.getValue();
            	  break;
              }
          }
          return returnValue;
    }
    
    /**
     * 设置指定cookie
     * @param key
     * @param value
     */
    public static void setCookieValue(String key, String value, int saveTime){
    	Cookie cookie = new Cookie(key, value);
    	cookie.setMaxAge(saveTime);
    	cookie.setPath(COOKIE_PATH);
    	cookie.setDomain(COOKIE_DOMAIN);
    	ServletActionContext.getResponse().addCookie(cookie);
    }
}
