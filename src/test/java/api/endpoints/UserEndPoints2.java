package api.endpoints;

//import org.apache.groovy.parser.antlr4.GroovyParser.CreatedNameContext;

//import com.aventstack.extentreports.gherkin.model.Given;

import api.payload.User;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for perform Create,Read,Update,Delete operation

public class UserEndPoints2 {
	
	
    public static Response createUser(User userPayload)
      {
    	Response response=given()
    	    .contentType(ContentType.JSON)
    	    .accept(ContentType.JSON)
       	    .body(userPayload)
    	 
    	  .when()
    	  .post(Routes.post_url);
    	  
    	  return response;
      }
    
    public static Response readUser(String userName)
    {
    	Response response=given()
    			.pathParam("username",userName)
        	    .contentType(ContentType.JSON)
        	    
        	 
        	  .when()
        	     .get(Routes.get_url);
        	  
        	  return response;
    }
    	
    public static Response updateUser(String userName,User payload)
    {
    	Response response=given()
    			.contentType(ContentType.JSON)
        	    .accept(ContentType.JSON)
    			.pathParam("username",userName)
    			 .body(payload)
        	    
        	 
        	  .when()
        	  .put(Routes.update_url);
        	  
        	  return response;
    }
    public static Response deleteUser(String userName)
    {
    	Response response=given()
    			.pathParam("username",userName)
        	    
        	 
        	  .when()
        	     .delete(Routes.delete_url);
        	  
        	  return response;
}
} 



