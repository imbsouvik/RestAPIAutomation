package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.testbase.TestBase;
import com.qa.util.TestUtil;

public class GETTestAPI extends TestBase{
	
	TestBase testBase;
	RestClient restClient;
	String url1;
	String url2;
	String url;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase = new TestBase();
		url1 = prop.getProperty("url");
		url2 = prop.getProperty("serviceurl");
		url = url1+url2;
		
	}
		
	@Test
	public void getTest() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is: "+statusCode); // Status code will be printed
		
		Assert.assertEquals(statusCode, STATUS_CODE, "Status code is invalid");
		
		//To get the entire string Body -- JSON will converted to String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		//We will convert String into JSON
		JSONObject responseJsonObject = new JSONObject(responseString);
		System.out.println("Response JSon Object from API--"+responseJsonObject);
		
		//Get value of JSON Object//
		String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
		System.out.println("Per page value --> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Get value of JSON Object Array eg: data[0], data[1], data[2], etc..//
		String last_name = TestUtil.getValueByJPath(responseJsonObject, "data[2]/last_name");
		System.out.println("Last name of 2nd Data Array  --> "+last_name);
		Assert.assertEquals(last_name, "Wong");
		
		
		//To validate Headers
		Header[] strHeader = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> hashmap = new HashMap<String, String>();
		
		for(Header header : strHeader){
			hashmap.put(header.getName(), header.getValue());
		}
		System.out.println("Header of APIs are--"+hashmap);
		
	}
	
	@Test
	public void get_Test() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Connection", "keep-alive");
		
//		headerMap.put("username", "souvik");
//		headerMap.put("password", "test123");
//		headerMap.put("token-id", "auth123");
		
		closeableHttpResponse = restClient.get(url, headerMap);
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is: "+statusCode); // Status code will be printed
		
		Assert.assertEquals(statusCode, STATUS_CODE, "Status code is invalid");
		
		//To get the entire string Body -- JSON will converted to String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		//We will convert String into JSON
		JSONObject responseJsonObject = new JSONObject(responseString);
		System.out.println("Response JSon Object from API--"+responseJsonObject);
		
		//Get value of JSON Object//
		String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
		System.out.println("Per page value --> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Get value of JSON Object Array eg: data[0], data[1], data[2], etc..//
		String last_name = TestUtil.getValueByJPath(responseJsonObject, "data[2]/last_name");
		System.out.println("Last name of 2nd Data Array  --> "+last_name);
		Assert.assertEquals(last_name, "Wong");
		
		
		//To validate Headers
		Header[] strHeader = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> hashmap = new HashMap<String, String>();
		
		for(Header header : strHeader){
			hashmap.put(header.getName(), header.getValue());
		}
		System.out.println("Header of APIs are--"+hashmap);
		
	}
	
}
