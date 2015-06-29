package com.oneapm.dto.file;

public class FileRecord {

    private Long id;
    
    private Long fileId;
    
    private int type; //1创建，2上传，3下载，4查看，5回收,6还原,7收藏，8移动,9修改
    
    private Long adminId;

    public FileRecord(Long id, Long fileId, int type, Long adminId){
        setId(id);
        setFileId(fileId);
        setType(type);
        setAdminId(adminId);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
