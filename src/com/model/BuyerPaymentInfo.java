package com.model;

public class BuyerPaymentInfo {
//	private String user_id;
	private int card_no;
	private String expiry_date;
	private int security_code;

//	public String getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}

	public BuyerPaymentInfo() {
		card_no = 0;
		expiry_date = "";
		security_code = 0;
	}
	
	public int getCard_no() {
		return card_no;
	}

	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public int getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(int security_code) {
		this.security_code = security_code;
	}

}
