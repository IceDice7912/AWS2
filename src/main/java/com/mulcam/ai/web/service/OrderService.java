package com.mulcam.ai.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.dao.OrderDAO;
import com.mulcam.ai.web.vo.OrderVO;
import com.mulcam.ai.web.vo.StyleVO;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;
	
	public void insert(OrderVO o) throws CafeException{
		orderDAO.insert(o);
	}
    
	
	public ArrayList<StyleVO> getMyStyle(String id) {
		return orderDAO.getMyStyle(id);
	}

}








