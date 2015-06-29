package com.oneapm.web.show;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.MailMode;
import com.oneapm.service.show.IndexShowService;
import com.oneapm.util.exception.DataException;
import com.oneapm.util.exception.PramaException;
import com.oneapm.vo.Page;
import com.oneapm.vo.Report;
import com.oneapm.web.SupportAction;

public class OneApmShowAction extends SupportAction {

        /**
	 * 
	 */
        private static final long serialVersionUID = 1L;

        protected static final Logger LOG = LoggerFactory.getLogger(OneApmShowAction.class);

        private int id;
        private Long recordId;
        private Long userId;

        private Report report;

        private int status;
        private String msg;
        private int nowPage;
        private int totalPage;
        private Page page;

        private String email;
        private String phone;
        private String name;
        private String company;
        private int searchId;
        private int language;

        public String show() throws InterruptedException {
                if (!isLogin()) {
                        return "login";
                }
                if (nowPage <= 0) {
                        setNowPage(1);
                        setTotalPage(1);
                }
                if (id <= 0) {
                        id = 1;
                }
                try {
                        page = new Page(nowPage, totalPage);
                        report = IndexShowService.index(id, page, getAdmin(), language);
                } catch (DataException e) {
                        LOG.error(e.getMessage());
                        e.printStackTrace();
                } catch (PramaException e) {
                        LOG.error(e.getMessage(), e);
                        e.printStackTrace();
                        return ERROR;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return "showIndex";
        }

        public void remove() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (userId == null)
                        return;
        }

        public List<com.oneapm.web.Menu> getMenu() {
                return menu;
        }

        public List<MailMode> getMAIL_MODE() {
                return MAIL_MODE;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Report getReport() {
                return report;
        }

        public void setReport(Report report) {
                this.report = report;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getMsg() {
                return msg;
        }

        public void setMsg(String msg) {
                this.msg = msg;
        }

        public int getNowPage() {
                return nowPage;
        }

        public void setNowPage(int nowPage) {
                this.nowPage = nowPage;
        }

        public int getTotalPage() {
                return totalPage;
        }

        public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
        }

        public Long getRecordId() {
                return recordId;
        }

        public void setRecordId(Long recordId) {
                this.recordId = recordId;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }

        public Page getPage() {
                return page;
        }

        public void setPage(Page page) {
                this.page = page;
        }

        public int getSearchId() {
                return searchId;
        }

        public void setSearchId(int searchId) {
                this.searchId = searchId;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public int getLanguage() {
                return language;
        }

        public void setLanguage(int language) {
                this.language = language;
        }

}
