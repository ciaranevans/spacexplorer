package com.ciaranevans.spacexplorer.unit.services

import com.ciaranevans.spacexplorer.exceptions.RoadsterNotFoundException
import com.ciaranevans.spacexplorer.models.Roadster
import com.ciaranevans.spacexplorer.services.RoadsterService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class RoadsterServiceTest {

    private val endpoint: String = "anEndpoint"

    @Mock
    lateinit var mockRestTemplate: RestTemplate

    @Mock
    lateinit var mockResponseEntity: ResponseEntity<Roadster>

    lateinit var roadsterService: RoadsterService

    @Before
    fun setup() {
        roadsterService = RoadsterService(mockRestTemplate, endpoint)
    }

    @Test(expected = RoadsterNotFoundException::class)
    fun whenGetRoadsterCallGetsNothingThenExceptionThrown() {
        Mockito.`when`(mockResponseEntity.body)
                .thenReturn(null)

        Mockito.`when`(mockRestTemplate.exchange(eq("$endpoint/roadster"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Roadster>(){})))
                .thenReturn(mockResponseEntity)

        roadsterService.getRoadster()
    }

    @Test(expected = RoadsterNotFoundException::class)
    fun whenGetRoadsterCallThrowsErrorThenExceptionThrown() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$endpoint/roadster"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Roadster>(){})))
                .thenThrow(RuntimeException("Wah wah waaaah"))

        roadsterService.getRoadster()
    }

    @Test
    fun whenGetRoadsterCallSuccessfulThenRoadsterReturned() {
        Mockito.`when`(mockRestTemplate.exchange(eq("$endpoint/roadster"),
                eq(HttpMethod.GET),
                eq(null),
                eq(object: ParameterizedTypeReference<Roadster>(){})))
                .thenReturn(ResponseEntity.ok(Roadster("Roadster Name")))

        val roadster = roadsterService.getRoadster()

        assertThat(roadster.name)
                .`as`("Should be the correct name")
                .isEqualTo("Roadster Name")
    }

}