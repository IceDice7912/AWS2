package com.mulcam.ai.web.vo;

import com.mulcam.ai.util.CafeException;

public class MemberVO {
	   
	private String name;
	private String id;
	private String pw;
	private String gender;
	private String favorite;


	public MemberVO(String id, String pw) throws CafeException {
		super();
		setId(id);
		setPw(pw);
	}
	
	public MemberVO(String id, String pw, String name) throws CafeException {
		
		this(id,pw);
		setName(name);
	}

	public MemberVO() throws CafeException {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String name, String id, String pw, String gender, String favorite) throws CafeException {		
		setName(name);
		setId(id);
		setPw(pw);
		setGender(gender);
		setFavorite(favorite);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws CafeException {
		if(name!="") {
			this.name = name;
		}else {
			throw new CafeException("이름이 입력되지 않았습니다");
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) throws CafeException {
		if(id!="") {
			this.id = id;
		}else {
			throw new CafeException("아이디가 입력되지 않았습니다");
		}
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) throws CafeException {
		if(pw!="" || pw.contains("!")  || pw.contains("@")  || pw.contains("#")  || pw.contains("$")  || pw.contains("%")  || pw.contains("^")  || pw.contains("&")  || pw.contains("*") ) {
			this.pw = pw;
		}else {
			throw new CafeException("패스워드가 입력되지 않았거나, 특수문자를 포함시키지 않았습니다.");
		}
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) throws CafeException {
		if(gender!=null) {
			this.gender = gender;
		}else {
			throw new CafeException("성별이 입력되지 않았습니다");
		}
	}	
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) throws CafeException {
		if(favorite!=null) {
			this.favorite = favorite;
		}else {
			throw new CafeException("좋아하는 장르가 입력되지 않았습니다");
		}
	}	
	
}
