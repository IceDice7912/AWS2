package com.mulcam.ai.web.controller;

import java.io.*;
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
public class CFRController_celebrity {

	@RequestMapping(value = "face-celebrity.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody		
    public static String main(String[] args) {

        StringBuffer reqStr = new StringBuffer();
        String clientId = "cwbj4zqzi3";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "MWpEyxSE9MsMbEARKuCxEt8E2naInJwjpbm5zaR9";//애플리케이션 클라이언트 시크릿값";
        String response = "";
        
        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/shotting-fv/"+"\\"+"face.jpg";
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/celebrity"; // 유명인 얼굴 인식
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
                System.out.println(response.toString());
            } else {
                System.out.println("error !!!");
            }
            
          //닮은꼴 연예인만 파싱
            JSONObject o=new JSONObject(response);
            JSONArray bubbles=o.getJSONArray("faces");
            JSONObject bubbles0=bubbles.getJSONObject(0);
            JSONObject data=bubbles0.getJSONObject("celebrity");
            String star=(String) data.get("value");           
            
            StringBuffer page = new StringBuffer();
            	page.append("<>");
            return star;
            
        } catch (Exception e) {
            System.out.println(e);
            return "error CFR_celebrity";
        }
        
		
    }
}