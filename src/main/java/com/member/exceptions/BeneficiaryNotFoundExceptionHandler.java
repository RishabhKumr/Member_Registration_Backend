package com.member.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BeneficiaryNotFoundExceptionHandler extends RuntimeException{
	
	private static final long serialVersionUID = 1;
	private String BeneficiaryName;
	private String fieldName;
	private Object fieldValue;
	public BeneficiaryNotFoundExceptionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BeneficiaryNotFoundExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public BeneficiaryNotFoundExceptionHandler(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public BeneficiaryNotFoundExceptionHandler(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public BeneficiaryNotFoundExceptionHandler(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public BeneficiaryNotFoundExceptionHandler(String beneficiaryName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : %s",beneficiaryName,fieldName,fieldValue));
		BeneficiaryName = beneficiaryName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBeneficiaryName() {
		return BeneficiaryName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}

}
