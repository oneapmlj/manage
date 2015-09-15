package com.oneapm.service.group;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oneapm.dao.group.impl.GroupDaoImpl;
import com.oneapm.dao.group.impl.GroupRecordDaoImpl;
import com.oneapm.dao.group.impl.GroupViewDaoImpl;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.group.GroupRecord;
import com.oneapm.dto.group.GroupView;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class GroupService {

        protected static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

        public static Group findById(Long groupId){
                return GroupDaoImpl.getInstance().findById(groupId);
        }
        
        public static GroupView findByInfoId(Long infoId){
                return GroupViewDaoImpl.getInstance().findByInfoId(infoId);
        }
        public static GroupView findByUserGroupId(Long usergroupId){
            return GroupViewDaoImpl.getInstance().findByUserGroupId(usergroupId);
    }
        public static int count(Long groupId){
                Group group = findById(groupId);
                if(group == null)return 0;
                return group.getTotal();
        }
        public static List<Group> findChild(Long groupId){
                return GroupDaoImpl.getInstance().findByFather(groupId);
        }
        
        public static boolean in(Long infoId, Long groupId){
                try{
                        String time = TimeTools.format();
                        Group group = findById(groupId);
                        if(group == null)return false;
                        GroupRecord record = new GroupRecord(null, 0L, groupId, 0L, time);
                        GroupView view = new GroupView(infoId, groupId, 0, null, time);
                        if(GroupViewDaoImpl.getInstance().insert(view)){
                                GroupDaoImpl.getInstance().update(group);
                                GroupRecordDaoImpl.getInstance().insert(record);
                                return true;
                        }
                        group.setTotal(group.getTotal() + 1);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public static String change(Long infoId, Long groupId, Long adminId){
                try{
                        String time = TimeTools.format();
                        Group group = findById(groupId);
                        if(group == null)return OneTools.getResult(0, "不存在该状态");
                        GroupView view = GroupViewDaoImpl.getInstance().findByInfoId(infoId);
                        if(view == null){
                                view = new GroupView(infoId, groupId, 0, null, time);
                                if(GroupViewDaoImpl.getInstance().insert(view)){
                                        group.setTotal(group.getTotal()+ 1);
                                        GroupDaoImpl.getInstance().update(group);
                                }
                                view = GroupViewDaoImpl.getInstance().findByInfoId(infoId);
                        }else{
                                if(view.getGroupId().equals(group.getId())){
                                        return OneTools.getResult(0, "已经是这种状态啦");
                                }
                                Group groupbefore = findById(view.getGroupId());
                                groupbefore.setTotal(groupbefore.getTotal()-1);
                                group.setTotal(group.getTotal() + 1);
                                GroupRecord record = new GroupRecord(null, view.getGroupId(), groupId, adminId, time);
                                view.setChangeTime(time);
                                view.setGroupId(groupId);
                                view.setScore(0);
                                view.setTypeTime(null);
                                if(GroupViewDaoImpl.getInstance().update(view)){
                                        GroupRecordDaoImpl.getInstance().insert(record);
                                        GroupDaoImpl.getInstance().update(groupbefore);
                                        GroupDaoImpl.getInstance().update(group);
                                }
                        }
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("group", getJSONFromGroup(group));
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        public static String insert(String name, String description, Long fatherId){
                try{
                        Group group = new Group(null, fatherId, false, 0, 0, 1, name, description);
                        if(fatherId > 0){
                                Group father = findById(fatherId);
                                group.setGrade(father.getGrade()+1);
                                father.setChild(true);
                                GroupDaoImpl.getInstance().update(father);
                        }
                        Long id = GroupDaoImpl.getInstance().insert(group);
                        if(id > 0){
                                JSONObject object = new JSONObject();
                                object.put("id", id);
                                object.put("father", fatherId);
                                object.put("name", name);
                                object.put("status", 1);
                                object.put("grade", group.getGrade());
                                return object.toJSONString();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "添加失败");
        }
        public static String group(Long groupId){
                try{
                        List<Group> groups = findChild(groupId);
                        JSONArray array = new JSONArray();
                        for(Group group : groups){
                                array.add(getJSONFromGroup(group));
                        }
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("groups", array);
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        private static JSONObject getJSONFromGroup(Group group){
                JSONObject object = new JSONObject();
                object.put("id", group.getId());
                object.put("father", group.getFather());
                object.put("grade", group.getGrade());
                object.put("name", group.getName());
                object.put("description", group.getDescription());
                object.put("total", group.getTotal());
                object.put("type", group.getType());
                return object;
        }
}
