package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.testbase.TestBase;

public class POSTTestAPI extends TestBase{
	TestBase testBase;
	String url1;
	String url2;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp(){
		testBase= new TestBase();
		url1 = prop.getProperty("url");
		url2 = prop.getProperty("serviceurl");
		url = url1+url2;
		
	}
	
	@Test
	public void postTest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API//
		ObjectMapper mapper = new ObjectMapper();
		
		//Creating Java Object Class//
		PostData postData = new PostData("morpheus", "leader"); //<-- Expected
		
		//Java Object to JSON// --> Marshelling
		mapper.writeValue(new File("C:/Users/USER/workspace/RestAPI/src/main/java/com/qa/config/data.json"), postData);
		
		//Java Object to Json String
		String jsonString = mapper.writeValueAsString(postData);
		
		closeableHttpResponse = restClient.post(url, jsonString, headerMap);
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --> "+ statusCode);
		
		String strResponseJson = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonObject = new JSONObject(strResponseJson);
		System.out.println(jsonObject);
		
		//JSON to Java Object// -->UnMarshelling
		
		PostData actPostData = mapper.readValue(strResponseJson, PostData.class); //<-- Actual
		
		//Name Validation//
		Assert.assertTrue(postData.getName().equals(actPostData.getName()));
		
		//Job Validation//
		Assert.assertTrue(postData.getJob().equals(actPostData.getJob()));
		
		System.out.println(postData.getCreatedAt());
		System.out.println(postData.getId());
		
		
	}
	
	
	

}
