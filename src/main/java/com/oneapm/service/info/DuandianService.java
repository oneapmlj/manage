package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupView;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.lable.Lable;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.lable.LableService;
import com.oneapm.service.mail.DownloadService;
import com.oneapm.service.message.TipService;
import com.oneapm.util.OneTools;
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
                                int power = InfoService.power(99999999L, 7, info);
                        }
                        args1.add("infos");
                        args2.add(InfoService.getArrayFromInfos(infos));
                        return OneTools.getResult(1, args1, args2);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        public static String chaxun(int agent, int data, int paixu, int sort, int jinri, String banben, String time, Admin admin, int fuze, Long groupId1, Long groupId2){
                try{
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        String dataTime = TimeTools.getDateTime(0);
                        List<Info> infos = new ArrayList<Info>();
                        String start = "2014-01-01 00:00:00";
                        String end = TimeTools.getDateTime(-1);
                        switch(data){
                                case 1:start = "2014-01-01 00:00:00";break;
                                case 2:start = TimeTools.getDateTime(7);break;
                                case 3:start = TimeTools.getDateTime(14);break;
                                case 4:start = TimeTools.getDateTime(30);break;
                                case 7:start = TimeTools.getDateTime(0);break;
                                case 8:
                                        start = TimeTools.getDateTime(1);
                                        end = TimeTools.getDateTime(0);
                                        break;
                                case 9:
                                        if(time == null || time.trim().length() <= 0){
                                                return OneTools.getResult(0, "参数错误");
                                        }
                                        start = time+" 00:00:00";
                                        try{
                                                TimeTools.formatTime.parse(start);
                                        }catch (Exception e) {
                                                return OneTools.getResult(0, "参数错误");
                                        }
                                        if(TimeTools.formatTime.parse(start).getTime() > TimeTools.formatTime.parse(TimeTools.getDateTime(0)).getTime()){
                                                return OneTools.getResult(0, "我不是先知啊，未来的数据我可预言不到");
                                        }
                                        end = TimeTools.next(start, 1);
                                        break;
                                default:break;
                        }
                        if(banben.equals("null")){
                                banben = null;
                        }
                        if(data <= 5 || data == 7 || data ==8 || data == 9){
                               if(data == 5){
                                        List<App> apps = AppService.findAppByAgent(agent, start, banben, end);
                                        for(int i=0;i<apps.size();i++){
                                                for(int j=i+1;j<apps.size();j++){
                                                        if(apps.get(i).getUserId().equals(apps.get(j).getUserId())){
                                                                apps.remove(j);
                                                                j--;
                                                        }
                                                }
                                                infos.add(InfoService.findByUserId(apps.get(i).getUserId(), admin)) ;
                                        }
                                }else{
                                        List<Aplication> apps = AppService.findByAgent(agent, start, banben, end);
                                        for(int i=0;i<apps.size();i++){
                                                for(int j=i+1;j<apps.size();j++){
                                                        if(apps.get(i).getUserId().equals(apps.get(j).getUserId())){
                                                                apps.remove(j);
                                                                j--;
                                                        }
                                                }
                                                infos.add(InfoService.findByUserId(apps.get(i).getUserId(), admin)) ;
                                        }
                                }
                        }else{
                                List<Download> downloads = DownloadService.findByAgent(agent, banben);
                                for(int i=0;i<downloads.size();i++){
                                        for(int j=i+1;j<downloads.size();j++){
                                                if(downloads.get(i).getUserId().equals(downloads.get(j).getUserId())){
                                                        downloads.remove(j);
                                                        j--;
                                                }
                                        }
                                        infos.add(InfoService.findByUserId(downloads.get(i).getUserId(), admin)) ;
                                }
                        }
                        boolean JINRI = false;
                        if(jinri == 1){
                                if(!start.equals(TimeTools.getDateTime(0))){
                                        JINRI = true;
                                }
                        }
                        for(int i=0;i<infos.size();i++){
                                if (infos.get(i) != null) {
                                        if(fuze == 1){
                                                if(!admin.getId().equals(infos.get(i).getSale()) && !admin.getId().equals(infos.get(i).getSupport()) && !admin.getId().equals(infos.get(i).getPreSale())){
                                                        infos.remove(i);
                                                        i--;
                                                        continue;
                                                }
                                        }
                                        InfoService.initTag(infos.get(i));
                                        if(groupId1 > 0){
                                                GroupView view = GroupService.findByInfoId(infos.get(i).getId());
                                                if(groupId2 > 0){
                                                        if(!view.getGroupId().equals(groupId2)){
                                                                infos.remove(i);
                                                                i--;
                                                                continue;  
                                                        }else{
                                                                Group group = GroupService.findById(view.getGroupId());
                                                                if(!group.getFather().equals(groupId2)){
                                                                        infos.remove(i);
                                                                        i--;
                                                                        continue;  
                                                                }
                                                        }
                                                }else{
                                                        if(!view.getGroupId().equals(groupId1)){
                                                                infos.remove(i);
                                                                i--;
                                                                continue;  
                                                        }
                                                }
                                        }
                                        if(JINRI){
                                                if(agent == 0){
                                                        if(AppDataDaoImpl.getInstance().exist(infos.get(i).getUserId(), dataTime, agent)){
                                                                infos.remove(i);
                                                                i--;
                                                                continue;
                                                        }
                                                }else{                                                                                                  
                                                        if(AppDataDaoImpl.getInstance().exist(infos.get(i).getUserId(), dataTime, agent)){
                                                                infos.remove(i);
                                                                i--;
                                                                continue;
                                                        }
                                                }
                                        }
                                        int power = InfoService.power(admin.getId(), admin.getGroup(), infos.get(i));
                                        if (power > 0) {
                                                InfoService.initInfo(infos.get(i));
                                        } else {
                                                return null;
                                        }
                                        infos.get(i).setMark(MarkService.findByAdminIdAndInfoId(admin.getId(), infos.get(i).getId()));
                                        infos.get(i).setTip(TipService.findByInfoId(infos.get(i).getId()));
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
