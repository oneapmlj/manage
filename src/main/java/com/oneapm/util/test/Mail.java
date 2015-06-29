package com.oneapm.util.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Mail {

        public static void main(String[] args) throws IOException{
                File file = new File("/home/abc/mail/webtransaction_20150518_.txt");
                FileReader fr = new FileReader(file);
                BufferedReader br= new BufferedReader(fr);
                String s = null;
                StringBuilder builder = new StringBuilder();
                br.readLine();
                int i = 0;
                while((s=br.readLine()) != null){
                        System.out.println(s);
                        i++;
                        if(i > 2){
                                break;
                        }
//                        String[] strings = s.split("\t");
//                        if(strings[0].equals("7296")){
//                                System.out.println(s);
//                                Apdex apdex = new Apdex(Long.parseLong(strings[0]), Double.parseDouble(strings[2]),
//                                              Double.parseDouble(strings[9]),
//                                              Double.parseDouble(strings[3]),
//                                              Double.parseDouble(strings[4]), 
//                                              Double.parseDouble(strings[5]),
//                                              Double.parseDouble(strings[6]),
//                                              Double.parseDouble(strings[7]));
//                                
//                                break;
//                        }
//                        Apdex apdex = new Apdex(Long.parseLong(strings[0]), Double.parseDouble(strings[2]),
//                                        Double.parseDouble(strings[9]),
//                                        Double.parseDouble(strings[3]),
//                                        Double.parseDouble(strings[4]), 
//                                        Double.parseDouble(strings[5]),
//                                        Double.parseDouble(strings[6]),
//                                        Double.parseDouble(strings[7]));
//                        if(apdex.getSum3() > 0){
//                                System.out.println(apdex.getUserId());
//                        }
//                        if(apdex.getUserId().equals(7043L)){
//                                System.out.println(apdex.getSum1());
//                                System.out.println(apdex.getSum2());
//                                System.out.println(apdex.getSum3());
//                                System.out.println(apdex.getSum4());
//                                System.out.println(apdex.getSum5());
//                                System.out.println(apdex.getSum6());
//                                System.out.println(apdex.getApdexscore());
//                                break;
//                        }
                }
                
        }
}
class Apdex{
        private Long userId;
        private double sum1;
        private double sum2;
        private double sum3;
        private double sum4;
        private double sum5;
        private double sum6;
        private double apdexscore;
        private double apdext;
        
        public Apdex(Long userId, double sum1, double sum2, double sum3, double sum4, double sum5, double sum6, double apdexscore){
                setUserId(userId);
                setSum1(sum1);
                setSum2(sum2);
                setSum3(sum3);
                setSum4(sum4);
                setSum5(sum5);
                setSum6(sum6);
                setApdexscore(apdexscore);
        }
        public Long getUserId() {
                return userId;
        }
        public void setUserId(Long userId) {
                this.userId = userId;
        }
        public double getSum1() {
                return sum1;
        }
        public void setSum1(double sum1) {
                this.sum1 = sum1;
        }
        public double getSum2() {
                return sum2;
        }
        public void setSum2(double sum2) {
                this.sum2 = sum2;
        }
        public double getSum3() {
                return sum3;
        }
        public void setSum3(double sum3) {
                this.sum3 = sum3;
        }
        public double getSum5() {
                return sum5;
        }
        public void setSum5(double sum5) {
                this.sum5 = sum5;
        }
        public double getSum4() {
                return sum4;
        }
        public void setSum4(double sum4) {
                this.sum4 = sum4;
        }
        public double getSum6() {
                return sum6;
        }
        public void setSum6(double sum6) {
                this.sum6 = sum6;
        }
        public double getApdexscore() {
                return apdexscore;
        }
        public void setApdexscore(double apdexscore) {
                this.apdexscore = apdexscore;
        }
        public double getApdext() {
                return apdext;
        }
        public void setApdext(double apdext) {
                this.apdext = apdext;
        }
        
        
        
        
}
