package com.model;

import java.util.Date;

public class ViewOrder {
	private int order_id;
	private int buyer_id;
	private String order_status;
	private String product_status;
	private String shipping_addr1;
	private String shipping_addr2;
	private String state;
	private String city;
	private String zipcode;
	private String phone;
	private String sellerName;
	private double shipping_cost;
	private double tax;
	private double total_price;
	private int total_quantity;
	private Date order_time;
	private String bookTitle;
	private int order_no;
	private int book_id;
	private int seller_id;
	private int quantity;
	private double price;

	public ViewOrder() {
		order_id = 0;
		buyer_id = 0;
		order_status = "";
		product_status = "";
		shipping_addr1 = "";
		shipping_addr2 = "";
		sellerName = "";
		state = "";
		city = "";
		zipcode = "";
		phone = "";
		shipping_cost = 0.0;
		tax = 0.0;
		total_price = 0.0;
		total_quantity = 0;
		order_no = 0;
		book_id = 0;
		quantity = 0;
		price = 0.0;
		seller_id = 0;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
	}
	
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String name) {
		this.sellerName = name;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getShippingCost() {
		return shipping_cost;
	}

	public void setShippingCost(double price) {
		this.shipping_cost = price;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
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
	
	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String order_status) {
		this.product_status = order_status;
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
