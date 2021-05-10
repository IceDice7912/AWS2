package com.mulcam.ai.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// 네이버 얼굴인식 API 예제
@Controller
public class CFRController_face {

	
	@RequestMapping(value = "face-face.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody	
    public static String main(String[] args) {

        StringBuffer reqStr = new StringBuffer();
        String clientId = "lv0h6xt1fj";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "EOX8UlngHnfHxKsq2TmYfZ1HiRIpgJe0xs6RBvx1";//애플리케이션 클라이언트 시크릿값";
        String response = "";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/"+"\\"+"face.jpg";
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
            
          //나이, 성별만 파싱
            JSONObject o=new JSONObject(response);
            JSONArray bubbles=o.getJSONArray("faces");
            JSONObject bubbles0=bubbles.getJSONObject(0);
            JSONObject data1=bubbles0.getJSONObject("age");
            JSONObject data2=bubbles0.getJSONObject("gender");       
            String personinfo=(String) data1.get("value") + (String) data2.get("value");
            
          //어린 나이 int
          String ages1 = (String) (personinfo).replaceAll("~", "");
          String ages2 = (String) (ages1).replaceAll("[a-z]", "");
          int agei = Integer.parseInt(ages2);
          agei = agei / 100;
          //성별
          String gender1 = (String) (personinfo).replaceAll("~", "");
          String gender2 = (String) (gender1).replaceAll("[0-9]", "");
          String male = "male";
          String female = "female";
          String child = "child";
          
          if(male.equals(gender2)==true)
        	  gender2 = "남성";
          if(female.equals(gender2)==true)
        	  gender2 = "여성";
          if(child.equals(gender2)==true)
        	  gender2 = "어린이";         
          
          
          return personinfo;
            
        } catch (Exception e) {
            System.out.println(e);
            return "error CFR face";
        }      
		
    }
}