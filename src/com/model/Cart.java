package com.model;

public class Cart {
	private int cart_id;
	private int book_id;
	private int seller_id;
	private int quantity;
	private float price;
	private float shippingCost;
	private String bookTitle;
	private String sellerName;

	
	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float price) {
		this.shippingCost = price;
	}
	
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Cart() {
		book_id = 0;
		seller_id = 0;
		quantity = 0;
		price = 0;
		shippingCost = 0;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int buyer_id) {
		this.book_id = buyer_id;
	}

	public float getPrice() {
		return price;
	}

	public void setprice(float price) {
		this.price = price;
	}
	
	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
