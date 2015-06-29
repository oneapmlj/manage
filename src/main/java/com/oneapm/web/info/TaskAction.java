package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.MailMode;
import com.oneapm.dto.Account.Admin;
import com.oneapm.record.Task;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.TaskService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class TaskAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 7112393993649033943L;

        private List<Task> tasks;
        private Long id;
        private Long passId;
        private int putTime;
        private int type;

        public String index() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        tasks = TaskService.index(getAdmin(), type);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return "index";
        }

        public void pass() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        Admin adm = AccountService.findById(passId);
                        if (adm == null) {
                                getServletResponse().getWriter().print(OneTools.getResult(0, "目标不存在"));
                                return;
                        }
                        if (!quanxian(adm.getGrades(), getGRADE().getMap().get(111))) {
                                getServletResponse().getWriter().print(OneTools.getResult(0, "目标拒绝接受任务"));
                                return;
                        }
                        String result = TaskService.pass(id, getAdmin(), adm);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void put() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TaskService.put(id, getAdmin(), putTime);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void remove() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TaskService.remove(id, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void mail() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TaskService.remove(id, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public List<MailMode> getMAIL_MODE() {
                return MAIL_MODE;
        }

        public List<Task> getTasks() {
                return tasks;
        }

        public void setTasks(List<Task> tasks) {
                this.tasks = tasks;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getPassId() {
                return passId;
        }

        public void setPassId(Long passId) {
                this.passId = passId;
        }

        public int getPutTime() {
                return putTime;
        }

        public void setPutTime(int putTime) {
                this.putTime = putTime;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }
}
