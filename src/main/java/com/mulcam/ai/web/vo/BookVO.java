package com.mulcam.ai.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	
	private String title;
	private String Author;
	private int Price;
	private String Publisher;
	private String Isbn;
	private String Category;
	private String Imgurl;
	private String Imgurl2;
	private String Detail;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getImgurl() {
		return Imgurl;
	}

	public void setImgurl(String imgurl) {
		Imgurl = imgurl;
	}

	public String getImgurl2() {
		return Imgurl2;
	}

	public void setImgurl2(String imgurl2) {
		Imgurl2 = imgurl2;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}
	

}
