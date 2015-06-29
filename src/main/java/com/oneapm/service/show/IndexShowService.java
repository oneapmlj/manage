package com.oneapm.service.show;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dao.opt.impl.OptDaoImpl;
import com.oneapm.dto.Zhengzailianxi;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.KFService;
import com.oneapm.service.info.ZhengzailianxiService;
import com.oneapm.util.exception.DataException;
import com.oneapm.util.exception.PramaException;
import com.oneapm.vo.Page;
import com.oneapm.vo.Report;
import com.oneapm.web.JWT;

public class IndexShowService {
        protected static final Logger LOG = LoggerFactory.getLogger(IndexShowService.class);
        public final static int pageNum = 30;

        public static String findEmailById(Long userId) {
                Info info = InfoDaoImpl.getInstance().findByUserId(userId);
                if (info != null) {
                        return info.getEmail();
                }
                return null;
        }

        public static Long findKFIdByEmail(String email, Long id) {
                if (id == null || email == null) {
                        return null;
                }
                Long kfId = InfoDaoImpl.getInstance().findKFById(id);
                if (kfId != null) {
                        return kfId;
                }
                kfId = KFService.findKFId(email, id);
                return kfId;
        }
        
        public static Long findUdeskIdByEmail(String email, Long id, String name) {
                if (id == null || email == null) {
                        return null;
                }
                Long udeskId = InfoDaoImpl.getInstance().findUdeskById(id);
                if (udeskId != null) {
                        return udeskId;
                }
                udeskId = JWT.findUdeskId(email, udeskId);
                return udeskId;
        }
        

        public static Report index(int id, Page page, Admin admin, int language) throws DataException, PramaException, ParseException {
                if (id >= 17)
                        return null;
                Report report = null;
                int start = (page.getNowPage() - 1) * pageNum;
                if (language == 0) {
                        report = findById(id, start, pageNum, admin);
                } else {
                        report = findById(id, 0, 0, admin);
                        List<Info> infos = report.getInfos();
                        List<Info> ins = new ArrayList<Info>();
                        int number = 0;
                        String l = new String(language + "").trim();
                        if (infos != null) {
                                for (int i = 0; i < infos.size(); i++) {
                                        if (infos.get(i).getTag().getLanguage() != null && infos.get(i).getTag().getLanguage().indexOf(l) >= 0) {
                                                number++;
                                                if (i >= start && i < (start + pageNum)) {
                                                        ins.add(infos.get(i));
                                                }
                                        } else {
                                                infos.remove(i);
                                                i--;
                                        }
                                }
                        }
                        report.setInfos(ins);
                        report.setTotal(number);
                }
                int total = report.getTotal() / pageNum;
                if (report.getTotal() % pageNum > 0) {
                        total++;
                }
                page.setTotalPage(total);
                if (report.getInfos() != null) {
                        report.setUpdateTime(OptDaoImpl.getInstance().findUpdateTime(id));
                        report.setLength(report.getInfos().size());
                }
                return report;
        }

        public static Report findById(int id, int start, int end, Admin admin) {
                List<Info> infos = OptDaoImpl.getInstance().findById(id, start, end);
                for (int i = 0; i < infos.size(); i++) {
                        Info info = infos.get(i);
                        Info in = InfoService.findByUserIdSingle(info.getUserId());
                        if (in == null) {
                                infos.remove(i);
                                i--;
                                continue;
                        }
                        info.setId(in.getId());
                        info.setSale(in.getSale());
                        info.setSupport(in.getSupport());
                        info.setPreSale(in.getPreSale());
                        info.setTag(in.getTag());
                        if (info.getTag().getMetric().equals(4)) {
                                infos.remove(i);
                                i--;
                                continue;
                        }
                        InfoService.power(admin.getId(), admin.getGroup(), info);
                }
                Report report = new Report();
                if (infos != null) {
                        report.setLength(infos.size());
                }
                report.setInfos(infos);
                report.setTotal(OptDaoImpl.getInstance().findLength(id));
                return report;
        }

}
