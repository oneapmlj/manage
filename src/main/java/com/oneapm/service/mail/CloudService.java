package com.oneapm.service.mail;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.mail.impl.MailModeDaoImpl;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.MailMode;
import com.oneapm.util.TimeTools;
import com.oneapm.web.SupportAction;

public class CloudService {

        protected static final Logger LOG = LoggerFactory.getLogger(CloudService.class);

        @SuppressWarnings("unchecked")
        public static String insert(String description, int timeType, String title, int status, String content) {
                JSONObject object = new JSONObject();
                try {
                        if (title == null || title.equals("")) {
                                object.put("status", 0);
                                object.put("msg", "主题不能为空！");
                                return object.toJSONString();
                        }
                        if (content == null || content.equals("")) {
                                object.put("status", 0);
                                object.put("msg", "内容不能为空！");
                                return object.toJSONString();
                        }
                        MailDto dto = new MailDto(0, timeType, title, content, status, TimeTools.format(), null, description);
                        dto.setUse(0);
                        if (MailModeDaoImpl.getInstance().insert(dto)) {
                                SupportAction.addMailMode(new MailMode(dto.getId(), dto.getDescription()));
                                object.put("status", 1);
                                return object.toJSONString();
                        }
                        object.put("status", 0);
                        object.put("msg", "添加失败！");
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        object.put("status", 0);
                        object.put("msg", "服务器内部错误！");
                }
                return object.toJSONString();
        }

        public static List<MailDto> findMails() {
                return MailModeDaoImpl.getInstance().findMails();
        }

}
