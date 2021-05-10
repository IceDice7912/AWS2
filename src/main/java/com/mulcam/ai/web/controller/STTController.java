package com.mulcam.ai.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.filechooser.FileSystemView;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class STTController {

	@RequestMapping(value = "stt.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody	
	public static String main(String[] args) {
		String response="";  
		
		try {
			String clientId ="cwbj4zqzi3";
			String clientSecret = "MWpEyxSE9MsMbEARKuCxEt8E2naInJwjpbm5zaR9";
	        try {       	
	            String imgFile = FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/"+"\\"+"chatbot.wav";
	            File voiceFile = new File(imgFile);

	            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
	            URL url = new URL(apiURL);

	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("Content-Type", "application/octet-stream");
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

	            OutputStream outputStream = conn.getOutputStream();
	            FileInputStream inputStream = new FileInputStream(voiceFile);
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            BufferedReader br = null;
	            int responseCode = conn.getResponseCode();
	            if(responseCode == 200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            String inputLine;

	            if(br != null) {
	                while ((inputLine = br.readLine()) != null) {
	                	response += inputLine;
	                }
	                br.close();
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        JSONObject o=new JSONObject(response);
        String isay = o.getString("text");
        response = "";
        o=null;
        
        return isay;
	}

}