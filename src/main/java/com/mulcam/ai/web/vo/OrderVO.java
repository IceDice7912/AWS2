package com.mulcam.ai.web.vo;

import com.mulcam.ai.util.CafeException;


public class OrderVO{

	private String id, name, title;
	private int totalprice;
	
	public OrderVO(int totalprice, String id, String name, String title) throws CafeException {
		setTotalprice(totalprice);
		setId(id);
		setName(name);
		setTitle(title);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}



}
