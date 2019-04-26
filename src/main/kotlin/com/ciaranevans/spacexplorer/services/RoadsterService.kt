package com.ciaranevans.spacexplorer.services

import com.ciaranevans.spacexplorer.exceptions.RoadsterNotFoundException
import com.ciaranevans.spacexplorer.models.Roadster
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RoadsterService(
        val restTemplate: RestTemplate,
        @Value("\${spacex-api.url}") val spacexApiEndpoint: String
) {

    private val roadsterEndpoint = "$spacexApiEndpoint/roadster"

    fun getRoadster(): Roadster {
        return runCatching {
            restTemplate.exchange(roadsterEndpoint,
                    HttpMethod.GET,
                    null,
                    object: ParameterizedTypeReference<Roadster>(){}
            ).body!!
        }.getOrElse { throw RoadsterNotFoundException("The information for Roadster could not be found") }
    }
}
