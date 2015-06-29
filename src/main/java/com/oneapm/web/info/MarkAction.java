package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.info.Mark;
import com.oneapm.service.info.MarkService;
import com.oneapm.web.SupportAction;

public class MarkAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 8112493288384471578L;

        private List<Mark> marks;
        private Long infoId;
        private int status;
        private Long id;

        public String list() {
                if (!isLogin()) {
                        return "login";
                }
                marks = MarkService.findByAdminId(getAdmin().getId());
                return "list";
        }

        public void add() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = MarkService.add(infoId, getAdmin().getId());
                getServletResponse().getWriter().print(result);
        }

        public void close() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = MarkService.up(id, 1);
                getServletResponse().getWriter().print(result);
        }

        public List<Mark> getMarks() {
                return marks;
        }

        public void setMarks(List<Mark> marks) {
                this.marks = marks;
        }


        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

}
