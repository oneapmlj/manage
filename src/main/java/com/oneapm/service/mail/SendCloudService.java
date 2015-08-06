package com.oneapm.service.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.util.exception.PramaException;

@SuppressWarnings("deprecation")
public class SendCloudService {

        private static final String URL_SEND = "https://sendcloud.sohu.com/webapi/mail.send.xml";
        private static final String ACCOUNT = "postmaster@mailchufa2.sendcloud.org";
        private static final String KEY = "ird5WXui2GxtCDj9";
        private static final String FROM = "OneAPM@push.oneapm.com";

        protected static final Logger LOG = LoggerFactory.getLogger(SendCloudService.class);

        @SuppressWarnings({ "resource" })
        public static boolean sendMail(String from, String to, String html, String title) {
                try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpost = new HttpPost(URL_SEND);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
                        nvps.add(new BasicNameValuePair("api_user", ACCOUNT));
                        nvps.add(new BasicNameValuePair("api_key", KEY));
                        nvps.add(new BasicNameValuePair("from", FROM));
                        nvps.add(new BasicNameValuePair("to", to));
                        nvps.add(new BasicNameValuePair("subject", title));
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

        public static boolean sendCloud(String to, String html, String title, String from) throws PramaException {
                try {
                        if (from == null || from.length() <= 1) {
                                from = FROM;
                        }
                        if (html == null)
                                return false;
                        @SuppressWarnings("resource")
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpost = new HttpPost(URL_SEND);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
                        nvps.add(new BasicNameValuePair("api_user", ACCOUNT));
                        nvps.add(new BasicNameValuePair("api_key", KEY));
                        nvps.add(new BasicNameValuePair("from", from));
                        nvps.add(new BasicNameValuePair("to", to));
                        nvps.add(new BasicNameValuePair("subject", title));
                        nvps.add(new BasicNameValuePair("html", html));
                        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                        // 请求
                        HttpResponse response = httpclient.execute(httpost);
                        // 处理响应
                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                                return true;
                        }
                } catch (UnsupportedEncodingException e) {
                        LOG.error(e.getMessage(), e);
                } catch (ParseException e) {
                        LOG.error(e.getMessage(), e);
                } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

}
