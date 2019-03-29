package com.ciaranevans.spacexplorer.controllers

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.services.RocketsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/rockets")
class RocketsController(
        val rocketsService: RocketsService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAllRockets(): List<Rocket> {
        return rocketsService.getAllRockets()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getSingleRocket(@PathVariable("id") rocketId: String): Rocket {
        return rocketsService.getOneRocket(rocketId)
    }

}