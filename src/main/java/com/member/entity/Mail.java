package com.member.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ContactUs")
public class Mail {
	private String mailerId;
	private String mailerEmail;
	private String mailerMessage;
	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mail(String mailerId, String mailerEmail, String mailerMessage) {
		super();
		this.mailerId = mailerId;
		this.mailerEmail = mailerEmail;
		this.mailerMessage = mailerMessage;
	}
	public String getMailerId() {
		return mailerId;
	}
	public void setMailerId(String mailerId) {
		this.mailerId = mailerId;
	}
	public String getMailerEmail() {
		return mailerEmail;
	}
	public void setMailerEmail(String mailerEmail) {
		this.mailerEmail = mailerEmail;
	}
	public String getMailerMessage() {
		return mailerMessage;
	}
	public void setMailerMessage(String mailerMessage) {
		this.mailerMessage = mailerMessage;
	}
	
	
}
