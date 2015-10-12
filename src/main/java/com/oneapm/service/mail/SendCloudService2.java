package com.oneapm.service.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Buffer;

@SuppressWarnings("deprecation")
public class SendCloudService2 {

        private static final String URL_SEND = "https://sendcloud.sohu.com/webapi/mail.send.xml";
        private static final String ACCOUNT = "yunying";
        private static final String KEY = "ird5WXui2GxtCDj9";
        private static final String FROM = "OneAPM@push.oneapm.com";

        protected static final Logger LOG = LoggerFactory.getLogger(SendCloudService2.class);

        @SuppressWarnings({ "resource" })
        public static boolean sendMail(String to, String html, String title, Long lable) {
                try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpost = new HttpPost(URL_SEND);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
                        nvps.add(new BasicNameValuePair("api_user", ACCOUNT));
                        nvps.add(new BasicNameValuePair("api_key", KEY));
                        nvps.add(new BasicNameValuePair("from", FROM));
                        nvps.add(new BasicNameValuePair("to", to));
                        nvps.add(new BasicNameValuePair("subject", title));
                        if(lable != null && lable > 0L){
                                nvps.add(new BasicNameValuePair("label", lable.toString()));
                        }
                        nvps.add(new BasicNameValuePair("html", html));
                        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                        // 请求
                        HttpResponse response = httpclient.execute(httpost);
                        // 处理响应
                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                                return true;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public static boolean sendMail(String to, String html, String title, Long lable, String from) {
                try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpost = new HttpPost(URL_SEND);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
                        nvps.add(new BasicNameValuePair("api_user", ACCOUNT));
                        nvps.add(new BasicNameValuePair("api_key", KEY));
                        nvps.add(new BasicNameValuePair("from", from));
                        nvps.add(new BasicNameValuePair("to", to));
                        nvps.add(new BasicNameValuePair("subject", title));
                        if(lable != null && lable > 0L){
                                nvps.add(new BasicNameValuePair("label", lable.toString()));
                        }
                        nvps.add(new BasicNameValuePair("html", html));
                        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                        // 请求
                        HttpResponse response = httpclient.execute(httpost);
                        // 处理响应
                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                                return true;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public static void main(String[] args){
                try{
                        File file = new File("/home/abc/download/20151012CT.html");
                        FileReader fReader = new FileReader(file);
                        BufferedReader bReader = new BufferedReader(fReader);
                        String string = null;
                        StringBuilder builder = new StringBuilder();
                        while((string = bReader.readLine()) != null){
                                builder.append(string);
                        }
                        String html = builder.toString();
//                        bReader = null;
//                        fReader = null;
//                        List<String> emails = new ArrayList<String>();
//                        file = new File("/home/abc/download/user_20150925.csv");
//                        fReader = new FileReader(file);
//                        bReader = new BufferedReader(fReader);
//                        while((string = bReader.readLine()) != null){
//                                emails.add(string.trim());
//                        }
//                        int i = 0;
                        SendCloudService2.sendMail("3217269761@qq.com", html, "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("2697997703@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("2243657235@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("974843350@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("384592648@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("3085189536@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("1953190856@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("3186249002@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("2931493021@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        SendCloudService2.sendMail("1156012163@qq.com", html,  "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试
                        
                        
                        SendCloudService2.sendMail("974843350@qq.com", html, "别等到宕机了才想起我，Cloud Test 帮您 7*24 小时监控网站可用性", 0L);//qq测试普小娟
//                        sendMail("puxiaojuan@oneapm.com", html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                        sendMail("lijiang@oneapm.com", html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                        sendMail("huangdong@oneapm.com", html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                        sendMail("tangjingjing@oneapm.com", html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                        for(String email : emails){
//                                if(email != null){
//                                        i++;
//                                        sendMail(email, html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                                        if(i %100 == 0){
//                                                System.out.println(i);
//                                        }
//                                }
//                        }
//                        sendMail("puxiaojuan@oneapm.com", html, "迎国庆准备工作-OneAlert荐", 17104L, "OneAlert@push.oneapm.com");
//                        sendMail("lijiang@oneapm.com", html, "OneAPM 免费的业务系统性能监控平台", 0L);
                }catch(Exception e){
                        e.printStackTrace();
                }
        }

}
