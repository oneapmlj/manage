package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.service.message.MessageService;
import com.oneapm.service.message.TipService;
import com.oneapm.vo.MessageVo;
import com.oneapm.web.SupportAction;

public class MessageAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = -946583650952830574L;

        private String warming;

        public void warming() throws IOException {
                String grades = isLogin(false);
                if (grades == null) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = MessageService.getWarming();
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void setWarming() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        if (!getAdmin().getId().equals(99999999L)) {
                                return;
                        }
                        if (warming != null) {
//                                warming = new String(warming.getBytes("ISO8859-1"), "UTF-8");
                                if (warming.length() > 0) {
                                        MessageService.setWarming(warming);
                                }
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void change() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }

        }

        private Long to;
        private Long infoId;

        public void tip() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = TipService.tip(getAdmin(), infoId, to);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void message() throws IOException {
                String grades = isLogin(false);
                if (grades == null) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = MessageService.findVosJSONByAdminId(getAdmin().getId());
                getServletResponse().getWriter().print(result);
        }
        
        private List<MessageVo> vos;
        private int page;
        private int pageTotal;
        public String index(){
                String grades = isLogin(false);
                if (grades == null) {
                        return "login";
                }
                try{
                        int skip = page*20;
                        int total = MessageService.findCount(getAdmin().getId());
                        if(total <= skip){
                                page --;
                                skip = page*20;
                                if(skip < 0){
                                        skip =0;
                                }
                        }
                        pageTotal = total/20+1;
                        vos = MessageService.findMessage(getAdmin().getId(), 20, skip);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "index";
        }

        private Long id;

        public void view() throws IOException {
                String grades = isLogin(false);
                if (grades == null) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = MessageService.check(id);
                getServletResponse().getWriter().print(result);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getWarming() {
                return warming;
        }

        public void setWarming(String warming) {
                this.warming = warming;
        }

        public Long getTo() {
                return to;
        }

        public void setTo(Long to) {
                this.to = to;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public List<MessageVo> getVos() {
                return vos;
        }

        public void setVos(List<MessageVo> vos) {
                this.vos = vos;
        }

        public int getPage() {
                return page;
        }

        public void setPage(int page) {
                this.page = page;
        }

        public int getPageTotal() {
                return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
                this.pageTotal = pageTotal;
        }
}
