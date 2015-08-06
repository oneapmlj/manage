package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.AppDaoImpl;
import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dao.info.impl.LoginDaoImpl;
import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.lable.Lable;
import com.oneapm.dto.tag.Language;
import com.oneapm.service.lable.LableService;
import com.oneapm.service.mail.DownloadService;
import com.oneapm.util.OneTools;
import com.oneapm.util.SortList;
import com.oneapm.util.TimeTools;

public class DuandianService {

        protected static final Logger LOG = LoggerFactory.getLogger(DuandianService.class);
        
        public final static int pageNum = 30;

        
        @SuppressWarnings("unchecked")
        public static String findVersions(int agent){
                try{
                        List<String> versions = new ArrayList<String>();
                        if(agent == 9){
                                versions.add("独立部署");
                                versions.add("AI注入");
                                versions.add("java注入");
                                versions.add("php注入");
                                versions.add("python注入");
                                versions.add("ruby注入");
                                versions.add("dotnet注入");
                                versions.add("nodejs注入");
                        }
                        versions = DownloadService.findVersions(agent);
                        for(int i=0;i<versions.size();i++){
                                for(int j=i+1;j<versions.size();j++){
                                        if(versions.get(i).equals(versions.get(j))){
                                                versions.remove(j);
                                                j--;
                                        }
                                }
                        }
                        JSONArray array = new JSONArray();
                        for(String version : versions){
                                array.add(version);
                        }
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("versions", array);
                        object.put("agent", agent);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误，请联系管理员");
        }
        public static String chaxun(Long fatherId){
                try{
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        Lable lable = LableService.findById(fatherId);
                        if(lable == null){
                                return OneTools.getResult(0, "分类不存在");
                        }
                        List<Info> infos = InfoService.findByFrom(lable.getFrom());
                        for(int i=0;i<infos.size();i++){
                                if(!infos.get(i).getComming().startsWith(lable.getFrom())){
                                    infos.remove(i);
                                    i--;
                                }
                        }
                        for(Info info : infos){
                                InfoService.initInfo(info);
                                InfoService.power(99999999L, 7, info);
                        }
                        args1.add("infos");
                        args2.add(InfoService.getArrayFromInfos(infos));
                        return OneTools.getResult(1, args1, args2);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static String chaxun_chazhi(String yuanStart, String yuanEnd){
                try{
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        String start = yuanStart + " 00:00:00";
                        String end = yuanEnd + " 00:00:00";
                        List<Info> infos = InfoDaoImpl.getInstance().countSign(start, end);
                        for(int i=0;i<infos.size();i++){
                                if(!DownDaoImpl.getInstance().findDownload(infos.get(i).getUserId())){
                                    infos.remove(i);
                                    i--;
                                }else{
                                        if(AppDaoImpl.getInstance().findApp(infos.get(i).getUserId())){
                                           infos.remove(i);
                                           i--;
                                        }
                        }
                        }
                        if(infos.size() == 0){
                                object.put("size", 0);
                        }else{
                                object.put("size", infos.size());
                                for(int i=0;i<infos.size();i++){
                                        infos.set(i, InfoService.findById(infos.get(i).getId()));
                                }
                                object.put("infos", InfoService.getArrayFromInfos(infos));
                        }
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        public static String chaxun(int yuan, String yuanStart, String yuanEnd, int agent, int leixing, int zuobiao, int zuobiaoZidingyi, String zuobiaoStart,
                        String zuobiaoEnd, int yuanType){
                List<String> args1 = new ArrayList<String>();
                List<Object> args2 = new ArrayList<Object>();
                String start = TimeTools.getDateTime(0);
                String end = TimeTools.getDateTime(-1);
                JSONObject object = new JSONObject();
                try{
                        if(zuobiao == 4 && zuobiaoZidingyi <= 0){
                                return OneTools.getResult(0, "坐标值不能为0");
                        }
                        if(zuobiao == 5){
                                if(zuobiaoStart == null || zuobiaoStart.trim().length() <= 0 || zuobiaoEnd == null || zuobiaoEnd.trim().length() <= 0){
                                        return OneTools.getResult(0, "自定义坐标区间不能为空");
                                }
                        }
                        
                        switch(yuan){
                                case 0:break;
                                case 1:start = TimeTools.getDateTime(1);end = TimeTools.getDateTime(0);break;
                                case 2:start = TimeTools.getDateTime(7);end = TimeTools.getDateTime(0);break;
                                case 3:
                                        if(yuanStart == null || yuanEnd == null || yuanStart.trim().length() <= 0 || yuanEnd.trim().length() <= 0){
                                                return OneTools.getResult(0, "数据源区间错误");
                                        }
                                        start = yuanStart+" 00:00:00";
                                        end = TimeTools.next(yuanEnd+" 00:00:00", 1);
                                        break;
                                default:return OneTools.getResult(0, "未知源数据区间");
                        }
                        switch (zuobiao) {
                                case 0:zuobiaoZidingyi=1;break;
                                case 1:zuobiaoZidingyi=3;break;
                                case 2:zuobiaoZidingyi=7;break;
                                case 3:zuobiaoZidingyi=30;break;
                                case 4:if(zuobiaoZidingyi <= 0)return OneTools.getResult(0, "坐标自定义不能小于0");break;
                                case 5:
                                        zuobiaoEnd = TimeTools.next(zuobiaoEnd+" 00:00:00", 1);
                                        zuobiaoStart += " 00:00:00";
                                        if(TimeTools.formatTime.parse(zuobiaoStart+" 00:00:00").getTime() >= TimeTools.formatTime.parse(zuobiaoEnd+" 00:00:00").getTime()){
                                                return OneTools.getResult(0, "坐标区间错误");
                                        }
                                        break;
                                default:break;
                        }
                        switch (leixing) {
                                case 0:object = chaxun_zhuanhua(start, end, zuobiaoStart, zuobiaoEnd, zuobiaoZidingyi);break;
                                case 1:object = chaxun_liucun(start, end, zuobiaoStart, zuobiaoEnd, zuobiaoZidingyi, agent, yuanType);break;
                                default:return OneTools.getResult(0, "无此查询类型");
                        }
                        if(object == null){
                                return OneTools.getResult(0, "服务器内部错误");
                        }
                        args1.add("baobiao");
                        args2.add(object);
                        args1.add("leixing");
                        args2.add(leixing);
                        return OneTools.getResult(1, args1, args2);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }
        
        public static JSONObject chaxun_zhuanhua(String start, String end, String zuobiaoStart, String zuobiaoEnd, int zuobiao){
                JSONObject object = new JSONObject();
                try{
                        String time =TimeTools.getDateTime(-1);
                        long timeLong = TimeTools.formatTime.parse(time).getTime();
                        List<Info> infos = InfoDaoImpl.getInstance().countSign(start, end);
                        
                        if(infos == null || infos.size() <= 0){
                                object.put("base", 0);
                                return object;
                        }
                        object.put("base", infos.size());
                        JSONArray datas = new JSONArray();
                        if(zuobiao > 0){
                                List<String> mapTime = new ArrayList<String>();
                                List<Long> mapLong = new ArrayList<Long>();
                                List<Integer> mapDownload_qujian = new ArrayList<Integer>();
                                List<Integer> mapApp_qujian = new ArrayList<Integer>();
                                List<Integer> mapDownload_zong = new ArrayList<Integer>();
                                List<Integer> mapApp_zong= new ArrayList<Integer>();
                                mapTime.add(start);
                                mapTime.add(end);
                                mapDownload_zong.add(0);
                                mapApp_zong.add(0);
                                mapDownload_qujian.add(0);
                                mapApp_qujian.add(0);
                                mapLong.add(TimeTools.formatTime.parse(start).getTime());
                                mapLong.add(TimeTools.formatTime.parse(end).getTime());
                                String b = end;
                                while(TimeTools.formatTime.parse(b).getTime() < timeLong){
                                        b = TimeTools.next(b, zuobiao);
                                        if(TimeTools.formatTime.parse(b).getTime() > timeLong){
                                                b = time;
                                        }
                                        mapTime.add(b);
                                        mapLong.add(TimeTools.formatTime.parse(b).getTime());
                                        mapDownload_zong.add(0);
                                        mapApp_zong.add(0);
                                        mapDownload_qujian.add(0);
                                        mapApp_qujian.add(0);
                                }
                                for(Info info : infos){
                                        Download download = DownDaoImpl.getInstance().findDonwTime(info.getUserId());
                                        Aplication aplication = AppDataDaoImpl.getInstance().findByUserIdfirst(info.getUserId());
                                        if(download != null){
                                                long d = TimeTools.formatTime.parse(download.getDownloadTime()).getTime();
                                                for(int i=0;i<mapLong.size()-1;i++){
                                                        if(d >= mapLong.get(i) && d < mapLong.get(i+1)){
                                                                mapDownload_qujian.set(i, mapDownload_qujian.get(i)+1);
                                                                mapDownload_zong.set(i, mapDownload_zong.get(i)+1);
                                                        }else{
                                                                if(d < mapLong.get(i)){
                                                                        mapDownload_zong.set(i, mapDownload_zong.get(i)+1);
                                                                }
                                                        }
                                                }
                                        }
                                        if(aplication != null){
                                                long a = TimeTools.formatTime.parse(aplication.getDataTime()).getTime();
                                                for(int i=0;i<mapLong.size()-1;i++){
                                                        if(a >= mapLong.get(i) && a < mapLong.get(i+1)){
                                                                mapApp_qujian.set(i, mapApp_qujian.get(i)+1);
                                                                mapApp_zong.set(i, mapApp_zong.get(i)+1);
                                                                if(download == null){
                                                                        mapDownload_qujian.set(i, mapDownload_qujian.get(i)+1);
                                                                        mapDownload_zong.set(i, mapDownload_zong.get(i)+1);
                                                                }
                                                        }else{
                                                                if(a < mapLong.get(i)){
                                                                        if(download == null){
                                                                                mapDownload_zong.set(i, mapDownload_zong.get(i)+1);
                                                                        }
                                                                        mapApp_zong.set(i, mapApp_zong.get(i)+1);
                                                                }
                                                        }
                                                }
                                        }
                                }
                                for(int i=0;i<mapTime.size() -1;i++){
                                        JSONObject object2 = new JSONObject();
                                        object2.put("time_start", mapTime.get(i).substring(0, 10));
                                        object2.put("time_end", mapTime.get(i+1).substring(0, 10));
                                        object2.put("download_qujian", mapDownload_qujian.get(i));
                                        object2.put("download_zong", mapDownload_zong.get(i));
                                        object2.put("app_qujian", mapApp_qujian.get(i));
                                        object2.put("app_zong", mapApp_zong.get(i));
                                        datas.add(object2);
                                }
                        }else{
                                List<String> mapTime = new ArrayList<String>();
                                List<Long> mapLong = new ArrayList<Long>();
                                List<Integer> mapDownload_qujian = new ArrayList<Integer>();
                                List<Integer> mapApp_qujian = new ArrayList<Integer>();
                                List<Integer> mapDownload_zong = new ArrayList<Integer>();
                                List<Integer> mapApp_zong= new ArrayList<Integer>();
                                mapTime.add(start);
                                mapTime.add(end);
                                mapTime.add(zuobiaoStart);
                                mapTime.add(zuobiaoEnd);
                                mapDownload_zong.add(0);
                                mapApp_zong.add(0);
                                mapDownload_qujian.add(0);
                                mapApp_qujian.add(0);
                                mapDownload_zong.add(0);
                                mapApp_zong.add(0);
                                mapDownload_qujian.add(0);
                                mapApp_qujian.add(0);
                                mapLong.add(TimeTools.formatTime.parse(start).getTime());
                                mapLong.add(TimeTools.formatTime.parse(end).getTime());
                                mapLong.add(TimeTools.formatTime.parse(zuobiaoStart).getTime());
                                mapLong.add(TimeTools.formatTime.parse(zuobiaoEnd).getTime());
                                for(Info info : infos){
                                        Download download = DownDaoImpl.getInstance().findDonwTime(info.getUserId());
                                        Aplication aplication = AppDataDaoImpl.getInstance().findByUserIdfirst(info.getUserId());
                                        if(download != null){
                                                long d = TimeTools.formatTime.parse(download.getDownloadTime()).getTime();
                                                if(d >= mapLong.get(0) && d < mapLong.get(1)){
                                                        mapDownload_qujian.set(0, mapDownload_qujian.get(0)+1);
                                                        mapDownload_zong.set(0, mapDownload_zong.get(0)+1);
                                                        mapDownload_zong.set(1, mapDownload_zong.get(1)+1);
                                                }else{
                                                        if(d >= mapLong.get(2) && d< mapLong.get(3)){
                                                                mapDownload_qujian.set(1, mapDownload_qujian.get(1)+1);
                                                                mapDownload_zong.set(1, mapDownload_zong.get(1)+1);
                                                        }else{
                                                                if(d >= mapLong.get(1) && d< mapLong.get(2)){
                                                                        mapDownload_zong.set(1, mapDownload_zong.get(1)+1);
                                                                }
                                                        }
                                                }
                                        }
                                        if(aplication != null){
                                                long a = TimeTools.formatTime.parse(aplication.getDataTime()).getTime();
                                                if(a >= mapLong.get(0) && a < mapLong.get(1)){
                                                        mapApp_qujian.set(0, mapApp_qujian.get(0)+1);
                                                        mapApp_zong.set(0, mapApp_zong.get(0)+1);
                                                        mapApp_zong.set(1, mapApp_zong.get(1)+1);
                                                }else{
                                                        if(a >= mapLong.get(2) && a< mapLong.get(3)){
                                                                mapApp_qujian.set(1, mapApp_qujian.get(1)+1);
                                                                mapApp_zong.set(1, mapApp_zong.get(1)+1);
                                                        }else{
                                                                if(a >= mapLong.get(1) && a< mapLong.get(2)){
                                                                        mapApp_zong.set(1, mapApp_zong.get(1)+1);
                                                                }
                                                        }
                                                }
                                        }
                                }
                                JSONObject object2 = new JSONObject();
                                object2.put("time_start", mapTime.get(0).substring(0, 10));
                                object2.put("time_end", mapTime.get(1).substring(0, 10));
                                object2.put("download_qujian", mapDownload_qujian.get(0));
                                object2.put("download_zong", mapDownload_zong.get(0));
                                object2.put("app_qujian", mapApp_qujian.get(0));
                                object2.put("app_zong", mapApp_zong.get(0));
                                datas.add(object2);
                                JSONObject object3 = new JSONObject();
                                object3.put("time_start", mapTime.get(2).substring(0, 10));
                                object3.put("time_end", mapTime.get(3).substring(0, 10));
                                object3.put("download_qujian", mapDownload_qujian.get(1));
                                object3.put("download_zong", mapDownload_zong.get(1));
                                object3.put("app_qujian", mapApp_qujian.get(1));
                                object3.put("app_zong", mapApp_zong.get(1));
                                datas.add(object3);
                        }
                        object.put("datas", datas);
                        object.put("zuobiao", zuobiao);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                        return null;
                }
                return object;
        }
        public static JSONObject chaxun_liucun(String start, String end, String zuobiaoStart, String zuobiaoEnd, int zuobiao, int agent, int yuanType){
                JSONObject object = new JSONObject();
                try{
                        List<Info> infos = new ArrayList<Info>();
                        List<Long> USERIDS = new ArrayList<Long>();
                        if(yuanType == 0){
                                USERIDS = AppDataDaoImpl.getInstance().findUserIdsByAgent(agent, start, end);
                                if(USERIDS.size() > 0){
                                        List<Long> userIds = AppDataDaoImpl.getInstance().exitBeforeTime(agent, start, USERIDS);
                                        for(Long userId: userIds){
                                                for(int i=0;i<USERIDS.size();i++){
                                                        if(USERIDS.get(i).equals(userId)){
                                                                USERIDS.remove(i);
                                                                i--;
                                                        }
                                                }
                                        }
                                        for(Long userId: USERIDS){
                                                infos.add(InfoService.findByUserId(userId));
                                        }
                                }
                        }else{
                                String end1 = TimeTools.next(start, 1);
                                USERIDS = AppDataDaoImpl.getInstance().findUserIdsByAgent(agent, start, end1);
                                List<Long> times_long = new ArrayList<Long>();
                                List<String> times = new ArrayList<String>();
                                while(TimeTools.formatTime.parse(end1).getTime() <= TimeTools.formatTime.parse(end).getTime()){
                                        times.add(end1);
                                        times_long.add(TimeTools.formatTime.parse(end1).getTime());
                                        end1 = TimeTools.next(end1, 1);
                                }
                                if(USERIDS.size() > 0){
                                        List<Long> userIds = AppDataDaoImpl.getInstance().exitBeforeTime(agent, start, USERIDS);
                                        for(Long userId: userIds){
                                                for(int i=0;i<USERIDS.size();i++){
                                                        if(USERIDS.get(i).equals(userId)){
                                                                USERIDS.remove(i);
                                                                i--;
                                                        }
                                                }
                                        }
                                }
                                for(Long userId: USERIDS){
                                        boolean in = true;
                                        for(int i=0;i<times_long.size()-1;i++){
                                                if(!AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(times.get(i), times.get(i+1), userId, agent)){
                                                        in = false;
                                                        break;
                                                }
                                        }
                                        if(in){
                                                infos.add(InfoService.findByUserId(userId));
                                        }
                                }
                        }
                        if(infos == null || infos.size() <= 0){
                                object.put("base", 0);
                                return object;
                        }
                        object.put("base", infos.size());
                        List<String> mapTime = new ArrayList<String>();
                        List<Long> mapLong = new ArrayList<Long>();
                        List<Integer> mapLianxu= new ArrayList<Integer>();
                        List<Integer> mapCunzai= new ArrayList<Integer>();
                        List<Integer> mapChixu= new ArrayList<Integer>();
                        String time =TimeTools.getDateTime(-1);
                        long timeLong = TimeTools.formatTime.parse(time).getTime();
                        JSONArray datas = new JSONArray();
                        if(zuobiao > 0){
                                mapTime.add(start);
                                mapTime.add(end);
                                mapLong.add(TimeTools.formatTime.parse(start).getTime());
                                mapLong.add(TimeTools.formatTime.parse(end).getTime());
                                mapLianxu.add(0);
                                mapCunzai.add(0);
                                mapChixu.add(0);
                                String b = end;
                                while(TimeTools.formatTime.parse(b).getTime() < timeLong){
                                        b = TimeTools.next(b, zuobiao);
                                        if(TimeTools.formatTime.parse(b).getTime() > timeLong){
                                                b = time;
                                        }
                                        mapTime.add(b);
                                        mapLong.add(TimeTools.formatTime.parse(b).getTime());
                                        mapLianxu.add(0);
                                        mapCunzai.add(0);
                                        mapChixu.add(0);
                                }
                                for(Info info: infos){
                                        boolean chixu = true;
                                        for(int i=0;i<mapTime.size()-1;i++){
                                                boolean lianxu = true;
                                                boolean cunzai = false;
                                                String a1 = mapTime.get(i);
                                                String a2 = TimeTools.next(mapTime.get(i), 1);
                                                while(TimeTools.formatTime.parse(a2).getTime() <= mapLong.get(i+1)){
                                                        if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(a1,a2, info.getUserId(), agent)){
                                                                cunzai = true;
                                                        }else{
                                                                if(i > 0){
                                                                        lianxu = false;
                                                                        chixu = false;
                                                                }
                                                        }
                                                        
                                                        a1 = TimeTools.next(a1, 1);
                                                        a2 = TimeTools.next(a2, 1);
                                                }
                                                if(i>0){
                                                        if(lianxu){
                                                                mapLianxu.set(i, mapLianxu.get(i)+1);
                                                        }
                                                        if(chixu){
                                                                mapChixu.set(i, mapChixu.get(i)+1);
                                                        }
                                                }
                                                if(cunzai){
                                                        mapCunzai.set(i, mapCunzai.get(i)+1);
                                                }
                                        }
                                }
                                for(int i=0;i<mapTime.size() -1;i++){
                                        JSONObject object2 = new JSONObject();
                                        object2.put("time_start", mapTime.get(i).substring(0, 10));
                                        object2.put("time_end", mapTime.get(i+1).substring(0, 10));
                                        object2.put("lianxu", mapLianxu.get(i));
                                        object2.put("cunzai", mapCunzai.get(i));
                                        object2.put("chixu", mapChixu.get(i));
                                        datas.add(object2);
                                }
                        }else{
                                mapTime.add(start);
                                mapTime.add(end);
                                mapLong.add(TimeTools.formatTime.parse(start).getTime());
                                mapLong.add(TimeTools.formatTime.parse(end).getTime());
                                mapLianxu.add(0);
                                mapCunzai.add(0);
                                mapChixu.add(0);
                                mapTime.add(zuobiaoStart);
                                mapTime.add(zuobiaoEnd);
                                mapLong.add(TimeTools.formatTime.parse(zuobiaoStart).getTime());
                                mapLong.add(TimeTools.formatTime.parse(zuobiaoEnd).getTime());
                                mapLianxu.add(0);
                                mapCunzai.add(0);
                                mapChixu.add(0);
                                for(Info info: infos){
                                        boolean lianxu = true;
                                        boolean cunzai = false;
                                        boolean chixu = true;
                                        String a1 = mapTime.get(0);
                                        String a2 = TimeTools.next(mapTime.get(0), 1);
                                        while(TimeTools.formatTime.parse(a2).getTime() <= mapLong.get(1)){
                                                if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(a1,a2, info.getUserId(), agent)){
                                                        cunzai = true;
                                                }
                                                
                                                a1 = TimeTools.next(a1, 1);
                                                a2 = TimeTools.next(a2, 1);
                                        }
                                        if(cunzai){
                                                mapCunzai.set(0, mapCunzai.get(0)+1);
                                        }
                                        cunzai = false;
                                        a1 = mapTime.get(2);
                                        a2 = TimeTools.next(mapTime.get(2), 1);
                                        while(TimeTools.formatTime.parse(a2).getTime() <= mapLong.get(3)){
                                                if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(a1,a2, info.getUserId(), agent)){
                                                        cunzai = true;
                                                }else{
                                                        lianxu = false;
                                                        chixu = false;
                                                }
                                                a1 = TimeTools.next(a1, 1);
                                                a2 = TimeTools.next(a2, 1);
                                        }
                                        if(lianxu){
                                                mapLianxu.set(1, mapLianxu.get(1)+1);
                                        }
                                        if(cunzai){
                                                mapCunzai.set(1, mapCunzai.get(1)+1);
                                        }
                                        if(chixu){
                                                mapChixu.set(1, mapChixu.get(1)+1);
                                        }
                                }
                                JSONObject object2 = new JSONObject();
                                object2.put("time_start", mapTime.get(0).substring(0, 10));
                                object2.put("time_end", mapTime.get(1).substring(0, 10));
                                object2.put("lianxu", mapLianxu.get(0));
                                object2.put("cunzai", mapCunzai.get(0));
                                object2.put("chixu", mapChixu.get(0));
                                datas.add(object2);
                                JSONObject object3 = new JSONObject();
                                object3.put("time_start", mapTime.get(2).substring(0, 10));
                                object3.put("time_end", mapTime.get(3).substring(0, 10));
                                object3.put("lianxu", mapLianxu.get(1));
                                object3.put("cunzai", mapCunzai.get(1));
                                object3.put("chixu", mapChixu.get(1));
                                datas.add(object3);
                        }
                        object.put("datas", datas);
                        object.put("zuobiao", zuobiao);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                        return null;
                }
                return object;
        }
        /**
         * 排序查询次序：
         * 有数据，状态，登录
         * 负责，分类
         * 独立用户只在有数据条件下有效
         * @param agent
         * @param data
         * @param paixu
         * @param banben
         * @param fuze
         * @param groupId1
         * @param groupId2
         * @param dataStart
         * @param dataEnd
         * @param nodataStart
         * @param nodataEnd
         * @param nodata
         * @param duli
         * @param login
         * @param loginStart
         * @param loginEnd
         * @return
         */
        public static String chaxun(int agent, int data, int paixu, String banben, int fuze, Long groupId1, Long groupId2, String dataStart, String dataEnd, String nodataStart, 
                        String nodataEnd, int nodata, int duli, int login, String loginStart, String loginEnd, int caozuo, int caozuoTime, String caozuoStart, String caozuoEnd, Admin admin){
                try{
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        String end = TimeTools.getDateTime(-1);
                        long data_start = 0;
                        long data_end = 0;
                        long nodata_start = 0;
                        long nodata_end = 0;
                        long login_start = 0;
                        long login_end = 0;
                        long caozuo_start = 0;
                        long caozuo_end = 0;
                        if(login > 0){
                                switch(login){
                                        case 0:loginStart = "2014-01-01 00:00:00";loginEnd = TimeTools.getDateTime(-1);break;
                                        case 1:loginStart = TimeTools.getDateTime(0);loginEnd = TimeTools.getDateTime(-1);break;
                                        case 2:loginStart = TimeTools.getDateTime(1);loginEnd = TimeTools.getDateTime(0);break;
                                        case 3:loginStart = TimeTools.getDateTime(7);loginEnd = TimeTools.getDateTime(0);break;
                                        case 4:loginStart = TimeTools.getDateTime(14);loginEnd = TimeTools.getDateTime(0);break;
                                        case 5:loginStart = TimeTools.getDateTime(30);loginEnd = TimeTools.getDateTime(0);break;
                                        case 6:
                                                if(loginStart == null)return OneTools.getResult(0, "请填写时间区间");
                                                loginStart += " 00:00:00";
                                                if(loginEnd == null){
                                                        loginEnd = end;
                                                }else{
                                                        loginEnd+= " 00:00:00";
                                                }
                                                try{
                                                        loginEnd = TimeTools.next(loginEnd, 1);
                                                        login_start = TimeTools.formatTime.parse(loginStart).getTime();
                                                        login_end= TimeTools.formatTime.parse(loginEnd).getTime();
                                                        if(login_start > new Date().getTime()){
                                                                return OneTools.getResult(0, "login 我不是先知啊！！以后的事我哪知道");
                                                        }
                                                        if(login_end <= login_start){
                                                                return OneTools.getResult(0, "login起始时间比结束时间还大，我们不是一个维度的");
                                                        }
                                                }catch (Exception e) {
                                                        return OneTools.getResult(0, "login 时间格式错误");
                                                }
                                                break;
                                        default:return OneTools.getResult(0, "login参数错误");
                                }
                        }
                        if(caozuoTime > 0){
                                switch(caozuoTime){
                                        case 1:caozuoStart = TimeTools.getDateTime(0);caozuoEnd = TimeTools.getDateTime(-1);break;
                                        case 2:caozuoStart = TimeTools.getDateTime(1);caozuoEnd = TimeTools.getDateTime(0);break;
                                        case 3:caozuoStart = TimeTools.getDateTime(7);caozuoEnd = TimeTools.getDateTime(0);break;
                                        case 4:caozuoStart = TimeTools.getDateTime(14);caozuoEnd = TimeTools.getDateTime(0);break;
                                        case 5:caozuoStart = TimeTools.getDateTime(30);caozuoEnd = TimeTools.getDateTime(0);break;
                                        case 6:
                                                if(caozuoStart == null)return OneTools.getResult(0, "请填写时间区间");
                                                caozuoStart += " 00:00:00";
                                                if(caozuoEnd == null){
                                                        caozuoEnd = end;
                                                }else{
                                                        caozuoEnd+= " 00:00:00";
                                                }
                                                try{
                                                        caozuoEnd = TimeTools.next(caozuoEnd, 1);
                                                        caozuo_start = TimeTools.formatTime.parse(caozuoStart).getTime();
                                                        caozuo_end= TimeTools.formatTime.parse(caozuoEnd).getTime();
                                                        if(caozuo_start > new Date().getTime()){
                                                                return OneTools.getResult(0, "caozuo 我不是先知啊！！以后的事我哪知道");
                                                        }
                                                        if(caozuo_end <= caozuo_start){
                                                                return OneTools.getResult(0, "caozuo起始时间比结束时间还大，我们不是一个维度的");
                                                        }
                                                }catch (Exception e) {
                                                        return OneTools.getResult(0, "caozuo 时间格式错误");
                                                }
                                                break;
                                        default:return OneTools.getResult(0, "caozuo参数错误");
                                }
                        }else{
                                caozuoStart=null;
                        }
                        if(data > 0){
                                switch(data){
                                        case 1:dataStart = TimeTools.getDateTime(0);dataEnd = TimeTools.getDateTime(-1);break;
                                        case 2:dataStart = TimeTools.getDateTime(1);dataEnd = TimeTools.getDateTime(0);break;
                                        case 3:dataStart = TimeTools.getDateTime(7);dataEnd = TimeTools.getDateTime(0);break;
                                        case 4:dataStart = TimeTools.getDateTime(14);dataEnd = TimeTools.getDateTime(0);break;
                                        case 5:dataStart = TimeTools.getDateTime(30);dataEnd = TimeTools.getDateTime(0);break;
                                        case 6:
                                                if(dataStart == null)return OneTools.getResult(0, "请填写时间区间");
                                                dataStart += " 00:00:00";
                                                if(dataEnd == null){
                                                        dataEnd = end;
                                                }else{
                                                        dataEnd+= " 00:00:00";
                                                }
                                                try{
                                                        dataEnd = TimeTools.next(dataEnd, 1);
                                                        data_start = TimeTools.formatTime.parse(dataStart).getTime();
                                                        data_end= TimeTools.formatTime.parse(dataEnd).getTime();
                                                        if(data_start > new Date().getTime()){
                                                                return OneTools.getResult(0, "data 我不是先知啊！！以后的事我哪知道");
                                                        }
                                                        if(data_end <= data_start){
                                                                return OneTools.getResult(0, "data起始时间比结束时间还大，我们不是一个维度的");
                                                        }
                                                }catch (Exception e) {
                                                        return OneTools.getResult(0, "data 时间格式错误");
                                                }
                                                break;
                                        default:return OneTools.getResult(0, "data参数错误");
                                }
                        }
                        if(nodata > 0){
                                switch(nodata){
                                        case 1:nodataStart = TimeTools.getDateTime(0);nodataEnd = TimeTools.getDateTime(-1);break;
                                        case 2:nodataStart = TimeTools.getDateTime(1);nodataEnd = TimeTools.getDateTime(0);break;
                                        case 3:nodataStart = TimeTools.getDateTime(7);nodataEnd = TimeTools.getDateTime(0);break;
                                        case 4:nodataStart = TimeTools.getDateTime(14);nodataEnd = TimeTools.getDateTime(0);break;
                                        case 5:nodataStart = TimeTools.getDateTime(30);nodataEnd = TimeTools.getDateTime(0);break;
                                        case 6:
                                                if(nodataStart == null)return OneTools.getResult(0, "请填写时间区间");
                                                nodataStart += " 00:00:00";
                                                if(nodataEnd == null){
                                                        nodataEnd = end;
                                                }else{
                                                        nodataEnd+= " 00:00:00";
                                                }
                                                try{
                                                        nodataEnd = TimeTools.next(nodataEnd, 1);
                                                        nodata_start = TimeTools.formatTime.parse(nodataStart).getTime();
                                                        nodata_end= TimeTools.formatTime.parse(nodataEnd).getTime();
                                                        if(nodata_start > new Date().getTime()){
                                                                return OneTools.getResult(0, "nodata 我不是先知啊！！以后的事我哪知道");
                                                        }
                                                        if(nodata_end <= nodata_start){
                                                                return OneTools.getResult(0, "nodata起始时间比结束时间还大，我们不是一个维度的");
                                                        }
                                                }catch (Exception e) {
                                                        return OneTools.getResult(0, "nodata 时间格式错误");
                                                }
                                                break;
                                        default:return OneTools.getResult(0, "nodata参数错误");
                                }
                        }
                        //有数据条件和无数据条件不能重合
                        if(nodata > 0 && data > 0){
                                if(!(data_start >= nodata_end || data_end <= nodata_start)){
                                        return OneTools.getResult(0, "时间区间交叉了，不要自相矛盾！");
                                }
                        }
                        //查询语言跟独立用户必须一致
                        if(agent > 0 && duli > 0){
                                if(duli <= 10){
                                        if(agent != duli){
                                                return OneTools.getResult(0, "在"+Language.getName(agent)+"用户里面寻找"+Language.getName(duli)+"独立用户，不怕被群殴么？");
                                        }
                                }else{
                                        switch (duli) {
                                                case 11:if(agent > 6)return OneTools.getResult(0, "在"+Language.getName(agent)+"用户里面寻找AI独立用户，不怕被群殴么？");break;
                                                case 12:if(agent < 7 || agent > 8)return OneTools.getResult(0, "在"+Language.getName(agent)+"用户里面寻找MI独立用户，不怕被群殴么？");break;
                                                default:return OneTools.getResult(0, "没有这种独立用户");
                                        }
                                }
                        }
                        List<Info> infos = new ArrayList<Info>();
                        try{
                                //有数据查询，（无数据，独立用户，登录，版本，语言）
                                if(data > 0){
                                        List<Aplication> aplications = AppService.findAplicationByAgent(agent, dataStart, banben, dataEnd);
                                        if(aplications.size() <= 0){
                                                args1.add("infos");
                                                args2.add(InfoService.getArrayFromInfos(infos));
                                                return OneTools.getResult(1, args1, args2);
                                        }
                                        for(int i=0;i<aplications.size()-1;i++){
                                                for(int j=i+1;j<aplications.size();j++){
                                                        if(aplications.get(i).getUserId().equals(aplications.get(j).getUserId())){
                                                                aplications.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                        for(int i=0;i<aplications.size();i++){
                                                if(nodata > 0){
                                                        if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(nodataStart, nodataEnd, aplications.get(i).getUserId(), agent)){
                                                                aplications.remove(i);
                                                                i--;
                                                                continue;
                                                        }
                                                }
                                                if(login > 0){
                                                        if(!LoginDaoImpl.getInstance().exit(loginStart, loginEnd, aplications.get(i).getUserId())){
                                                                aplications.remove(i);
                                                                i--;
                                                                continue;
                                                        }
                                                }
                                                if(duli > 0){
                                                        if(duli <= 10){
                                                                if(AppDataDaoImpl.getInstance().duli(dataStart, dataEnd, aplications.get(i).getUserId(), duli+1, duli-1)){
                                                                        aplications.remove(i);
                                                                        i--;
                                                                        continue;
                                                                }
                                                        }else{
                                                                switch (duli) {
                                                                case 11:
                                                                        if(AppDataDaoImpl.getInstance().duli(dataStart, dataEnd, aplications.get(i).getUserId(), 7, 0)){
                                                                                aplications.remove(i);
                                                                                i--;
                                                                                continue;
                                                                        }
                                                                        break;
                                                                case 12:
                                                                        if(AppDataDaoImpl.getInstance().duli(dataStart, dataEnd, aplications.get(i).getUserId(), 9, 6)){
                                                                                aplications.remove(i);
                                                                                i--;
                                                                                continue;
                                                                        }
                                                                        break;
                                                                default:break;
                                                                }
                                                        }
                                                }
                                        }
                                        for(Aplication aplication : aplications){
                                                infos.add(InfoService.findByUserId(aplication.getUserId()));
                                        }
                                }else{
                                        if(caozuo > 0){
                                                switch (caozuo) {
                                                //下载，（无数据，登录，版本，语言）
                                                        case 1:
                                                                List<Download> downloads = DownloadService.findByAgent(agent, banben, caozuoStart, caozuoEnd);
                                                                if(downloads.size() > 0){
                                                                        for(int i=0;i<downloads.size()-1;i++){
                                                                                for(int j=i+1;j<downloads.size();j++){
                                                                                        if(downloads.get(i).getUserId().equals(downloads.get(j).getUserId())){
                                                                                                downloads.remove(j);
                                                                                                j--;
                                                                                        }
                                                                                }
                                                                        }
                                                                        for(Download download: downloads){
                                                                                infos.add(InfoService.findByUserId(download.getUserId()));
                                                                        }
                                                                }
                                                                break;
                                                        case 2:
                                                                List<App> apps = AppDaoImpl.getInstance().findByAgent(agent, banben, false, caozuoStart, caozuoEnd);
                                                                if(apps.size() > 0){
                                                                        for(int i=0;i<apps.size()-1;i++){
                                                                                for(int j=i+1;j<apps.size();j++){
                                                                                        if(apps.get(i).getUserId().equals(apps.get(j).getUserId())){
                                                                                                apps.remove(j);
                                                                                                j--;
                                                                                        }
                                                                                }
                                                                        }
                                                                        for(App app : apps){
                                                                                infos.add(InfoService.findByUserId(app.getUserId()));
                                                                        }
                                                                }
                                                                break;
                                                        case 3:
                                                                List<App> aps = AppDaoImpl.getInstance().findByAgent(agent, banben, true, caozuoStart, caozuoEnd);
                                                                if(aps.size() > 0){
                                                                        for(int i=0;i<aps.size()-1;i++){
                                                                                for(int j=i+1;j<aps.size();j++){
                                                                                        if(aps.get(i).getUserId().equals(aps.get(j).getUserId())){
                                                                                                aps.remove(j);
                                                                                                j--;
                                                                                        }
                                                                                }
                                                                        }
                                                                        for(App app : aps){
                                                                                infos.add(InfoService.findByUserId(app.getUserId()));
                                                                        }
                                                                }
                                                                break;
                                                        default:break;
                                                }
                                                for(int i=0;i<infos.size();i++){
                                                        if(nodata > 0){
                                                                if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(nodataStart, nodataEnd, infos.get(i).getUserId(), agent)){
                                                                        infos.remove(i);
                                                                        i--;
                                                                        continue;
                                                                }
                                                        }
                                                        if(login > 0){
                                                                if(!LoginDaoImpl.getInstance().exit(loginStart, loginEnd, infos.get(i).getUserId())){
                                                                        infos.remove(i);
                                                                        i--;
                                                                        continue;
                                                                }
                                                        }
                                                }
                                        }else{
                                               //登录，（无数据，版本，语言）
                                                if(login > 0){
                                                        List<Long> logins = LoginDaoImpl.getInstance().findByTime(loginStart, loginEnd);
                                                        if(logins.size() > 0){
                                                                for(int i=0;i<logins.size()-1;i++){
                                                                        for(int j=i+1;j<logins.size();j++){
                                                                                if(logins.get(i).equals(logins.get(j))){
                                                                                        logins.remove(j);
                                                                                        j--;
                                                                                }
                                                                        }
                                                                }
                                                                for(int i=0;i<logins.size();i++){
                                                                        if(nodata > 0){
                                                                                if(AppDataDaoImpl.getInstance().existByTimeAndUserIdAndAgent(nodataStart, nodataEnd, logins.get(i), agent)){
                                                                                        logins.remove(i);
                                                                                        i--;
                                                                                        continue;
                                                                                }
                                                                        }
                                                                        if(agent > 0){
                                                                                if(!AppDaoImpl.getInstance().findByAgentAndUserId(logins.get(i), agent, banben)){
                                                                                        if(!DownDaoImpl.getInstance().findByAgentAndUserId(agent, banben, logins.get(i))){
                                                                                                logins.remove(i);
                                                                                                i--;
                                                                                                continue;
                                                                                        }
                                                                                }
                                                                        }
                                                                        infos.add(InfoService.findByUserId(logins.get(i)));
                                                                }
                                                        }
                                                }else{
                                                        return OneTools.getResult(0, "请按规则填写选项");
                                                }
                                        }
                                }
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                                return OneTools.getResult(0, "服务器错误！");
                        }
                        if(fuze == 1 && infos.size() > 0){
                                for(int i=0;i<infos.size();i++){
                                        Info info = infos.get(i);
                                        if(!((info.getSale() != null && info.getSale().equals(admin.getId())) || (info.getPreSale() != null && info.getPreSale().equals(admin.getId())) || 
                                                        (info.getSupport() != null && info.getSupport().equals(admin.getId())))){
                                                infos.remove(i);
                                                i--;
                                        }
                                }
                        }
                        if(paixu > 0 && infos.size() > 1){
                                SortList<Info> sortList = new SortList<Info>();  
                                switch (paixu) {
                                        case 1:sortList.Sort(infos, "getCreateTime", null);break;
                                        case 2:sortList.Sort(infos, "getUserIdPaixu", null);break;
                                        default:break;
                                }
                        }
                        args1.add("infos");
                        args2.add(InfoService.getArrayFromInfos(infos));
                        return OneTools.getResult(1, args1, args2);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
}
