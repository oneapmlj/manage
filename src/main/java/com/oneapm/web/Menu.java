package com.oneapm.web;

import java.io.UnsupportedEncodingException;

public class Menu {
	private String name;
	
	private int menuId;
	
	public Menu(String name, int menuId){
		setName(name);
		setMenuId(menuId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			this.name = new String(name.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
}
