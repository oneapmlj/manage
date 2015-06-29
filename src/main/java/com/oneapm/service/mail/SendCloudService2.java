package com.oneapm.service.mail;

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

@SuppressWarnings("deprecation")
public class SendCloudService2 {

        private static final String URL_SEND = "https://sendcloud.sohu.com/webapi/mail.send.xml";
        private static final String ACCOUNT = "yunying";
        private static final String KEY = "txTnvxQ7lgLefc1i";
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
        
        public static void main(String[] args){
                System.out.println(1);
                sendMail("lijiang1225@qq.com", "测试<a href='http://www.oneapm.com'>1111</a>", "测试邮件", 0L);
                System.out.println(2);
//                sendMail("lijiang@oneapm.com", "测试邮件<a href='http://www.oneapm.com?from=1'>1111</a>", "测试", 10759L);
        }

}
