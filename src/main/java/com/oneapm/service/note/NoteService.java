package com.oneapm.service.note;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.note.impl.NoteTypeDaoImpl;
import com.oneapm.dto.NoteType;
import com.oneapm.dto.Account.Admin;
import com.oneapm.service.info.TagService;
import com.oneapm.service.info.TaskService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class NoteService {

        protected static final Logger LOG = LoggerFactory.getLogger(NoteService.class);

        public static NoteType findTypeById(Long id) {
                if (id == null)
                        return null;
                return NoteTypeDaoImpl.getInstance().findById(id);
        }

        public static List<NoteType> findTypesByFather(Long father) {
                if (father == null)
                        return null;
                return NoteTypeDaoImpl.getInstance().findByFather(father);
        }

        public static NoteType insert(Long father, String name, Long adminId, int todu) {
                try {
                        if (father <= 0)
                                return null;
                        Long id = NoteTypeDaoImpl.getInstance().getIdest();
                        NoteType fa = findTypeById(father);
                        if (fa == null)
                                return null;
                        String createTime = TimeTools.format();
                        NoteType note = new NoteType(id, father, name, createTime, 1, adminId, todu);
                        if (fa.getTodu() > 0) {
                                note.setTodu(fa.getTodu());
                        }
                        if (NoteTypeDaoImpl.getInstance().insert(note)) {
                                return note;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static boolean update(Long id, String name, Long father) {
                try {
                        NoteType note = NoteTypeDaoImpl.getInstance().findById(id);
                        if (note == null)
                                return false;
                        if (name != null && name.length() > 0) {
                                note.setName(name);
                        }
                        if (father != null && father > 0) {
                                note.setFather(father);
                        }
                        return NoteTypeDaoImpl.getInstance().update(note);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        @SuppressWarnings("unchecked")
        public static String insert(Long father, String name, String description, Long adminId, int todu) {
                JSONObject object = new JSONObject();
                try {
                        NoteType note = insert(father, name, adminId, todu);
                        if (note != null) {
                                object.put("status", 1);
                                object.put("note", getObjectFromNoteType(note));
                                return object.toJSONString();
                        } else {
                                OneTools.getResult(0, "添加失败，一级目录不允许添加");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        @SuppressWarnings("unchecked")
        public static String findNextType(Long father) {
                JSONObject object = new JSONObject();
                try {
                        List<NoteType> notes = findTypesByFather(father);
                        if (notes == null || notes.size() <= 0) {
                                return OneTools.getResult(1, "");
                        }
                        JSONArray array = new JSONArray();
                        for (NoteType note : notes) {
                                array.add(getObjectFromNoteType(note));
                        }
                        object.put("status", 1);
                        object.put("notes", array);
                        return object.toJSONString();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        public static boolean todu(int todu, Long infoId, Admin admin, String putTime, boolean point) {
                try {
                        if (todu <= 0)
                                return false;
                        if (todu > 0) {
                                switch (todu) {
                                // 销售
                                case 1000:
                                        TaskService.insert(infoId, 80000028L, 10, TimeTools.format(), admin.getId(), point);
                                        break;
                                // 支持
                                case 1001:
                                        TaskService.insert(infoId, 10000005L, 11, TimeTools.format(), admin.getId(), point);
                                        break;
                                // 再联系
                                case 1002:
                                        TaskService.insert(infoId, admin.getId(), 9, putTime, admin.getId(), point);
                                        break;
                                // 关闭
                                case 1003:
                                        TagService.metric(infoId, 4, admin);
                                        break;
                                default:
                                        break;
                                }
                        }
                        return true;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        
        public static boolean toduWithGroupId(int todu, Long groupId, Admin admin, String putTime, boolean point) {
            try {
                    if (todu <= 0)
                            return false;
                    if (todu > 0) {
                            switch (todu) {
                            // 销售
                            case 1000:
                                    TaskService.insertWithGroupId(groupId, 80000028L, 10, TimeTools.format(), admin.getId(), point);
                                    break;
                            // 支持
                            case 1001:
                                    TaskService.insertWithGroupId(groupId, 10000005L, 11, TimeTools.format(), admin.getId(), point);
                                    break;
                            // 再联系
                            case 1002:
                                    TaskService.insertWithGroupId(groupId, admin.getId(), 9, putTime, admin.getId(), point);
                                    break;
                            // 关闭
                            case 1003:
                                    TagService.metric(groupId, 4, admin);
                                    break;
                            default:
                                    break;
                            }
                    }
                    return true;
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return false;
    }

        @SuppressWarnings("unchecked")
        public static String getTypeById(Long id) {
                try {
                        if (id <= 0) {
                                JSONObject object = new JSONObject();
                                object.put("status", 1);
                                return object.toJSONString();
                        }
                        NoteType note = findTypeById(id);
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("note", getObjectFromNoteType(note));
                        return object.toJSONString();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getObjectFromNoteType(NoteType note) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", note.getId());
                        object.put("father", note.getFather());
                        object.put("name", note.getName());
                        object.put("todu", note.getTodu());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
