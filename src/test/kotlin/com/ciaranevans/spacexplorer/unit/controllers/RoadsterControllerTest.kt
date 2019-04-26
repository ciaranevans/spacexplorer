package com.ciaranevans.spacexplorer.unit.controllers

import com.ciaranevans.spacexplorer.controllers.RoadsterController
import com.ciaranevans.spacexplorer.models.Roadster
import com.ciaranevans.spacexplorer.services.RoadsterService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoadsterControllerTest {

    @Mock
    lateinit var roadsterService: RoadsterService

    @InjectMocks
    lateinit var roadsterController: RoadsterController

    @Test
    fun whenGetRoadsterCalledThenRoadsterReturned() {
        val expectedRoadster = Roadster("Roadster Name")

        Mockito.`when`(roadsterService.getRoadster())
                .thenReturn(expectedRoadster)

        val actualRoadster = roadsterController.getRoadster()

        assertThat(actualRoadster.name)
                .`as`("Should be the same name as expected Roadster")
                .isEqualTo(expectedRoadster.name)
    }

}