package com.mulcam.ai.web.controller;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import android.util.Base64;

@Controller
public class ChatbotController {

	
//	@RequestMapping(value = "chat.jes", 
//			method= {RequestMethod.GET,RequestMethod.POST},
//			produces = "application/text; charset=utf8")			
//	@ResponseBody
//	public String chat(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String chatbotsay=request.getParameter("chat");
//		System.out.println("챗봇이 받은 메시지 : " + chatbotsay);
//		return chatbotsay;
//	}	
	
	  //public static String main(String voiceMessage, String apiURL, String secretKey) {
			@RequestMapping(value = "chat.jes", 
					method= {RequestMethod.GET,RequestMethod.POST},
					produces = "application/text; charset=utf8")			
			@ResponseBody
		  public static String main(String [] args, HttpServletRequest request,
					HttpServletResponse response) {  
	        String chatbotMessage = "";

	        try {
	            String apiURL = "https://7a47b2dd7e4f474c9f005449eea5498a.apigw.ntruss.com/custom/v1/4466/023a84523481e8a085d8f48c8a594a4b55be6e41bd06633432768fa5227f6372";

	            URL url = new URL(apiURL);

//	            String voiceMessage="좋은 책 추천해줘";
	            String voiceMessage=request.getParameter("chat");
	            String message = getReqMessage(voiceMessage);

	            String secretKey="c2laS295TGllbWF4TUJnTGZWSm11a0tjWU1Ocnd2ZFU=";
	            String encodeBase64String = makeSignature(message, secretKey);

	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Content-Type", "application/json;UTF-8");
	            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

	            // post request
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.write(message.getBytes("UTF-8"));
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode();

	            BufferedReader br;

	            if(responseCode==200) { // Normal call

	                BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                                con.getInputStream()));
	                String decodedString;
	                while ((decodedString = in.readLine()) != null) {
	                    chatbotMessage = decodedString;
	                }
	                //chatbotMessage = decodedString;
	                in.close();

	            } else {  // Error occurred
	                chatbotMessage = con.getResponseMessage();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
       //챗봇 문자만 받기
            JSONObject o=new JSONObject(chatbotMessage);
            JSONArray bubbles=o.getJSONArray("bubbles");
            JSONObject bubbles0=bubbles.getJSONObject(0);
            JSONObject data=bubbles0.getJSONObject("data");
            String description=(String) data.get("description");
            
            return description;
	    }


	    public static String makeSignature(String message, String secretKey) {

	        String encodeBase64String = "";

	        try {
	            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

	            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
	            Mac mac = Mac.getInstance("HmacSHA256");
	            mac.init(signingKey);

	            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
	            encodeBase64String = Base64.encodeToString(rawHmac, Base64.NO_WRAP);

	            return encodeBase64String;

	        } catch (Exception e){
	            System.out.println(e);
	        }

	        return encodeBase64String;

	    }


	    public static String getReqMessage(String voiceMessage) {

	        String requestBody = "";

	        try {

	            JSONObject obj = new JSONObject();

	            long timestamp = new Date().getTime();

	            System.out.println("##"+timestamp);

	            obj.put("version", "v2");
	            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2IceDice");
	//=> userId is a unique code for each chat user, not a fixed value, recommend use UUID. use different id for each user could help you to split chat history for users.

	            obj.put("timestamp", timestamp);

	            JSONObject bubbles_obj = new JSONObject();

	            bubbles_obj.put("type", "text");

	            JSONObject data_obj = new JSONObject();
	            data_obj.put("description", voiceMessage);

	            bubbles_obj.put("type", "text");
	            bubbles_obj.put("data", data_obj);

	            JSONArray bubbles_array = new JSONArray();
	            bubbles_array.put(bubbles_obj);

	            obj.put("bubbles", bubbles_array);
	            obj.put("event", "send");

	            requestBody = obj.toString();

	        } catch (Exception e){
	            System.out.println("## Exception : " + e);
	        }

	        return requestBody;
	    }
	    
	}