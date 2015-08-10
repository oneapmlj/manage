package com.oneapm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiData {
	public List<String> httpURLConectionGET(String GET_URL) throws IOException {
		String string = null;
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		String[] email;
		url = new URL(GET_URL); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		List<String> str = new ArrayList<String>();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
			Pattern pattern = Pattern.compile("[\\w[.-]]+\\@[\\w[.-]]{2,}\\.com+");
			Matcher matcher = pattern.matcher(line);

			while (matcher.find()) {
				/* System.out.println(matcher.group()); */
				string = new String(matcher.group());
				
				str.add(string);

			}
			for (int i = 0; i < str.size(); i++) {
				System.out.println(str.get(i));
			}
		}
			br.close();
			connection.disconnect();// 断开连接

            
			return str;
			}

}

