package com.oneapm.service.mail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.opt.impl.DownDaoImpl;
import com.oneapm.dto.Download;

public class DownloadService {

        protected static final Logger LOG = LoggerFactory.getLogger(DownloadService.class);

        public static List<Download> findByUserId(Long userId) {
                if (userId == null || userId <= 0)
                        return null;
                List<Download> downloads = DownDaoImpl.getInstance().findByUserId(userId);
                return downloads;
        }
        
        public static List<Download> findByAgent(int agent, String banben, String start, String end){
                return DownDaoImpl.getInstance().findByAgent(agent, banben, start, end);
        }
        public static List<String> findVersions(int agent){
                return DownDaoImpl.getInstance().findVersions(agent);
        }

        public static boolean insert(Download download) {
                if (download == null || download.getId() == null) {
                        return false;
                }
                if (findById(download.getId()) == null) {
                        return DownDaoImpl.getInstance().insert(download);
                }
                return false;
        }

        public static Download findById(Long downloadId) {
                if (downloadId == null || downloadId <= 0) {
                        return null;
                }
                return DownDaoImpl.getInstance().findById(downloadId);
        }

        public static Download findDownTime(Long userId) {
                return DownDaoImpl.getInstance().findDonwTime(userId);
        }
}
