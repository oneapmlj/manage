package com.oneapm.util.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.service.mail.SendCloudService;

public class AliyunMail {

        public static void send(String html, String to){
                SendCloudService.sendMail("lijiang@oneapm.com", to, html, "OneAPM");
        }
        
        public static void main(String[] args) throws IOException{
                String emial ="lijiang@oneapm.com";
                String html = "";
                File file = new File("/home/abc/workspace/manage/WebRoot/WEB-INF/pages/test/20150521/mail.html");
              FileReader fr = new FileReader(file);
              BufferedReader br= new BufferedReader(fr);
              String s = null;
              StringBuilder builder = new StringBuilder();
              while((s=br.readLine()) != null){
                      builder.append(s);
              }
              SendCloudService.sendMail("lijiang@oneapm.com", emial, builder.toString(), "OneAPM");
              
//                File file = new File("/home/abc/download/aliyun.html");
//                FileReader fr = new FileReader(file);
//                BufferedReader br= new BufferedReader(fr);
//                String s = null;
//                StringBuilder builder = new StringBuilder();
//                while((s=br.readLine()) != null){
//                        builder.append(s);
//                }
//                File file2 = new File("/home/abc/download/aliyunusers.txt");
//                FileReader fr2 = new FileReader(file2);
//                BufferedReader br2= new BufferedReader(fr2);
//                String s2 = null;
//                List<String> emails = new ArrayList<String>();
//                while((s2 = br2.readLine()) != null){
//                        emails.add(s2.trim());
//                }
//                for(String email : emails){
//                        System.out.println(email);
//                        send(builder.toString(), email);
//                }
//                br.close();
//                fr.close();
        }
}
