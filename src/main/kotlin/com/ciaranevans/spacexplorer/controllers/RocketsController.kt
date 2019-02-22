package com.ciaranevans.spacexplorer.controllers

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.services.RocketsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rockets")
class RocketsController(
        val rocketsService: RocketsService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAllRockets(): List<Rocket> {
        return rocketsService.getAllRockets()
    }

}