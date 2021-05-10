package com.mulcam.ai.web.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TTSController {

	@RequestMapping(value = "tts.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody	
    public static void main(String[] args, HttpServletRequest request,
			HttpServletResponse responseH) {
		
        String clientId = "cwbj4zqzi3";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "MWpEyxSE9MsMbEARKuCxEt8E2naInJwjpbm5zaR9";//애플리케이션 클라이언트 시크릿값";
        String response="";
        String tts = request.getParameter("chat");
        File f = null;
        
        try {
            String text = URLEncoder.encode(tts, "UTF-8"); // 13자
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=nsujin&volume=0&speed=0&pitch=0&format=wav&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];               
                f = new File(FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/"+"\\"+"chatbot.wav");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
	            if(br != null) {
	                while ((inputLine = br.readLine()) != null) {
	                	response += inputLine;
	                }
                br.close();
	            }
            }
        } catch (Exception e) {
        }
        
        
        try {
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
        
        
    }
}