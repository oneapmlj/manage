package com.oneapm.service.info;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dao.opt.impl.GongdanDaoImpl;
import com.oneapm.dto.Gongdan;
import com.oneapm.dto.info.Info;
import com.oneapm.util.TimeTools;
import com.oneapm.web.LoginApi;

public class KFService {

        protected static final Logger LOG = LoggerFactory.getLogger(KFService.class);

        public static Long findKFId(String email, Long id) {
                Long kfId = null;
                try {
                        final String base_url = "http://oneapm.kf5.com/api/v1/user/view";
                        String sign = LoginApi.getSign(email);
                        String url = base_url + "?username=" + email + "&sign=" + sign;
                        String result = sendGet(url);
                        JSONObject object = new JSONObject(result);
                        kfId = Long.parseLong(object.getJSONObject("datas").get("id").toString());
                        if (kfId != null) {
                                InfoDaoImpl.getInstance().updateKf(id, kfId);
                        }
                        return kfId;
                } catch (Exception e) {
                }
                try {
                        final String base_url = "http://oneapm.kf5.com/api/v1/user/add";
                        String sign = LoginApi.getSign(email);
                        String url = base_url + "?username=" + email + "&sign=" + sign;
                        String result = sendGet(url);
                        JSONObject object = new JSONObject(result);
                        kfId = Long.parseLong(object.getJSONObject("datas").get("id").toString());
                        if (kfId != null) {
                                InfoDaoImpl.getInstance().updateKf(id, kfId);
                        }
                        return kfId;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static List<Gongdan> findByUserId(Long userId) {
                return GongdanDaoImpl.getInstance().findByUserId(userId);
        }

        public static void GongdanUpdate() {
                boolean go = false;
                int currentpage = 1;
                List<Gongdan> gongdans = new ArrayList<Gongdan>();
                do {
                        try {
                                go = false;
                                String result = findGongdanList(currentpage, 100);
                                org.json.JSONObject object = new org.json.JSONObject(result);
                                if (object.getInt("err") == 0) {
                                        JSONArray datas = object.getJSONArray("datas");
                                        for (int i = 0; i < datas.length(); i++) {
                                                try {
                                                        JSONObject obj = datas.getJSONObject(i);
                                                        Long id = obj.getLong("id");
                                                        String title = obj.getString("title");
                                                        String email = obj.getString("requester");
                                                        String dueTime = obj.get("due_date").toString();
                                                        int status = obj.getInt("status");
                                                        Long assigneeId = null;
                                                        try {
                                                                assigneeId = obj.getLong("assignee_id");
                                                        } catch (Exception e) {
                                                        }
                                                        String createTime = TimeTools.getStrTime(obj.getString("created"));
                                                        String assigneTime = null;
                                                        try {
                                                                assigneTime = TimeTools.getStrTime(obj.getString("assigneed"));
                                                        } catch (Exception e) {
                                                        }
                                                        String resolvedTime = null;
                                                        try {
                                                                resolvedTime = TimeTools.getStrTime(obj.getString("resolved"));
                                                        } catch (Exception e) {
                                                        }
                                                        String closedTime = null;
                                                        try {
                                                                closedTime = TimeTools.getStrTime(obj.getString("closed"));
                                                        } catch (Exception e) {
                                                        }
                                                        String source = obj.getString("source");
                                                        Gongdan gongdan = new Gongdan(id, title, email, dueTime, status, assigneeId, createTime, assigneTime, resolvedTime, closedTime, source);
                                                        Info info = InfoService.findByEmail(gongdan.getEmail());
                                                        if (info != null) {
                                                                gongdan.setUserId(info.getUserId());
                                                        }
                                                        gongdans.add(gongdan);
                                                } catch (Exception e) {
                                                        LOG.error(e.getMessage(), e);
                                                }
                                        }
                                        int count = object.getInt("count");
                                        if (count / 100 > currentpage) {
                                                currentpage++;
                                                go = true;
                                        }
                                }
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                } while (go);
                if (gongdans.size() > 0) {
                        for (Gongdan gongdan : gongdans) {
                                Gongdan gong = GongdanDaoImpl.getInstance().findById(gongdan.getId());
                                if (gong == null) {
                                        GongdanDaoImpl.getInstance().insert(gongdan);
                                } else {
                                        GongdanDaoImpl.getInstance().update(gongdan);
                                }
                        }
                }
        }

        public static String findGongdanList(int currentpage, int pagesize) {
                final String base_url = "http://oneapm.kf5.com/api/v1/ticket/list";
                String sign;
                try {
                        sign = LoginApi.getGongdan(currentpage, pagesize);
                        String url = base_url + "?currentpage=" + currentpage + "&pagesize=" + pagesize + "&sign=" + sign;
                        String result = sendGet(url);
                        return result;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static String sendGet(String url) {
                String result = "";
                BufferedReader in = null;
                try {
                        URL realUrl = new URL(url);
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
                return result;
        }
}
