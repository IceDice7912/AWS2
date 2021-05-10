package com.mulcam.ai.web.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.ProductListDAO;
import com.mulcam.ai.web.vo.ProductListVO;


@Service
public class ProductListService {
	@Autowired
	ProductListDAO productListDAO;

	public ArrayList<ProductListVO> getAll(int end) {
		return productListDAO.getAll(end);
	}

	public ArrayList<ProductListVO> getCategoryList(String category) {
		return productListDAO.getCategoryList(category);
	}
}
