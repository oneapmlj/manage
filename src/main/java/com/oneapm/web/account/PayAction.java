package com.oneapm.web.account;

import java.io.IOException;

import com.oneapm.service.account.PayService;
import com.oneapm.util.OneTools;
import com.oneapm.util.test.Shouhui;
import com.oneapm.web.SupportAction;

public class PayAction extends SupportAction {

        /**
	 * 
	 */
        private static final long serialVersionUID = 1L;

        private int type;
        private int level;
        private String endTime;
        private Long userId;
        public String pay() {
                if (!isLogin()) {
                        return "login";
                }
                try {
//                        Xiaoshouyi.tuisongxiaoshouyi();
//                        Shouhui.wajue();
//                       LOG.info("START LIANXUI");
//                        Shouhui.genzongbaobiao();
                        Shouhui.xinxifugai();
//                        LOG.info("START DIAOSHUJU");
//                        Shouhui.diaoshujuliebiao();
//                        LOG.info("START LIUCUN");
//                        Shouhui.meizhoubaobiao();
//                        LOG.info("END.........................");
                } catch (Exception e) {
                        LOG.info(e.getMessage(), e);
                }
                return "pay";
        }
        
        public void payadd() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = PayService.payadd(userId, endTime, null, null, level);
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(),  e);
                }
                getServletResponse().getWriter().print(OneTools.getResult(0, "服务器内部错误"));
        }
        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public String getEndTime() {
                return endTime;
        }

        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public int getLevel() {
                return level;
        }

        public void setLevel(int level) {
                this.level = level;
        }
}
