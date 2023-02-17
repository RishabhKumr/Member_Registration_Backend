package com.member.dto;

public class MemberRegisterDto {
	private String memberName;
	private String memberAddress;
	private String memberState;
	private String memberCountry;
	private String memberEmail;
	private String memberPAN;
	private String memberContactNo;
	private String memberDOB;
	public MemberRegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberRegisterDto(String memberName, String memberAddress, String memberState, String memberCountry,
			String memberEmail, String memberPAN, String memberContactNo, String memberDOB) {
		super();
		this.memberName = memberName;
		this.memberAddress = memberAddress;
		this.memberState = memberState;
		this.memberCountry = memberCountry;
		this.memberEmail = memberEmail;
		this.memberPAN = memberPAN;
		this.memberContactNo = memberContactNo;
		this.memberDOB = memberDOB;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberState() {
		return memberState;
	}
	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPAN() {
		return memberPAN;
	}
	public void setMemberPAN(String memberPAN) {
		this.memberPAN = memberPAN;
	}
	public String getMemberContactNo() {
		return memberContactNo;
	}
	public void setMemberContactNo(String memberContactNo) {
		this.memberContactNo = memberContactNo;
	}
	public String getMemberDOB() {
		return memberDOB;
	}
	public void setMemberDOB(String memberDOB) {
		this.memberDOB = memberDOB;
	}
}
