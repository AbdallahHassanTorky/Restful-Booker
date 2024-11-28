package TestAutomation;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class BookingListRequest {


    public static Response getBookingListSize() {
        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().response();
        return response;
    }
}


