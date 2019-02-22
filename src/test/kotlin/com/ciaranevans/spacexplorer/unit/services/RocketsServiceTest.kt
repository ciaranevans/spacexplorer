package com.ciaranevans.spacexplorer.unit.services

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.exceptions.RocketNotFoundException
import com.ciaranevans.spacexplorer.services.RocketsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.lang.RuntimeException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class RocketsServiceTest {

    @Mock
    lateinit var mockRestTemplate: RestTemplate

    @InjectMocks
    lateinit var rocketsService: RocketsService

    @Test
    fun whenGetAllRocketsCallReturnsEmptyListThenEmptyListOfRocketsReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets"),
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
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets"),
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
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets"),
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
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets/0"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenReturn(ResponseEntity.ok((Rocket(0, true, 1, 1, 9999.0f))))

        val rocket = rocketsService.getOneRocket(0)

        assertThat(rocket.id)
                .`as`("Should have the correct ID")
                .isEqualTo(0)

        assertThat(rocket.costPerLaunch)
                .`as`("Should have the correct cost")
                .isEqualTo(9999.0f)
    }

    @Test(expected = RocketNotFoundException::class)
    fun whenGetOneRocketGetsNothingThenExceptionThrown() {
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets/0"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenReturn(null)

        rocketsService.getOneRocket(0)
    }

    @Test(expected = RocketNotFoundException::class)
    fun whenGetOneRocketThrowsErrorThenExceptionThrown() {
        Mockito.`when`(mockRestTemplate.exchange(eq("https://api.spacexdata.com/v3/rockets/0"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Rocket>(){})))
                .thenThrow(RuntimeException("Bad things happened"))

        rocketsService.getOneRocket(0)
    }

}