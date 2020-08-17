package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	
	public int STATUS_CODE =200;
	
	public TestBase(){
		FileInputStream ip= null;
		try {
			prop = new Properties();
			String userDir= System.getProperty("user.dir");
			ip = new FileInputStream(userDir+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
