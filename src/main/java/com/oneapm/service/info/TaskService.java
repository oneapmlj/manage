package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.mail.impl.MailModeDaoImpl;
import com.oneapm.dao.mail.impl.MailPushDaoImpl;
import com.oneapm.dao.mail.impl.PushDealDaoImpl;
import com.oneapm.dto.MailDto;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.record.MailPush;
import com.oneapm.record.PushDeal;
import com.oneapm.record.Task;
import com.oneapm.service.account.AccountService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class TaskService extends OneTools {

        protected static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

        public static MailPush findById(Long id) {
                return MailPushDaoImpl.getInstance().findById(id);
        }

        public static int findLength(Long adminId) {
                try {
                        return MailPushDaoImpl.getInstance().length(adminId, TimeTools.format());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public static List<MailPush> findByInfoIdAndAdmin(Long infoId, Long adminId) {
                return MailPushDaoImpl.getInstance().findByInfoIdAndAdmin(infoId, adminId);
        }
        public static List<MailPush> findByInfoId(Long infoId) {
                return MailPushDaoImpl.getInstance().findByInfoId(infoId);
        }

        public static MailPush findByInfoIdAdminType(Long infoId, Long adminId, int type) {
                return MailPushDaoImpl.getInstance().findByInfoIdAdminType(infoId, adminId, type);
        }

        public static boolean touch(Long infoId, Long adminId, String touchTime) {
                return MailPushDaoImpl.getInstance().touch(infoId, adminId, touchTime);
        }

        public static String pass(Long id, Admin admin, Admin adm) {
                try {
                        if (adm == null || id == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        MailPush push = findById(id);
                        if (push == null) {
                                return OneTools.getResult(2, "参数错误或者任务已经过期");
                        }
                        push.setFromId(push.getAdminId());
                        push.setFrom(1);
                        push.setAdminId(adm.getId());
                        if (MailPushDaoImpl.getInstance().update(push)) {
                                PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), admin.getId(), 3, TimeTools.format(), adm.getId(), -1, null, null));
                                List<String> args1 = new ArrayList<String>();
                                List<Object> args2 = new ArrayList<Object>();
                                args1.add("id");
                                args2.add(id);
                                return OneTools.getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "内部服务器错误");
        }

        public static String put(Long id, Admin admin, int putTime) {
                try {
                        if (id == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        if (putTime == 0 || putTime > 100) {
                                return OneTools.getResult(0, "不可无限期推迟");
                        }
                        MailPush push = findById(id);
                        if (push == null) {
                                return OneTools.getResult(2, "参数错误或者任务已经过期");
                        }
                        String put = TimeTools.addHour(putTime);
                        push.setPutTime(put);
                        if (MailPushDaoImpl.getInstance().update(push)) {
                                PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), admin.getId(), 2, TimeTools.format(), null, -1, null, put));
                                List<String> args1 = new ArrayList<String>();
                                List<Object> args2 = new ArrayList<Object>();
                                args1.add("id");
                                args2.add(id);
                                return OneTools.getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "内部服务器错误");
        }

        public static String remove(Long id, Admin admin) {
                try {
                        if (id == null) {
                                return OneTools.getResult(0, "参数错误");
                        }
                        MailPush push = MailPushDaoImpl.getInstance().findById(id);
                        if (push == null) {
                                return OneTools.getResult(2, "参数错误或者任务已经过期");
                        }
//                        if (!push.getAdminId().equals(admin.getId())) {
//                                return OneTools.getResult(2, "权限不足");
//                        }
                        push.setStatus(1);
                        if (MailPushDaoImpl.getInstance().update(push)) {
                                PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), admin.getId(), 1, TimeTools.format(), null, -1, null, null));
                                List<String> args1 = new ArrayList<String>();
                                List<Object> args2 = new ArrayList<Object>();
                                args1.add("id");
                                args2.add(id);
                                return OneTools.getResult(1, args1, args2);
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "内部服务器错误");
        }

        public static void close(Long infoId, Long adminId) {
                try {
                        List<MailPush> pushs = MailPushDaoImpl.getInstance().findByInfoIdAndAdmin(infoId, adminId);
                        if (pushs.size() <= 0) {
                                return;
                        }
                        for (MailPush push : pushs) {
                                push.setStatus(1);
                                if (MailPushDaoImpl.getInstance().update(push)) {
                                        PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), adminId, 6, TimeTools.format(), null, -1, null, null));
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public static void deal(Long infoId, Long adminId, Long callId) {
                try {
                        List<MailPush> pushs = MailPushDaoImpl.getInstance().findByInfoIdAndAdmin(infoId, adminId);
                        if (pushs.size() <= 0) {
                                return;
                        }
                        for (MailPush push : pushs) {
                                push.setNumber(push.getNumber() + 1);
                                if (MailPushDaoImpl.getInstance().update(push)) {
                                        PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), adminId, 4, TimeTools.format(), null, -1, callId, null));
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public static int mailType(MailPush push) {
                switch (push.getType()) {
                case 1:
                        return 104;
                case 2:
                        return 105;
                case 3:
                        return 106;
                case 4:
                        return 101;
                case 5:
                        return 106;
                case 6:
                        return 102;
                case 7:
                        return 103;
                default:
                        break;
                }
                return 0;
        }

        public static boolean mail(Long id, Admin admin, int mode) {
                try {
                        if (id == null) {
                                return false;
                        }
                        MailPush push = MailPushDaoImpl.getInstance().findById(id);
                        if (push == null) {
                                return false;
                        }
                        if (mode <= 0) {
                                return false;
                        }
                        push.setNumber(push.getNumber() + 1);
                        if (MailPushDaoImpl.getInstance().update(push)) {
                                return PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), admin.getId(), 5, TimeTools.format(), null, mode, null, null));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public static void mail(Long infoId, Long adminId, int mode) {
                try {
                        List<MailPush> pushs = MailPushDaoImpl.getInstance().findByInfoIdAndAdmin(infoId, adminId);
                        if (pushs.size() <= 0) {
                                return;
                        }
                        for (MailPush push : pushs) {
                                push.setNumber(push.getNumber() + 1);
                                if (MailPushDaoImpl.getInstance().update(push)) {
                                        PushDealDaoImpl.getInstance().insert(new PushDeal(PushDealDaoImpl.getInstance().getIdest(), push.getId(), adminId, 5, TimeTools.format(), null, mode, null, null));
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public static List<Task> index(Admin admin, int type) {
                List<Task> tasks = new ArrayList<Task>();
                try {
                        List<MailPush> pushs = MailPushDaoImpl.getInstance().findByAdmin(admin.getId(), TimeTools.format(), type);
                        if (pushs != null && pushs.size() > 0) {
                                String time = TimeTools.format();
                                for (MailPush push : pushs) {
                                        if (push.getFromId() != null && push.getFromId() > 0) {
                                                push.setFromName(AccountService.findName(push.getFromId()));
                                        }
                                        Info info = InfoService.findByIdSimple(push.getInfoId());
                                        Task task = new Task(push, findByTaskType(push.getType()), info, push.isPoint());
                                        if (push.getWarming() > 0) {
                                                task.setWarmingHour(TimeTools.apartHour(time, push.getWarmingTime()));
                                                if (task.getWarmingHour() < 0) {
                                                        task.setWarmingHour(0);
                                                }
                                        }
                                        tasks.add(task);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tasks;
        }

        public static boolean insert(Long infoId, Long adminId, int type, String putTime, Long from, boolean point) {
                MailPush pp = findByInfoIdAdminType(infoId, adminId, type);
                if (pp != null) {
                        pp.setPutTime(putTime);
                        pp.setPoint(point);
                        MailPushDaoImpl.getInstance().update(pp);
                        return false;
                }
                MailPush push = new MailPush(MailPushDaoImpl.getInstance().getIdest(), TimeTools.format(), 0,
                                adminId, type, infoId, putTime, 0, TimeTools.format(), 2, 0, null, point);
                push.setFromId(from);
                return MailPushDaoImpl.getInstance().insert(push);
        }

        public static MailDto findByTaskType(int type) {
                int m = 0;
                switch (type) {
                case 1:
                        ;
                        break;
                case 2:
                        ;
                        break;
                default:
                        break;
                }
                MailDto mode = MailModeDaoImpl.getInstance().findById(m);
                return mode;
        }

}
