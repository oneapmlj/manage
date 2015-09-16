package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.TagDaoImpl;
import com.oneapm.dto.App;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.tag.Category;
import com.oneapm.dto.tag.From;
import com.oneapm.dto.tag.Fuwuqi;
import com.oneapm.dto.tag.Language;
import com.oneapm.dto.tag.Loudou;
import com.oneapm.dto.tag.Metric;
import com.oneapm.dto.tag.Person;
import com.oneapm.dto.tag.Province;
import com.oneapm.dto.tag.Rongzi;
import com.oneapm.dto.tag.Tag;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.service.message.MessageService;
import com.oneapm.service.record.RecordService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class TagService extends OneTools {

        protected static final Logger LOG = LoggerFactory.getLogger(TagService.class);

        public static List<Tag> findByLanguage(String language) {
                return TagDaoImpl.getInstance().findByLanguage(language);
        }

        public static String support(Long id, Long adminId) {
                try {
                        if (id == null || id <= 0) {
                                return OneTools.getResult(0, "id有误");
                        }
                        Tag tag = findById(id);
                        if (tag == null) {
                                return OneTools.getResult(0, "id有误");
                        }
                        Info info = InfoService.findByIdSimple(tag.getInfoId());
                        info.setSupport(adminId);
                        if (InfoService.update(info)) {
                                return OneTools.getResult(1, "");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String languageList(Long infoId) {
                try {
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        Tag tag = findByInfoId(infoId);
                        String language = tag.getLanguage();
                        if (language != null && language.length() > 0) {
                                String[] str = language.trim().split(OneTools.sp);
                                if (str != null && str.length > 0) {
                                        JSONArray array = new JSONArray();
                                        for (String s : str) {
                                                array.put(s);
                                        }
                                        args1.add("length");
                                        args1.add("language");
                                        args2.add(str.length);
                                        args2.add(array);
                                }
                        }
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }

        public static String metric(Long infoId, int metric, Admin admin) {
                try {
                        Info info = InfoService.findByIdSimple(infoId);
                        if (!SupportAction.quanxian(admin.getGrades(), SupportAction.getGRADE().getMap().get(106)) && (info.getSale() == null || ((info.getSale() != null && info.getSale() > 0) && !info.getSale().equals(admin.getId())))) {
                                return OneTools.getResult(0, "权限不足");
                        }
                        Tag tag = findByInfoId(infoId);
                        if (tag.getMetric() == metric) {
                                return OneTools.getResult(0, "定位已经是" + Metric.findById(metric).getName() + "了");
                        }
                        RecordService.insert(admin.getId(), 19, infoId, null, 0, metric, 0, tag.getMetric(), tag.getLoudou());
                        tag.setMetric(metric);
                        update(tag);
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("metric");
                        args2.add(metric);
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }
        
        public static String metricWithGroupId(Long groupId, int metric, Admin admin) {
            try {
                    UserGroups userGroups = UserGroupService.findByGroupIdSimple(groupId);
                    if (!SupportAction.quanxian(admin.getGrades(), SupportAction.getGRADE().getMap().get(106)) && (userGroups.getSale() == null || ((userGroups.getSale() != null && userGroups.getSale() > 0) && !userGroups.getSale().equals(admin.getId())))) {
                            return OneTools.getResult(0, "权限不足");
                    }
                    Tag tag = findByGroupId(groupId);
                    if (tag.getMetric() == metric) {
                            return OneTools.getResult(0, "定位已经是" + Metric.findById(metric).getName() + "了");
                    }
                    Long infoId = 0L;
                    RecordService.insertWithGroupId(admin.getId(), 19, infoId, null, 0, metric, 0, tag.getMetric(), tag.getLoudou(),userGroups.getGroupId());
                    tag.setMetric(metric);
                    update(tag);
                    List<String> args1 = new ArrayList<String>();
                    List<Object> args2 = new ArrayList<Object>();
                    args1.add("metric");
                    args2.add(metric);
                    return OneTools.getResult(1, args1, args2);
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return OneTools.getResult(0, "服务器错误");
    }

        public static String loudou(Long infoId, Admin admin) {
                try {
                        Info info = InfoService.findByIdSimple(infoId);
                        if ((info.getSale() == null || !info.getSale().equals(admin.getId())) && admin.getGroup() < 4) {
                                return OneTools.getResult(0, "权限不足");
                        }
                        Tag tag = findByInfoId(infoId);
                        int loudou = tag.getLoudou() + 1;
                        if (loudou > 6) {
                                return OneTools.getResult(0, "已经到最高进度了");
                        }
                        RecordService.insert(admin.getId(), 20, infoId, null, 0, 0, loudou, tag.getMetric(), tag.getLoudou());
                        if (loudou == 3) {
                                List<Admin> admins = AccountService.findByGroup(1);
                                for (Admin ad : admins) {
                                        MessageService.insert(admin.getId(), ad.getId(), 0, infoId, 20);
                                }
                        }
                        tag.setLoudou(loudou);
                        ;
                        update(tag);
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("loudou");
                        args2.add(Loudou.findById(loudou).getName());
                        if ((loudou + 1) <= 6) {
                                args1.add("next");
                                args2.add(Loudou.findById(loudou + 1).getName());
                        }
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static String language(Long infoId, int id) {
                try {
                        Tag tag = findByInfoId(infoId);
                        String language = tag.getLanguage();
                        StringBuilder builder = new StringBuilder();
                        boolean in = false;
                        if (language != null && language.length() > 0) {
                                String[] str = language.trim().split(OneTools.sp);
                                for (String s : str) {
                                        if (Integer.parseInt(s) == id) {
                                                in = true;
                                        } else {
                                                builder.append(s).append(OneTools.sp);
                                        }
                                }
                                if (!in) {
                                        builder.append(id).append(OneTools.sp);
                                }
                        } else {
                                builder.append(id).append(OneTools.sp);
                        }
                        tag.setLanguage(builder.toString());
                        TagDaoImpl.getInstance().update(tag);
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("language");
                        args2.add(id);
                        args1.add("add");
                        if (in) {
                                args2.add(0);
                        } else {
                                args2.add(1);
                        }
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static long countLoudou(Integer id) {
                return TagDaoImpl.getInstance().countLoudou(id);
        }

        public static long countMetric(Integer id) {
                return TagDaoImpl.getInstance().countMetric(id);
        }

        public static String sals(Long id, Long adminId) {
                try {
                        if (id == null || id <= 0) {
                                return getResult(0, "id有误");
                        }
                        Tag tag = findById(id);
                        if (tag == null) {
                                return getResult(0, "id有误");
                        }
                        Info info = InfoService.findByIdSimple(tag.getInfoId());
                        info.setSale(adminId);
                        if (InfoService.update(info)) {
                                return getResult(1, "");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }

        public static List<Tag> search(int metric, int loudou) {
                List<Tag> tags = TagDaoImpl.getInstance().search(metric, loudou);
                return tags;
        }

        public static String update(Long adminId, Long id, Integer category, int person, int province, int from, String description, int rongzi, int fuwuqi) {
                try {
                        if (id == null || id <= 0) {
                                return getResult(0, "id错误");
                        }
                        Tag tag = findById(id);
                        if (category > 0) {
                                tag.setCategory(category);
                        }
                        if (from > 0) {
                                tag.setFrom(from);
                        }
                        if (person > 0) {
                                tag.setPerson(person);
                        }
                        if (province > 0) {
                                tag.setProvince(province);
                        }
                        tag.setFuwuqi(fuwuqi);
//                        if (uv > 0) {
//                                tag.setUv(uv);
//                        }
//                        if (pv > 0) {
//                                tag.setPv(pv);
//                        }
                        if(rongzi > 0){
                                tag.setRongzi(rongzi);
                        }
                        if (description != null) {
                                tag.setDescription(description);
                        }
                        if (description != null && description.length() > 0) {
                                tag.setDescription(description);
                        }
                        if (update(tag)) {
                                return getResult(1, "");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }

        public static boolean update(Tag tag) {
                return TagDaoImpl.getInstance().update(tag);
        }

        public static String view(Long id) {
                try {
                        Tag tag = findById(id);
                        if (tag == null) {
                                return getResult(0, "参数错误");
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("tag");
                        args2.add(getJSONFromTag(tag));
                        return getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return getResult(0, "服务器内部错误");
        }

        public static Tag findById(Long id) {
                Tag tag = TagDaoImpl.getInstance().findById(id);
                initTag(tag);
                return tag;
        }

        public static Tag findByInfoId(Long infoId)  {
                Tag tag = TagDaoImpl.getInstance().findByInfoId(infoId);
                if (tag == null) {
                        tag = new Tag(null, infoId, 0, 0, new Integer(0), new Integer(0), null, 0, new Integer(0), new Integer(0), null, new Integer(0), new Integer(0));
                        initLanguage(tag);
                        tag = TagDaoImpl.getInstance().insert(tag);
                } else {
                        if (tag.getLanguage() == null || tag.getLanguage().trim().equals("") || tag.getLanguage().equals("0")) {
                                initLanguage(tag);
                                update(tag);
                        }
                }
                initTag(tag);
                return tag;
        }
        
        public static Tag findByGroupId(Long groupId)  {
            Tag tag = TagDaoImpl.getInstance().findByGroupId(groupId);
            if (tag == null) {
                    tag = new Tag(null, groupId, 0, 0, new Integer(0), new Integer(0), null, 0, new Integer(0), new Integer(0), null, new Integer(0), new Integer(0));
                    tag.setGroupId(groupId);
                    initLanguageByGroupId(tag);
                    tag = TagDaoImpl.getInstance().insert(tag);
            } else {
                    if (tag.getLanguage() == null || tag.getLanguage().trim().equals("") || tag.getLanguage().equals("0")) {
                            initLanguageByGroupId(tag);
                            update(tag);
                    }
            }
            initTag(tag);
            return tag;
    }

        public static void initLanguage(Tag tag)  {
                if (tag == null)
                        return;
                Info info = InfoService.findByIdSimple(tag.getInfoId());
                if (info == null)
                        return;
                List<App> apps = AppService.findByUserId(info.getUserId());
//                List<App> appsM = AppService.findByUserIdM(info.getUserId());
                StringBuilder builder = new StringBuilder();
                Set<String> set = new HashSet<String>();
                if (apps != null && apps.size() > 0) {
                        for (App app : apps) {
                                if (Language.getName(app.getAgent()) != null) {
                                        set.add(Language.getName(app.getAgent()));
                                }
                        }
                }
//                if (appsM != null && appsM.size() > 0) {
//                        for (App app : appsM) {
//                                if (Language.getName(app.getAgent()) != null) {
//                                        set.add(Language.getName(app.getAgent()));
//                                }
//                        }
//                }
                if (set.size() > 0) {
                        for (String s : set) {
                                builder.append(Language.getId(s.trim())).append(OneTools.sp);
                        }
                }
                tag.setLanguage(builder.toString());
        }
        
        public static void initLanguageByGroupId(Tag tag)  {
            if (tag == null)
                    return;
            UserGroups userGroups = UserGroupService.findByGroupIdSimple(tag.getGroupId());
            if (userGroups == null)
                    return;
            List<App> apps = AppService.findByGroupId(userGroups.getGroupId());
//            List<App> appsM = AppService.findByUserIdM(info.getUserId());
            StringBuilder builder = new StringBuilder();
            Set<String> set = new HashSet<String>();
            if (apps != null && apps.size() > 0) {
                    for (App app : apps) {
                            if (Language.getName(app.getAgent()) != null) {
                                    set.add(Language.getName(app.getAgent()));
                            }
                    }
            }
//            if (appsM != null && appsM.size() > 0) {
//                    for (App app : appsM) {
//                            if (Language.getName(app.getAgent()) != null) {
//                                    set.add(Language.getName(app.getAgent()));
//                            }
//                    }
//            }
            if (set.size() > 0) {
                    for (String s : set) {
                            builder.append(Language.getId(s.trim())).append(OneTools.sp);
                    }
            }
            tag.setLanguage(builder.toString());
    }

        public static void initTag(Tag tag) {
                if (tag == null) {
                        return;
                }
                if (tag.getMetric() > 999) {
                        tag.setMetric(tag.getMetric() % 1000);
                }
                if (tag.getLoudou() > 999) {
                        tag.setLoudou(tag.getLoudou() % 1000);
                }
                if (tag.getMetric() == null || tag.getMetric() <= 0) {
                        tag.setMetric(new Integer(0));
                }
                tag.setMe(Metric.findById(tag.getMetric()));

                if (tag.getLoudou() == null || tag.getLoudou() <= 0) {
                        tag.setLoudou(new Integer(0));
                }
                tag.setLou(Loudou.findById(tag.getLoudou()));
                tag.setMe(Metric.findById(tag.getMetric()));
                tag.setPersonName(Person.getName(tag.getPerson()));
                tag.setProvinceName(Province.getName(tag.getProvince()));
//                tag.setPvName(Pv.getName(tag.getPv()));
//                tag.setUvName(Uv.getName(tag.getUv()));
                tag.setRongziName(Rongzi.getName(tag.getRongzi()));
                tag.setCategoryName(Category.getName(tag.getCategory()));
        }

        public static List<Tag> findByTag(int type) {
                try {
                        switch (type) {
                        case 7:
                                return TagDaoImpl.getInstance().findTagsByMetric(1);
                        case 8:
                                return TagDaoImpl.getInstance().findTagsByMetric(3);
                        case 9:
                                return TagDaoImpl.getInstance().findTagsByMetric(2);
                        case 10:
                                return TagDaoImpl.getInstance().findTagsByMetric(4);
                        case 11:
                                return TagDaoImpl.getInstance().findTagsByLoudou(1);
                        case 12:
                                return TagDaoImpl.getInstance().findTagsByLoudou(2);
                        case 13:
                                return TagDaoImpl.getInstance().findTagsByLoudou(3);
                        case 14:
                                List<Tag> tags = TagDaoImpl.getInstance().findTagsByLoudou(4);
                                List<Tag> ts = TagDaoImpl.getInstance().findTagsByLoudou(5);
                                if (tags == null)
                                        tags = new ArrayList<Tag>();
                                if (ts != null && ts.size() > 0) {
                                        for (Tag tag : ts) {
                                                tags.add(tag);
                                        }
                                }
                                return tags;
                        case 15:
                                return TagDaoImpl.getInstance().findTagsByLoudou(4);
                        case 16:
                                return TagDaoImpl.getInstance().findTagsByLoudou(5);
                        default:
                                break;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromTag(Tag tag) {
                if (tag == null)
                        return null;
                JSONObject object = new JSONObject();
                try {
                        object.put("metric", tag.getMetric());
                        object.put("loudou", tag.getLoudou());
                        object.put("person", tag.getPerson());
                        object.put("province", tag.getProvince());
                        object.put("rongzi", tag.getRongzi());
                        object.put("category", tag.getCategory());
                        object.put("description", tag.getDescription());
                        object.put("from", tag.getFrom());
                        object.put("fuwuqi", tag.getFuwuqi());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
        
        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromTagName(Tag tag) {
                if (tag == null)
                        return null;
                JSONObject object = new JSONObject();
                try {
                        if(tag.getPerson() > 0){
                                object.put("person", Person.getName(tag.getPerson()));
                        }else{
                                object.put("person", "未知");
                        }
                        if(tag.getProvince() > 0){
                                object.put("province", Province.getName(tag.getProvince()));
                        }else{
                                object.put("province", "未知");
                        }
                        if(tag.getRongzi() > 0){
                                object.put("rongzi", Rongzi.getName(tag.getRongzi()));
                        }else{
                                object.put("rongzi", "未知");
                        }
                        if(tag.getCategory() > 0){
                                object.put("category", Category.getName(tag.getCategory()));
                        }else{
                                object.put("category", "未知");
                        }
                        if(tag.getFrom() > 0){
                                object.put("from", From.getName(tag.getFrom()));
                        }else{
                                object.put("from", "未知");
                        }
                        if(tag.getFuwuqi() > 0){
                                object.put("fuwuqi", Fuwuqi.getName(tag.getFuwuqi()));
                        }else{
                                object.put("fuwuqi", "未知");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
