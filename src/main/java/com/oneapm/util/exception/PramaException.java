package com.oneapm.util.exception;

public class PramaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5644322258085711624L;
	
	public PramaException(String message){
		super(message);
	}
	
	public PramaException(){
		super();
	}
}
