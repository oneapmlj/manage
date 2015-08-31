package com.oneapm.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.oneapm.dao.info.impl.InfoDaoImpl;

public class KF5 {
	
        private static final String return_to="http://oneapm.kf5.com/apiv2/requests.json";
        public static String getUrl(Long id, String email, String name, String company, Long userId, String adminEmail, String phone){
                Long kfId = getKfId(id, email, name, company, userId, phone);
                String return_to = "https://oneapm.kf5.com/agent/#/user/"+kfId+"/tickets";
                long now=System.currentTimeMillis()/1000;
                        
                StringBuffer msg=new StringBuffer().append(adminEmail).append(now).append("ef1cd5c3205104eceff100d7c57738");
                MessageDigest md;
                String token = null;
                try {
                        md = MessageDigest.getInstance("MD5");
                        token = Sdk.byte2hex(md.digest(msg.toString().getBytes("utf-8")));
                } catch (Exception e) {
                }
                String url = "http://oneapm.kf5.com/user/remote?username="+adminEmail+"&time="+now
                                +"&token="+token+"&return_to="+return_to;
                return url;
        }
        
        public static String apiPost() {
                ByteArrayOutputStream bos = null;
                InputStream bis = null;
                byte[] buf = new byte[10240];
                String content = null;
                DefaultHttpClient client = null;
                try {
                        String return_to = "https://oneapm.kf5.com/apiv2/requests.json";
                        client = new DefaultHttpClient();
                        client.addRequestInterceptor(new HttpRequestInterceptor() {
                                public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                                        request.addHeader("Accept-Language", "zh-CN");
                                        request.addHeader("Accept-Encoding", "gzip");
                                        request.addHeader("Content-Type","application/json");
//                                        request.addHeader("Basic Auth","lijiang@oneapm.com/token:ef1cd5c3205104eceff100d7c57738");
                                        request.addHeader("Authorization", "Basic lijiang@oneapm.com/token:ef1cd5c3205104eceff100d7c57738");
                                }
                        });
                        
                        HttpPost request = new HttpPost(return_to);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                        request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post  
                        
                        HttpResponse response = client.execute(request);

                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                bis = response.getEntity().getContent();
                                Header[] gzip = response.getHeaders("Content-Encoding");

                                bos = new ByteArrayOutputStream();
                                int count;
                                while ((count = bis.read(buf)) != -1) {
                                        bos.write(buf, 0, count);
                                }
                                bis.close();
                                content = bos.toString();
                        } else {
                                System.out.println(response.getStatusLine().getStatusCode());
                        }
                        return content;
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (bis != null) {
                                try {
                                        client.close();
                                        bis.close();
                                } catch (Exception e) {
                                }
                        }
                }
                return null;
        }
        public static void main(String args[]){
                System.out.println(apiPost());
//                long now=System.currentTimeMillis()/1000;
//                StringBuffer msg=new StringBuffer().append("lijiang@oneapm.com").append(now).append("ef1cd5c3205104eceff100d7c57738");
//                MessageDigest md;
//                String token = null;
//                try {
//                        md = MessageDigest.getInstance("MD5");
//                        token = Sdk.byte2hex(md.digest(msg.toString().getBytes("utf-8")));
//                } catch (Exception e) {
//                }
//                String return_to = "https://oneapm.kf5.com/apiv2/requests.json";
//                String url = "http://oneapm.kf5.com/user/remote?username=lijiang@oneapm.com&time="+now
//                                +"&token="+token+"&return_to="+return_to;
//                System.out.println(url);
        }
      
 
        /*public static String callInterface(Long id, String email, String name, String company, Long userId, String adminEmail, String phone) throws IOException{
            Long kfId = getKfId(id, email, name, company, userId, phone);
           
            long now=System.currentTimeMillis()/1000;
                    
            StringBuffer msg=new StringBuffer().append(adminEmail).append(now).append("ef1cd5c3205104eceff100d7c57738");
            MessageDigest md;
            String token = null;
            try {
                    md = MessageDigest.getInstance("MD5");
                    token = Sdk.byte2hex(md.digest(msg.toString().getBytes("utf-8")));
            } catch (Exception e) {
            }
            String crul = "http://oneapm.kf5.com/user/remote?username="+adminEmail+"&time="+now
                                +"&token="+token+"&return_to="+return_to;
            ApiData getJson = new ApiData();
            getJson.httpURLConectionGETJsonData("http://oneapm.kf5.com/user/remote?username="+adminEmail+"&time="+now
                    +"&token="+token+"");
            getJson.httpURLConectionGETJsonData(return_to);
            return crul;
    }  */
      
        private static String converToBASE64(String s) {  
            if (s == null)  
                return null;  
            String encoder = new BASE64Encoder().encode(s.getBytes());  
           
                return encoder;  
              
        }
      

        private static String getFromBASE64(String s) {  
            if (s == null)  
                return null;  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                byte[] b = decoder.decodeBuffer(s);  
                return new String(b);  
            } catch (Exception e) {  
                return null;  
            }  
        }
      
        public static Long getKfId(Long id, String email, String name, String company, Long userId, String phone){
                Long kfId = InfoDaoImpl.getInstance().findKFById(id);
                if(kfId == null){
                        String result = Sdk.getResult("user/view", getUser(email));
                        JSONObject object = new JSONObject(result);
                        int err = object.getInt("err");
                        if(err == 0){
                                kfId = object.getJSONObject("datas").getLong("id");
                        }else{
                                Long organizationId = null;
                                result = Sdk.getResult("organization/view",getCompany(company));
                                object = new JSONObject(result);
                                if(object.getInt("err") == 0){
                                        organizationId = object.getJSONObject("datas").getLong("id");
                                }else{
                                        result = Sdk.getResult("organization/add",createCompany(company));
                                        object = new JSONObject(result);
                                        organizationId = object.getJSONObject("datas").getLong("id");
                                }
                                result = Sdk.getResult("user/add",createUser(email, name, organizationId, userId, phone));
                                object = new JSONObject(result);
                                err = object.getInt("err");
                                if(err == 0){
                                        kfId = object.getJSONObject("datas").getLong("id");
                                }
                        }
                        InfoDaoImpl.getInstance().updateKf(id, kfId);
                }
                return kfId;
        }
        
      
        

        public static String getUser(String email) {
                TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
                apiparamsMap.put("username", email);
                // 生成签名
                String sign = Sdk.md5Signature(apiparamsMap);
                apiparamsMap.put("sign", sign);
                StringBuilder param = new StringBuilder();
                for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, String> e = it.next();
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
                return param.toString().substring(1);
        }
        

        public static String createUser(String email, String name, Long organizationId, Long userId, String phone){
                TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
                apiparamsMap.put("username", email);
                apiparamsMap.put("name", name);
                apiparamsMap.put("organization_id", organizationId.toString());
                apiparamsMap.put("details", userId.toString());
                apiparamsMap.put("phone", phone);
                // 生成签名
                String sign = Sdk.md5Signature(apiparamsMap);
                apiparamsMap.put("sign", sign);
                StringBuilder param = new StringBuilder();
                for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, String> e = it.next();
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
                return param.toString().substring(1);
        }
        
        public static String getCompany(String company) {
                TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
                apiparamsMap.put("name", company);
                // 生成签名
                String sign = Sdk.md5Signature(apiparamsMap);
                apiparamsMap.put("sign", sign);
                StringBuilder param = new StringBuilder();
                for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, String> e = it.next();
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
                return param.toString().substring(1);
        }

        public static String createCompany(String company) {
                TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
                apiparamsMap.put("name", company);
                // 生成签名
                String sign = Sdk.md5Signature(apiparamsMap);
                apiparamsMap.put("sign", sign);
                StringBuilder param = new StringBuilder();
                for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<String, String> e = it.next();
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
                return param.toString().substring(1);
        }
}