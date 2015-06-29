package com.oneapm.dto.Account;


public class Admin {

	private Long id;
	
	private String username;
	
	private String password;
	
	private int grade;
	
	private int status;
	
	private String createTime;
	
	private Long createId;
	
	private String name;
	
	private String loginTime;
	
	private int group;
	
	private String position;
	
	private String phone;
	
	private String email;
	
	private String nickName;
	
	private String grades;
	
	private int assign;
	
	private int manage;
	
	private int review;
	
	private int pay;
	
	private int duandian;
	
	public Admin(int group, Long id, String username, String password, int grade,
	        int status, String createTime, Long createId, String position, String phone,
	        String email, String nickName, String grades){
		setId(id);
		setUsername(username);
		setPassword(password);
		setGrade(grade);
		setStatus(status);
		setCreateTime(createTime);
		setCreateId(createId);
		setGroup(group);
		setPosition(position);
		setPhone(phone);
		setEmail(email);
		setNickName(nickName);
		setGrades(grades);
		setDuandian(0);
	}
	
	public Admin(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public int getAssign() {
        return assign;
    }

    public void setAssign(int assign) {
        this.assign = assign;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

public int getPay() {
        return pay;
}

public void setPay(int pay) {
        this.pay = pay;
}

public int getDuandian() {
        return duandian;
}

public void setDuandian(int duandian) {
        this.duandian = duandian;
}
	
}
