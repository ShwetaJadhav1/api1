package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.groovy.parser.antlr4.GroovyParser.ThisFormalParameterContext;
import org.apache.logging.log4j.LogManager;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;

import api.payload.User;
import freemarker.log.Logger;
//import groovy.util.logging.Log;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public org.apache.logging.log4j.Logger logger;
	
	@BeforeClass
	public void Setup()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("********Creating User*********");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********User is created*********");
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		logger.info("********Reading User Information*********");
		UserEndPoints.readUser(this.userPayload.getUsername());
	
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********User information is displayed*********");

}
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		
		logger.info("******** Updating User*********");
		//update the data using Payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
	
		//	response.then().log().body().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update

		Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
		logger.info("********User is updated*********");

	}
	
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
	
		logger.info("******** Deleting User*********");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
	
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******** User Deleted*********");
	}
	
	
}
