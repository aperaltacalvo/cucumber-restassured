package steps;

import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import java.io.FileNotFoundException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;


import static io.restassured.RestAssured.given;


public class Steps {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("^A simple GET request to api (.*?)$")
    public void simple_get(String uri) {
        response = given().header("Content-Type","application/json; charset=UTF-8").request().get(uri);
    }
    @Given("^A DELETE request to api (.*?)$")
    public void simple_delete(String uri){
        response = given().header("Content-Type","application/json; charset=UTF-8").request().delete(uri);

    }

    @When("^I send a POST with body file (.*?) to URL (.*?)$")
    public void postWithBody(String bodyFile, String url) throws FileNotFoundException {
        request = given().header("Content-Type","application/json; charset=UTF-8").body(new File(ClassLoader.getSystemClassLoader().getResource(bodyFile).getFile()));
        response = request.post(url);
    }

    @When("^I send a PUT with body file (.*?) to URL (.*?)$")
    public void putWithBodyAndHeaders(String bodyFile, String url) throws FileNotFoundException {
        request = given().header("Content-Type","application/json; charset=UTF-8").body(new File(ClassLoader.getSystemClassLoader().getResource(bodyFile).getFile()));
        response = request.put(url);
    }

    /**
     * asserts on json arrays
     */
    @Then("^Check that response code is (.*?)$")
    public void response_contains_code(String code) {
        given().response().statusCode(Integer.parseInt(code)).validate(response);
    }
    @Then("^Check that response body is (.*?)$")
    public void response_contains_body(String responseBody) {
        JsonPath jsonExpected = new JsonPath(ClassLoader.getSystemClassLoader().getResource(responseBody));
        JsonPath jsonObtained = response.getBody().jsonPath();
        Assert.assertEquals("Different bodies",jsonExpected.get().toString(),jsonObtained.get().toString());
    }
}