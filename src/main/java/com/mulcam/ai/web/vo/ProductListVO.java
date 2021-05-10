package com.mulcam.ai.web.vo;

public class ProductListVO {
	private String title, author, publisher, category, imgurl, imgurl2, isbn;
	private int price;
	private int end;
	
	public ProductListVO(String category, int end) {
		super();
		this.category = category;
		this.end = end;
	}

		
	public ProductListVO(String title, String author, String publisher, String isbn, String category, String imgurl, int price) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.category = category;
		this.imgurl = imgurl;
		this.imgurl2 = imgurl2;
		this.price = price;
	}
	
		
	
	public ProductListVO() {
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
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public String getImgurl2() {
		return imgurl2;
	}
	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	
	
}
