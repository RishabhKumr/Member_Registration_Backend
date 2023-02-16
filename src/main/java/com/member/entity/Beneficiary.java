package com.member.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document(collection="beneficiary")
public class Beneficiary {
	
	@Transient
    public static final String SEQUENCE_NAME = "beneficiary_sequence";
	
	@Id
	private String beneficiaryId;
	
	private String beneficiaryName;
	
	private String beneficiaryDOB;
	
	private String beneficiaryRelation;
	
	private String beneficiaryPAN;
	
	private String  beneficiaryAddress;
	
	private String beneficiaryState;
	
	private String beneficiaryCountry;
	
	private String memberId;

	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Beneficiary(String beneficiaryId, String beneficiaryName, String beneficiaryDOB, String beneficiaryRelation,
			String beneficiaryPAN, String beneficiaryAddress, String beneficiaryState, String beneficiaryCountry,
			String memberId) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryDOB = beneficiaryDOB;
		this.beneficiaryRelation = beneficiaryRelation;
		this.beneficiaryPAN = beneficiaryPAN;
		this.beneficiaryAddress = beneficiaryAddress;
		this.beneficiaryState = beneficiaryState;
		this.beneficiaryCountry = beneficiaryCountry;
		this.memberId = memberId;
	}

	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryDOB() {
		return beneficiaryDOB;
	}

	public void setBeneficiaryDOB(String beneficiaryDOB) {
		this.beneficiaryDOB = beneficiaryDOB;
	}

	public String getBeneficiaryRelation() {
		return beneficiaryRelation;
	}

	public void setBeneficiaryRelation(String beneficiaryRelation) {
		this.beneficiaryRelation = beneficiaryRelation;
	}

	public String getBeneficiaryPAN() {
		return beneficiaryPAN;
	}

	public void setBeneficiaryPAN(String beneficiaryPAN) {
		this.beneficiaryPAN = beneficiaryPAN;
	}

	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}

	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}

	public String getBeneficiaryState() {
		return beneficiaryState;
	}

	public void setBeneficiaryState(String beneficiaryState) {
		this.beneficiaryState = beneficiaryState;
	}

	public String getBeneficiaryCountry() {
		return beneficiaryCountry;
	}

	public void setBeneficiaryCountry(String beneficiaryCountry) {
		this.beneficiaryCountry = beneficiaryCountry;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	

}
