package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.AppDaoImpl;
import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.info.impl.LoginDaoImpl;
import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupView;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.lable.Lable;
import com.oneapm.dto.tag.Language;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.lable.LableService;
import com.oneapm.service.mail.DownloadService;
import com.oneapm.util.OneTools;
import com.oneapm.util.SortList;
import com.oneapm.util.TimeTools;

public class DuandianService {

        protected static final Logger LOG = LoggerFactory.getLogger(DuandianService.class);
        
        public final static int pageNum = 30;

        
        public static String findVersions(int agent){
                try{
                        List<String> versions = new ArrayList<String>();
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
                        String nodataEnd, int nodata, int duli, int login, String loginStart, String loginEnd, int caozuo, Admin admin){
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
                        if(login > 0){
                                switch(login){
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
                                                                List<Download> downloads = DownloadService.findByAgent(agent, banben);
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
                                                                List<App> apps = AppDaoImpl.getInstance().findByAgent(agent, banben, false);
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
                                                                List<App> aps = AppDaoImpl.getInstance().findByAgent(agent, banben, true);
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
                                                                                if(AppDaoImpl.getInstance().findByAgentAndUserId(logins.get(i), agent, banben)){
                                                                                        logins.remove(i);
                                                                                        i--;
                                                                                        continue;
                                                                                }
                                                                                if(DownDaoImpl.getInstance().findByAgentAndUserId(agent, banben, logins.get(i))){
                                                                                        logins.remove(i);
                                                                                        i--;
                                                                                        continue;
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
                                        if(!(info.getSale().equals(admin.getId()) || info.getPreSale().equals(admin.getId()) || info.getSupport().equals(admin.getId()))){
                                                infos.remove(i);
                                                i--;
                                        }
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
