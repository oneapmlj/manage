/*
  This example depends on the following jar files
  commons-codec.jar from http://commons.apache.org/proper/commons-codec/
  json-smart.jar from https://code.google.com/p/json-smart/
  nimbus-jose-jwt.jar from https://bitbucket.org/nimbusds/nimbus-jose-jwt/overview
  
  Because of this [1] issue in nimbus-jose-jwt, please make sure to use a 
  version >= 2.13.1 as UDesk expects seconds in the iat parameter
  [1]: https://bitbucket.org/nimbusds/nimbus-jose-jwt/issue/35/jwtclaimsset-milliseconds-vs-seconds-issue
 */

package com.oneapm.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.oneapm.dao.info.impl.InfoDaoImpl;

public class JWT {
        private static final String SHARED_KEY = "6e8d9c47a1fb6d1550547ebea668876c";

        public static String jwt(String name, String email) {
                if (email == null || name == null) {
                        return null;
                }
                JWTClaimsSet jwtClaims = new JWTClaimsSet();
                jwtClaims.setIssueTime(new Date());
                jwtClaims.setJWTID(UUID.randomUUID().toString());
                jwtClaims.setCustomClaim("name", name);
                jwtClaims.setCustomClaim("email", email);

                JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
                JWSObject jwsObject = new JWSObject(header, new Payload(jwtClaims.toJSONObject()));

                JWSSigner signer = new MACSigner(SHARED_KEY.getBytes());

                try {
                        jwsObject.sign(signer);
                } catch (com.nimbusds.jose.JOSEException e) {
                        System.err.println("Error signing JWT: " + e.getMessage());
                        return null;
                }
                String jwt = jwsObject.serialize();
                if(jwt == null)return null;
                String redirectUrl = "http://oneapm.udesk.cn/users/auth/jwt/callback?jwt=" + jwt;
                return redirectUrl;
        }
        
        public static String getUrl(String email, String name, String return_to){
                try{
                        JWTClaimsSet jwtClaims = new JWTClaimsSet();
                        jwtClaims.setIssueTime(new Date());
                        jwtClaims.setJWTID(UUID.randomUUID().toString());
                        jwtClaims.setCustomClaim("name", name);
                        jwtClaims.setCustomClaim("email", email);

                        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
                        JWSObject jwsObject = new JWSObject(header, new Payload(jwtClaims.toJSONObject()));

                        JWSSigner signer = new MACSigner(SHARED_KEY.getBytes());

                        try {
                                jwsObject.sign(signer);
                        } catch (com.nimbusds.jose.JOSEException e) {
                                System.err.println("Error signing JWT: " + e.getMessage());
                                return null;
                        }
                        String jwt = jwsObject.serialize();
                        String redirectUrl = "http://oneapm.udesk.cn/users/auth/jwt/callback?jwt=" + jwt+"&return_to="+return_to;
                        return redirectUrl;
                }catch(Exception e){
                        throw new java.lang.RuntimeException("sign error !");
                }
        }
        
        public static String getToken(String email){
                if (email == null) {
                        return null;
                }
                String token = null;
                try{
                        MessageDigest md;
                        md = MessageDigest.getInstance("MD5");
                        String msg = "email="+email+"&"+SHARED_KEY;
                        token = byte2hex(md.digest(msg.toString().getBytes("utf-8")));
                }catch(Exception e){
                }
                return token;
        }
        
        public static Long findUdeskId(String email, Long id){
                Long udeskId = null;
                try {
                        String result = "";
                        BufferedReader in = null;
                        try {
                                String sign = getToken(email);
                                if(sign == null)return null;
                                URL realUrl = new URL("http://oneapm.udesk.cn/api/v1/customers/find_or_create_customer.json?email="+email+"&sign=" + sign);
                                // 打开和URL之间的连接
                                URLConnection connection = realUrl.openConnection();
                                // 设置通用的请求属性
                                connection.setRequestProperty("accept", "*/*");
                                connection.setRequestProperty("connection", "Keep-Alive");
                                connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                                // 建立实际的连接
                                connection.connect();
                                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String line;
                                while ((line = in.readLine()) != null) {
                                        result += line;
                                }
                                JSONObject object = new JSONObject(result);
                                udeskId = Long.parseLong(object.getJSONObject("customer").get("id").toString());
                                if (udeskId != null) {
                                        InfoDaoImpl.getInstance().updateUdesk(id, udeskId);
                                }
                        } catch (Exception e) {
                                System.out.println("发送GET请求出现异常！" + e);
                                e.printStackTrace();
                        } finally {
                                try {
                                        if (in != null) {
                                                in.close();
                                        }
                                } catch (Exception e2) {
                                        e2.printStackTrace();
                                }
                        }
                } catch (Exception e) {
                }
                return udeskId;
        }

        private static String encode(String url) {
                try {
                        return URLEncoder.encode(url, "UTF-8");
                } catch (UnsupportedEncodingException ignore) {
                        System.err.println("UTF-8 is not supported!");
                        return url;
                }
        }
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
}