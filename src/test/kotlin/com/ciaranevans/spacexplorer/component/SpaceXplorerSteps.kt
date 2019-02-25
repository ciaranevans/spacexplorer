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
@AutoConfigureWireMock(port = 0)
class SpaceXplorerSteps(@LocalServerPort
                        val port: Int) {

    lateinit var response: Response

    @Given("^https://api.spacexdata.com/v3/rockets is mocked to return some rockets$")
    fun theOriginalRocketsEndpointIsMockedToReturnRockets() {
        stubFor(
                WireMock
                        .get(urlEqualTo("/v3/rockets"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                        .withBody("""[{"id":0,"active":true,"stages":2,
                                            "boosters":0,"cost_per_launch":50000000},{"id":1,"active":true,"stages":2,
                                            "boosters":0,"cost_per_launch":50000000}]""")
                        )
        )
    }

    @Given("^https://api.spacexdata.com/v3/rockets/999 is mocked to return one rocket$")
    fun theOriginalGetOneRocketEndpointIsMockedToReturnRocket() {
        stubFor(
                WireMock
                        .get(urlPathMatching("/v3/rockets/.*"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                        .withBody("""{"id":999,"active":true,"stages":2,
                                            "boosters":0,"cost_per_launch":50000000}""")
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

    @Then("^I receive a response of \"(\\d+)\" rockets$")
    fun iReceiveAResponseOf4Rockets(numberOfRockets: Int) {
        val rockets: List<Rocket> = response.body.jsonPath().getList("", Rocket::class.java)
        assertThat(rockets.size)
                .`as`("Should contain $numberOfRockets rockets")
                .isEqualTo(numberOfRockets)

        assertThat(rockets[1].id)
                .`as`("Should be one of the mocked rockets")
                .isEqualTo(1)
    }

    @Then("^I receive a response of one rocket$")
    fun iReceiveAResponseOf1Rocket() {
        val rocket: Rocket = response.body.jsonPath().getObject("", Rocket::class.java)

        assertThat(rocket.id)
                .`as`("Should be the mocked rocket")
                .isEqualTo(999)
    }

}