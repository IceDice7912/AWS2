package com.mulcam.ai.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.dao.MemberDAO;
import com.mulcam.ai.web.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	public void memberInsert(MemberVO m) throws CafeException{
		memberDAO.memberInsert(m);
	}

	public String login(MemberVO m) throws CafeException{
		return memberDAO.login(m);		
	}
	
	

}
