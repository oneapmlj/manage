package com.oneapm.web.note;

import java.io.IOException;

import com.oneapm.service.note.NoteService;
import com.oneapm.web.SupportAction;

public class NoteAction extends SupportAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long father;
	private String name;
	private String description;
	private int todu;
	private Long id;
	public void types() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
	    try{
	        String result = NoteService.findNextType(father);
	        getServletResponse().getWriter().print(result);
	    }catch(Exception e){
	        LOG.error(e.getMessage(),  e);
	    }
	}
	
	public void insert() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            name = new String(name.getBytes("ISO8859-1"),"UTF-8"); 
            String result = NoteService.insert(father, name, description, getAdmin().getId(), todu);
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(),  e);
        }
	}
	
	public void type() throws IOException{
	    if(!isLogin()){
            getServletResponse().sendRedirect("/login.action");
            return;
        }
        try{
            String result = NoteService.getTypeById(id);
            getServletResponse().getWriter().print(result);
        }catch(Exception e){
            LOG.error(e.getMessage(),  e);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTodu() {
        return todu;
    }

    public void setTodu(int todu) {
        this.todu = todu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
