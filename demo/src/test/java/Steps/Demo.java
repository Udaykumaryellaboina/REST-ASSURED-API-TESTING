package Steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class Demo {
    private Response response;

    @Given("the API endpoint is {string}")
    public void theAPIEndpointIs(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = endpoint;
    }

    @When("I send a GET request to the API")
    public void iSendAGETRequestToTheAPI() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .get();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of {int} users")
    public void theResponseShouldContainAListOfUsers(int expectedCount) {
        int actualCount = response.jsonPath().getList("").size();
        Assert.assertEquals(expectedCount, actualCount);
    }

    @Then("the response should contain the user details:")
    public void theResponseShouldContainTheUserDetails(io.cucumber.datatable.DataTable dataTable) {
        String id = dataTable.cell(1, 1);
        String name = dataTable.cell(1, 2);
        String username = dataTable.cell(1, 3);
        String email = dataTable.cell(1, 4);

        String actualId = response.jsonPath().getString("id");
        String actualName = response.jsonPath().getString("name");
        String actualUsername = response.jsonPath().getString("username");
        String actualEmail = response.jsonPath().getString("email");

        Assert.assertEquals(id, actualId);
        Assert.assertEquals(name, actualName);
        Assert.assertEquals(username, actualUsername);
        Assert.assertEquals(email, actualEmail);
    }

    @Then("the response should contain the user address:")
    public void theResponseShouldContainTheUserAddress(io.cucumber.datatable.DataTable dataTable) {
        String street = dataTable.cell(1, 1);
        String suite = dataTable.cell(1, 2);
        String city = dataTable.cell(1, 3);
        String zipcode = dataTable.cell(1, 4);
        String geoLat = dataTable.cell(1, 5);
        String geoLng = dataTable.cell(1, 6);

        String actualStreet = response.jsonPath().getString("address.street");
        String actualSuite = response.jsonPath().getString("address.suite");
        String actualCity = response.jsonPath().getString("address.city");
        String actualZipcode = response.jsonPath().getString("address.zipcode");
        String actualGeoLat = response.jsonPath().getString("address.geo.lat");
        String actualGeoLng = response.jsonPath().getString("address.geo.lng");

        Assert.assertEquals(street, actualStreet);
        Assert.assertEquals(suite, actualSuite);
        Assert.assertEquals(city, actualCity);
        Assert.assertEquals(zipcode, actualZipcode);
        Assert.assertEquals(geoLat, actualGeoLat);
        Assert.assertEquals(geoLng, actualGeoLng);
    }
}
