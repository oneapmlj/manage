package com.oneapm.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class JiraApi {
	final static String baseUrl = "http://localhost:8081/transfer/jira";
	public static String getIssueApi(String key, String username, String password) throws IOException {
		String string = null;
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		String uri = baseUrl+"?key="+key+"&username="+username+"&password="+password;
		url = new URL(uri); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		List<String> str = new ArrayList<String>();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接

            
			return sb.toString();
			}
	public static String createIssueApi(String GET_URL,String projectName,String assigneeName,
			String issueType, String description, String summary,
			String userName, String password) throws IOException {
		String string = null;
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		String uri = baseUrl+"?projectname="+projectName+"&assigneename="+assigneeName+"&"
				+ "issuetype="+issueType+"&description="+description+"&summary="+summary+"&"
						+ "username="+userName+"&password="+password;
		url = new URL(uri); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		List<String> str = new ArrayList<String>();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接

            
			return sb.toString();
			}
/*	public static void main(String[] args) {
		String str;
		try {
			str = getIssueApi("USER-150", "lijiang", "19881225");
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}*/

}
