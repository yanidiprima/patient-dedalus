package com.dedalus.patients.utils;

import org.springframework.http.HttpStatus;

public class ThrowErrorResponse extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;

    public ThrowErrorResponse(HttpStatus notFound, String message) {
        super(message);
        this.status= notFound;
    }

    public HttpStatus getStatus() {
        return status ;
    }

}
