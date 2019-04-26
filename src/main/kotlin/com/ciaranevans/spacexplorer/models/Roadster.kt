package com.ciaranevans.spacexplorer.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Roadster(
        val name: String? = "no data",
        @JsonProperty(value = "launch_date_utc")
        val launchDateUtc: String? = "no data",
        @JsonProperty(value = "launch_date_unix")
        val launchDateUnix: Int = 0,
        @JsonProperty(value = "launch_mass_kg")
        val launchMassKg: Float = 0.0f,
        @JsonProperty(value = "launch_mass_lbs")
        val launchMassLb: Float = 0.0f,
        @JsonProperty(value = "norad_id")
        val noradId: Int = 0,
        @JsonProperty(value = "epoch_jd")
        val epochJd: Double = 0.0
)