package com.oneapm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.web.SupportAction;

public class FileSystem {

        protected static final Logger LOG = LoggerFactory.getLogger(FileSystem.class);

        public static void write(String path, String content) throws IOException {
                File file = new File(path);
                if (!file.exists()) {
                        file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file, false);
                out.write(content.getBytes("UTF-8"));
                out.close();
        }

        public static String read(String content) throws IOException {
                String path = SupportAction.systemApplication.getAttribute("filesystem") + content;
                // String path = "/data/filesystem/" + content;
                File file = new File(path);
                if (!file.exists() || file.isDirectory()) {
                        LOG.info(path + "----模板读取失败");
                        return null;
                }
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String s = null;
                StringBuilder builder = new StringBuilder();
                while ((s = br.readLine()) != null) {
                        builder.append(s);
                }
                br.close();
                // FileInputStream fis=new FileInputStream(file);
                // byte[] buf = new byte[1024];
                // StringBuffer sb=new StringBuffer();
                // while((fis.read(buf))!=-1){
                // sb.append(new String(buf));
                // buf=new byte[1024];
                // }
                // fis.close();
                String re = new String(builder.toString().getBytes(), "utf-8");
                return re;
        }
}
