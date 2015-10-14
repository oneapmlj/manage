package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.service.account.AccountRecordService;
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
                int skip = page * pageSize;
                if (grades == null) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = MessageService.findVosJSONByAdminId(getAdmin().getId(), pageSize, skip);
                getServletResponse().getWriter().print(result);
        }
        
        private List<MessageVo> vos;
        private int page;
        private int pageNow;
        private int pageTotal;
        private int pageSize;
        private int total;
        public String index(){
                String grades = isLogin(false);
                if (grades == null) {
                        return "login";
                }
                try{	
                		if(pageNow == 0){
                			pageNow = 1;
                		}
                		if(pageSize == 0){
                			pageSize = 30;
                		}
                        int skip = page*pageSize;
                        total = MessageService.findCount(getAdmin().getId());
                        if(total <= skip){
                                page --;
                                skip = page*pageSize;
                                if(skip < 0){
                                        skip =0;
                                }
                        }
                        pageTotal = total/pageSize+1;
                        vos = MessageService.findMessage(getAdmin().getId(), pageSize, skip);
                        String result = MessageService.findMessageWithPagination(getAdmin().getId(),  pageSize, page,pageTotal,vos);
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "index";
        }
        public void page() throws IOException{
            String grades = isLogin(false);
            if (grades == null) {
            	getServletResponse().sendRedirect("/login.action");
                return;
            }
            try{	
            		if(pageSize == 0){
            			pageSize = 30;
            		}
                    int skip = page*pageSize;
                    total = MessageService.findCount(getAdmin().getId());
                    if(total <= skip){
                            page --;
                            skip = page*pageSize;
                            if(skip < 0){
                                    skip =0;
                            }
                    }
                    pageTotal = total/pageSize+1;
                    vos = MessageService.findMessage(getAdmin().getId(), pageSize, skip);
                    String result = MessageService.findMessageWithPagination(getAdmin().getId(),  pageSize, page,pageTotal,vos);
                    getServletResponse().getWriter().print(result);
            }catch(Exception e){
                    LOG.error(e.getMessage(), e);
            }
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
        
		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageNow() {
			return pageNow;
		}

		public void setPageNow(int pageNow) {
			this.pageNow = pageNow;
		}
        
}
