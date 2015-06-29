package com.oneapm.web.group;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.group.Group;
import com.oneapm.service.group.GroupService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class GroupAction extends SupportAction {


        /**
         * 
         */
        private static final long serialVersionUID = -4820517592928861137L;

        private Long groupId;
        
        public void group() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = GroupService.group(groupId);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        private List<Group> groups;
        public String add(){
                if(!isLogin()){
                        return "login";
                }
                try{
                        groups = GroupService.findChild(0L);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "add";
        }
        private String name;
        private String description;
        private Long father;
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
                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        }else{
                                getServletResponse().getWriter().print(OneTools.getResult(0, "分类不能为空"));
                                return;
                        }
                        if (description != null) {
                                description = new String(description.getBytes("ISO8859-1"), "UTF-8");
                        }else{
                                description="";
                        }
                        String result = GroupService.insert(name, description, father);
                        getServletResponse().getWriter().print(result);
                        return;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public String group_index(){
                try{
                        if (!isLogin()) {
                                return "login";
                        }
                        
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "group_index";
        }

        public Long getGroupId() {
                return groupId;
        }

        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }

        public Long getFather() {
                return father;
        }

        public void setFather(Long father) {
                this.father = father;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public List<Group> getGroups() {
                return groups;
        }

        public void setGroups(List<Group> groups) {
                this.groups = groups;
        }

}
