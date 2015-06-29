package com.oneapm.service.account;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.Account.Admin;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

@SuppressWarnings("deprecation")
public class PayService {

        protected static final Logger LOG = LoggerFactory.getLogger(PayService.class);

        public static String URL = "https://user.oneapm.com/account/user/crm.do?";
        
        public static String payadd(Long userId, String endTime, String mark, Admin admin, int level){
                if(pay(userId, endTime, mark, admin, level)){
                        return OneTools.getResult(1, "成功");
                }
                return OneTools.getResult(0, "失败");
        }
        
        public static boolean pay(Long userId, String endTime, String mark, Admin admin, int level){
                DefaultHttpClient client= new DefaultHttpClient();
                client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
                client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
                String url = URL;
                if(level <= 0 || level > 40 || level%10 != 0){
                        return false;
                }
                try {
                        Date date = TimeTools.formatTime.parse(endTime);
                        url += "userId="+userId+"&level="+level+"&expireTime="+date.getTime();
                } catch (ParseException e1) {
                        e1.printStackTrace();
                }
                HttpPost request = new HttpPost(url);
//                ByteArrayOutputStream bos = null;
                InputStream bis = null;
//                byte[] buf = new byte[10240];
                try {
                        HttpResponse response = client.execute(request);
                        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
//                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                                bis = response.getEntity().getContent();
//                                Header[] gzip = response.getHeaders("Content-Encoding");
//
//                                bos = new ByteArrayOutputStream();
//                                int count;
//                                while ((count = bis.read(buf)) != -1) {
//                                        bos.write(buf, 0, count);
//                                }
//                                bis.close();
//
//                                if (gzip.length > 0 && gzip[0].getValue().equalsIgnoreCase("gzip")) {
//                                        GZIPInputStream gzin = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray()));
//                                        StringBuffer sb = new StringBuffer();
//                                        int size;
//                                        while ((size = gzin.read(buf)) != -1) {
//                                                sb.append(new String(buf, 0, size, "utf-8"));
//                                        }
//                                        gzin.close();
//                                        bos.close();
//
//                                } 
//                        } 
                }catch(Exception e){
                        e.printStackTrace();
                }finally {
                        if (bis != null) {
                                try {
                                        client.close();
                                        bis.close();
                                } catch (Exception e) {
                                }
                        }
                }
                return false;
        }
        
}
