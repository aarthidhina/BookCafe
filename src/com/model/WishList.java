package com.model;

public class WishList {

	private int wishlist_id;
	private int book_id;
	private int seller_id;
	private String sellerName;
	private String bookTitle;
	private double price;
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getWishlist_id() {
		return wishlist_id;
	}

	public void setWishlist_id(int wishlist_id) {
		this.wishlist_id = wishlist_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

}
