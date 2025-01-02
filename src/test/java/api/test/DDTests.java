package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.Userendpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	  @Test(priority = 1, dataProvider="Data", dataProviderClass=DataProviders.class) 
	  public void testpostUser(String uid, String username, String fn, String ln, String email, String pw) 
	  {
		  User userpayload = new User();
		  userpayload.setId(Integer.parseInt(uid));
		  userpayload.setUsername(username);
		  userpayload.setFirstName(fn);
		  userpayload.setLastName(ln);
		  userpayload.setEmail(email);
		  userpayload.setPassword(pw);
		  Response response = Userendpoints.createUser(userpayload); 
		  response.then().log().all();
		  System.out.println(userpayload.getUsername());
		 Assert.assertEquals(response.getStatusCode(), 200); 
	  }
	  
	  @Test(priority = 2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	  public void testdeleteUser(String username) {
		  Response response = Userendpoints.deleteUser(username); 
		  response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200); 
	  }
}
