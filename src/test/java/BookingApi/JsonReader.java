package BookingApi;  // Corrected package declaration

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    @DataProvider(name = "Json_parsing")
    public Object[][] jsonReader() throws IOException, ParseException {
        // Parsing the file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("C:\\Users\\AMIT\\Desktop\\Restful-Booker\\src\\test\\resources\\test_data.json");

        // Parsing the JSON file
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;

        // Fetching the correct key for authentication data
        JSONArray authDataArray = (JSONArray) jsonObject.get("authData");
        if (authDataArray == null) {
            throw new RuntimeException("authData key is missing or invalid in the JSON file.");
        }

        // Fetching the correct key for booking data
        JSONArray bookingDataArray = (JSONArray) jsonObject.get("bookingData");
        if (bookingDataArray == null) {
            throw new RuntimeException("bookingData key is missing or invalid in the JSON file.");
        }

        // Prepare the data array for DataProvider (combining authData and bookingData here)
        Object[][] data = new Object[authDataArray.size()][2];  // 2 columns: authData, bookingData

        for (int i = 0; i < authDataArray.size(); i++) {
            JSONObject authData = (JSONObject) authDataArray.get(i);
            String username = (String) authData.get("username");
            String password = (String) authData.get("password");

            // Fetching booking data for the same index
            JSONObject bookingData = (JSONObject) bookingDataArray.get(i);
            String firstname = (String) bookingData.get("firstname");
            String lastname = (String) bookingData.get("lastname");
            long totalprice = (Long) bookingData.get("totalprice");
            boolean depositpaid = (Boolean) bookingData.get("depositpaid");
            JSONObject bookingdates = (JSONObject) bookingData.get("bookingdates");
            String checkin = (String) bookingdates.get("checkin");
            String checkout = (String) bookingdates.get("checkout");
            String additionalneeds = (String) bookingData.get("additionalneeds");

            // Store both authData and bookingData in the data array
            data[i][0] = new String[] { username, password };  // Authentication data
            data[i][1] = new String[] { firstname, lastname, String.valueOf(totalprice), String.valueOf(depositpaid), checkin, checkout, additionalneeds };  // Booking data
        }

        return data;  // Return the array with both authentication and booking data
    }

}
