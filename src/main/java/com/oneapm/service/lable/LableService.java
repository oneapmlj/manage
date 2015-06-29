package com.oneapm.service.lable;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oneapm.dao.lable.impl.LableDaoImpl;
import com.oneapm.dto.lable.Lable;
import com.oneapm.util.OneTools;

public class LableService {

        protected static final Logger LOG = LoggerFactory.getLogger(LableService.class);

        public static Lable findById(Long lableId){
                return LableDaoImpl.getInstance().findById(lableId);
        }
        
        public static List<Lable> findChild(Long lableId){
                return LableDaoImpl.getInstance().findByFather(lableId);
        }
        
        
        public static String insert(String name, String description, Long fatherId, String key){
                try{
                        key = key.trim();
                        name = name.trim();
                        description = description.trim();
                        if(LableDaoImpl.getInstance().findByKey(key, fatherId)){
                                return OneTools.getResult(0, "key重复");
                        }
                        if(LableDaoImpl.getInstance().findByName(name, fatherId)){
                                return OneTools.getResult(0, "分类重复");
                        }
                        Lable lable = new Lable(null, fatherId, false, 1, name, description, key, null);
                        List<String> keys = new ArrayList<String>();
                        keys.add(key);
                        Long lastId = fatherId;
                        while(lastId > 0){
                                Lable father = findById(lastId);
                                keys.add(father.getKey());
                                lastId = father.getFather();
                        }
                        StringBuilder builder = new StringBuilder();
                        for(int i=keys.size()-1; i>=0;i--){
                                builder.append(keys.get(i));
                        }
                        String from = builder.toString();
                        if(LableDaoImpl.getInstance().findByFrom(from)){
                                return OneTools.getResult(0, "生成链接重复，请更换字符");
                        }
                        lable.setFrom(from);
                        if(fatherId > 0){
                                Lable father = findById(fatherId);
                                lable.setGrade(father.getGrade()+1);
                                father.setChild(true);
                                LableDaoImpl.getInstance().update(father);
                        }
                        Long id = LableDaoImpl.getInstance().insert(lable);
                        if(id > 0){
                                JSONObject object = new JSONObject();
                                object.put("id", id);
                                object.put("father", fatherId);
                                object.put("name", name);
                                object.put("status", 1);
                                object.put("grade", lable.getGrade());
                                object.put("key", key);
                                object.put("from", from);
                                return object.toJSONString();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "添加失败");
        }
        
        public static String single(Long lableId){
                try{
                        JSONObject object = new JSONObject();
                        Lable lable = findById(lableId);
                        object.put("status", 1);
                        object.put("lable", getJSONFromLable(lable));
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public static String lable(Long lableId){
                try{
                        List<Lable> lables = findChild(lableId);
                        JSONArray array = new JSONArray();
                        for(Lable lable : lables){
                                array.add(getJSONFromLable(lable));
                        }
                        JSONObject object = new JSONObject();
                        if(lableId > 0){
                                Lable labl = findById(lableId);
                                object.put("from", labl.getFrom());
                        }else{
                                object.put("from", "");
                        }
                        object.put("status", 1);
                        object.put("lables", array);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        private static JSONObject getJSONFromLable(Lable lable){
                JSONObject object = new JSONObject();
                object.put("id", lable.getId());
                object.put("father", lable.getFather());
                object.put("grade", lable.getGrade());
                object.put("name", lable.getName());
                object.put("description", lable.getDescription());
                object.put("key", lable.getKey());
                object.put("from", lable.getFrom());
                return object;
        }
}
