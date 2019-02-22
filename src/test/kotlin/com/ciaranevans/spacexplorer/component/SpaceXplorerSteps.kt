package com.ciaranevans.spacexplorer.component

import com.ciaranevans.spacexplorer.SpaceXplorerApplication
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [SpaceXplorerApplication::class])
class SpaceXplorerSteps(@LocalServerPort
                        val port: Int) {

    lateinit var response : Response

    @When("^I make a GET request to the \"([^\"]*)\" endpoint$")
    fun iMakeAGetRequestToAnEndpoint(endpoint: String) {
        response = given()
                .port(port)
                .get("/hello")
    }

    @Then("^I receive a response of$")
    fun iReceiveAResponseOf(expectedResponse: String) {
        assertThat(response.asString())
                .`as`("Should return correct response")
                .isEqualTo(expectedResponse)
    }

}