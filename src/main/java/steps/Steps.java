package steps;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.util.Asserts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;

public class Steps {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("^A simple GET request to api (.*?)$")
    public void simple_get(String uri) {
        response = given().request().get(uri);
    }

    @Given("^A body request with this data (.*?)$")
    public void simple_post(String body){
        request = given().body(body);
        request.post();
    }


    /**
     * asserts on json arrays
     */
    @And("^Check that response code is (.*?)$")
    public void response_contains_in_any_order(String code) {
        Asserts.check(response.getStatusCode() == Integer.parseInt(code), "Code expected is " + code + " and code found is " + response.getStatusCode());
    }
}