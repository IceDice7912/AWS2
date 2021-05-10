package com.mulcam.ai.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.dao.OrderDAO;
import com.mulcam.ai.web.vo.OrderVO;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;
	
	public void insert(OrderVO o) throws CafeException{
		orderDAO.insert(o);
	}
    
}








