package com.oneapm.web.count;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.service.count.CountService;
import com.oneapm.web.SupportAction;

public class CountShowAction extends SupportAction{
	private static final long serialVersionUID = 1L;
	
    protected static final Logger LOG = LoggerFactory.getLogger(CountShowAction.class);
    private List<CountDto> countDto;
    private String name;
    private String event;
    private String email;
    private String labelid;
    private int number;
    private String date;
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLabelid() {
		return labelid;
	}
	public void setLabelid(String labelid) {
		this.labelid = labelid;
	}
	public List<CountDto> getCountDto() {
		return countDto;
	}
	public void setCountDto(List<CountDto> countDto) {
		this.countDto = countDto;
	}
	public void index(){
	
		List<String> jsonList;
		this.date = date;
		try {
			jsonList = CountService.getJson(date);
			for(int i = 0; i < jsonList.size(); i ++){
			String outJson = new String(jsonList.get(i).getBytes("iso-8859-1"),"utf-8");
			getServletResponse().getWriter().print(outJson);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	String jsonList;

			try {
				jsonList = CountService.getJson(date);
				getServletResponse().getWriter().print(jsonList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
		
	
	}
	 public void insert() {
		 
       List<CountDto> dtolist = new ArrayList<>();
       CountDto dto = new CountDto();
		 try {
			 int num = CountService.getJson(date).size();
			 System.out.println(num);
			 for(int i=0; i<num; i++){
				 dto.setCreate_time(date);
				 dto.setEmail(null);
				 dto.setEvent(null);
				 dto.setUserId(0l);
				dtolist.add(dto);
			 }
			 CountService.insertCountDtoList(dtolist);
			getServletResponse().getWriter().print("1111");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	 
        		
        		
        	

	 } 
    
}
