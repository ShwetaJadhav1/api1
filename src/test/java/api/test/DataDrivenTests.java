package api.test;

import org.apache.commons.collections4.Put;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.*;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID,String UserName,String FirstName,String LastName,String Email,String Password,String Phone)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	@Test(priority = 2,dataProvider = "username",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	
	{

		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
}
		

	