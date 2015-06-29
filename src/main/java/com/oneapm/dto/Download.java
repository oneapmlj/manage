package com.oneapm.dto;

public class Download {

	private	String downloadTime;
	
	private Long userId;
	
	private Long id;
	
	private String agentName;
	
	private String vesion;
	
	public Download(){
		
	}
	
	public Download(Long id, Long userId, String downloadTime, String agentName, String vesion){
		setId(id);
		setUserId(userId);
		setDownloadTime(downloadTime);
		setAgentName(agentName);
		setVesion(vesion);
	}

	public String getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVesion() {
		return vesion;
	}

	public void setVesion(String vesion) {
		this.vesion = vesion;
	}

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

}
