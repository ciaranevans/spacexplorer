package com.ciaranevans.spacexplorer.services

import com.ciaranevans.spacexplorer.Rocket
import com.ciaranevans.spacexplorer.exceptions.RocketNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RocketsService(
        val restTemplate: RestTemplate,
        @Value("\${spacex-api.url}") spacexApiEndpoint: String
) {

    private val rocketsEndpoint = "$spacexApiEndpoint/rockets"

    fun getAllRockets(): List<Rocket> {
        return runCatching {
            restTemplate.exchange(rocketsEndpoint,
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<Rocket>>() {}
            ).body.orEmpty()
        }.getOrElse { emptyList() }
    }

    fun getOneRocket(rocketId: String): Rocket {
        return runCatching {
            restTemplate.exchange("$rocketsEndpoint/$rocketId",
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<Rocket>() {}
            ).body!!
        }.getOrElse { throw RocketNotFoundException("Rocket of ID: $rocketId could not be found") }
    }

}