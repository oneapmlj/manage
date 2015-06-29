package com.oneapm.service.file;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.file.impl.FileDaoImpl;
import com.oneapm.dao.file.impl.FileRecordDaoImpl;
import com.oneapm.dao.file.impl.WendangDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.file.FileDto;
import com.oneapm.dto.file.FileRecord;
import com.oneapm.dto.file.Wendang;
import com.oneapm.service.account.AccountService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class FileService {
        protected static final Logger LOG = LoggerFactory.getLogger(FileService.class);

        public static FileDto download(Long fileId, Admin admin) {
                FileDto dto = findById(fileId);
                if (dto != null) {
                        if (FileRecordDaoImpl.getInstance().insert(new FileRecord(FileRecordDaoImpl.getInstance().getIdest(), fileId, 3, admin.getId()))) {
                                return dto;
                        }
                }
                return null;
        }

        @SuppressWarnings("unchecked")
        public static String save(String ids, Long father, int status, Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        if (ids == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        object.put("status", 1);
                        String IDS[] = ids.split(OneTools.sp);
                        if (IDS == null || IDS.length <= 0) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        List<FileDto> dtos = new ArrayList<FileDto>();
                        for (String s : IDS) {
                                Wendang wendang = WendangDaoImpl.getInstance().findById(Long.parseLong(s));
                                FileDto dto = new FileDto(FileDaoImpl.getInstance().getIdest(), wendang.getName(), wendang.getCode(), 0, 2, wendang.getName().substring(wendang.getName().lastIndexOf(".") + 1).toUpperCase(), father, wendang.getCreateTime(), null, null, status, null, admin.getId());
                                if (FileDaoImpl.getInstance().insert(dto)) {
                                        FileRecordDaoImpl.getInstance().insert(new FileRecord(FileRecordDaoImpl.getInstance().getIdest(), dto.getId(), 2, admin.getId()));
                                        dtos.add(dto);
                                }
                        }
                        object.put("files", getArrayFromDtos(dtos));
                        return object.toJSONString();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }

        public static Wendang uploadWendang(String name, String code) {
                try {
                        String time = TimeTools.format();
                        Wendang wendang = new Wendang(WendangDaoImpl.getInstance().getIdest(), code, 0, time, name);
                        if (WendangDaoImpl.getInstance().insert(wendang)) {
                                return wendang;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public static FileDto findById(Long fileId) {
                return FileDaoImpl.getInstance().findById(fileId);
        }

        public static FileDto upload(Admin admin, String name, int grade, int type, Long father, int status, String password) {
                String code = AccountService.string2MD5(name);
                String ext = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
                String createTime = TimeTools.format();
                FileDto dto = new FileDto(FileDaoImpl.getInstance().getIdest(), name, code, grade, type, ext, father, createTime, null, null, status, password, admin.getId());
                if (FileDaoImpl.getInstance().insert(dto)) {
                        FileRecordDaoImpl.getInstance().insert(new FileRecord(FileRecordDaoImpl.getInstance().getIdest(), dto.getId(), 2, admin.getId()));
                        return dto;
                }
                return null;
        }

        @SuppressWarnings("unchecked")
        public static String create(Admin admin, String name, int grade, int type, Long father, int status, String password) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        FileDto dto = upload(admin, name, grade, type, father, status, password);
                        object.put("file", getJSONFromDto(dto));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String show(Long father, Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        List<FileDto> dtos = findByFather(father);
                        if (dtos == null) {
                                return OneTools.getResult(0, "目录为空");
                        }
                        List<FileDto> lians = new ArrayList<FileDto>();
                        if (father > 0) {
                                FileDto dto = findById(father);
                                object.put("name", dto.getName());
                                while (dto.getFather() > 0) {
                                        dto = findById(dto.getFather());
                                        lians.add(dto);
                                }
                        } else {
                                object.put("name", null);
                        }
                        object.put("lians", getArrayFromDtos(lians));
                        object.put("length", dtos.size());
                        object.put("files", getArrayFromDtos(dtos));
                        object.put("father", father);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String mulus(Long father, Admin admin) {
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        List<FileDto> dtos = findByFather(father, 1);
                        List<FileDto> lians = new ArrayList<FileDto>();
                        if (father > 0) {
                                FileDto dto = findById(father);
                                object.put("name", dto.getName());
                                while (dto.getFather() > 0) {
                                        dto = findById(dto.getFather());
                                        lians.add(dto);
                                }
                        } else {
                                object.put("name", null);
                        }
                        if (dtos != null && dtos.size() > 0) {
                                object.put("files", getArrayFromDtos(dtos));
                        }
                        object.put("lians", getArrayFromDtos(lians));
                        object.put("length", dtos.size());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
                return object.toJSONString();
        }

        public static List<FileDto> findByFather(Long father) {
                List<FileDto> dtos = FileDaoImpl.getInstance().findByFather(father);
                for (FileDto dto : dtos) {
                        if (dto.getType() == 1) {
                                dto.setNumber(FileDaoImpl.getInstance().total(dto.getId()));
                        }
                }
                return dtos;
        }

        public static List<FileDto> findByFather(Long father, int type) {
                List<FileDto> dtos = FileDaoImpl.getInstance().findByFather(father, type);
                for (FileDto dto : dtos) {
                        if (dto.getType() == 1) {
                                dto.setNumber(FileDaoImpl.getInstance().total(dto.getId()));
                        }
                }
                return dtos;
        }

        @SuppressWarnings("unchecked")
        public static String rename(Long id, String name, Admin admin) {
                if (id == null || id <= 0 || name == null || name.trim().equals("")) {
                        return OneTools.getResult(0, "参数错误");
                }
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        object.put("id", id);
                        object.put("name", name);
                        FileDto dto = FileDaoImpl.getInstance().findById(id);
                        if (dto == null) {
                                return OneTools.getResult(0, "目录不存在");
                        }
                        dto.setName(name);
                        if (FileDaoImpl.getInstance().update(dto)) {
                                FileRecordDaoImpl.getInstance().insert(new FileRecord(FileRecordDaoImpl.getInstance().getIdest(), id, 9, admin.getId()));
                        } else {
                                return OneTools.getResult(0, "服务器错误");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static String delete(Long id, Admin admin) {
                if (id == null || id <= 0) {
                        return OneTools.getResult(0, "参数错误");
                }
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        object.put("id", id);
                        FileDto dto = FileDaoImpl.getInstance().findById(id);
                        if (dto == null) {
                                return OneTools.getResult(0, "目录不存在");
                        }
                        List<FileDto> dtos = findByFather(id);
                        if (dtos != null && dtos.size() > 0) {
                                return OneTools.getResult(0, "目录不为空，不能删除");
                        }
                        if (FileDaoImpl.getInstance().remove(id)) {
                                FileRecordDaoImpl.getInstance().insert(new FileRecord(FileRecordDaoImpl.getInstance().getIdest(), id, 5, admin.getId()));
                        } else {
                                return OneTools.getResult(0, "服务器错误");
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器错误");
                }
                return object.toJSONString();
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromDtos(List<FileDto> dtos) {
                if (dtos == null || dtos.size() <= 0) {
                        return null;
                }
                JSONArray array = new JSONArray();
                try {
                        for (FileDto dto : dtos) {
                                array.add(getJSONFromDto(dto));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromDto(FileDto dto) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", dto.getId());
                        object.put("name", dto.getName());
                        object.put("code", dto.getCode());
                        object.put("grade", dto.getGrade());
                        object.put("type", dto.getType());
                        object.put("ext", dto.getExt());
                        object.put("father", dto.getFather());
                        object.put("create_time", dto.getCreateTime());
                        object.put("download_time", dto.getDownloadTime());
                        object.put("view_time", dto.getViewTime());
                        object.put("status", dto.getStatus());
                        object.put("password", dto.getPassword());
                        object.put("admin_id", dto.getAdminId());
                        object.put("admin_name", AccountService.findById(dto.getAdminId()).getName());
                        object.put("number", dto.getNumber());
                        return object;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
}
