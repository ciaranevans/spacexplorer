package com.ciaranevans.spacexplorer.unit.services

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.exceptions.RocketNotFoundException
import com.ciaranevans.spacexplorer.services.RocketsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.lang.RuntimeException
import java.util.*

const val END_POINT: String = "anEndpoint"

@RunWith(MockitoJUnitRunner::class)
class RocketsServiceTest {

    @Mock
    lateinit var mockRestTemplate: RestTemplate

    @Mock
    lateinit var mockResponseEntity: ResponseEntity<Rocket>

    lateinit var rocketsService: RocketsService

    @Before
    fun setup() {
        rocketsService = RocketsService(mockRestTemplate, END_POINT)
    }

    @Test
    fun whenGetAllRocketsCallReturnsEmptyListThenEmptyListOfRocketsReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<List<Rocket>>(){})))
                .thenReturn(ResponseEntity.of(Optional.empty()))

        val rockets = rocketsService.getAllRockets()

        assertThat(rockets.size)
                .`as`("Should contain no rockets")
                .isEqualTo(0)
    }

    @Test
    fun whenGetAllRocketsCallThrowsErrorThenEmptyListOfRocketsReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<List<Rocket>>(){})))
                .thenThrow(RuntimeException("Bad things happened"))

        val rockets = rocketsService.getAllRockets()

        assertThat(rockets.size)
                .`as`("Should contain no rockets")
                .isEqualTo(0)
    }

    @Test
    fun whenGetAllRocketsCallIsSuccessfulThenListOfRocketsReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<List<Rocket>>(){})))
                .thenReturn(ResponseEntity.ok(listOf(Rocket(1, true, 1, 1, 0.0f))))

        val rockets = rocketsService.getAllRockets()

        assertThat(rockets.size)
                .`as`("Should only contain one rocket")
                .isEqualTo(1)

        assertThat(rockets[0].id)
                .`as`("Rocket contains correct id")
                .isEqualTo(1)
    }

    @Test
    fun whenGetOneRocketCallIsSuccessfulThenOneRocketReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets/rocketOne"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenReturn(ResponseEntity.ok((Rocket(0, true, 1, 1, 9999.0f))))

        val rocket = rocketsService.getOneRocket("rocketOne")

        assertThat(rocket.id)
                .`as`("Should have the correct ID")
                .isEqualTo(0)

        assertThat(rocket.costPerLaunch)
                .`as`("Should have the correct cost")
                .isEqualTo(9999.0f)
    }

    @Test(expected = RocketNotFoundException::class)
    fun whenGetOneRocketGetsNothingThenExceptionThrown() {
        Mockito.`when`(mockResponseEntity.body)
                .thenReturn(null)

        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets/rocketOne"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenReturn(mockResponseEntity)

        rocketsService.getOneRocket("rocketOne")
    }

    @Test(expected = RocketNotFoundException::class)
    fun whenGetOneRocketThrowsErrorThenExceptionThrown() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$END_POINT/rockets/rocketOne"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenThrow(RuntimeException("Bad things happened"))

        rocketsService.getOneRocket("rocketOne")
    }

}