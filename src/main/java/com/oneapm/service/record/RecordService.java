package com.oneapm.service.record;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.record.impl.RecordDaoImpl;
import com.oneapm.record.Record;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.InfoService;
import com.oneapm.util.TimeTools;
import com.oneapm.vo.RecordVo;

public class RecordService {

        protected static final Logger LOG = LoggerFactory.getLogger(RecordService.class);

        public static Record findById(Long id) {
                if (id == null)
                        return null;
                return RecordDaoImpl.getInstance().findById(id);
        }

        public static List<Record> findRecords(int start, int number) {
                List<Record> records = RecordDaoImpl.getInstance().findRecords(start, number);
                return records;
        }

        public static List<Record> findByAdminId(Long adminId) {
                if (adminId == null)
                        return null;
                return RecordDaoImpl.getInstance().findByAdminId(adminId);
        }

        public static List<RecordVo> findRecordVos(int start, int number) {
                List<Record> records = findRecords(start, number);
                if (records == null || records.size() <= 0)
                        return null;
                List<RecordVo> vos = new ArrayList<RecordVo>();
                for (Record record : records) {
                        vos.add(getRecordVoFromRecord(record));
                }
                return vos;
        }

        public static List<RecordVo> findRecordVosByAdminId(Long adminId) {
                List<Record> records = findByAdminId(adminId);
                if (records == null || records.size() <= 0)
                        return null;
                List<RecordVo> vos = new ArrayList<RecordVo>();
                for (Record record : records) {
                        vos.add(getRecordVoFromRecord(record));
                }
                return vos;
        }

        public static boolean insert(Long adminId, int type, Long infoId, Long fromId, int status, int metric, int loudou, int me, int lou) {
                Record record = new Record(null, adminId, infoId, type, TimeTools.format(), fromId, status, metric, loudou, me, lou);
                return RecordDaoImpl.getInstance().insert(record);
        }

        public static RecordVo getRecordVoFromRecord(Record record) {
                if (record == null)
                        return null;
                String adminName = AccountService.findById(record.getAdminId()).getName();
                String fromName = AccountService.findById(record.getFromId()).getName();
                String company = InfoService.findByIdSingle(record.getInfoId()).getCompany();
                RecordVo vo = new RecordVo(record, adminName, fromName, company);
                return vo;
        }
}
