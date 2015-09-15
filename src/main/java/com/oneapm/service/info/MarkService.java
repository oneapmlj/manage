package com.oneapm.service.info;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.MarkDaoImpl;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.info.Mark;
import com.oneapm.service.group.UserGroupService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class MarkService {

        protected static final Logger LOG = LoggerFactory.getLogger(MarkService.class);

        public static Mark findByAdminIdAndInfoId(Long adminId, Long infoId) {
                return MarkDaoImpl.getInstance().findAdminIdAndInfoId(adminId, infoId);
        }
        public static Mark findByAdminIdAndGroupId(Long adminId, Long infoId) {
            return MarkDaoImpl.getInstance().findAdminIdAndGroupId(adminId, infoId);
        }

        public static List<Mark> findByAdminId(Long adminId) {
                List<Mark> marks = MarkDaoImpl.getInstance().findByAdminId(adminId);
                if (marks != null && marks.size() > 0) {
                        for (Mark mark : marks) {
                                UserGroups userGroups = UserGroupService.findByGroupIdSingle(mark.getGroupId());
                                mark.setProjectName(userGroups.getGroupName());
                        }
                }
                return marks;
        }

        public static Mark findById(Long id) {
                return MarkDaoImpl.getInstance().findById(id);
        }

        @SuppressWarnings("unchecked")
        public static String add(Long infoId, Long adminId) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        Info info = InfoService.findByIdSimple(infoId);
                        if (info == null) {
                                object.put("msg", "参数错误");
                                return object.toJSONString();
                        }
                        Mark mark = MarkDaoImpl.getInstance().findAdminIdAndInfoId(adminId, infoId);
                        if(mark != null){
                                return OneTools.getResult(0, "已经标记了");
                        }
                                        
                        mark = new Mark(null, infoId, TimeTools.format(), 0, adminId);
                        mark = insert(mark);
                        if (mark == null) {
                                object.put("msg", "服务器内部错误");
                                return object.toJSONString();
                        }
                        object.put("status", 1);
                        object.put("mark", getJSONFromMark(mark));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("msg", "服务器错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String up(Long id, int status) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 0);
                        Mark mark = findById(id);
                        if (mark == null) {
                                object.put("msg", "参数错误");
                                return object.toJSONString();
                        }
                        if (status > 0) {
                                mark.setStatus(status);
                        }
                        if (update(mark)) {
                                object.put("status", 1);
                                object.put("mark", getJSONFromMark(mark));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("msg", "服务器错误");
                }
                return object.toJSONString();
        }

        public static Mark insert(Mark mark) {
                return MarkDaoImpl.getInstance().insert(mark);
        }

        public static boolean update(Mark mark) {
                return MarkDaoImpl.getInstance().update(mark);
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromMark(Mark mark) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", mark.getId());
                        object.put("status", mark.getStatus());
                        object.put("infoId", mark.getInfoId());
                        object.put("adminId", mark.getAdminId());
                        object.put("createTime", mark.getCreateTime());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
