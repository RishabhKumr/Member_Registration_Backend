package com.member.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1;
	private String MemberName;
	private String fieldName;
	private Object fieldValue;
	public MemberNotFoundException(String memberName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : %s",memberName,fieldName,fieldValue));
		MemberName = memberName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMemberName() {
		return MemberName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	
	

}
