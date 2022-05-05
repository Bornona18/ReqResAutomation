package com.ReqResApiAutomation.scripts;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ReqResApiAutomation.utility.BaseAPIHelper;
import com.beust.jcommander.Strings;

public class APITestCases extends BaseAPIHelper
{

	@Test(description="Verify the response when user hit List users API")
	public void test1()
	{
		Assert.assertEquals(httpResponse.statusCode(),200);
	}

	@Test(description="Verify the keys displayed when user hit the api")
	public void test2()
	{
		jsonPathValue = httpResponse.jsonPath();
		Assert.assertEquals(httpResponse.statusCode(),200);
		Assert.assertFalse(Strings.isStringEmpty(((Integer) getDetailsFromResponseBody("page")).toString()));
		Assert.assertFalse(Strings.isStringEmpty(((Integer) getDetailsFromResponseBody("per_page")).toString()));
		Assert.assertFalse(Strings.isStringEmpty(((Integer) getDetailsFromResponseBody("total")).toString()));
		Assert.assertFalse(Strings.isStringEmpty(((Integer) getDetailsFromResponseBody("total_pages")).toString()));
	}


	@Test(description="Verify the keys available under data array")
	public void test3()
	{
		jsonPathValue = httpResponse.jsonPath();
		Assert.assertEquals(httpResponse.statusCode(),200);
		Assert.assertFalse(Strings.isStringEmpty(((Integer) getDetailsFromResponseBody("data[0].id")).toString()));
		Assert.assertFalse(Strings.isStringEmpty(((String) getDetailsFromResponseBody("data[0].email"))));
		Assert.assertFalse(Strings.isStringEmpty(((String) getDetailsFromResponseBody("data[0].first_name"))));
		Assert.assertFalse(Strings.isStringEmpty(((String) getDetailsFromResponseBody("data[0].last_name"))));
		Assert.assertFalse(Strings.isStringEmpty(((String) getDetailsFromResponseBody("data[0].avatar"))));
	}

	@SuppressWarnings("unchecked")
	@Test(description="Verify that per_page key's value is same as the count of available data on the page")
	public void test4()
	{
		jsonPathValue = httpResponse.jsonPath();
		Assert.assertEquals(httpResponse.statusCode(),200);
		int per_page_value = (Integer) getDetailsFromResponseBody("per_page");
		int dataArraySize = ((List<String>)getDetailsFromResponseBody("data.id")).size();

		Assert.assertEquals(dataArraySize, per_page_value);
	}

	@Test(description="Verify that page key's value is 1 bydefault")
	public void test5()
	{
		httpResponse = httpRequest.get("/api/users?page=N");
		jsonPathValue = httpResponse.jsonPath();
		Assert.assertEquals(httpResponse.statusCode(),200);
		int page_value =  (Integer) getDetailsFromResponseBody("page");

		Assert.assertEquals(1, page_value);
	}

}

;