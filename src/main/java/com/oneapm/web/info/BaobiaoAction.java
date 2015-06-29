package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.info.Baobiao;
import com.oneapm.dto.info.DataLiucunShow;
import com.oneapm.dto.info.DataLiucunView;
import com.oneapm.service.info.BaobiaoService;
import com.oneapm.service.info.LiucunYunyingService;
import com.oneapm.web.SupportAction;

public class BaobiaoAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = 8112493288384471578L;

        private int type;
        private int hang;
        private int lie;
        private int number;
        private String name;
        private Long id;
        private int page;
        private List<Baobiao> baobiaos;
        private List<DataLiucunShow> shows;
        private int fenlei;
        private String dataTime;
        private String from;
        private int agent;

        public String index() {
                if (!isLogin()) {
                        return "login";
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        setBaobiaos(BaobiaoService.findByType(type));
                }
                return "index";
        }

        public String liucun_yunying() {
                if (!isLogin()) {
                        return "login";
                }
                setName("用户留存率");
                return "liucun_yunying";
        }

        public void liucun_yunying_data() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        String result = LiucunYunyingService.liucun_yunying_data(agent);
                        getServletResponse().getWriter().print(result);
                }
        }

        public void liucun_yunying_sign() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        String result = LiucunYunyingService.liucun_yunying_sign(agent);
                        getServletResponse().getWriter().print(result);
                }
        }

        private DataLiucunView liucunView;

        public String liucun_yunying_view() throws IOException {
                if (!isLogin()) {
                        return "login";
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        liucunView = LiucunYunyingService.liucun_yunying_data_view(getAdmin(), dataTime, from, agent);
                }
                return "liucun_yunying_view";
        }
        
        public String sign_yunying_view() throws IOException {
                if (!isLogin()) {
                        return "login";
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        liucunView = LiucunYunyingService.liucun_yunying_sign_view(0, getAdmin(), dataTime, from, agent);
                }
                return "sign_yunying_view";
        }
        public String download_yunying_view() throws IOException {
                if (!isLogin()) {
                        return "login";
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        liucunView = LiucunYunyingService.liucun_yunying_sign_view(2, getAdmin(), dataTime, from, agent);
                }
                return "sign_yunying_view";
        }
        public String app_yunying_view() throws IOException {
                if (!isLogin()) {
                        return "login";
                }
                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(109))) {
                        liucunView = LiucunYunyingService.liucun_yunying_sign_view(3, getAdmin(), dataTime, from, agent);
                }
                return "sign_yunying_view";
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public List<Baobiao> getBaobiaos() {
                return baobiaos;
        }

        public void setBaobiaos(List<Baobiao> baobiaos) {
                this.baobiaos = baobiaos;
        }

        public int getHang() {
                return hang;
        }

        public void setHang(int hang) {
                this.hang = hang;
        }

        public int getLie() {
                return lie;
        }

        public void setLie(int lie) {
                this.lie = lie;
        }

        public List<DataLiucunShow> getShows() {
                return shows;
        }

        public void setShows(List<DataLiucunShow> shows) {
                this.shows = shows;
        }

        public int getNumber() {
                return number;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public DataLiucunView getLiucunView() {
                return liucunView;
        }

        public void setLiucunView(DataLiucunView liucunView) {
                this.liucunView = liucunView;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getPage() {
                return page;
        }

        public void setPage(int page) {
                this.page = page;
        }

        public int getFenlei() {
                return fenlei;
        }

        public void setFenlei(int fenlei) {
                this.fenlei = fenlei;
        }

        public String getDataTime() {
                return dataTime;
        }

        public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

        public String getFrom() {
                return from;
        }

        public void setFrom(String from) {
                this.from = from;
        }
}
