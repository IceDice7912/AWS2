package com.mulcam.ai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.service.OrderService;
import com.mulcam.ai.web.vo.OrderVO;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	
	///////////// 진짜로 쓰는 주문 처리, 기존의 것은 걍 안씀. 삭제해도 무방. //////////////////	
	@RequestMapping(value = "order",
			method = { RequestMethod.GET, RequestMethod.POST },
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String orders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int totalprice=Integer.parseInt((request.getParameter("totalprice")));
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		
		try {
			OrderVO m=new OrderVO(totalprice, id, name, title);
			orderService.insert(m);
			return "주문 처리가 완료되었습니다.";
		} catch (CafeException e) {
			e.printStackTrace();
			return "주문 처리가 실패하였습니다." + e.getMessage();
		}
	}

}
