package com.ciaranevans.spacexplorer.unit.controllers

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.controllers.RocketsController
import com.ciaranevans.spacexplorer.services.RocketsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest

@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class RocketsControllerTest {

    @Mock
    lateinit var rocketsService: RocketsService

    @InjectMocks
    lateinit var rocketsController : RocketsController

    @Test
    fun whenGetAllRocketsCalledThenListOfRocketsReturned() {
        val rocket1 = Rocket(0, true, 1, 1, 0.0f)
        val rocket2 = Rocket(1, true, 1, 1, 0.0f)

        Mockito.`when`(rocketsService.getAllRockets())
                .thenReturn(listOf(rocket1, rocket2))

        val rockets = rocketsController.getAllRockets()

        assertThat(rockets.size)
                .`as`("Should contain two rockets")
                .isEqualTo(2)

        assertThat(rockets[1].id)
                .`as`("Should contain one of the rockets that were mocked")
                .isEqualTo(1)
    }

    @Test
    fun whenGetOneRocketCalledThenOneRocketReturned() {
        val rocket1 = Rocket(0, true, 1, 1, 0.0f)

        Mockito.`when`(rocketsService.getOneRocket(0))
                .thenReturn(rocket1)

        val rocket = rocketsController.getSingleRocket(0)

        assertThat(rocket.id)
                .`as`("Should be the rocket that was mocked")
                .isEqualTo(0)
    }

}