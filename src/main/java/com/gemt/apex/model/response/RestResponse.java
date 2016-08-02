package com.gemt.apex.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponse<T> extends ResponseEntity<T>{

	public RestResponse(Object result) {
		super((HttpStatus) result);
	}
	
	
}
