package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.group.impl.UserGroupDaoImpl;
import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.info.impl.DataDaoImpl;
import com.oneapm.dao.info.impl.LoginDaoImpl;
import com.oneapm.dao.info.impl.TianjiaDaoImpl;
import com.oneapm.dao.info.impl.TongjiDaoImpl;
import com.oneapm.dao.info.impl.XiazaiDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.UserGroup;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.info.Tongji;
import com.oneapm.dto.info.TongjiIndex;
import com.oneapm.dto.tag.Tag;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class TongjiService {

        protected static final Logger LOG = LoggerFactory.getLogger(TongjiService.class);


        public static TongjiIndex index() {
                try {
                        List<Tongji> tongjis = find(-1L, 30);
                        TongjiIndex index = new TongjiIndex();
                        index.setTongjis(tongjis);
                        Long id = tongjis.get(tongjis.size() - 1).getId();
                        index.setId(id - 1L);
                        index.setNext(false);
                        if (id > 0L) {
                                if (TongjiDaoImpl.getInstance().findById(id - 1L) != null) {
                                        index.setNext(true);
                                }
                        }
                        return index;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public static Tongji findLast(){
                return TongjiDaoImpl.getInstance().findLast();
        }

        public static List<Tongji> find(Long start, int number) {
                List<Tongji> tongjis = TongjiDaoImpl.getInstance().index(start, number);
                if (tongjis != null && tongjis.size() > 0) {
                        for (int i = 0; i < tongjis.size(); i++) {
                                Tongji tongji = tongjis.get(i);
                                tongji.setData_time(tongji.getData_time().substring(0, 10));
                                if (i + 1 < tongjis.size()) {
                                        Tongji before = tongjis.get(i + 1);
                                        setUp(tongji, before);
                                } else {
                                        Tongji before = TongjiDaoImpl.getInstance().findById(tongji.getId() - 1L);
                                        if (before != null) {
                                                setUp(tongji, before);
                                        }
                                }
                        }
                }
                return tongjis;
        }

        public static void setUp(Tongji tongji, Tongji before) {
                tongji.setTotalUp(tongji.getTotal() - before.getTotal() > 0 ? 1 : tongji.getTotal() - before.getTotal() == 0 ? 2 : 3);
                tongji.setGroupUp(tongji.getGroup() - before.getGroup() > 0 ? 1 : tongji.getGroup() - before.getGroup() == 0 ? 2 : 3);
                tongji.setLoginUp(tongji.getLogin() - before.getLogin() > 0 ? 1 : tongji.getLogin() - before.getLogin() == 0 ? 2 : 3);
                tongji.setDownloadUp(tongji.getDownload() - before.getDownload() > 0 ? 1 : tongji.getDownload() - before.getDownload() == 0 ? 2 : 3);
                tongji.setAppUp(tongji.getApp() - before.getApp() > 0 ? 1 : tongji.getApp() - before.getApp() == 0 ? 2 : 3);
                tongji.setDataUp(tongji.getData() - before.getData() > 0 ? 1 : tongji.getData() - before.getData() == 0 ? 2 : 3);
                try {
                        tongji.setAppDataUp(tongji.getAppData() - before.getAppData() > 0 ? 1 : tongji.getAppData() - before.getAppData() == 0 ? 2 : 3);
                } catch (Exception e) {
                }
        }

        @SuppressWarnings("unchecked")
        public static String index(Long start) {
                JSONObject object = new JSONObject();
                try {
                        List<Tongji> tongjis = find(start, 5);
                        if (tongjis == null || tongjis.size() <= 0) {
                                return OneTools.getResult(0, "没有更多数据了！");
                        }
                        JSONArray array = getArrayFromTongjis(tongjis);
                        object.put("tongjis", array);
                        Long id = tongjis.get(tongjis.size() - 1).getId();
                        object.put("id", id - 1L);
                        object.put("isNext", false);
                        if (id > 0L) {
                                Tongji tongji = TongjiDaoImpl.getInstance().findById(id - 1L);
                                if (tongji != null) {
                                        object.put("isNext", true);
                                }
                        }
                        object.put("status", 1);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器错误！");
                }
                return object.toJSONString();
        }

        public static TongjiIndex findCompile(Long id, int type, Admin admin) {
                try {
                        TongjiIndex index = new TongjiIndex();
                        Tongji tongji = TongjiDaoImpl.getInstance().findById(id);
                        index.setDate(tongji.getData_time().substring(0, 10));
                        Tongji tong = TongjiDaoImpl.getInstance().findById(id + 1L);
                        Tongji ton = TongjiDaoImpl.getInstance().findById(id - 1L);
                        String start = TimeTools.getDateTime(tongji.getData_time());
                        String end = null;
                        String before = null;
                        if (tong != null) {
                                end = TimeTools.getDateTime(tong.getData_time());
                        }
                        if (ton != null) {
                                before = TimeTools.getDateTime(ton.getData_time());
                        }
                        List<Long> ids = null;
                        List<Long> IDS = null;
                        List<Long> ups = new ArrayList<Long>();
                        List<Long> downs = new ArrayList<Long>();
                        List<Long> common = new ArrayList<Long>();
                        boolean find = false;
                        List<Tag> tags = null;
                        boolean byId = false;
                        List<Long> upsgroup = new ArrayList<Long>();
                        List<Long> downsgroup = new ArrayList<Long>();
                        List<Long> commongroup = new ArrayList<Long>();
                        switch (type) {
                        // 注册
                        case 2:
                                index.setName("新注册用户");
                                ids = UserGroupsDaoImpl.getInstance().findByTime(start, end);
                                ups = ids;
                                index.setName("新注册用户");
                                break;
                        // 登录
                        case 3:
                                index.setName("当日登录");
                                ids = LoginDaoImpl.getInstance().findByTime(start, end);
                                if (ids == null) {
                                        return null;
                                }
                                if (ids.size() > 1) {
                                        for (int i = 0; i < ids.size() - 1; i++) {
                                                for (int j = i + 1; j < ids.size(); j++) {
                                                        if (ids.get(i).equals(ids.get(j))) {
                                                                ids.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                IDS = LoginDaoImpl.getInstance().findByTime(before, start);
                                if (IDS == null) {
                                        ups = ids;
                                        break;
                                }
                                if (IDS.size() > 1) {
                                        for (int i = 0; i < IDS.size() - 1; i++) {
                                                for (int j = i + 1; j < IDS.size(); j++) {
                                                        if (IDS.get(i).equals(IDS.get(j))) {
                                                                IDS.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                for (int i = 0; i < ids.size(); i++) {
                                        find = false;
                                        for (Long ID : IDS) {
                                                if (ID.equals(ids.get(i))) {
                                                        find = true;
                                                        common.add(ID);
                                                        break;
                                                }
                                        }
                                        if (!find) {
                                                ups.add(ids.get(i));
                                        }
                                }
                                for (int i = 0; i < IDS.size(); i++) {
                                        find = false;
                                        for (Long ID : ids) {
                                                if (ID.equals(IDS.get(i))) {
                                                        find = true;
                                                        break;
                                                }
                                        }
                                        if (!find) {
                                                downs.add(IDS.get(i));
                                        }
                                }
                                for(Long iduser : ups){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                upsgroup.add(group.getGroupId());
                                        }
                                }
                                for(Long iduser : downs){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                downsgroup.add(group.getGroupId());
                                        }
                                }
                                for(Long iduser : common){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                commongroup.add(group.getGroupId());
                                        }
                                }
                                ups = upsgroup;
                                downs = downsgroup;
                                common = commongroup;
                                break;
                        case 4:
                                index.setName("当日下载");
                                ids = XiazaiDaoImpl.getInstance().findByTime(start, end);
                                if (ids == null) {
                                        return null;
                                }
                                if (ids.size() > 1) {
                                        for (int i = 0; i < ids.size() - 1; i++) {
                                                for (int j = i + 1; j < ids.size(); j++) {
                                                        if (ids.get(i).equals(ids.get(j))) {
                                                                ids.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                ups = ids;
                                for(Long iduser : ups){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                upsgroup.add(group.getGroupId());
                                        }else{
                                                UserGroup userGroup = UserGroupDaoImpl.getInstance().findUsersByUserId(iduser);
                                                if(userGroup != null){
                                                        group = UserGroupsDaoImpl.getInstance().findById(userGroup.getGroupId());
                                                        if(group != null){
                                                                if(group.getParentId() > 0){
                                                                        group = UserGroupsDaoImpl.getInstance().findById(group.getParentId());
                                                                        if(group != null){
                                                                                upsgroup.add(group.getGroupId());
                                                                        }
                                                                }else{
                                                                        upsgroup.add(group.getGroupId());
                                                                }
                                                        }
                                                }
                                        }
                                }
                                for(Long iduser : downs){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                downsgroup.add(group.getGroupId());
                                        }
                                }
                                for(Long iduser : common){
                                        UserGroups group = UserGroupsDaoImpl.getInstance().findByAdminId(iduser);
                                        if(group != null){
                                                commongroup.add(group.getGroupId());
                                        }
                                }
                                ups = upsgroup;
                                downs = downsgroup;
                                common = commongroup;
                                break;
                        case 5:
                                index.setName("当日添加应用");
                                ids = TianjiaDaoImpl.getInstance().findByTime(start, end);
                                if (ids == null) {
                                        return null;
                                }
                                if (ids.size() > 1) {
                                        for (int i = 0; i < ids.size() - 1; i++) {
                                                for (int j = i + 1; j < ids.size(); j++) {
                                                        if (ids.get(i).equals(ids.get(j))) {
                                                                ids.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                ups = ids;
                                break;
                        case 6:
                                index.setName("当日有数据");
                                ids = AppDataDaoImpl.getInstance().findLongByTime(start, end);
                                if (ids == null) {
                                        return null;
                                }
                                if (ids.size() > 1) {
                                        for (int i = 0; i < ids.size() - 1; i++) {
                                                for (int j = i + 1; j < ids.size(); j++) {
                                                        if (ids.get(i).equals(ids.get(j))) {
                                                                ids.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                IDS = AppDataDaoImpl.getInstance().findLongByTime(before, start);
                                if (IDS == null) {
                                        ups = ids;
                                        break;
                                }
                                if (IDS.size() > 1) {
                                        for (int i = 0; i < IDS.size() - 1; i++) {
                                                for (int j = i + 1; j < IDS.size(); j++) {
                                                        if (IDS.get(i).equals(IDS.get(j))) {
                                                                IDS.remove(j);
                                                                j--;
                                                        }
                                                }
                                        }
                                }
                                for (int i = 0; i < ids.size(); i++) {
                                        find = false;
                                        for (Long ID : IDS) {
                                                if (ID.equals(ids.get(i))) {
                                                        find = true;
                                                        common.add(ID);
                                                        break;
                                                }
                                        }
                                        if (!find) {
                                                ups.add(ids.get(i));
                                        }
                                }
                                for (int i = 0; i < IDS.size(); i++) {
                                        find = false;
                                        for (Long ID : ids) {
                                                if (ID.equals(IDS.get(i))) {
                                                        find = true;
                                                        break;
                                                }
                                        }
                                        if (!find) {
                                                downs.add(IDS.get(i));
                                        }
                                }
                                break;
                        default:
                                break;
                        }
                        index.setTodayUp(getInfosFromUserIds(ups, admin));
                        index.setTodayDown(getInfosFromUserIds(downs, admin));
                        index.setToday(getInfosFromUserIds(common, admin));
                        return index;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static List<Info> getInfosFromIds(List<Long> ids, Admin admin) {
                if (ids == null || ids.size() <= 0)
                        return null;
                List<Info> infos = new ArrayList<Info>();
                Set<Long> set = new HashSet<Long>();
                for (Long id : ids) {
                        if (!set.contains(id)) {
                                Info info = InfoService.findByIdSingle(id);
                                InfoService.power(admin.getId(), admin.getGroup(), info);
                                infos.add(info);
                                set.add(id);
                        }
                }
                return infos;
        }

        public static List<UserGroups> getInfosFromUserIds(List<Long> ids, Admin admin) {
                if (ids == null || ids.size() <= 0)
                        return null;
                JSONArray array = new JSONArray();
                for(Long id: ids){
                        array.add(id);
                }
                List<UserGroups> groups = UserGroupsDaoImpl.getInstance().findByArray(array);
                for(UserGroups userGroups : groups){
                        try{
                                UserGroupService.init(userGroups, true, true, true, true, admin.getId(), admin.getGroup());
                        }catch(Exception e){
                                LOG.error(e.getMessage(), e);
                        }
                }
//                for (Long id : ids) {
//                        try {
//                                UserGroups userGroups = UserGroupService.findByGroupIdInitTagAndLan(id);
//                                UserGroupService.power(admin.getId(), admin.getGroup(), userGroups);
//                                groups.add(userGroups);
//                        } catch (Exception e) {
//                                LOG.error(e.getMessage(), e);
//                        }
//                }
                return groups;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromTongjis(List<Tongji> tongjis) {
                if (tongjis == null || tongjis.size() <= 0) {
                        return null;
                }
                JSONArray array = new JSONArray();
                for (Tongji tongji : tongjis) {
                        array.add(getJSONFromTongji(tongji));
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromTongji(Tongji tongji) {
                JSONObject object = null;
                try {
                        object = new JSONObject();
                        object.put("id", tongji.getId());
                        object.put("total", tongji.getTotal());
                        object.put("jiaoliu", tongji.getJiaoliu());
                        object.put("ceshi", tongji.getCeshi());
                        object.put("caigou", tongji.getCaigou());
                        object.put("wancheng_success", tongji.getWancheng_success());
                        object.put("wancheng_fail", tongji.getWancheng_fail());
                        object.put("wancheng", tongji.getWancheng());
                        object.put("point", tongji.getPoint());
                        object.put("common", tongji.getCommon());
                        object.put("unbin", tongji.getUnbin());
                        object.put("unuse", tongji.getUnuse());
                        object.put("sign", tongji.getSign());
                        object.put("login", tongji.getLogin());
                        object.put("download", tongji.getDownload());
                        object.put("app", tongji.getApp());
                        object.put("data", tongji.getData());
                        object.put("data_time", tongji.getData_time());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
