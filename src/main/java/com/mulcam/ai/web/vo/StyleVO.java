package com.mulcam.ai.web.vo;

import com.mulcam.ai.util.CafeException;


public class StyleVO{

	private String id, category;
	private int cnt;
	
	
	public StyleVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public StyleVO(String id, String category, int cnt) {
		super();
		this.id = id;
		this.category = category;
		this.cnt = cnt;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	@Override
	public String toString() {
		return "StyleVO [id=" + id + ", category=" + category + ", cnt=" + cnt + "]";
	}
	
	
	
}
