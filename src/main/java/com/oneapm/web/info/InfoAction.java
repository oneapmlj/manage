package com.oneapm.web.info;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.oneapm.dto.Call;
import com.oneapm.dto.Mail;
import com.oneapm.dto.MailMode;
import com.oneapm.dto.card.Card;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.tag.Language;
import com.oneapm.service.card.CardService;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.info.AppService;
import com.oneapm.service.info.DashboardService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.ZhengzailianxiService;
import com.oneapm.service.mail.MailService;
import com.oneapm.service.record.Xiaoshouyi;
import com.oneapm.service.show.CallService;
import com.oneapm.service.show.IndexShowService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;
import com.oneapm.web.JWT;
import com.oneapm.web.LoginApi;
import com.oneapm.web.SupportAction;

public class InfoAction extends SupportAction {

        /**
     * 
     */
        private static final long serialVersionUID = -221095790153864272L;
        private Long userId;
        private String name;
        private String company;
        private String phone;
        private String email;
        private Info info;
        private List<Info> infos;
        private String signStart;
        private String signEnd;
        private String loginStart;
        private String loginEnd;
        private String downloadStart;
        private String downloadEnd;
        private String addStart;
        private String addEnd;
        private Long id;
        private Long support;
        private Long sale;
        private Long cardId;
        private String mark;
        private int type;
        private Long recordType;
        private Long adminId;
        private String time;
        private String project;

        private int size;
        private String lang;
        
        private Long appId;
        private int agent;

