package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	
	
	//1. GET Method without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient =HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); //Hit the GET url
		return closeableHttpResponse;
	}
	
	
	//2. GET Method with Headers
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient =HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			
			for(Map.Entry<String, String> entry : headerMap.entrySet()){
				httpGet.addHeader(entry.getKey(), entry.getValue());;
			}
			
			CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); //Hit the GET url
			return closeableHttpResponse;
		}
		
	//3. Post Method with Headers
		
		public CloseableHttpResponse post(String url, String entityBody, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(entityBody));
			
			for(Map.Entry<String, String> m : headerMap.entrySet()){
				httpPost.addHeader(m.getKey(), m.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
			return closeableHttpResponse;
		}
		

}
