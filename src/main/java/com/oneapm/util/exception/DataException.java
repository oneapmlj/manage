package com.oneapm.util.exception;

public class DataException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5644322258085711624L;
	
	public DataException(String message){
		super(message);
	}
	
	public DataException(){
		super();
	}
}
