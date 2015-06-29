package com.oneapm.util.exception;

public class TimeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5644322258085711624L;
	
	public TimeException(String message){
		super(message);
	}
	
	public TimeException(){
		super();
	}
}
