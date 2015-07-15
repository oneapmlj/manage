package com.oneapm.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

 

public class Sdk {
		private static String _domain="http://oneapm.kf5.com";
		private static String _secretKey="ef1cd5c3205104eceff100d7c57738";


         public static String md5Signature(TreeMap<String, String> params ) {

                 String result = null;

                 StringBuffer orgin = getBeforeSign(params);

                 if (orgin == null)

                 return result;

                 orgin.append(_secretKey);

                 try {

                         MessageDigest md = MessageDigest.getInstance("MD5");

                         result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));

                 } catch (Exception e) {

                         throw new java.lang.RuntimeException("sign error !");

                 }

             return result;

         }

         /**

         * ������ת�ַ�

         */

         public static String byte2hex(byte[] b) {

                 StringBuffer hs = new StringBuffer();

                 String stmp = "";

                 for (int n = 0; n < b.length; n++) {

                         stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

                         if (stmp.length() == 1)

                                 hs.append("0").append(stmp);

                         else

                                 hs.append(stmp);

                 }

                 return hs.toString().toLowerCase();

         }

         /**

         * ��Ӳ���ķ�װ����

         */

         private static StringBuffer getBeforeSign(TreeMap<String, String> params) {
        	    StringBuffer sb=new StringBuffer();
        	    
                 if (params == null)

                         return null;

                 Map<String, String> treeMap = new TreeMap<String, String>();

                 treeMap.putAll(params);

                 Iterator<String> iter = treeMap.keySet().iterator();

                 while (iter.hasNext()) {

                         String name = (String) iter.next();

                         sb.append(name).append("=").append(params.get(name)).append("&");

                 }
               
                 return sb;

         }

         /**���ӵ�TOP����������ȡ���*/

         public static String getResult(String method, String content) {

                 URL url = null;
                 String urlStr=_domain+"/api/v1/"+method;

                 HttpURLConnection connection = null;

 

                 try {

                         url = new URL(urlStr);

                         connection = (HttpURLConnection) url.openConnection();

                         connection.setDoOutput(true);

                         connection.setDoInput(true);

                         connection.setRequestMethod("POST");

                         connection.setUseCaches(false);

                         connection.connect();

 

                         DataOutputStream out = new DataOutputStream(connection.getOutputStream());

                         out.write(content.getBytes("utf-8"));

                         out.flush();

                         out.close();

 

                         BufferedReader reader =

                         new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                         StringBuffer buffer = new StringBuffer();

                         String line = "";

                         while ((line = reader.readLine()) != null) {

                                 buffer.append(line);

                         }

                         reader.close();

                         return buffer.toString();

                 } catch (IOException e) {

                         e.printStackTrace();

                 } finally {

                         if (connection != null) {

                                 connection.disconnect();

                         }

                 }

                 return null;

         }

}
