package com.mulcam.ai.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.service.OrderService;
import com.mulcam.ai.web.vo.MemberVO;
import com.mulcam.ai.web.vo.OrderVO;
import com.mulcam.ai.web.vo.StyleVO;

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
	
	
	
	@RequestMapping(value = "myStyle.jes",
			method = { RequestMethod.GET, RequestMethod.POST },
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String myStyle(HttpSession session) throws Exception {
		
		
		try {
			MemberVO m=(MemberVO) session.getAttribute("member");
			//System.out.println(m.getId());
			ArrayList<StyleVO> list=orderService.getMyStyle(m.getId());
			JSONArray array=new JSONArray();
			
			for(StyleVO vo : list) {	
				JSONObject o=new JSONObject();
				o.put("category", vo.getCategory());
				o.put("count", vo.getCnt());
				array.add(o);
			}
			
			return array.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}


}
