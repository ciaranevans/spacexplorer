package com.ciaranevans.spacexplorer.component

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.SpaceXplorerApplication
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [SpaceXplorerApplication::class])
@AutoConfigureWireMock(port = 8090)
class SpaceXplorerSteps(@LocalServerPort
                        val port: Int) {

    lateinit var response : Response

    @Given("^the https://api.spacexdata.com/v3/rockets is mocked to return some rockets$")
    fun theOriginalRocketsEndpointIsMockedToReturnRockets() {
        stubFor(
                WireMock
                        .get(urlEqualTo("https://api.spacexdata.com/v3/rockets"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                        .withBody("hello")
                        )
        )
    }

    @When("^I make a GET request to the \"([^\"]*)\" endpoint$")
    fun iMakeAGetRequestToAnEndpoint(endpoint: String) {
        response = given()
                .port(port)
                .get(endpoint)
    }

    @Then("^I receive a response of$")
    fun iReceiveAResponseOf(expectedResponse: String) {
        assertThat(response.asString())
                .`as`("Should return correct response")
                .isEqualTo(expectedResponse)
    }

    @Then("^I receive a response of 4 rockets$")
    fun iReceiveAResponseOf4Rockets() {
        val rockets: List<Rocket> = response.body.jsonPath().getList("")
        assertThat(rockets.size)
                .`as`("Should contain 4 rockets")
                .isEqualTo(3)
    }

}