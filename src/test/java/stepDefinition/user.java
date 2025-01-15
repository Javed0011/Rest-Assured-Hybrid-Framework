package stepDefinition;

import org.testng.Assert;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class user {
	static Response response;
	static User userPayload = new User();
	@Given("I have the user details {string}, {string}, {string}, {string}, {string}, {string}, and {string}")
	public void i_have_the_user_details(String userID, String userName, String FName, String LName, String email, String Pwd, String Ph) {
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(FName);
		userPayload.setLastname(LName);
		userPayload.setEmail(email);
		userPayload.setPassword(Pwd);
		userPayload.setPhone(Ph);
	}

	@When("I send a POST request to create the user")
	public void i_send_a_post_request_to_create_the_user() {
	    response = UserEndPoints.createUser(userPayload);
	    response.then().log().all();
	}

	@Then("I should receive a {int} status code in the response")
	public void i_should_receive_a_status_code_in_the_response(Integer statusCode) {
	    Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
	}

	@Then("the user should be successfully created")
	public void the_user_should_be_successfully_created() {
	    System.out.println("User created successfully");
	}
}
