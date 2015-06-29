package com.oneapm.web.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dto.file.FileDto;
import com.oneapm.dto.file.Wendang;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.file.FileService;
import com.oneapm.util.OneTools;
import com.oneapm.web.SupportAction;

public class FileAction extends SupportAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final Logger LOG = LoggerFactory.getLogger(FileAction.class);
			
	private File file;
	
	private File[] files;
	
	private String fileName;
	
	private String fileFileName;
	
	private String contentType;
	
	private String filePath;
	
	private Wendang wendang;
	
    public void download() {
        try {
            FileDto dto = FileService.findById(id);
            if(dto == null){
                return;
            }
            String path = systemApplication.getAttribute("filesystem") + "file/" +dto.getCode();
            HttpServletResponse response = ServletActionContext.getResponse();
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
//            String filename = file.getName();
            String filename = dto.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String filenameString = new String(filename.getBytes("utf-8"),
                    "iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response
                    .getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	public void upload() throws Exception {
        String path = systemApplication.getAttribute("filesystem")+"file/";
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        String[] fileSuffix = new String[] { ".xlsx",".doc",".docx",".pdf",".txt", ".wps", "ppt", ".pps",".pot",
                ".dpt", ".dps", ".xls"};// 禁止文件
        try {
            File f = this.getFile();
            boolean in = false;
            // 判断文件格式
            for (int i = 0; i < fileSuffix.length; i++) {
                if(fileFileName.endsWith(fileSuffix[i])){
                    in = true;
                    break;
                }
            }
            if(!in){
                getServletResponse().getWriter().print(OneTools.getResult(0, "不允许文件类型"));
                return;
            }
            Random random = new Random();
            String code = AccountService.string2MD5(new Date().getTime()+name+random.nextFloat());
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream(path + code);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            wendang = FileService.uploadWendang(fileFileName, code);
            List<String> args1 = new ArrayList<String>();
            List<Object> args2 = new ArrayList<Object>();
            args1.add("id");
            args1.add("name");
            args2.add(wendang.getId());
            args2.add(fileFileName);
            String result = OneTools.getResult(1, args1, args2);
            getServletResponse().getWriter().print(result);
            return;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        getServletResponse().getWriter().print(OneTools.getResult(0, "服务器内部错误"));
        return;
    }
	
	public void save() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            if(quanxian(getAdmin().getGrades(), getGRADE().getMap().get(107))){
                String result = FileService.save(ids, father, status, getAdmin());
                getServletResponse().getWriter().print(result);
                return;
            }else{
                getServletResponse().getWriter().print(OneTools.getResult(0, "权限不足，请联系管理员"));
            }
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
        getServletResponse().getWriter().print(OneTools.getResult(0, "服务器错误"));
	}
	
	public String file(){
	    if(!isLogin()){
            return "login";
        }
	    return "file";
	}
	
	private Long father;
	private String name;
	private int filegrade;
	private int type;
	private int status;
	private String password;
	private Long id;
	private String ids;
	public void show() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            if(father == null || father < 0){
                setFather(0L);
            }
            String result = FileService.show(father, getAdmin());
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	}
	
	public void mulus() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            if(father == null || father < 0){
                setFather(0L);
            }
            String result = FileService.mulus(father, getAdmin());
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	}
	
	public void delete() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            if(getAdmin().getGroup() < 4){
                getServletResponse().getWriter().print("{'status':1,'msg':'没有权限'}");
            }
            String result = FileService.delete(id, getAdmin());
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	}
	
	public void rename() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            if(getAdmin().getGroup() < 4){
                getServletResponse().getWriter().print("{'status':1,'msg':'没有权限'}");
            }
            name = new String(name.getBytes("ISO8859-1"),"UTF-8"); 
            String result = FileService.rename(id, name, getAdmin());
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	}
	
	public void create() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            name = new String(name.getBytes("ISO8859-1"),"UTF-8");
            String result = FileService.create(getAdmin(), name, filegrade, type, father, status, password);
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
	}

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public Long getFather() {
        return father;
    }

    public void setFather(Long father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFilegrade() {
        return filegrade;
    }

    public void setFilegrade(int filegrade) {
        this.filegrade = filegrade;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wendang getWendang() {
        return wendang;
    }

    public void setWendang(Wendang wendang) {
        this.wendang = wendang;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
