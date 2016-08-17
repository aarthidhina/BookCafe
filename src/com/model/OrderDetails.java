package com.model;

public class OrderDetails {
	private String bookTitle;
	private int order_no;
	private int book_id;
	private int seller_id;
	private int quantity;
	private double price;
	private double shipping_cost;

//	public String getOrder_details_id() {
//		return order_details_id;
//	}
//
//	public void setOrder_details_id(String order_details_id) {
//		this.order_details_id = order_details_id;
//	}
	
	public OrderDetails() {
		order_no = 0;
		book_id = 0;
		quantity = 0;
		price = 0.0;
		shipping_cost = 0.0;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
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
}
