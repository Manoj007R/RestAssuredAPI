package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userpayload;
	public Logger logger;
	
	@BeforeTest
	public void setupData() {

		faker = new Faker();
		userpayload = new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setUserStatus(1);
		
		logger = LogManager.getLogger(this.logger);
	}



	  @Test(priority = 1) public void testpostUser() { 
		  logger.info("***** Creation of User *****");
		  Response response = Userendpoints.createUser(userpayload); 
		  response.then().log().all();
		  System.out.println(userpayload.getUsername());
		 Assert.assertEquals(response.getStatusCode(), 200); 
		  }

	@Test(priority = 2)
	public void testGetUserByName() {
		Response response = Userendpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		  logger.info("***** Update User *****");
		userpayload.setFirstName(faker.address().firstName());
		userpayload.setLastName(faker.address().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		Response response = Userendpoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
