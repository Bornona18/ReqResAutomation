package com.ReqResApiAutomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.path.json.JsonPath;


public class BaseAPIHelper 
{

	protected RequestSpecification httpRequest;
	protected Response httpResponse;
	protected JsonPath jsonPathValue;

	@BeforeMethod
	public void initilizeRequest() throws FileNotFoundException, IOException
	{
		RestAssured.baseURI=readPropertiesByKey("baseUri");
		httpRequest = RestAssured.given();
		httpResponse = httpRequest.get("/api/users?page=1");
	}

	private String readPropertiesByKey(String key) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"+File.separator+"ReqResApiAutomation"+File.separator+"config"+File.separator+"ApplicationDetails.properties")));
		return prop.getProperty(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getDetailsFromResponseBody(String path)
	{
		return (T) jsonPathValue.get(path);
	}
}
