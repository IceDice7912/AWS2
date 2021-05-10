package com.mulcam.ai.web.vo;

public class RecommendVO {
	
	private String title, author, publisher, category, imgurl, isbn;
	private int price;
	
		
	public RecommendVO(String title, String author, String publisher, String category, String imgurl, String isbn, int price) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		this.imgurl = imgurl;
		this.isbn = isbn;
		this.price = price;
	}
	
	
	public RecommendVO() {
		super();
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
