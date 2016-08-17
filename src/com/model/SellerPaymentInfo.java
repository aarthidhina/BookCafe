package com.model;

public class SellerPaymentInfo {
	private int routing_no;
	private int acct_no;
	
	public SellerPaymentInfo() {
		routing_no = 0;
		acct_no = 0;
	}

	public int getRouting_no() {
		return routing_no;
	}

	public void setRouting_no(int routing_no) {
		this.routing_no = routing_no;
	}

	public int getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(int acct_no) {
		this.acct_no = acct_no;
	}

}
