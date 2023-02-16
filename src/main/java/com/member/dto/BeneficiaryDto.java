package com.member.dto;

public class BeneficiaryDto {
	
	private String beneficiaryName;
	
	private String beneficiaryDOB;
	
	private String beneficiaryRelation;
	
	private String beneficiaryPAN;
	
	private String  beneficiaryAddress;
	
	private String beneficiaryState;
	
	private String beneficiaryCountry;

	public BeneficiaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryDto(String beneficiaryName, String beneficiaryDOB, String beneficiaryRelation,
			String beneficiaryPAN, String beneficiaryAddress, String beneficiaryState, String beneficiaryCountry) {
		super();
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryDOB = beneficiaryDOB;
		this.beneficiaryRelation = beneficiaryRelation;
		this.beneficiaryPAN = beneficiaryPAN;
		this.beneficiaryAddress = beneficiaryAddress;
		this.beneficiaryState = beneficiaryState;
		this.beneficiaryCountry = beneficiaryCountry;
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
	
	
}
