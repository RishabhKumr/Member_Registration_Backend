package com.member.dto;

public class ClaimDto {
	
	
	private String claimType;
	
	private String claimName;
	
	private String claimAmount;
	
	private String claimFor;
	
	private String claimerId;
	
	private String claimAdmissionDate;
	
	private String claimDischargeDate;
	
	private String claimProvider;
	
	private String claimerName;
	
	private String claimerDOB;

	public ClaimDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClaimDto(String claimName, String claimType, String claimAmount, String claimFor, String claimerId,
			String claimAdmissionDate, String claimDischargeDate, String claimProvider, String claimerName,
			String claimerDOB) {
		super();
		this.claimName = claimName;
		this.claimType = claimType;
		this.claimAmount = claimAmount;
		this.claimFor = claimFor;
		this.claimerId = claimerId;
		this.claimAdmissionDate = claimAdmissionDate;
		this.claimDischargeDate = claimDischargeDate;
		this.claimProvider = claimProvider;
		this.claimerName = claimerName;
		this.claimerDOB = claimerDOB;
	}

	public String getClaimName() {
		return claimName;
	}

	public void setClaimName(String claimName) {
		this.claimName = claimName;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimFor() {
		return claimFor;
	}

	public void setClaimFor(String claimFor) {
		this.claimFor = claimFor;
	}

	public String getClaimerId() {
		return claimerId;
	}

	public void setClaimerId(String claimerId) {
		this.claimerId = claimerId;
	}

	public String getClaimAdmissionDate() {
		return claimAdmissionDate;
	}

	public void setClaimAdmissionDate(String claimAdmissionDate) {
		this.claimAdmissionDate = claimAdmissionDate;
	}

	public String getClaimDischargeDate() {
		return claimDischargeDate;
	}

	public void setClaimDischargeDate(String claimDischargeDate) {
		this.claimDischargeDate = claimDischargeDate;
	}

	public String getClaimProvider() {
		return claimProvider;
	}

	public void setClaimProvider(String claimProvider) {
		this.claimProvider = claimProvider;
	}

	public String getClaimerName() {
		return claimerName;
	}

	public void setClaimerName(String claimerName) {
		this.claimerName = claimerName;
	}

	public String getClaimerDOB() {
		return claimerDOB;
	}

	public void setClaimerDOB(String claimerDOB) {
		this.claimerDOB = claimerDOB;
	}
	
}
