package com.oneapm.web.account;

import java.util.List;

import com.oneapm.dao.account.impl.AdminDaoImpl;
import com.oneapm.dto.Account.Account;
import com.oneapm.dto.Account.Admin;
import com.oneapm.service.account.AccountRecordService;
import com.oneapm.service.account.AccountService;
import com.oneapm.web.SupportAction;

public class ReviewAction extends SupportAction {

        /**
	 * 
	 */
        private static final long serialVersionUID = 1L;

        private Account account;
        private Long id;
        private List<Admin> admins;
        private int type;
        private int adminType;
        public String review() {
                if (!isLogin()) {
                        return "login";
                }
                try {
                        if (getAdmin().getGrades() != null) {
                                if (quanxian(getAdmin().getGrades(), getGRADE().getMap().get(110))) {
                                        admins = AdminDaoImpl.getInstance().findAdmins(adminType);
                                        if (admins == null || admins.size() <= 0) {
                                                return "error";
                                        }
                                        Admin admin = null;
                                        if (id == null || id <= 0) {
                                                admin = admins.get(0);
                                        } else {
                                                admin = AccountService.findById(id);
                                        }
                                        account = AccountRecordService.findByAdmin(admin, type, true);
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        return "error";
                }
                return "review";
        }

        public Account getAccount() {
                return account;
        }

        public void setAccount(Account account) {
                this.account = account;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public List<Admin> getAdmins() {
                return admins;
        }

        public void setAdmins(List<Admin> admins) {
                this.admins = admins;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getAdminType() {
                return adminType;
        }

        public void setAdminType(int adminType) {
                this.adminType = adminType;
        }

}
