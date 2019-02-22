package com.ciaranevans.spacexplorer.services

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.exceptions.RocketNotFoundException
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RocketsService(val restTemplate: RestTemplate) {

    fun getAllRockets(): List<Rocket> {
        return runCatching {
            restTemplate.exchange("https://api.spacexdata.com/v3/rockets",
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<Rocket>>() {}
            ).body.orEmpty()
        }.getOrElse { emptyList() }
    }

    fun getOneRocket(rocketId: Int): Rocket {
        return kotlin.runCatching {
            restTemplate.exchange("https://api.spacexdata.com/v3/rockets/$rocketId",
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<Rocket>() {}
            ).body!!
        }.getOrElse { throw RocketNotFoundException("Rocket of ID: $rocketId could not be found") }
    }

}