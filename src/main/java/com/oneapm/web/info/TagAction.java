package com.oneapm.web.info;

import java.io.IOException;

import com.oneapm.service.info.TagService;
import com.oneapm.web.SupportAction;

public class TagAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 7112393993649033943L;

        private Long id;
        private Integer loudou;
        private Integer metric;
        private int category;
        private int from;
        private String description;
        private int status;
        private int language;
        private int person;
        private int province;
//        private int pv;
//        private int uv;
        private int rongzi;
        private Long infoId;
        private int fuwuqi;
        public void update() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (description != null) {
                        description = new String(description.getBytes("ISO8859-1"), "UTF-8");
                }
                String result = TagService.update(getAdmin().getId(), id, category, person, province, from, description, rongzi,fuwuqi);
                getServletResponse().getWriter().print(result);
        }

        public void sals() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = TagService.sals(id, getAdmin().getId());
                getServletResponse().getWriter().print(result);
        }

        public void support() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = TagService.support(id, getAdmin().getId());
                getServletResponse().getWriter().print(result);
        }

        public void view() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = TagService.view(id);
                getServletResponse().getWriter().print(result);
        }

        public void language() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TagService.language(infoId, language);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void language_list() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TagService.languageList(infoId);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void metric() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TagService.metric(infoId, metric, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void loudou() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TagService.loudou(infoId, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Integer getLoudou() {
                return loudou;
        }

        public void setLoudou(Integer loudou) {
                this.loudou = loudou;
        }

        public Integer getMetric() {
                return metric;
        }

        public void setMetric(Integer metric) {
                this.metric = metric;
        }

        public int getCategory() {
                return category;
        }

        public void setCategory(int category) {
                this.category = category;
        }

        public int getFrom() {
                return from;
        }

        public void setFrom(int from) {
                this.from = from;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public int getLanguage() {
                return language;
        }

        public void setLanguage(int language) {
                this.language = language;
        }

        public int getPerson() {
                return person;
        }

        public void setPerson(int person) {
                this.person = person;
        }

        public int getProvince() {
                return province;
        }

        public void setProvince(int province) {
                this.province = province;
        }

//        public int getPv() {
//                return pv;
//        }
//
//        public void setPv(int pv) {
//                this.pv = pv;
//        }
//
//        public int getUv() {
//                return uv;
//        }
//
//        public void setUv(int uv) {
//                this.uv = uv;
//        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public int getRongzi() {
                return rongzi;
        }

        public void setRongzi(int rongzi) {
                this.rongzi = rongzi;
        }

        public int getFuwuqi() {
                return fuwuqi;
        }

        public void setFuwuqi(int fuwuqi) {
                this.fuwuqi = fuwuqi;
        }
}
