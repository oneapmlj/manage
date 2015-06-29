package com.oneapm.util.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Student {
        
        /**
         * 导出
         * 
         * @param file
         *                csv文件(路径+文件名)，csv文件不存在会自动创建
         * @param dataList
         *                数据
         * @return
         */
        public static boolean exportCsv() {
                boolean isSucess = false;

                FileOutputStream out = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                try {
                        File file = new File("/home/abc/abc.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("a,b,c,d").append("\r");
                        bw.append("a1,b1,c1,d1").append("\r");
                        bw.append("a2,b2,c2,d2").append("\r");
                        isSucess = true;
                } catch (Exception e) {
                        isSucess = false;
                } finally {
                        if (bw != null) {
                                try {
                                        bw.close();
                                        bw = null;
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (osw != null) {
                                try {
                                        osw.close();
                                        osw = null;
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (out != null) {
                                try {
                                        out.close();
                                        out = null;
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }

                return isSucess;
        }
        
        public static void main(String[] args){
                exportCsv();
        }
}
