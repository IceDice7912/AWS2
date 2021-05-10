package com.mulcam.ai.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.ProductListService;
import com.mulcam.ai.web.vo.ProductListVO;

@Controller
public class ProductListController {
	
	@Autowired 
	ProductListService productListService;
	
	
	@RequestMapping(value = "productListAll.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String getProductAll(HttpServletRequest request, HttpServletResponse response){
		String pnum = request.getParameter("page_num");
		int page_num = Integer.parseInt(pnum);	
		// 페이지 당 20개 띄움
		int end = page_num * 20;
		ArrayList<ProductListVO> list = new ArrayList<ProductListVO>();
		list = productListService.getAll(end);
		
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
		for (int i=0;i<list.size();i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("book", list.get(i));
			jArray.put(sObject);		
		}
		obj.put("data", jArray);		
		return obj.toString();
		
		
	}	
	
	
	
	
	@RequestMapping(value = "productListCategory.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String getProductCategory(HttpServletRequest request, HttpServletResponse response){
		String category = request.getParameter("category");
		ArrayList<ProductListVO> list = new ArrayList<ProductListVO>();
		list = productListService.getCategoryList(category);
		
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
		for (int i=0;i<list.size();i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("book", list.get(i));
			jArray.put(sObject);		
		}
		obj.put("data", jArray);		
		return obj.toString();
	}	


}
