package com.mulcam.ai.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.RecommendService;
import com.mulcam.ai.web.vo.RecommendVO;



@Controller
public class RecommendController {
	
	@Autowired 
	RecommendService recommendService;
	
	@RequestMapping(value = "recommend.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=UTF-8")			
	
	@ResponseBody
	public String recommend(HttpServletRequest request, HttpServletResponse response){
		String title = request.getParameter("title");
		String category = recommendService.findCategory(title);
		String sb = "";
		RecommendVO recommendVO = new RecommendVO();
		
        try {
			URL url = new URL("http://54.83.91.161:8000/recommend/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8"); //json형식으로 전송, Request body를 JSON으로 던져줌
			conn.setRequestProperty("Accept", "application/json");	// Request data를 JSON으로 받도록 설정
			conn.setDoOutput(true);	// Output Stream을 POST 데이터로 전송
			String jsonInputString = "{\"title\":\""+title+"\",\"category\":\""+category+"\"}";
			
			//JSON 보내는 Output Stream
			try(OutputStream os = conn.getOutputStream()){
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input,0,input.length);
			}
			
			// Response data 받는 부분
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "UTF-8"))){
				String responseLine = null;
				while((responseLine = br.readLine()) != null) {
					sb = responseLine;
				}
				br.close();
			}
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        sb = sb.replaceAll("\\\"","");
        sb = sb.replaceAll("\\\\","\"");
        JSONObject o=new JSONObject(sb);       
        JSONObject ISBN=o.getJSONObject("ISBN");
        
        ArrayList<RecommendVO> list = new ArrayList<RecommendVO>();
        JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
        for (int i=0;i<ISBN.length();i++) {
        	String num = Integer.toString(i);
        	String isbn = String.valueOf(ISBN.get(num));
        	list = recommendService.recommendBook(isbn);        	
        	JSONObject sObject = new JSONObject();
        	sObject.put("book", list);
			jArray.put(sObject);	        	
        }
		obj.put("data", jArray);
		return obj.toString();
		
	}
	
	
	
	@RequestMapping(value = "search.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String searchBook(HttpServletRequest request, HttpServletResponse response){
		String title = request.getParameter("title");
		List<RecommendVO> b = recommendService.searchBook(title);
		
		JSONArray array=new JSONArray();
		for(RecommendVO vo:b) {
			JSONObject o=new JSONObject();
			o.put("title", vo.getTitle());
			o.put("author",vo.getAuthor());
			o.put("price", vo.getPrice());
			o.put("publisher", vo.getPublisher());
			o.put("isbn", vo.getIsbn());
			o.put("category", vo.getCategory());
			o.put("imgurl", vo.getImgurl());
			array.put(o);
		}	
		return array.toString(); 	
		
	}
}

