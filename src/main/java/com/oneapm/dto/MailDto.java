package com.oneapm.dto;

public class MailDto {
	private int id;
	private String title;
	private String content;
	private int status;
	private int use;
	private String createTime;
	private String lastUseTime;
	private String description;
	private int timeType;
	private String time;
	
	public MailDto(){}
	
	public MailDto(int id, int timeType, String title, String content, int status, String createTime, String lastUseTime, String description){
		setId(id);
		setTitle(title);
		setContent(content);
		setStatus(status);
		setCreateTime(createTime);
		setLastUseTime(lastUseTime);
		setDescription(description);
		setTimeType(timeType);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastUseTime() {
		return lastUseTime;
	}
	public void setLastUseTime(String lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
