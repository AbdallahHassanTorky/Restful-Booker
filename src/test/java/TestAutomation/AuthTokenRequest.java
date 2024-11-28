package TestAutomation;

import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class AuthTokenRequest {

    public static Response getAuthToken(String username, String password) {
        // Constructing the request body with username and password
        String requestBody = "{ \"username\" : \"" + username + "\", \"password\" : \"" + password + "\" }";

        // Sending the POST request to authenticate and obtain the token
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200) // Ensure status code is 200 (success)
                .extract().response();

        return response;
    }
}
