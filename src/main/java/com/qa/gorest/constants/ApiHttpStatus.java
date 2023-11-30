package com.qa.gorest.constants;

public enum ApiHttpStatus {
	
	OK_200(200,"Ok"),
	CREATED_201(201,"Created"),
	NO_CONTENT_204(204,"No Content"),
	BAD_REQUEST_400(400,"Bad Request"),
	UNAUTHORIZED_401(401,"Unautorized"),
	FORBIDDEN_403(403,"Forbidden"),
	NOT_FOUND(404,"Not Found"),
	INTERNAL_SERVER_ERROR_500(500,"Internal Server Error");
	
	private final int code;
	private final String message;

	ApiHttpStatus(int code, String message) {
		this.message = message;
		this.code=code;
		
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return code+" "+message;
	}
	

}
