package restAssuredAPI;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJsonObjects {

	@Test
	public void parsingJson() {
		Response res= given()
			.when()
				.get("https://reqres.in/api/users?page=2");
		
		// Traversing the json object
		getJsonObjectFromTheArray(res, "data", "first_name");
		Assert.assertEquals("Get request not successfull", res.statusCode(), 200);
	}
	
	
	void getJsonObjectFromTheArray(Response response, String arrayName, String getDataName) {
		JSONObject jo = new JSONObject(response.asString());

		for (int i = 0; i < jo.getJSONArray(arrayName).length(); i++) {
			String first_name = jo.getJSONArray("data").getJSONObject(i).get(getDataName).toString();
			System.out.println("First name from the json array: " + first_name);
		}
	}

}
