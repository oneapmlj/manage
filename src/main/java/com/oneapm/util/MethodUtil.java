package com.oneapm.util;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodUtil {

        private static final Logger logger = LoggerFactory.getLogger(MethodUtil.class);
        @SuppressWarnings("unchecked")
        private static final List<String> INVALIDATEEMAIL = Collections.EMPTY_LIST;

        // Arrays
        // .asList(new String[] { "163.com", "vip.163.com", "126.com",
        // "qq.com", "vip.qq.com", "foxmail.com", "gmail.com",
        // "sohu.com", "tom.com", "yahoo.com.cn", "yahoo.cn",
        // "vip.sina.com", "sina.com.cn", "yeah.net", "21cn.com",
        // "hotmail.com", "sogou.com", "188.com", "139.com", "189.cn",
        // "wo.com.cn", "139.com" });

        public static List<String> getInvalidateEmail() {
                return INVALIDATEEMAIL;
        }

        public static boolean isValidateEmail(String email) {
                try {
                        String emailSuffix = email.split("@")[1];
                        if (getInvalidateEmail().contains(emailSuffix)) {
                                return false;
                        }
                        return true;
                } catch (Exception e) {
                        logger.warn("error", e);
                        return false;
                }
        }

        public static String getMD5(String str, String encoding, int no_Lower_Upper) {
                if (null == encoding)
                        encoding = "utf-8";
                StringBuffer sb = new StringBuffer();
                try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] array = md.digest(str.getBytes(encoding));
                        for (int i = 0; i < array.length; i++) {
                                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                if (no_Lower_Upper == 0) {
                        return sb.toString();
                }
                if (no_Lower_Upper == 1) {
                        return sb.toString().toLowerCase();
                }
                if (no_Lower_Upper == 2) {
                        return sb.toString().toUpperCase();
                }
                return null;
        }

        public static String getRandomString(int length) {
                String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
                Random random = new Random();
                StringBuffer sf = new StringBuffer();
                for (int i = 0; i < length; i++) {
                        int number = random.nextInt(62);// 0~61
                        sf.append(str.charAt(number));

                }
                sf.append(System.currentTimeMillis());
                return MethodUtil.getMD5(sf.toString(), "UTF-8", 0);
                // return sf.toString();
        }

}