        public String view() {
                if (!isLogin()) {
                        return "login";
                }
                info = InfoService.findById(id, getAdmin());
                if (info != null) {
                        if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(105))) {
                                info.setAssign(1);
                        }
                }
                return "view";
        }
        
        public void appMap() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = AppService.appMap(appId, agent);
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        private Long groupId;
        /**
         * 更改状态
         * @throws IOException
         */
        public void group() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        if(quanxian(getAdmin().getGrades(), getGRADE().getMap().get(117))){
                                String result = GroupService.change(infoId, groupId, getAdmin().getId());
                                getServletResponse().getWriter().print(result);
                        }else{
                                getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public void dashboard() throws IOException{
                try{
                        String result = DashboardService.dashboard();
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public String  dashboard_show() {
                return "dashboard_show";
        }
        
        public String onlianxi(){
                if (!isLogin()) {
                        return "login";
                }
                try{
                        infos = InfoService.onlianxi(getAdmin());
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return "onlianxi";
        }
        
        private String license;
        private int pay_level;
        public void edit() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        if (qq != null) {
                                qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (project != null) {
                                project = new String(project.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (name != null) {
                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (phone != null) {
                                phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (email != null) {
                                email = new String(email.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (license != null) {
                                license = new String(license.getBytes("ISO8859-1"), "UTF-8");
                        }
                        String result = InfoService.edit(id, project, qq, name, phone, email, license, gender, pay_level, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public String language() {
                if (!isLogin()) {
                        return "login";
                }
                if (getAdmin().getGroup() < 4) {
                        return ERROR;
                }
                try {
                        if (language <= 0) {
                                language = 1;
                        }
                        lang = Language.getName(language);
                        infos = InfoService.findByLanguage(language);
                        setSize(infos.size());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return "language";
        }
        
        public void zhengzailianxi() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = ZhengzailianxiService.findPush(infoId, getAdmin());
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public void lianxi() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = ZhengzailianxiService.lianxi(infoId, getAdmin());
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        public void unlianxi() throws IOException{
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        String result = ZhengzailianxiService.unlianxi(infoId, getAdmin());
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }

        public String user_view() {
                if (!isLogin()) {
                        return "login";
                }
                info = InfoService.findByUserId(userId, getAdmin());
                if (info != null) {
                        if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(105))) {
                                info.setAssign(1);
                        }
                }
                return "view";
        }
        public String salespanel(){
                return "salespanel";
        }
        private String xiaoshou;
        public void xiaoshouyi() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try{
                        if (xiaoshou != null) {
                                xiaoshou = new String(xiaoshou.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if(!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(116))){
                                getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足"));
                                return;
                        }
                        String result = Xiaoshouyi.xiaoshouyi(getAdmin(), infoId, xiaoshou);
                        getServletResponse().getWriter().print(result);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }

        public void support() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = InfoService.assign(getAdmin(), 2, id, adminId);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void back() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = InfoService.back(getAdmin(), type, id);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void delete() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = InfoService.delete(getAdmin(), id, type);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void sale() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = InfoService.assign(getAdmin(), 1, id, adminId);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void presale() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        String result = InfoService.assign(getAdmin(), 3, id, adminId);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void view_json() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = null;
                if ((id == null || id <= 0) && (infoId == null || infoId <= 0)) {
                        result = InfoService.findJsonByUserId(userId);
                } else {
                        result = InfoService.findJsonById(id);
                }
                getServletResponse().getWriter().print(result);
        }

        public String add() throws UnsupportedEncodingException {
                if (!isLogin()) {
                        return "login";
                }
                if (company != null) {
                        company = new String(company.getBytes("ISO8859-1"), "UTF-8");
                }
                if (name != null) {
                        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                }
                if (qq != null) {
                        qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                }
                infos = InfoService.addSearch(getAdmin(), email, name, phone, company, qq, false);
                return "add";
        }

        public void insert() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (name == null || phone == null || email == null || company == null) {
                        String result = "{'status':0,'msg':信息不完整}";
                        getServletResponse().getWriter().print(result);
                        return;
                }
                try {
                        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        company = new String(company.getBytes("ISO8859-1"), "UTF-8");
                        position = new String(position.getBytes("ISO8859-1"), "UTF-8");
                        branch = new String(branch.getBytes("ISO8859-1"), "UTF-8");
                        qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                        project = new String(project.getBytes("ISO8859-1"), "UTF-8");
                        String result = InfoService.insertInfo(new Card(null, name, branch, position, phone, email, null, getAdmin().getId(), null, null, gender, qq), project, company);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public String update() {
                if (!isLogin()) {
                        return "login";
                }
                if (id == null || id <= 0) {
                        return "error";
                }
                try {
                        info = InfoService.findById(id, getAdmin());

                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return "update";
        }

        public String search() {
                if (!isLogin()) {
                        return "login";
                }
                return "search";
        }

        private boolean in;
        private int metric;
        private int loudou;
        private int language;
        private String qq;
        private Long gongdan;

        public void searchOut() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (name != null) {
                        name = new String(name.getBytes("ISO8859-1"), "UTF-8").trim();
                }
                if (company != null) {
                        company = new String(company.getBytes("ISO8859-1"), "UTF-8");
                }
                if (qq != null) {
                        qq = new String(qq.getBytes("ISO8859-1"), "UTF-8").trim();
                }
                String result = InfoService.searchOut(email, name, phone, company, in, getAdmin(), qq);
                getServletResponse().getWriter().print(result);
        }

        private Long from;
        private Long messageId;

        public void change() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                String result = InfoService.change(getAdmin(), from, id, type, messageId);
                getServletResponse().getWriter().print(result);
        }

        private Long infoId;
        private int gender;
        private String position;
        private String branch;

        public void add_card() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(102))) {
                        try {
                                getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                                return;
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                try {
                        if (name != null) {
                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (position != null) {
                                position = new String(position.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (branch != null) {
                                branch = new String(branch.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (phone != null) {
                                phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (email != null) {
                                email = new String(email.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (qq != null) {
                                qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                        }
                        String result = CardService.insert(new Card(userId, name, branch, position, phone, email, null, getAdmin().getId(), infoId, null, gender, qq), getAdmin().getName());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void edit_card() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                try {
                        if (name != null) {
                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (position != null) {
                                position = new String(position.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (branch != null) {
                                branch = new String(branch.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (phone != null) {
                                phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (email != null) {
                                email = new String(email.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (qq != null) {
                                qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                        }
                        String result = CardService.update(cardId, name, branch, phone, qq, gender, email, position);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        private String putTime;
        private String add_time;
        private boolean add_call_point;

        public void add_call() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(103))) {
                        try {
                                getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                                return;
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                try {
                        mark = new String(mark.getBytes("ISO8859-1"), "UTF-8");
                        if (phone != null) {
                                phone = new String(phone.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (email != null) {
                                email = new String(email.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (qq != null) {
                                qq = new String(qq.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (name != null) {
                                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (position != null) {
                                position = new String(position.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (branch != null) {
                                branch = new String(branch.getBytes("ISO8859-1"), "UTF-8");
                        }
                        if (add_time == null || add_time.trim().length() < 1) {
                                putTime = TimeTools.format();
                        } else {
                                putTime = add_time;
                        }
                        String result = CallService.insert(infoId, cardId, qq, time, gongdan, mark, recordType, getAdmin(), putTime, name, phone, email, gender, position, branch, add_call_point);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public void check() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(104))) {
                        try {
                                getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                                return;
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                email = IndexShowService.findEmailById(userId);
                // 获取用户运营系统的id
                Long KFId = IndexShowService.findKFIdByEmail(email, id);
                String url = LoginApi.loginApi("yunyingadmin@oneapm.com", "/agent/#/user/" + KFId + "/tickets");
                getServletResponse().sendRedirect(url);
        }

        public void check_udesk() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                        return;
                }
                if (!quanxian(getAdmin().getGrades(), getGRADE().getMap().get(104))) {
                        try {
                                getServletResponse().getWriter().print("{'status':0,'msg':'无此操作权限'}");
                                return;
                        } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                        }
                }
                Info info = InfoService.findByIdSimple(id);
                // 获取用户运营系统的id
                Long udeskId = JWT.findUdeskId(info.getEmail(), id);
//                Long udeskId = JWT.findUdeskId("520287450@QQ.com", id);
                String url = JWT.getUrl(getAdmin().getEmail(), getAdmin().getName(), "http://oneapm.udesk.cn/entry/customer_center/customers/" + udeskId);
                getServletResponse().sendRedirect(url);
        }

        private List<Call> calls;

        public String calls() {
                if (!isLogin()) {
                        return "login";
                }
                if (infoId == null) {
                        return "error";
                }
                calls = CallService.findByInfoId(infoId);
                info = InfoService.findByIdSimple(infoId);
                for (Call call : calls) {
                        if (call.getCardName() == null) {
                                call.setCardName(info.getName());
                        }
                }
                return "calls";
        }

        private List<Mail> mails;

        public String mails() {
                if (!isLogin()) {
                        return "login";
                }
                if (infoId == null) {
                        return "error";
                }
                mails = MailService.findMailsById(infoId);
                info = InfoService.findByIdSimple(infoId);
                return "mails";
        }

        public List<Info> getInfos() {
                return infos;
        }

        public void setInfos(List<Info> infos) {
                this.infos = infos;
        }

        public String getSignStart() {
                return signStart;
        }

        public void setSignStart(String signStart) {
                this.signStart = signStart;
        }

        public String getSignEnd() {
                return signEnd;
        }

        public void setSignEnd(String signEnd) {
                this.signEnd = signEnd;
        }

        public String getLoginStart() {
                return loginStart;
        }

        public void setLoginStart(String loginStart) {
                this.loginStart = loginStart;
        }

        public String getLoginEnd() {
                return loginEnd;
        }

        public void setLoginEnd(String loginEnd) {
                this.loginEnd = loginEnd;
        }

        public String getDownloadStart() {
                return downloadStart;
        }

        public void setDownloadStart(String downloadStart) {
                this.downloadStart = downloadStart;
        }

        public String getDownloadEnd() {
                return downloadEnd;
        }

        public void setDownloadEnd(String downloadEnd) {
                this.downloadEnd = downloadEnd;
        }

        public String getAddStart() {
                return addStart;
        }

        public void setAddStart(String addStart) {
                this.addStart = addStart;
        }

        public String getAddEnd() {
                return addEnd;
        }

        public void setAddEnd(String addEnd) {
                this.addEnd = addEnd;
        }

        public Info getInfo() {
                return info;
        }

        public void setInfo(Info info) {
                this.info = info;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getSupport() {
                return support;
        }

        public void setSupport(Long support) {
                this.support = support;
        }

        public Long getSale() {
                return sale;
        }

        public void setSale(Long sale) {
                this.sale = sale;
        }

        public String getBranch() {
                return branch;
        }

        public void setBranch(String branch) {
                this.branch = branch;
        }

        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public int getGender() {
                return gender;
        }

        public void setGender(int gender) {
                this.gender = gender;
        }

        public Long getInfoId() {
                return infoId;
        }

        public void setInfoId(Long infoId) {
                this.infoId = infoId;
        }

        public Long getCardId() {
                return cardId;
        }

        public void setCardId(Long cardId) {
                this.cardId = cardId;
        }

        public String getMark() {
                return mark;
        }

        public void setMark(String mark) {
                this.mark = mark;
        }

        public List<Call> getCalls() {
                return calls;
        }

        public void setCalls(List<Call> calls) {
                this.calls = calls;
        }

        public List<MailMode> getMAIL_MODE() {
                return MAIL_MODE;
        }

        public List<Mail> getMails() {
                return mails;
        }

        public void setMails(List<Mail> mails) {
                this.mails = mails;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public Long getFrom() {
                return from;
        }

        public void setFrom(Long from) {
                this.from = from;
        }

        public Long getMessageId() {
                return messageId;
        }

        public void setMessageId(Long messageId) {
                this.messageId = messageId;
        }

        public boolean isIn() {
                return in;
        }

        public void setIn(boolean in) {
                this.in = in;
        }

        public int getMetric() {
                return metric;
        }

        public void setMetric(int metric) {
                this.metric = metric;
        }

        public int getLoudou() {
                return loudou;
        }

        public void setLoudou(int loudou) {
                this.loudou = loudou;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public int getLanguage() {
                return language;
        }

        public void setLanguage(int language) {
                this.language = language;
        }

        public int getSize() {
                return size;
        }

        public void setSize(int size) {
                this.size = size;
        }

        public String getLang() {
                return lang;
        }

        public void setLang(String lang) {
                this.lang = lang;
        }

        public Long getGongdan() {
                return gongdan;
        }

        public void setGongdan(Long gongdan) {
                this.gongdan = gongdan;
        }

        public String getQq() {
                return qq;
        }

        public void setQq(String qq) {
                this.qq = qq;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public Long getRecordType() {
                return recordType;
        }

        public void setRecordType(Long recordType) {
                this.recordType = recordType;
        }

        public String getAdd_time() {
                return add_time;
        }

        public void setAdd_time(String add_time) {
                this.add_time = add_time;
        }

        public String getPutTime() {
                return putTime;
        }

        public void setPutTime(String putTime) {
                this.putTime = putTime;
        }

        public String getProject() {
                return project;
        }

        public void setProject(String project) {
                this.project = project;
        }

        public boolean isAdd_call_point() {
                return add_call_point;
        }

        public void setAdd_call_point(boolean add_call_point) {
                this.add_call_point = add_call_point;
        }

        public Long getAppId() {
                return appId;
        }

        public void setAppId(Long appId) {
                this.appId = appId;
        }

        public String getLicense() {
                return license;
        }

        public void setLicense(String license) {
                this.license = license;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

        public int getPay_level() {
                return pay_level;
        }

        public void setPay_level(int pay_level) {
                this.pay_level = pay_level;
        }

        public String getXiaoshou() {
                return xiaoshou;
        }

        public void setXiaoshou(String xiaoshou) {
                this.xiaoshou = xiaoshou;
        }

        public Long getGroupId() {
                return groupId;
        }

        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }
}
