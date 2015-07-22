package com.oneapm.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

import com.oneapm.dao.info.impl.InfoDaoImpl;

public class KF5 {
        
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
        
        
        
        public static void main(String[ ]args){
//                System.out.println(getKfId(111L, "lijiang@oneapm.com", "李江", "测试公司", 1122999L));
//                String result = Sdk.getResult("user/view",getUser("lijiang@oneapm.com"));
//                System.out.println(result);
//                JSONObject object = new JSONObject(result);
//                System.out.println(object.getString("msg"));
                
//                String result = Sdk.getResult("user/add",createUser("lijiang@oneapm.com", "李江"));
//                System.out.println(result);
//              JSONObject object = new JSONObject(result);
//              System.out.println(object.getString("msg"));
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