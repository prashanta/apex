package com.gemt.apex.exception;

public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int errorCode = -1;
	private String errorMessage = "";
	private String displayMessage = "";
	
	public RestException(RestError error, String displayMessage){
		super(error.message());
		this.errorCode = error.code();		
		this.errorMessage = error.message();
		this.displayMessage = displayMessage;
	}
	
	public RestException(RestError error, Exception ex){
		super(ex.getLocalizedMessage());
		this.errorCode = error.code();
		this.errorMessage = ex.getMessage();
		this.displayMessage = error.message();
	}
	
	public RestException(RestError error){
		super(error.message());
		this.errorCode = error.code();
		this.displayMessage = error.message();
	}
	
	public int getErrorCode(){
		return this.errorCode;
	}
		
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public String getDisplayMessage(){
		return this.displayMessage;
	}
}
