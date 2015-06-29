package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.info.Tongji;
import com.oneapm.dto.info.TongjiIndex;
import com.oneapm.service.info.TongjiService;
import com.oneapm.web.SupportAction;

public class TongjiAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 7112393993649033943L;

        private List<Tongji> tongjis;
        private TongjiIndex index;
        private Long start;
        private Long id;
        private int type;

        public String index() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        if (getAdmin().getGroup() >= 4 || getAdmin().getGrades().indexOf(getGRADE().getMap().get(109).getQuanxian()) > -1) {
                                setIndex(TongjiService.index());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return "index";
        }

        public String view() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        if (getAdmin().getGroup() >= 4 || getAdmin().getGrades().indexOf(getGRADE().getMap().get(109).getQuanxian()) > -1) {
                                index = TongjiService.findCompile(id, type, getAdmin());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return "view";
        }

        public void more() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = TongjiService.index(start);
                getServletResponse().getWriter().print(result);
        }

        public List<Tongji> getTongjis() {
                return tongjis;
        }

        public void setTongjis(List<Tongji> tongjis) {
                this.tongjis = tongjis;
        }

        public long getStart() {
                return start;
        }

        public void setStart(long start) {
                this.start = start;
        }

        public TongjiIndex getIndex() {
                return index;
        }

        public void setIndex(TongjiIndex index) {
                this.index = index;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }
}
