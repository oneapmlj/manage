package com.oneapm.service.message;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.TipDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Tip;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.record.RecordService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class TipService {

        protected static final Logger LOG = LoggerFactory.getLogger(TipService.class);

        @SuppressWarnings("unchecked")
        public static String tip(Admin admin, Long infoId, Long to) {
                String dataTime = TimeTools.format();
                JSONObject object = new JSONObject();
                try {
                        object.put("status", 1);
                        if (to == null || infoId == null) {
                                return OneTools.getResult(0, "数据为空");
                        }
                        if (MessageService.insert(admin.getId(), to, 0, infoId, 7)) {
                                TipDaoImpl.getInstance().insert(new Tip(TipDaoImpl.getInstance().getIdest() + 1, admin.getId(), to, dataTime, infoId));
                                RecordService.insert(admin.getId(), 7, infoId, to, 0, 0, 0, 0, 0);
                                object.put("status", 1);
                                return object.toJSONString();
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器错误");
                }
                return object.toJSONString();
        }

        public static Tip findByInfoId(Long infoId) {
                Tip tip = TipDaoImpl.getInstance().findByInfoId(infoId);
                if (tip == null)
                        return tip;
                tip.setFromName(AccountService.findById(tip.getFrom()).getName());
                tip.setToName(AccountService.findById(tip.getTo()).getName());
                return tip;
        }
}
