package com.ciaranevans.spacexplorer

import com.fasterxml.jackson.annotation.JsonProperty

data class Rocket(
        val id: Int,
        val active: Boolean,
        val stages: Int,
        val boosters: Int,
        @JsonProperty(value = "cost_per_launch")
        val costPerLaunch: Float,
        @JsonProperty(value = "success_rate_pct")
        val successRatePercentage: Int = 0,
        @JsonProperty(value = "first_flight")
        val firstFlight: String = "no data",
        val country: String = "no data",
        val company: String = "no data",
        val height: Height = Height(),
        val diameter: Diameter = Diameter(),
        val mass: Mass = Mass()
)

data class Height(
        val meters: Float = 0.0f,
        val feet: Float = 0.0f
)

data class Diameter(
        val meters: Float = 0.0f,
        val feet: Float = 0.0f
)

data class Mass(
        val kg: Float = 0.0f,
        val lb: Float = 0.0f
)