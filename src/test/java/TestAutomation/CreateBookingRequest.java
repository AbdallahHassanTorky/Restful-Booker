package TestAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class CreateBookingRequest {

    public static Response createBooking(String token, String firstname, String lastname, int totalprice, boolean depositpaid) {

        String requestBody = "{"
                + "\"firstname\": \"" + firstname + "\","
                + "\"lastname\": \"" + lastname + "\","
                + "\"totalprice\": " + totalprice + ","
                + "\"depositpaid\": " + depositpaid + ","
                + "\"bookingdates\": {"
                + "\"checkin\": \"2018-01-01\","
                + "\"checkout\": \"2019-01-01\""
                + "},"
                + "\"additionalneeds\": \"Breakfast\""
                + "}";



        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().response();



        return response;
    }
}
