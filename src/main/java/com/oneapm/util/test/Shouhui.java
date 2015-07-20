package com.oneapm.util.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.info.impl.DataDaoImpl;
import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dao.info.impl.LoginDaoImpl;
import com.oneapm.dao.info.impl.TianjiaDaoImpl;
import com.oneapm.dao.info.impl.XiazaiDaoImpl;
import com.oneapm.dao.opt.impl.AddDaoImpl;
import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.lable.Lable;
import com.oneapm.dto.tag.Category;
import com.oneapm.dto.tag.From;
import com.oneapm.dto.tag.Fuwuqi;
import com.oneapm.dto.tag.Person;
import com.oneapm.dto.tag.Province;
import com.oneapm.dto.tag.Rongzi;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.AppService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.lable.LableService;
import com.oneapm.util.TimeTools;
class Laiyuan{
        private String name;
        private String from;
        private int sign;
        private int download;
        private int app;
        private int data;
        private int data_app;
        private double data_sign;
        private double data_app_data;
        
        public Laiyuan(String name, String from, int sign, int download, int app, int data, int data_app, double data_sign, double data_app_data){
                setName(name);
                setFrom(from);
                setSign(sign);
                setDownload(download);
                setApp(app);
                setData(data);
                setData_app(data_app);
                setData_sign(data_sign);
                setData_app_data(data_app_data);
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getFrom() {
                return from;
        }
        public void setFrom(String from) {
                this.from = from;
        }
        public int getSign() {
                return sign;
        }
        public void setSign(int sign) {
                this.sign = sign;
        }
        public int getDownload() {
                return download;
        }
        public void setDownload(int download) {
                this.download = download;
        }
        public int getApp() {
                return app;
        }
        public void setApp(int app) {
                this.app = app;
        }
        public int getData() {
                return data;
        }
        public void setData(int data) {
                this.data = data;
        }
        public int getData_app() {
                return data_app;
        }
        public void setData_app(int data_app) {
                this.data_app = data_app;
        }
        public double getData_sign() {
                return data_sign;
        }
        public void setData_sign(double data_sign) {
                this.data_sign = data_sign;
        }
        public double getData_app_data() {
                return data_app_data;
        }
        public void setData_app_data(double data_app_data) {
                this.data_app_data = data_app_data;
        }
}
public class Shouhui {
        protected static final Logger LOG = LoggerFactory.getLogger(Shouhui.class);
        /**
         * 掉数据列表
         */
        public static void diaoshujuliebiao(){
                String start = TimeTools.getDateTime(7);
                String end = TimeTools.getDateTime(0);
                String end1 = TimeTools.getDateTime(-1);
                List<Info> infos = new ArrayList<Info>();
                List<Aplication> aplications = AppDataDaoImpl.getInstance().findByTime(start, end);
                List<Aplication> aplications2 = AppDataDaoImpl.getInstance().findByTime(end, end1);
                for(Aplication aplication : aplications){
                        boolean in = false;
                        for(Aplication aplication2 : aplications2){
                                if(aplication.getUserId().equals(aplication2.getUserId())){
                                        in = true;
                                        break;
                                }
                        }
                        if(!in){
                                infos.add(InfoService.findByUserId(aplication.getUserId()));
                        }
                }
                FileOutputStream out = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                try{
                        File file = new File("/data/filesystem/report/diaoshuju.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("UserId,公司,地域,融资,分类,销售,运营,客户管理").append("\r");
                        bw.newLine();
                        for(Info info: infos){
                                bw.append(info.getUserId()+",");
                                if(info.getProject() != null){
                                        bw.append(info.getProject()+",");
                                }else{
                                        bw.append(info.getCompany()+",");
                                }
                                if(info.getTag() != null){
                                        if(info.getTag().getProvince() > 0){
                                                bw.append(Province.getName(info.getTag().getProvince())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getRongzi() > 0){
                                                bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getCategory() > 0){
                                                bw.append(Category.getName(info.getTag().getCategory())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }else{
                                        bw.append("未知,未知,未知,");
                                }
                                if(info.getSale() != null && info.getSale() > 0){
                                        bw.append(AccountService.findById(info.getSale()).getName()+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getSupport() != null && info.getSupport() > 0){
                                        bw.append(AccountService.findById(info.getSupport()).getName()+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getPreSale() != null && info.getPreSale() > 0){
                                        bw.append(AccountService.findById(info.getPreSale()).getName());
                                }else{
                                        bw.append("无");
                                }
                                bw.append("\r");
                                bw.newLine();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }finally {
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
        }
        /**
         * 跟踪报表
         */
        public static void genzongbaobiao(){
                int before = 3;
                String start = TimeTools.getDateTime(7+before);
                String end = TimeTools.getDateTime(0+before);
                String end1 = TimeTools.getDateTime(-1+before);
                List<Laiyuan> laiyuans = new ArrayList<Laiyuan>();
                List<Info> infos = new ArrayList<Info>();
                addLaiyuan(0L, "", start, end, end1, laiyuans, "", infos);
                shengcheng("qingcloud", start, end, laiyuans, end1, "青云", infos);
                FileOutputStream out = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                try{
                        File file = new File("/data/filesystem/report/from.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("来源,参数,注册,下载,应用,活跃,活跃应用,活跃/注册（%）,活跃应用/活跃（%）").append("\r");
                        bw.newLine();
                        for(Laiyuan laiyuan : laiyuans){
                                bw.append(laiyuan.getName()+",").append(laiyuan.getFrom()+",").append(laiyuan.getSign()+",").append(laiyuan.getDownload()+",")
                                .append(laiyuan.getApp()+",").append(laiyuan.getData()+",").append(laiyuan.getData_app()+",")
                                .append(laiyuan.getData_sign()+",").append(laiyuan.getData_app_data()+"").append("\r");
                                bw.newLine();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }finally {
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
                try{
                        File file = new File("/data/filesystem/report/from_list.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("userId,公司,地域,分类,融资,来源,注册时间").append("\r");
                        bw.newLine();
                        for(Info info : infos){
                                bw.append(info.getUserId()+",");
                                if(info.getProject() != null && info.getProject().trim().length() > 0){
                                        bw.append(info.getProject()+",");
                                }else{
                                        bw.append(info.getCompany()+",");
                                }
                                if(info.getTag() != null){
                                        if(info.getTag().getProvince() > 0){
                                                bw.append(Province.getName(info.getTag().getProvince())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getCategory() > 0){
                                                bw.append(Category.getName(info.getTag().getCategory())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getRongzi() > 0){
                                                bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }else{
                                        bw.append("未知,").append("未知,").append("未知,");
                                }
                                bw.append(info.getFrom()+",").append(info.getCreateTime()).append("\r");
                                bw.newLine();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }finally {
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
        }
        
        public static void addLaiyuan(Long fatherId, String from, String start, String end, String end1, List<Laiyuan> laiyuans, String name, List<Info> infos){
                List<Lable> lables = LableService.findChild(fatherId);
                for(Lable lable : lables){
                        String name1 = "";
                        LOG.info(name);
                        if(name.trim().length() <=0){
                                name1 = lable.getName();
                        }else{
                                name1 = name+"|"+lable.getName();
                        }
                        from = lable.getFrom();
                        shengcheng(from, start, end, laiyuans, end1, name1, infos);
                        addLaiyuan(lable.getId(), from, start, end, end1, laiyuans, name1, infos);
                }
        }
        
        public static void shengcheng(String from, String start, String end, List<Laiyuan> laiyuans, String end1, String name, List<Info> infos){
                List<Info> infos2 = InfoDaoImpl.getInstance().search_from(from.trim(), start, end);
                for(int i=0;i<infos2.size();i++){
                        if(!infos2.get(i).getComming().startsWith(from)){
                                infos2.remove(i);
                                i--;
                        }
                }
                List<Info> infos3 = InfoDaoImpl.getInstance().search_from_one(from.trim(), start, end);
                for(int i=0;i<infos3.size();i++){
                        if(!infos3.get(i).getComming().trim().equals(from.trim())){
                                infos3.remove(i);
                                i--;
                        }
                }
                int sign1 = infos2.size();
                int download1 = 0;
                int app1 = 0;
                int data1 = 0;
                int data_app1 = 0;
                for(Info info : infos2){
                        if(DownDaoImpl.getInstance().findByUserId(info.getUserId()).size() > 0){
                                download1 ++;
                        }
                        if(TianjiaDaoImpl.getInstance().findById(info.getUserId())){
                              app1++;  
                        }
//                        if(AppDataDaoImpl.getInstance().existByTimeAndUserId(start, end, info.getUserId())){
//                                data1++;
//                        }
//                        List<Aplication> aplications = AppDataDaoImpl.getInstance().findByTimeAndUserId(end, end1, info.getUserId());
                        List<Aplication> aplications = AppDataDaoImpl.getInstance().findByTimeAndUserId(TimeTools.getDateTime(3), end1, info.getUserId());
                        Set<Long> set = new HashSet<Long>();
                        Set<Long> setUser = new HashSet<Long>();
                        for(Aplication aplication : aplications){
                                set.add(aplication.getAppId());
                                setUser.add(aplication.getUserId());
                        }
                        data1 += setUser.size();
                        data_app1 += set.size();
                }
                double data_sign1 = 0;
                double data_app_data1 = 0;
                if(sign1 > 0){
                        data_sign1 = (double) data1*100/sign1;
                }
                if(data1 > 0){
                        data_app_data1 = (double) data_app1*100/data1;
                }
                Laiyuan laiyuan = new Laiyuan(name, from, sign1, download1, app1, data1, data_app1, data_sign1, data_app_data1);
                for(Info info : infos3){
                        info.setFrom(name);
                        InfoService.initInfo(info);
                        InfoService.initTag(info);
                        infos.add(info);
                }
                laiyuans.add(laiyuan);
        }
        public static void xinxifugai(){
                String start = TimeTools.getDateTime(17);
                String end = TimeTools.getDateTime(10);
                String dataTime = TimeTools.getDateTime(0);
                
                String start_sign = TimeTools.getDateTime(10);
                String end_sing = TimeTools.getDateTime(3);
                try{
                        List<Aplication> aplications = AppDataDaoImpl.getInstance().findByAgent(0, start, end);
                        Set<Long> ids = new HashSet<Long>();
                        for(Aplication aplication : aplications){
                                ids.add(aplication.getUserId());
                        }
                        List<Info> datas = new ArrayList<Info>();
                        List<Info> signs = InfoDaoImpl.getInstance().countSign(start_sign, end_sing);
                        for(Long id : ids){
                                datas.add(InfoService.findByUserId(id));
                        }
                        FileOutputStream out = null;
                        OutputStreamWriter osw = null;
                        BufferedWriter bw = null;
                        /*try{
                                File file = new File("/data/filesystem/report/shujufugai.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("序号,UserId,公司,注册时间,登录时间,地域,融资,分类,规模,平台,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,java,php,node,python,donet,ruby,android,ios,browser,server").append("\r");
                                bw.newLine();
                                int i =1;
                                for(Info info : datas){
                                        bw.append(i+",").append(info.getUserId()+",");
                                        if(info.getProject() != null && info.getProject().trim().length() > 0){
                                                bw.append(info.getProject()+",");
                                        }else{
                                                bw.append(info.getCompany()+",");
                                        }
                                        bw.append(info.getCreateTime()+",").append(info.getLoginTime()+",");
                                        if(info.getTag() != null){
                                                if(info.getTag().getProvince() > 0){
                                                        bw.append(Province.getName(info.getTag().getProvince())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getRongzi() > 0){
                                                        bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getCategory() > 0){
                                                        bw.append(Category.getName(info.getTag().getCategory())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getPerson() > 0){
                                                        bw.append(Person.getName(info.getTag().getPerson())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFuwuqi() > 0){
                                                        bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                        }else{
                                                bw.append("未知,未知,未知,未知,未知,");
                                        }
                                        String start1 = TimeTools.getDateTime(17);
                                        String end1 = TimeTools.getDateTime(16);
                                        long endLong = TimeTools.formatTime.parse(dataTime).getTime();
                                        while(TimeTools.formatTime.parse(end1).getTime() <= endLong){
                                                if(AppDataDaoImpl.getInstance().existByTimeAndUserId(start1, end1, info.getUserId())){
                                                        bw.append("1,");
                                                }else{
                                                        bw.append("0,");
                                                }
                                                start1 = end1;
                                                end1 = TimeTools.next(end1, 1);
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 1)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 2)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 3)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 4)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 5)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 6)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 7)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 8)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 9)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        if(AppDataDaoImpl.getInstance().exist(info.getUserId(), start, 10)){
                                                bw.append("1,");
                                        }else{
                                                bw.append("0,");
                                        }
                                        bw.newLine();
                                        i++;
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        }*/
                        List<Info> signs_geren = new ArrayList<Info>();
                        out = null;
                        osw = null;
                        bw = null;
                        try{
                                File file = new File("/data/filesystem/report/fugai.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("序号,UserId,公司,注册时间,登录时间,地域,融资,分类,规模,平台,来源").append("\r");
                                bw.newLine();
                                int i =1;
                                for(Info info : signs){
                                        info = InfoService.findByUserId(info.getUserId());
                                        if(info.getTag() != null && info.getTag().getMetric() == 3){
                                                signs_geren.add(info);
                                                continue;
                                        }
                                        bw.append(i+",").append(info.getUserId()+",");
                                        if(info.getProject() != null && info.getProject().trim().length() > 0){
                                                bw.append(info.getProject()+",");
                                        }else{
                                                bw.append(info.getCompany()+",");
                                        }
                                        bw.append(info.getCreateTime()+",").append(info.getLoginTime()+",");
                                        if(info.getTag() != null){
                                                if(info.getTag().getProvince() > 0){
                                                        bw.append(Province.getName(info.getTag().getProvince())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getRongzi() > 0){
                                                        bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getCategory() > 0){
                                                        bw.append(Category.getName(info.getTag().getCategory())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getPerson() > 0){
                                                        bw.append(Person.getName(info.getTag().getPerson())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFuwuqi() > 0){
                                                        bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFrom() > 0){
                                                        bw.append(From.getName(info.getTag().getFrom())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                        }else{
                                                bw.append("未知,未知,未知,未知,未知,未知,");
                                        }
                                        bw.newLine();
                                        i++;
                                }
                                for(Info info : signs_geren){
                                        bw.append(i+",").append(info.getUserId()+",");
                                        if(info.getProject() != null && info.getProject().trim().length() > 0){
                                                bw.append(info.getProject()+",");
                                        }else{
                                                bw.append(info.getCompany()+",");
                                        }
                                        bw.append(info.getCreateTime()+",").append(info.getLoginTime()+",");
                                        if(info.getTag() != null){
                                                if(info.getTag().getProvince() > 0){
                                                        bw.append(Province.getName(info.getTag().getProvince())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getRongzi() > 0){
                                                        bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getCategory() > 0){
                                                        bw.append(Category.getName(info.getTag().getCategory())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getPerson() > 0){
                                                        bw.append(Person.getName(info.getTag().getPerson())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFuwuqi() > 0){
                                                        bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFrom() > 0){
                                                        bw.append(From.getName(info.getTag().getFrom())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                        }else{
                                                bw.append("未知,未知,未知,未知,未知,未知,");
                                        }
                                        bw.append("个人,");
                                        bw.newLine();
                                        i++;
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        
                        out = null;
                        osw = null;
                        bw = null;
                        try{
                                for(int i=0;i<signs.size();i++){
                                        if(!AppDataDaoImpl.getInstance().existByTimeAndUserId(start_sign, end_sing, signs.get(i).getUserId())){
                                            signs.remove(i);
                                            i--;
                                        }
                                }
                                for(int i=0;i<signs_geren.size();i++){
                                        if(!AppDataDaoImpl.getInstance().existByTimeAndUserId(start_sign, end_sing, signs_geren.get(i).getUserId())){
                                            signs_geren.remove(i);
                                            i--;
                                        }
                                }
                                File file = new File("/data/filesystem/report/datafugai.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("序号,UserId,公司,注册时间,登录时间,地域,融资,分类,规模,平台,来源").append("\r");
                                bw.newLine();
                                int i =1;
                                for(Info info : signs){
                                        info = InfoService.findByUserId(info.getUserId());
                                        if(info.getTag() != null && info.getTag().getMetric() == 3){
                                                continue;
                                        }
                                        bw.append(i+",").append(info.getUserId()+",");
                                        if(info.getProject() != null && info.getProject().trim().length() > 0){
                                                bw.append(info.getProject()+",");
                                        }else{
                                                bw.append(info.getCompany()+",");
                                        }
                                        bw.append(info.getCreateTime()+",").append(info.getLoginTime()+",");
                                        if(info.getTag() != null){
                                                if(info.getTag().getProvince() > 0){
                                                        bw.append(Province.getName(info.getTag().getProvince())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getRongzi() > 0){
                                                        bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getCategory() > 0){
                                                        bw.append(Category.getName(info.getTag().getCategory())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getPerson() > 0){
                                                        bw.append(Person.getName(info.getTag().getPerson())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFuwuqi() > 0){
                                                        bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFrom() > 0){
                                                        bw.append(From.getName(info.getTag().getFrom())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                        }else{
                                                bw.append("未知,未知,未知,未知,未知,未知,");
                                        }
                                        bw.newLine();
                                        i++;
                                }
                                for(Info info : signs_geren){
                                        bw.append(i+",").append(info.getUserId()+",");
                                        if(info.getProject() != null && info.getProject().trim().length() > 0){
                                                bw.append(info.getProject()+",");
                                        }else{
                                                bw.append(info.getCompany()+",");
                                        }
                                        bw.append(info.getCreateTime()+",").append(info.getLoginTime()+",");
                                        if(info.getTag() != null){
                                                if(info.getTag().getProvince() > 0){
                                                        bw.append(Province.getName(info.getTag().getProvince())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getRongzi() > 0){
                                                        bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getCategory() > 0){
                                                        bw.append(Category.getName(info.getTag().getCategory())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getPerson() > 0){
                                                        bw.append(Person.getName(info.getTag().getPerson())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFuwuqi() > 0){
                                                        bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                                if(info.getTag().getFrom() > 0){
                                                        bw.append(From.getName(info.getTag().getFrom())+",");
                                                }else{
                                                        bw.append("未知,");
                                                }
                                        }else{
                                                bw.append("未知,未知,未知,未知,未知,未知,");
                                        }
                                        bw.append("个人,");
                                        bw.newLine();
                                        i++;
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("end..............................................");
        }
        /**
         * 每周报表
         */
        public static void meizhoubaobiao(){
                String time1 = TimeTools.getDateTime(2);
                String start = TimeTools.getDateTime(17);
                String end = TimeTools.getDateTime(16);
                DecimalFormat    df   = new DecimalFormat("######0.00");   
                DecimalFormat    df2   = new DecimalFormat("######0.000");   
                try{
                        List<Integer> logins = new ArrayList<Integer>();
                        List<Double> login_percent = new ArrayList<Double>();
                        List<Double> bili = new ArrayList<Double>();
                        List<Double> bili_percent = new ArrayList<Double>();
                        List<Integer> downloads = new ArrayList<Integer>();
                        List<Double> download_percent= new ArrayList<Double>();
                        List<Integer> apps = new ArrayList<Integer>();
                        List<Double> app_percent = new ArrayList<Double>();
                        
                        List<Integer> java = new ArrayList<Integer>();
                        List<Double> java_percent = new ArrayList<Double>();
                        List<Integer> php = new ArrayList<Integer>();
                        List<Double> php_percent = new ArrayList<Double>();
                        List<Integer> python = new ArrayList<Integer>();
                        List<Double> python_percent = new ArrayList<Double>();
                        List<Integer> nodejs = new ArrayList<Integer>();
                        List<Double> nodejs_percent = new ArrayList<Double>();
                        List<Integer> donet = new ArrayList<Integer>();
                        List<Double> donet_percent = new ArrayList<Double>();
                        List<Integer> ruby = new ArrayList<Integer>();
                        List<Double> ruby_percent = new ArrayList<Double>();
                        List<Integer> server = new ArrayList<Integer>();
                        List<Double> server_percent = new ArrayList<Double>();
                        List<Integer> browser = new ArrayList<Integer>();
                        List<Double> browser_percent = new ArrayList<Double>();
                        List<Integer> android = new ArrayList<Integer>();
                        List<Double> android_percent = new ArrayList<Double>();
                        List<Integer> ios = new ArrayList<Integer>();
                        List<Double> ios_percent = new ArrayList<Double>();
                        
                        
                        while(TimeTools.formatTime.parse(end).getTime() <= TimeTools.formatTime.parse(time1).getTime()){
                                LOG.info("end:"+end);
                                List<Long> ids = LoginDaoImpl.getInstance().findByTime(start, end);
                                for(int i = 0;i<ids.size();i++){
                                        Info info = InfoService.findByUserIdSimple(ids.get(i));
                                        if(TimeTools.formatTime.parse(info.getCreateTime()).getTime() > TimeTools.formatTime.parse(start).getTime()){
                                                ids.remove(i);
                                                i--;
                                        }
                                }
                                for(int i = 0;i<ids.size()-1;i++){
                                        for(int j=i+1;j<ids.size();j++){
                                                if(ids.get(i).equals(ids.get(j))){
                                                        ids.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                logins.add(ids.size());
                                List<Long> IDS = DataDaoImpl.getInstance().findByTime(start, end);
                                for(int i=0;i<IDS.size()-1;i++){
                                        for(int j=i+1;j<IDS.size();j++){
                                                if(IDS.get(i).equals(IDS.get(j))){
                                                        IDS.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                List<Aplication> Apps = AppDataDaoImpl.getInstance().findByTime(start, end);
                                for(int i=0;i<Apps.size()-1;i++){
                                        for(int j=i+1;j<Apps.size();j++){
                                                if(Apps.get(i).getAppId().equals(Apps.get(j).getAppId()) && Apps.get(i).getAgent() == Apps.get(j).getAgent()){
                                                        Apps.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                List<Aplication> javas = AppDataDaoImpl.getInstance().findByAgent(1, start, end);
                                List<Aplication> phps = AppDataDaoImpl.getInstance().findByAgent(2, start, end);
                                List<Aplication> nodejss = AppDataDaoImpl.getInstance().findByAgent(3, start, end);
                                List<Aplication> pythons = AppDataDaoImpl.getInstance().findByAgent(4, start, end);
                                List<Aplication> dotnets = AppDataDaoImpl.getInstance().findByAgent(5, start, end);
                                List<Aplication> rubys = AppDataDaoImpl.getInstance().findByAgent(6, start, end);
                                List<Aplication> browsers = AppDataDaoImpl.getInstance().findByAgent(9, start, end);
                                List<Aplication> servers = AppDataDaoImpl.getInstance().findByAgent(10, start, end);
                                List<Aplication> androids = AppDataDaoImpl.getInstance().findByAgent(7, start, end);
                                List<Aplication> ioss = AppDataDaoImpl.getInstance().findByAgent(8, start, end);
                                for(int i=0;i<javas.size()-1;i++){
                                        for(int j=i+1;j<javas.size();j++){
                                                if(javas.get(i).getUserId().equals(javas.get(j).getUserId())){
                                                        javas.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<phps.size()-1;i++){
                                        for(int j=i+1;j<phps.size();j++){
                                                if(phps.get(i).getUserId().equals(phps.get(j).getUserId())){
                                                        phps.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<nodejss.size()-1;i++){
                                        for(int j=i+1;j<nodejss.size();j++){
                                                if(nodejss.get(i).getUserId().equals(nodejss.get(j).getUserId())){
                                                        nodejss.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<pythons.size()-1;i++){
                                        for(int j=i+1;j<pythons.size();j++){
                                                if(pythons.get(i).getUserId().equals(pythons.get(j).getUserId())){
                                                        pythons.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<dotnets.size()-1;i++){
                                        for(int j=i+1;j<dotnets.size();j++){
                                                if(dotnets.get(i).getUserId().equals(dotnets.get(j).getUserId())){
                                                        dotnets.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<rubys.size()-1;i++){
                                        for(int j=i+1;j<rubys.size();j++){
                                                if(rubys.get(i).getUserId().equals(rubys.get(j).getUserId())){
                                                        rubys.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<browsers.size()-1;i++){
                                        for(int j=i+1;j<browsers.size();j++){
                                                if(browsers.get(i).getUserId().equals(browsers.get(j).getUserId())){
                                                        browsers.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<servers.size()-1;i++){
                                        for(int j=i+1;j<servers.size();j++){
                                                if(servers.get(i).getUserId().equals(servers.get(j).getUserId())){
                                                        servers.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<androids.size()-1;i++){
                                        for(int j=i+1;j<androids.size();j++){
                                                if(androids.get(i).getUserId().equals(androids.get(j).getUserId())){
                                                        androids.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                for(int i=0;i<ioss.size()-1;i++){
                                        for(int j=i+1;j<ioss.size();j++){
                                                if(ioss.get(i).getUserId().equals(ioss.get(j).getUserId())){
                                                        ioss.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                java.add(javas.size());
                                php.add(phps.size());
                                donet.add(dotnets.size());
                                ruby.add(rubys.size());
                                python.add(pythons.size());
                                nodejs.add(nodejss.size());
                                server.add(servers.size());
                                browser.add(browsers.size());
                                android.add(androids.size());
                                ios.add(ioss.size());
                                bili.add((double)Apps.size()/IDS.size());
                                List<Long> downloads2 = XiazaiDaoImpl.getInstance().findByTime(start, end);
                                for(int i=0;i<downloads2.size();i++){
                                        if(XiazaiDaoImpl.getInstance().findBeforeByIdAndTime(start, downloads2.get(i))){
                                                downloads2.remove(i);
                                                i--;
                                        }
                                }
                                for(int i=0;i<downloads2.size()-1;i++){
                                        for(int j=i+1;j<downloads2.size();j++){
                                                if(downloads2.get(i).equals(downloads2.get(j))){
                                                        downloads2.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                downloads.add(downloads2.size());
                                List<Long> apps2 = TianjiaDaoImpl.getInstance().findByTime(start, end);
                                for(int i=0;i<apps2.size();i++){
                                        if(TianjiaDaoImpl.getInstance().findBeforeByIdAndTime(start, apps2.get(i))){
                                                apps2.remove(i);
                                                i--;
                                        }
                                }
                                for(int i=0;i<apps2.size()-1;i++){
                                        for(int j=i+1;j<apps2.size();j++){
                                                if(apps2.get(i).equals(apps2.get(j))){
                                                        apps2.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                apps.add(apps2.size());
                                
                                start = TimeTools.next(start, 1);
                                end = TimeTools.next(end, 1);
                        }
                        for(int i=0;i<7;i++){
                                login_percent.add((double)(logins.get((i+7))*100)/logins.get(i));
                                bili_percent.add((double)(bili.get((i+7))*100)/bili.get(i));
                                download_percent.add((double)(downloads.get((i+7))*100)/downloads.get(i));
                                app_percent.add((double)(apps.get((i+7))*100)/apps.get(i));
                                php_percent.add((double)(php.get((i+7))*100)/php.get(i));
                                donet_percent.add((double)(donet.get((i+7))*100)/donet.get(i));
                                python_percent.add((double)(python.get((i+7))*100)/python.get(i));
                                nodejs_percent.add((double)(nodejs.get((i+7))*100)/nodejs.get(i));
                                ruby_percent.add((double)(ruby.get((i+7))*100)/ruby.get(i));
                                java_percent.add((double)(java.get((i+7))*100)/java.get(i));
                                if(server.get(i) > 0){
                                        server_percent.add((double)(server.get((i+7))*100)/server.get(i));
                                }else{
                                        server_percent.add(0d);
                                }
                                browser_percent.add((double)(browser.get((i+7))*100)/browser.get(i));
                                android_percent.add((double)(android.get((i+7))*100)/android.get(i));
                                ios_percent.add((double)(ios.get((i+7))*100)/ios.get(i));
                                
                        }
                        FileOutputStream out = null;
                        OutputStreamWriter osw = null;
                        BufferedWriter bw = null;
                        try{
                                File file = new File("/data/filesystem/report/report.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(logins.get(i)+",").append(logins.get(i+7)+",").append(df.format(login_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(df2.format(bili.get(i))+",").append(df2.format(bili.get(i+7))+",").append(df.format(bili_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(downloads.get(i)+",").append(downloads.get(i+7)+",").append(df.format(download_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(apps.get(i)+",").append(apps.get(i+7)+",").append(df.format(app_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/java.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(java.get(i)+",").append(java.get(i+7)+",").append(df.format(java_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/php.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(php.get(i)+",").append(php.get(i+7)+",").append(df.format(php_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/nodejs.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(nodejs.get(i)+",").append(nodejs.get(i+7)+",").append(df.format(nodejs_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/dotnet.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(donet.get(i)+",").append(donet.get(i+7)+",").append(df.format(donet_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/python.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(python.get(i)+",").append(python.get(i+7)+",").append(df.format(python_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/ruby.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(ruby.get(i)+",").append(ruby.get(i+7)+",").append(df.format(ruby_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/android.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(android.get(i)+",").append(android.get(i+7)+",").append(df.format(android_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/ios.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(ios.get(i)+",").append(ios.get(i+7)+",").append(df.format(ios_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/server.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(server.get(i)+",").append(server.get(i+7)+",").append(df.format(server_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                        try{
                                File file = new File("/data/filesystem/report/browser.csv");
                                out = new FileOutputStream(file);
                                osw = new OutputStreamWriter(out);
                                bw = new BufferedWriter(osw);
                                bw.append("日期,首周,次周,增长比").append("\r");
                                bw.newLine();
                                for(int i=0;i<7;i++){
                                        bw.append(i+",").append(browser.get(i)+",").append(browser.get(i+7)+",").append(df.format(browser_percent.get(i)-100)).append("\r");
                                        bw.newLine();
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }finally {
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
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public static void youguoshuju(){
                List<App> apps = AddDaoImpl.getInstance().findByAgent("2014-01-01 00:00:00", TimeTools.format());
                LOG.info("apps:"+apps.size());
                List<Info> infos = new ArrayList<Info>();
                Set<Long> userIds = new HashSet<Long>();
                for(App app : apps){
                        userIds.add(app.getUserId());
                }
                LOG.info("userIds:"+userIds.size());
                for(Long userId : userIds){
                        infos.add(InfoService.findByUserId(userId));
                }
                LOG.info("infos:"+infos.size());
                FileOutputStream out = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                try{
                        File file = new File("/data/filesystem/report/report.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("序号,userId,公司,电话,地域,融资,分类,规模,销售,售前,支持").append("\r");
                        bw.newLine();
                        int i=1;
                        for(Info info : infos){
                                bw.append(i+",").append(info.getUserId()+",");
                                if(info.getProject() != null){
                                        bw.append(info.getProject()+",");
                                }else{
                                        if(info.getCompany() != null){
                                                bw.append(info.getCompany()+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }
                                bw.append(info.getPhone()+",");
                                if(info.getTag() != null){
                                        if(info.getTag().getProvince() > 0){
                                                bw.append(Province.getName(info.getTag().getProvince())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getRongzi() > 0){
                                                bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getCategory() > 0){
                                                bw.append(Category.getName(info.getTag().getCategory())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getPerson() > 0){
                                                bw.append(Person.getName(info.getTag().getPerson())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }else{
                                        bw.append("未知,未知,未知,未知,");
                                }
                                if(info.getSale() != null && info.getSale() > 0){
                                        bw.append(AccountService.findName(info.getSale())+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getPreSale() != null && info.getPreSale() > 0){
                                        bw.append(AccountService.findName(info.getPreSale())+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getSupport() != null && info.getSupport() > 0){
                                        bw.append(AccountService.findName(info.getSupport())+"").append("\r");
                                }else{
                                        bw.append("无").append("\r");
                                }
                                i++;
                                bw.newLine();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }finally {
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
        }
        
        public static void wajue() throws IOException{
                List<Aplication> aplications = AppDataDaoImpl.getInstance().findByTime("2015-07-07 00:00:00", "2015-07-08 00:00:00");
                Set<Long> userIds = new HashSet<Long>();
                for(Aplication aplication : aplications){
                        userIds.add(aplication.getUserId());
                }
                List<Info> infos = new ArrayList<Info>();
                for(Long userId : userIds){
                        infos.add(InfoService.findByUserId(userId));
                }
                FileOutputStream out = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                try{
                        File file = new File("/data/filesystem/report/0707.csv");
                        out = new FileOutputStream(file);
                        osw = new OutputStreamWriter(out);
                        bw = new BufferedWriter(osw);
                        bw.append("序号,userId,公司,电话,邮箱,地域,融资,分类,规模,平台,销售,售前,支持").append("\r");
                        bw.newLine();
                        int i=1;
                        for(Info info : infos){
                                bw.append(i+",").append(info.getUserId()+",");
                                if(info.getProject() != null){
                                        bw.append(info.getProject()+",");
                                }else{
                                        if(info.getCompany() != null){
                                                bw.append(info.getCompany()+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }
                                bw.append(info.getPhone()+","+info.getEmail()+",");
                                if(info.getTag() != null){
                                        if(info.getTag().getProvince() > 0){
                                                bw.append(Province.getName(info.getTag().getProvince())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getRongzi() > 0){
                                                bw.append(Rongzi.getName(info.getTag().getRongzi())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getCategory() > 0){
                                                bw.append(Category.getName(info.getTag().getCategory())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getPerson() > 0){
                                                bw.append(Person.getName(info.getTag().getPerson())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                        if(info.getTag().getFuwuqi() > 0){
                                                bw.append(Fuwuqi.getName(info.getTag().getFuwuqi())+",");
                                        }else{
                                                bw.append("未知,");
                                        }
                                }else{
                                        bw.append("未知,未知,未知,未知,未知,");
                                }
                                if(info.getSale() != null && info.getSale() > 0){
                                        bw.append(AccountService.findName(info.getSale())+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getPreSale() != null && info.getPreSale() > 0){
                                        bw.append(AccountService.findName(info.getPreSale())+",");
                                }else{
                                        bw.append("无,");
                                }
                                if(info.getSupport() != null && info.getSupport() > 0){
                                        bw.append(AccountService.findName(info.getSupport())+"").append("\r");
                                }else{
                                        bw.append("无").append("\r");
                                }
                                i++;
                                bw.newLine();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }finally {
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
        }
}
