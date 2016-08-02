package com.gemt.apex.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage extends Object{
	
	private int error;
	private String message = "";
	private String displayMessage = "";
	
	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(int code, String message, String displayMessage) {
		super();
		this.error = code;
		this.message = message;
		this.displayMessage = displayMessage;
	}
	
	public ErrorMessage(int code, String message) {
		super();
		this.error = code;
		this.message = message;
		this.displayMessage = message;
	}
	
	public ErrorMessage(RestException ex) {
		super();
		this.error = ex.getErrorCode();
		this.message = ex.getMessage();
		this.displayMessage = ex.getDisplayMessage();
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
}