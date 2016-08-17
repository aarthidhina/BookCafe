package com.model;

public class Review {
	private String seller_user_id;
	private String book_id;
	private String seller_review_id;
	private String seller_First_name;
	private String seller_Last_name;
	private String buyer_user_id;
	private String review_text;
	private String ranking;
	
	public Review() {
		seller_user_id ="";
		buyer_user_id = "";
		review_text = "";
		ranking = "";
		book_id="";
		seller_review_id="";
		seller_First_name="";
		seller_Last_name="";
	}

	public String getSeller_user_id() {
		return seller_user_id;
	}

	public void setSeller_user_id(String seller_user_id) {
		this.seller_user_id = seller_user_id;
	}
	public String get_book_id() {
		return book_id;
	}

	public void set_book_id(String book_id) {
		this.book_id = book_id;
	}
	public String getseller_review_id() {
		return seller_review_id;
	}

	public void setseller_review_id(String seller_review_id) {
		this.seller_review_id = seller_review_id;
	}
	public void setSeller_First_name(String seller_First_name) {
		this.seller_First_name = seller_First_name;
	}
	public String getSeller_First_name()
	{
		return seller_First_name;
	}
    public void setSeller_last_name(String seller_Last_name)
    {
    	this.seller_Last_name=seller_Last_name;
    }
    public String getSeller_Last_name()
    {
    	return seller_Last_name;
    }
	public String getBuyer_user_id() {
		return buyer_user_id;
	}

	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

}
