package com.model;

public class CartDetails {
//	private String cart_details_id;
	private int cart_id;
	private int book_id;
	private int quantity;

//	public String getCart_details_id() {
//		return cart_details_id;
//	}
//
//	public void setCart_details_id(String cart_details_id) {
//		this.cart_details_id = cart_details_id;
//	}
	
	public CartDetails() {
		cart_id = 0;
		book_id = 0;
		quantity = 0;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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

}
