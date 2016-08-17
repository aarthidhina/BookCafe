package com.model;

public class Approve {

	private String username;
	private String userid;
	private String pw;
	private String privilege;
	private String action;
	private String status;
	
	public String getuserid() {
		return userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getusername() {
		return username;
	}
	public String getpw() {
		return pw;
	}
	public String getprivilege() {
		return privilege;
	}
	public String getactive() {
		return action;
	}
	public void setuserid(String userid) {
		this.userid = userid;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public void setpw(String pw) {
		this.pw = pw;
	}
	public void setprivilege(String privilege) {
		this.privilege = privilege;
	}
	public void setaction(String action) {
		this.action = action;
	}
	
	
	public Approve() {
		username = null;
		userid = null;
		pw=null;
		privilege = null;
		action = null;
	}
}
