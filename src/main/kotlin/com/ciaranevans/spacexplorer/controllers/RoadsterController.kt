package com.ciaranevans.spacexplorer.controllers

import com.ciaranevans.spacexplorer.models.Roadster
import com.ciaranevans.spacexplorer.services.RoadsterService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/roadster")
class RoadsterController(
        val roadsterService: RoadsterService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getRoadster(): Roadster {
        return roadsterService.getRoadster()
    }

}
