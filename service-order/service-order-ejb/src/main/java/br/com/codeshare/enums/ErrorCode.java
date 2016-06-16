package br.com.codeshare.enums;

public enum ErrorCode {

	PHONE_HAS_SERVICE_ORDER("100"),
	LEAST_ONE_PHONE_OBLIGATORY("101"),
	LENGTH_MUST_BE_BETWEEN_13_14("102");
	
	String errorCode;
	
	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
