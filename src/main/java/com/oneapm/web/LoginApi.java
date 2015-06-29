package com.oneapm.web;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginApi {

        private static final String url = "http://oneapm.kf5.com/user/remote";
        private static final String key = "ef1cd5c3205104eceff100d7c57738";

        public static String loginApi(String email, String return_to) {
                try {
                        long now = System.currentTimeMillis() / 1000;
                        MessageDigest md;
                        md = MessageDigest.getInstance("MD5");
                        StringBuffer msg = new StringBuffer().append(email).append(now).append(key);
                        String token = byte2hex(md.digest(msg.toString().getBytes("utf-8")));
                        String url2 = url + "?username=" + email + "&time=" + now + "&token=" + token + "&return_to=" + return_to;
                        return url2;
                } catch (Exception e) {
                        throw new java.lang.RuntimeException("sign error !");
                }
        }

        public static String getSign(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
                MessageDigest md;
                md = MessageDigest.getInstance("MD5");
                StringBuffer msg = new StringBuffer().append("username=" + email).append("&" + key);
                String sign = byte2hex(md.digest(msg.toString().getBytes("utf-8")));
                return sign;
        }

        public static String getGongdan(int currentpage, int pagesize) throws NoSuchAlgorithmException, UnsupportedEncodingException {
                MessageDigest md;
                md = MessageDigest.getInstance("MD5");
                StringBuffer msg = new StringBuffer().append("currentpage=" + currentpage).append("&pagesize=" + pagesize).append("&" + key);
                String sign = byte2hex(md.digest(msg.toString().getBytes("utf-8")));
                return sign;
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
