package com.oneapm.web.group;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.lable.Lable;
import com.oneapm.service.lable.LableService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class LableAction extends SupportAction {


        /**
         * 
         */
        private static final long serialVersionUID = -3936614252515150734L;
        private List<Lable> lables;
        private Long lableId;
        public String add(){
                if(!isLogin()){
                        return "login";
                }
                try{
                        setLables(LableService.findChild(0L));
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "add";
        }
        
        private String name;
        private String description;
        private Long father;
        private String key;
        public void insert() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        if(!getAdmin().getId().equals(99999999L)){
                                getServletResponse().getWriter().print(OneTools.getResult(0, "无权限"));
                                return;
                        }
                        if (name != null) {
//                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                                if(name.trim().length() <= 0 || name.trim().length() > 20){
                                        getServletResponse().getWriter().print(OneTools.getResult(0, "分类不符合规则"));
                                        return;
                                }
                        }else{
                                getServletResponse().getWriter().print(OneTools.getResult(0, "分类不能为空"));
                                return;
                        }
                        if (description != null) {
//                                description = new String(description.getBytes("ISO8859-1"), "UTF-8");
                        }else{
                                description="";
                        }
                        if(key == null || key.trim().length() <= 0 || key.trim().length() > 2){
                                getServletResponse().getWriter().print(OneTools.getResult(0, "字符不符合规则"));
                                return;
                        }
                        String result = LableService.insert(name, description, father, key);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public void lable() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = LableService.lable(lableId);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        public void single() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = LableService.lable(lableId);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public List<Lable> getLables() {
                return lables;
        }
        public void setLables(List<Lable> lables) {
                this.lables = lables;
        }
        public Long getLableId() {
                return lableId;
        }
        public void setLableId(Long lableId) {
                this.lableId = lableId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Long getFather() {
                return father;
        }

        public void setFather(Long father) {
                this.father = father;
        }

        public String getKey() {
                return key;
        }

        public void setKey(String key) {
                this.key = key;
        }

}
