package com.oneapm.util;

import java.util.List;

import org.json.simple.JSONObject;

import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.User;

public class OneTools {

        public final static String sp = ",";
        public final static String sp_email = ";";
        public final static String left = "(";
        public final static String right = ")";

        public static String listString(List<Long> ids) {
                StringBuilder builder = new StringBuilder();
                builder.append(left);
                for (Long id : ids) {
                        builder.append(id).append(sp);
                }
                builder.delete(builder.length() - 1, builder.length());
                builder.append(right);
                return builder.toString();
        }

        public static String listStringUser(List<User> users) {
                StringBuilder builder = new StringBuilder();
                builder.append(left);
                for (User user : users) {
                        if (builder.length() > 1) {
                                builder.append(sp).append(user.getUserId());
                        } else {
                                builder.append(user.getUserId());
                        }
                }
                builder.append(right);
                return builder.toString();
        }

        public static String listStringUserEmail(List<User> users) {
                StringBuilder builder = new StringBuilder();
                for (User user : users) {
                        builder.append(user.getEmail()).append(sp_email);
                }
                return builder.toString();
        }

        public static String listStringDownload(List<Download> downloads) {
                StringBuilder builder = new StringBuilder();
                builder.append(left);
                for (Download download : downloads) {
                        builder.append(download.getUserId()).append(sp);
                }
                builder.append(right);
                return builder.toString();
        }

        public static String listStringApp(List<App> apps) {
                StringBuilder builder = new StringBuilder();
                builder.append(left);
                for (App app : apps) {
                        builder.append(app.getUserId()).append(sp);
                }
                builder.append(right);
                return builder.toString();
        }

        @SuppressWarnings("unchecked")
        public static String getResult(int status, String msg) {
                JSONObject object = new JSONObject();
                object.put("status", status);
                object.put("msg", msg);
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String getResult(int status, List<String> args1, List<Object> args2) {
                JSONObject object = new JSONObject();
                object.put("status", status);
                if (args1 != null && args1.size() > 0) {
                        for (int i = 0; i < args1.size(); i++) {
                                object.put(args1.get(i), args2.get(i));
                        }
                }
                return object.toJSONString();
        }
        public static String getResult(int status, String args1, long args2) {
            JSONObject object = new JSONObject();
            object.put("status", status);
            object.put("email", args1);
            object.put("userId", args2);
            return object.toJSONString();
    }
        public static String getResult(int status, String args1, long args2, String args3, String args4, String args5) {
            JSONObject object = new JSONObject();
            object.put("status", status);
            object.put("email", args1);
            object.put("userId", args2);
            object.put("event", args3);
            object.put("date", args4);
            object.put("url", args5);
            return object.toJSONString();
    }
}
