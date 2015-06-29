package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.AppDataDaoImpl;
import com.oneapm.dao.info.impl.DataDaoImpl;
import com.oneapm.dao.info.impl.LoginDaoImpl;
import com.oneapm.dao.info.impl.SignDaoImpl;
import com.oneapm.dao.info.impl.TianjiaDaoImpl;
import com.oneapm.dao.info.impl.TongjiDaoImpl;
import com.oneapm.dao.info.impl.XiazaiDaoImpl;
import com.oneapm.dao.opt.impl.AddDaoImpl;
import com.oneapm.dao.opt.impl.AddMDaoImpl;
import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.info.Tongji;
import com.oneapm.dto.info.TongjiIndex;
import com.oneapm.dto.tag.Language;
import com.oneapm.dto.tag.Loudou;
import com.oneapm.dto.tag.Metric;
import com.oneapm.dto.tag.Tag;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class TongjiService {

        protected static final Logger LOG = LoggerFactory.getLogger(TongjiService.class);

        public static boolean init() {
                try {
                        // long goutong =
                        // TagService.countLoudou(Loudou.GOUTONG.getId());
                        long jiaoliu = TagService.countLoudou(Loudou.JIAOLIU.getId());
                        long ceshi = TagService.countLoudou(Loudou.CESHI.getId());
                        // long shengchan =
                        // TagService.countLoudou(Loudou.SHENGCHAN.getId());
                        long caigou = TagService.countLoudou(Loudou.CAIGOU.getId());
                        long wancheng_success = TagService.countLoudou(Loudou.FINISH_SUCCESS.getId());
                        long wancheng_fail = TagService.countLoudou(Loudou.FINISH_FAIL.getId());
                        long wancheng = TagService.countLoudou(Loudou.FINISH_SUCCESS.getId()) + TagService.countLoudou(Loudou.FINISH_FAIL.getId());

                        long point = TagService.countMetric(Metric.POINT.getId());
                        long common = TagService.countMetric(Metric.COMMON.getId());
                        long unbin = TagService.countMetric(Metric.UNBIN.getId());
//                        long unuse = TagService.countMetric(Metric.UNUSE.getId());
                        long unuse = 0;

                        long total = InfoService.countTotle();
                        List<Info> signs = InfoService.countSign();
                        List<Info> logins = InfoService.countLogin();
                        List<App> datas = AppService.countData();
                        List<App> datasM = AppService.countDataM();
                        /**
                         * 有数据的应用
                         */
                        if (datasM != null && datasM.size() > 0) {
                                for (App app : datasM) {
                                        datas.add(app);
                                }
                        }
                        List<App> apps = AddDaoImpl.getInstance().findByTime(TimeTools.getDateTime(0));
                        List<App> appsM = AddMDaoImpl.getInstance().findByTime(TimeTools.getDateTime(0));
                        if (appsM != null && appsM.size() > 0) {
                                for (App app : appsM) {
                                        apps.add(app);
                                }
                        }
                        List<Download> downloads = DownDaoImpl.getInstance().findByTime(TimeTools.getDateTime(0));
                        long sign = 0;
                        if (signs != null) {
                                sign = signs.size();
                                for (Info info : signs) {
                                        if (!SignDaoImpl.getInstance().findByIdAndTime(info.getUserId())) {
                                                SignDaoImpl.getInstance().insert(info.getUserId(), info.getCreateTime());
                                        }
                                }
                        }
                        long login = 0;
                        if (logins != null) {
                                login = logins.size();
                                for (Info info : logins) {
                                        if (!LoginDaoImpl.getInstance().findByIdAndTime(TimeTools.getDateTime(0), info.getUserId())) {
                                                LoginDaoImpl.getInstance().insert(info.getUserId(), info.getLoginTime());
                                        }
                                }
                        }
                        long data = 0;
                        long appData = 0;
                        if (datas != null) {
                                Set<Long> set = new HashSet<Long>();
                                for (App app : datas) {
                                        set.add(app.getUserId());
                                        if (!AppDataDaoImpl.getInstance().findByIdAndTime(TimeTools.getDateTime(0), app.getAppId(), Language.getId(app.getLanguage()))) {
                                                AppDataDaoImpl.getInstance().insert(new Aplication(app.getAppId(), Language.getId(app.getLanguage()), app.getUserId(), TimeTools.format(), null));
                                        }
                                        if (!DataDaoImpl.getInstance().findByIdAndTime(TimeTools.getDateTime(0), app.getUserId())) {
                                                DataDaoImpl.getInstance().insert(app.getUserId(), TimeTools.format());
                                        }
                                }
                                appData = datas.size();
                                data = set.size();
                        }
                        long download = 0;
                        if (downloads != null) {
                                Set<Long> set = new HashSet<Long>();
                                for (Download down : downloads) {
                                        set.add(down.getUserId());
                                        if (!XiazaiDaoImpl.getInstance().findByIdAndTime(TimeTools.getDateTime(0), down.getUserId())) {
                                                XiazaiDaoImpl.getInstance().insert(down.getUserId(), down.getDownloadTime());
                                        }
                                }
                                download = set.size();
                        }
                        long app = 0;
                        if (apps != null) {
                                Set<Long> set = new HashSet<Long>();
                                for (App ap : apps) {
                                        set.add(ap.getUserId());
                                        if (!TianjiaDaoImpl.getInstance().findByIdAndTime(TimeTools.getDateTime(0), ap.getUserId())) {
                                                TianjiaDaoImpl.getInstance().insert(ap.getUserId(), ap.getCreateTime());
                                        }
                                }
                                app = set.size();
                        }
                        Tongji tongji = new Tongji(null, total, jiaoliu, ceshi, caigou, wancheng, wancheng_success, wancheng_fail, point, common, unbin, unuse, sign, login, download, app, data, appData);
                        tongji.setData_time(TimeTools.format());
                        Tongji tong = TongjiDaoImpl.getInstance().findByTime(TimeTools.getDateTime(0));
                        if (tong == null) {
                                return TongjiDaoImpl.getInstance().insert(tongji);
                        } else {
                                tongji.setId(tong.getId());
                                return TongjiDaoImpl.getInstance().update(tongji);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

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
                        /*if(index.getTongjis().get(0).getData() > 410){
                                index.getTongjis().get(0).setData((index.getTongjis().get(0).getData()-410)/2 + 410);
                        }*/
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
                tongji.setSignUp(tongji.getSign() - before.getSign() > 0 ? 1 : tongji.getSign() - before.getSign() == 0 ? 2 : 3);
                tongji.setLoginUp(tongji.getLogin() - before.getLogin() > 0 ? 1 : tongji.getLogin() - before.getLogin() == 0 ? 2 : 3);
                tongji.setDownloadUp(tongji.getDownload() - before.getDownload() > 0 ? 1 : tongji.getDownload() - before.getDownload() == 0 ? 2 : 3);
                tongji.setAppUp(tongji.getApp() - before.getApp() > 0 ? 1 : tongji.getApp() - before.getApp() == 0 ? 2 : 3);
                tongji.setDataUp(tongji.getData() - before.getData() > 0 ? 1 : tongji.getData() - before.getData() == 0 ? 2 : 3);
                try {
                        tongji.setAppDataUp(tongji.getAppData() - before.getAppData() > 0 ? 1 : tongji.getAppData() - before.getAppData() == 0 ? 2 : 3);
                } catch (Exception e) {
                }
                tongji.setPointUp(tongji.getPoint() - before.getPoint() > 0 ? 1 : tongji.getPoint() - before.getPoint() == 0 ? 2 : 3);
                tongji.setCommonUp(tongji.getCommon() - before.getCommon() > 0 ? 1 : tongji.getCommon() - before.getCommon() == 0 ? 2 : 3);
                tongji.setUnbinUp(tongji.getUnbin() - before.getUnbin() > 0 ? 1 : tongji.getUnbin() - before.getUnbin() == 0 ? 2 : 3);
                tongji.setUnuseUp(tongji.getUnuse() - before.getUnuse() > 0 ? 1 : tongji.getUnuse() - before.getUnuse() == 0 ? 2 : 3);
                tongji.setJiaoliuUp(tongji.getJiaoliu() - before.getJiaoliu() > 0 ? 1 : tongji.getJiaoliu() - before.getJiaoliu() == 0 ? 2 : 3);
                tongji.setCeshiUp(tongji.getCeshi() - before.getCeshi() > 0 ? 1 : tongji.getCeshi() - before.getCeshi() == 0 ? 2 : 3);
                tongji.setCaigouUp(tongji.getCaigou() - before.getCaigou() > 0 ? 1 : tongji.getCaigou() - before.getCaigou() == 0 ? 2 : 3);
                tongji.setWanchengUp(tongji.getWancheng() - before.getWancheng() > 0 ? 1 : tongji.getWancheng() - before.getWancheng() == 0 ? 2 : 3);
                tongji.setWancheng_successUp(tongji.getWancheng_success() - before.getWancheng_success() > 0 ? 1 : tongji.getWancheng_success() - before.getWancheng_success() == 0 ? 2 : 3);
                tongji.setWancheng_failUp(tongji.getWancheng_fail() - before.getWancheng_fail() > 0 ? 1 : tongji.getWancheng_fail() - before.getWancheng_fail() == 0 ? 2 : 3);
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
                        switch (type) {
                        // 注册
                        case 2:
                                index.setName("新注册用户");
                                ids = SignDaoImpl.getInstance().findByTime(start, end);
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
                                ids = DataDaoImpl.getInstance().findByTime(start, end);
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
                                IDS = DataDaoImpl.getInstance().findByTime(before, start);
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
                        case 7:
                                byId = true;
                                index.setName("重点用户");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 8:
                                byId = true;
                                index.setName("普通用户");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 9:
                                byId = true;
                                index.setName("未定义用户");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 10:
                                byId = true;
                                index.setName("已关闭用户");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 11:
                                byId = true;
                                index.setName("交流");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 12:
                                byId = true;
                                index.setName("测试");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 13:
                                byId = true;
                                index.setName("采购");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 14:
                                byId = true;
                                index.setName("完成");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 15:
                                byId = true;
                                index.setName("成单");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        case 16:
                                byId = true;
                                index.setName("输单");
                                tags = TagService.findByTag(type);
                                if (tags != null && tags.size() > 0) {
                                        for (Tag tag : tags) {
                                                ups.add(tag.getInfoId());
                                        }
                                }
                                break;
                        default:
                                break;
                        }
                        if (byId) {
                                index.setTodayUp(getInfosFromIds(ups, admin));
                                index.setTodayDown(getInfosFromIds(downs, admin));
                                index.setToday(getInfosFromIds(common, admin));
                        } else {
                                index.setTodayUp(getInfosFromUserIds(ups, admin));
                                index.setTodayDown(getInfosFromUserIds(downs, admin));
                                index.setToday(getInfosFromUserIds(common, admin));
                        }
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

        public static List<Info> getInfosFromUserIds(List<Long> ids, Admin admin) {
                if (ids == null || ids.size() <= 0)
                        return null;
                List<Info> infos = new ArrayList<Info>();
                Set<Long> set = new HashSet<Long>();
                for (Long id : ids) {
                        if (!set.contains(id)) {
                                try {
                                        Info info = InfoService.findByUserIdSingle(id);
                                        InfoService.power(admin.getId(), admin.getGroup(), info);
                                        infos.add(info);
                                        set.add(id);
                                } catch (Exception e) {
                                        LOG.error(e.getMessage(), e);
                                }
                        }
                }
                return infos;
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
