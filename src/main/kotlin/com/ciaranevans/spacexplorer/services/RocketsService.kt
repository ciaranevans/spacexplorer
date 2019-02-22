package com.ciaranevans.spacexplorer.services

import com.ciaranevans.spacexplorer.Rocket
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.math.truncate

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

}