package com.oneapm.service.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.mail.AppReport;
import com.oneapm.dto.mail.MailReport;

public class MailReportService {

        protected static final Logger LOG = LoggerFactory.getLogger(MailReportService.class);

        public static List<MailReport> reportList(){
                Map<Long, MailReport> reports = new HashMap<Long, MailReport>();
                try{
                        File file = new File("/home/abc/mail/apdex_20150518_7.txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader br= new BufferedReader(fr);
                        String s = null;
                        br.readLine();
                        while((s=br.readLine()) != null){
                                try{
                                        String[] strings = s.split("\t");
                                        Long userId = Long.parseLong(strings[0]);
                                        float apdex = Float.parseFloat(strings[9]);
                                        Long appId = Long.parseLong(strings[1]);
                                        AppReport report = new AppReport(userId, appId, 0, 0, apdex);
                                        if(reports.containsKey(userId)){
                                                reports.get(userId).getApps().put(appId, report);
                                        }else{
                                                Map<Long, AppReport> apps = new HashMap<Long, AppReport>();
                                                apps.put(appId, report);
                                                MailReport mailReport = new MailReport(userId, apps);
                                                reports.put(userId, mailReport);
                                        }
                                }catch(Exception e){
                                        
                                }
                        }
                        fr.close();
                        br.close();
                        file = new File("/home/abc/mail/webtransaction_20150518_7.txt");
                        fr = new FileReader(file);
                        br= new BufferedReader(fr);
                        br.readLine();
                        while((s=br.readLine()) != null){
                                try{
                                        String[] strings = s.split("\t");
                                        Long userId = Long.parseLong(strings[0]);
                                        Long appId = Long.parseLong(strings[1]);
                                        float responsetime = Float.parseFloat(strings[9]);
                                        float cpm = Float.parseFloat(strings[10]);
                                        if(reports.containsKey(userId)){
                                                if(reports.get(userId).getApps().containsKey(appId)){
                                                        reports.get(userId).getApps().get(appId).setResponsetime(responsetime);
                                                        reports.get(userId).getApps().get(appId).setCpm(cpm);
                                                }
                                        }
                                }catch(Exception e){
                                        
                                }
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                List<MailReport> mailReports = new ArrayList<MailReport>();
                try{
                      Iterator iter = reports.entrySet().iterator();
                      while (iter.hasNext()) {
                              Map.Entry<Long, MailReport> entry = (Map.Entry<Long, MailReport>) iter.next();
                              MailReport val = entry.getValue();
                              Iterator iter1 = val.getApps().entrySet().iterator();
                              while (iter1.hasNext()) {
                                      Map.Entry<Long, AppReport> entry1 = (Map.Entry<Long, AppReport>) iter1.next();
                                      AppReport val1 = entry1.getValue();
                                      if(val1.getApdex() >0 && val1.getCpm() > 0 && val1.getResponsetime() > 0){
                                              mailReports.add(val);
                                              break;
                                      }
                              }
                      }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                System.out.println(reports.size());
                System.out.println(mailReports.size());
                return mailReports;
        }
        
        public static void sendReport(){
                List<MailReport> mailReports = reportList();
                
        }
        
        public static void main(String[] args){
                reportList();
//                HashMap<Long, Apdex> apdexs = new HashMap<Long, Apdex>();
//                List<Apdex> aps = new ArrayList<Apdex>();
//                try{
//                        File file = new File("/home/abc/mail/apdex_20150518_7.txt");
//                        FileReader fr = new FileReader(file);
//                        BufferedReader br= new BufferedReader(fr);
//                        String s = null;
//                        br.readLine();
//                        while((s=br.readLine()) != null){
//                                try{
//                                        String[] strings = s.split("\t");
//                                        Long userId = Long.parseLong(strings[0]);
//                                        float apdex = Float.parseFloat(strings[9]);
//                                        Long appId = Long.parseLong(strings[1]);
//                                        if(!apdexs.containsKey(appId)){
//                                                Apdex apdex2 = new Apdex();
//                                                apdex2.setUserId(userId);
//                                                apdex2.setAppId(appId);
//                                                apdex2.setApdex(apdex);
//                                                apdexs.put(appId, apdex2);
//                                        }
//                                }catch(Exception e){
//                                        LOG.error(e.getMessage(), e);
//                                }
//                        }
//                        fr.close();
//                        br.close();
//                        File file1 = new File("/home/abc/mail/webtransaction_20150518_7.txt");
//                        FileReader fr1 = new FileReader(file1);
//                        BufferedReader br1= new BufferedReader(fr1);
//                        br1.readLine();
//                        while((s=br1.readLine()) != null){
//                                try{
//                                        String[] strings = s.split("\t");
//                                        Long appId = Long.parseLong(strings[1]);
//                                        float responsetime = Float.parseFloat(strings[9]);
//                                        float cpm = Float.parseFloat(strings[10]);
//                                        if(apdexs.containsKey(appId)){
//                                                Apdex apdex = apdexs.get(appId);
//                                                apdex.setCpm(cpm);
//                                                apdex.setResponsetime(responsetime);
//                                                apdexs.put(appId, apdex);
//                                        }
//                                }catch(Exception e){
//                                        LOG.error(e.getMessage(), e);
//                                }
//                        }
//                        fr1.close();
//                        br1.close();
//                        Iterator iter = apdexs.entrySet().iterator();
//                        while (iter.hasNext()) {
//                                Map.Entry<Long, Apdex> entry = (Map.Entry<Long, Apdex>) iter.next();
//                                Apdex val = entry.getValue();
//                                /*if(val.getCpm() > 0 || val.getResponsetime() > 0){
//                                        System.out.println(entry.getKey());
//                                }*/
//                                if(val.getApdex() >0 && val.getCpm() > 0){
//                                        aps.add(val);
//                                }
//                        }
//                }catch(Exception e){
//                        LOG.error(e.getMessage(), e);
//                }
//                System.out.println(aps.size());
//                System.out.println(apdexs.size());
        }
}
class Apdex{
        private Long userId;
        private Long appId;
        private float apdex;
        private float cpm;
        private float responsetime;
        public Long getUserId() {
                return userId;
        }
        public void setUserId(Long userId) {
                this.userId = userId;
        }
        public Long getAppId() {
                return appId;
        }
        public void setAppId(Long appId) {
                this.appId = appId;
        }
        public float getApdex() {
                return apdex;
        }
        public void setApdex(float apdex) {
                this.apdex = apdex;
        }
        public float getCpm() {
                return cpm;
        }
        public void setCpm(float cpm) {
                this.cpm = cpm;
        }
        public float getResponsetime() {
                return responsetime;
        }
        public void setResponsetime(float responsetime) {
                this.responsetime = responsetime;
        }
}
