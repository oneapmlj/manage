package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.group.Group;
import com.oneapm.dto.info.Info;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.info.DuandianService;
import com.oneapm.web.SupportAction;

public class DuandianAction extends SupportAction {

        private static final long serialVersionUID = 7112393993649033943L;

        private List<Info> infos;
        private int agent;
        private int data;
        private int login;
        private int paixu;
        private int sort;
        private int guanbi;
        private int jinri;
        private String banben;
        private String time;
        private int fuze;

        private List<Group> groups;
        public String index() {
                if (!isLogin()) {
                        return "login";
                }
                try{
                        groups = GroupService.findChild(0L);
                }catch(Exception e){
                        LOG.error(e.getMessage(),  e);
                }
                return "index";
        }

        private Long groupId1;
        private Long groupId2;
        
        public void chaxun() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        String result = DuandianService.chaxun(agent, data, paixu, sort, jinri, banben, time, getAdmin(), fuze, groupId1, groupId2);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void versions() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        String result = DuandianService.findVersions(agent);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public List<Info> getInfos() {
                return infos;
        }

        public void setInfos(List<Info> infos) {
                this.infos = infos;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

        public int getData() {
                return data;
        }

        public void setData(int data) {
                this.data = data;
        }

        public int getLogin() {
                return login;
        }

        public void setLogin(int login) {
                this.login = login;
        }

        public int getPaixu() {
                return paixu;
        }

        public void setPaixu(int paixu) {
                this.paixu = paixu;
        }

        public int getSort() {
                return sort;
        }

        public void setSort(int sort) {
                this.sort = sort;
        }

        public int getGuanbi() {
                return guanbi;
        }

        public void setGuanbi(int guanbi) {
                this.guanbi = guanbi;
        }

        public int getJinri() {
                return jinri;
        }

        public void setJinri(int jinri) {
                this.jinri = jinri;
        }

        public String getBanben() {
                return banben;
        }

        public void setBanben(String banben) {
                this.banben = banben;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public int getFuze() {
                return fuze;
        }

        public void setFuze(int fuze) {
                this.fuze = fuze;
        }

        public List<Group> getGroups() {
                return groups;
        }

        public void setGroups(List<Group> groups) {
                this.groups = groups;
        }

        public Long getGroupId1() {
                return groupId1;
        }

        public void setGroupId1(Long groupId1) {
                this.groupId1 = groupId1;
        }

        public Long getGroupId2() {
                return groupId2;
        }

        public void setGroupId2(Long groupId2) {
                this.groupId2 = groupId2;
        }
}
