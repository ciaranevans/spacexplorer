package com.ciaranevans.spacexplorer

import com.fasterxml.jackson.annotation.JsonProperty

data class Rocket(
        val id: Int,
        val active: Boolean,
        val stages: Int,
        val boosters: Int,
        @JsonProperty(value = "cost_per_launch")
        val costPerLaunch: Float
)