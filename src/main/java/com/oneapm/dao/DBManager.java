package com.oneapm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	
	private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    public static void init(String driver1, String url1, String username1,
            String password1) {
        driver = driver1;
        url = url1;
        username = username1;
        password = password1;

    }
    
    public static Connection getConncection() throws SQLException, Exception {
    	 driver = "com.mysql.jdbc.Driver";
         url = "jdbc:mysql://100.98.70.86:3306/managerdb";
    	 //url = "jdbc:mysql://localhost:3306/webhook";
    	 //url = "jdbc:mysql://119.29.28.190:3306/webhook";
       username = "manage_saas";
       password = "3acr89Mc0bc2a";
    	// username = "root";
    	// password = "qinh_webhook123";
    	// password = "";
        Class.forName(driver);
        return DriverManager.getConnection(url,username,password);
    }

}
