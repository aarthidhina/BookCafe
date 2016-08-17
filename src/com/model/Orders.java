package com.model;

import java.util.Date;

public class Orders {
	private int order_id;
	private int buyer_id;
	private String order_status;
	private String shipping_addr1;
	private String shipping_addr2;
	private String state;
	private String city;
	private String zipcode;
	private String phone;
	private double shipping_cost;
	private double tax;
	private double total_price;
	private int total_quantity;
	private Date order_time;
	
	

	public Orders() {
		order_id = 0;
		buyer_id = 0;
		order_status = "";
		shipping_addr1 = "";
		shipping_addr2 = "";
		state = "";
		city = "";
		zipcode = "";
		phone = "";
		shipping_cost = 0.0;
		tax = 0.0;
		total_price = 0.0;
		total_quantity = 0;		
	}

	public int getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getShipping_addr() {
		return shipping_addr1;
	}

	public void setShipping_addr(String shipping_addr) {
		this.shipping_addr1 = shipping_addr;
	}

	public double getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(double shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getShipping_addr2() {
		return shipping_addr2;
	}

	public void setShipping_addr2(String shipping_addr2) {
		this.shipping_addr2 = shipping_addr2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
