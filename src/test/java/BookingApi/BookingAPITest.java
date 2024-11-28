package BookingApi;

import TestAutomation.AuthTokenRequest;
import TestAutomation.BookingListRequest;
import TestAutomation.CreateBookingRequest;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingAPITest {
    String token;



    @Test(dataProvider = "Json_parsing", dataProviderClass = JsonReader.class)
    public void Validate_that_response_not_empty_and_token(String[] authData, String[] bookingData) {
        String user = authData[0];
        String pass = authData[1];

        Response response = AuthTokenRequest.getAuthToken(user, pass);
        String token = response.jsonPath().getString("token");
        this.token=token;
        int resLength = response.getBody().asString().length();

        // Todo: Validate that response not empty
        Assert.assertTrue(resLength > 0, "Response body is empty");


        // Todo: Validate that token is generated successfully
        Assert.assertTrue(token != null && token.length() > 0, "Token is empty or not generated successfully");


        // Attach response body and status code to Allure report
        Allure.addAttachment("Response Body", response.getBody().asPrettyString());
        Allure.addAttachment("Response Status", response.getStatusLine());
    }



    @Test(dataProvider = "Json_parsing", dataProviderClass = JsonReader.class)
    public void Verify_that_book_is_added_successfully(String[] authData, String[] bookingData) {
    String firstname = bookingData[0];
    String lastname = bookingData[1];
    int totalprice = Integer.parseInt(bookingData[2]);
    boolean depositpaid= Boolean.parseBoolean(bookingData[3]);
    Response response = CreateBookingRequest.createBooking(token, firstname, lastname, totalprice, depositpaid);


    // Todo:Validate response status code
    Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but found " + response.getStatusCode());
    System.out.println(response.getStatusLine());
    // Todo:Validate that the response body contains the correct data
    JsonPath jsonPath = response.jsonPath();

    Assert.assertEquals(jsonPath.getString("booking.firstname"), firstname, "First name does not match!");
    Assert.assertEquals(jsonPath.getString("booking.lastname"), lastname, "Last name does not match!");
    Assert.assertEquals(jsonPath.getInt("booking.totalprice"), totalprice, "Total price does not match!");
    Assert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), depositpaid, "Deposit paid status does not match!");


// Attach response body and status code to Allure report
        Allure.addAttachment("Response Body", response.getBody().asPrettyString());
        Allure.addAttachment("Response Status", response.getStatusLine());
}


@Test
    public void Verify_that_list_of_books_using_ID_in_response_is_greater_than_Zero() {

        Response response = BookingListRequest.getBookingListSize();
    int bookingsListSize = response.jsonPath().getList("").size();

    // Todo:Assert that list is greater than zero.
    Assert.assertTrue(bookingsListSize > 0, "The list of bookings should be greater than zero, but found " + bookingsListSize);



// Attach response body and status code to Allure report
        Allure.addAttachment("Response Body", response.getBody().asPrettyString());
        Allure.addAttachment("Response Status", response.getStatusLine());
        Allure.addAttachment("list", String.valueOf(bookingsListSize));

    }



}