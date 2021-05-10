package com.mulcam.ai.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.BookService;
import com.mulcam.ai.web.vo.BookVO;


@Controller
public class BookController {
	
	@Autowired 
	BookService bookService;
	
	@GetMapping("bookList")
	public String showBook(Model model) throws Exception{
		
		List<BookVO> bookList = new ArrayList<>();
		bookList = bookService.bookList();
		model.addAttribute("bookList", bookList);
		return "bookList"; 
		
	}
	
	
	
	@RequestMapping(value="selectbookisbn",
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String Bookisbn(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		String isbn=request.getParameter("isbn");
		
		List<BookVO> list=bookService.selectbookisbn(isbn);
		JSONArray array=new JSONArray();
		for(BookVO vo:list) {
			JSONObject o=new JSONObject();
			o.put("title", vo.getTitle());
			o.put("author",vo.getAuthor());
			o.put("price", vo.getPrice());
			o.put("publisher", vo.getPublisher());
			o.put("isbn", vo.getIsbn());
			o.put("category", vo.getCategory());
			o.put("imgurl", vo.getImgurl());
			o.put("imgurl2", vo.getImgurl2());
			o.put("detail", vo.getDetail());
			array.add(o);
		}
		
		return array.toJSONString(); 
		
	}
	
	
	
	@PostMapping("topicBook")
	@ResponseBody
	public String topicBook() throws Exception{
		
		List<BookVO> list=bookService.topicBook();
		JSONArray array=new JSONArray();
		for(BookVO vo:list) {
			JSONObject o=new JSONObject();
			o.put("title", vo.getTitle());
			o.put("author",vo.getAuthor());
			o.put("price", vo.getPrice());
			o.put("publisher", vo.getPublisher());
			o.put("isbn", vo.getIsbn());
			o.put("category", vo.getCategory());
			o.put("imgurl", vo.getImgurl());
			o.put("detail", vo.getDetail());
			array.add(o);
		}

		
		
		return array.toJSONString(); 
		
	}

	@PostMapping("recommandBook")
	@ResponseBody
	public String recommandBook() throws Exception{	
		
		List<BookVO> list=bookService.recommandBook();
		JSONArray array=new JSONArray();
		for(BookVO vo:list) {
			JSONObject o=new JSONObject();
			o.put("title", vo.getTitle());
			o.put("author",vo.getAuthor());
			o.put("price", vo.getPrice());
			o.put("publisher", vo.getPublisher());
			o.put("isbn", vo.getIsbn());
			o.put("category", vo.getCategory());
			o.put("imgurl", vo.getImgurl());
			o.put("imgurl2", vo.getImgurl2());
			o.put("detail", vo.getDetail());
			array.add(o);
		}

		
		
		return array.toJSONString(); 
		
	}
	
	
	@PostMapping("todayBook")
	@ResponseBody
	public String todaydBook() throws Exception{	
		
		List<BookVO> list=bookService.todayBook();
		JSONArray array=new JSONArray();
		for(BookVO vo:list) {
			JSONObject o=new JSONObject();
			o.put("title", vo.getTitle());
			o.put("author",vo.getAuthor());
			o.put("price", vo.getPrice());
			o.put("publisher", vo.getPublisher());
			o.put("isbn", vo.getIsbn());
			o.put("category", vo.getCategory());
			o.put("imgurl", vo.getImgurl());
			o.put("imgurl2", vo.getImgurl2());
			o.put("detail", vo.getDetail());
			array.add(o);		
		}

		
		
		return array.toJSONString(); 
		
	}
	


}
