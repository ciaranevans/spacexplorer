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
        val firstFlight: String? = "no data",
        val country: String? = "no data",
        val company: String? = "no data",
        val height: Height = Height(),
        val diameter: Diameter = Diameter(),
        val mass: Mass = Mass(),
        @JsonProperty(value = "payload_weights")
        val payloadWeights: List<PayloadWeight> = emptyList(),
        @JsonProperty(value = "first_stage")
        val firstStage: FirstStage = FirstStage(),
        @JsonProperty(value = "second_stage")
        val secondStage: SecondStage = SecondStage(),
        val engines: Engines = Engines(),
        @JsonProperty(value = "landing_legs")
        val landingLegs: LandingLegs = LandingLegs(),
        val wikipedia: String? = "no data",
        val description: String? = "no data",
        @JsonProperty(value = "rocket_id")
        val rocketId: String? = "no data",
        @JsonProperty(value = "rocket_name")
        val rocketName: String? = "no data",
        @JsonProperty(value = "rocket_type")
        val rocketType: String? = "no data"
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

data class PayloadWeight(
        val id: String? = "no data",
        val name: String? = "no data",
        val kg: Float = 0.0f,
        val lb: Float = 0.0f
)

data class FirstStage(
        val reusable: Boolean = false,
        val engines: Int = 0,
        @JsonProperty(value = "fuel_amount_tons")
        val fuelAmountTons: Float = 0.0f,
        @JsonProperty(value = "burn_time_sec")
        val burnTimeSec: Float = 0.0f,
        @JsonProperty(value = "thrust_sea_level")
        val thrustSeaLevel: Thrust = Thrust(),
        @JsonProperty(value = "thrust_vacuum")
        val thrustVacuum: Thrust = Thrust()
)

data class SecondStage(
        val engines: Int = 0,
        @JsonProperty(value = "fuel_amount_tons")
        val fuelAmountTons: Float = 0.0f,
        @JsonProperty(value = "burn_time_sec")
        val burnTimeSec: Float = 0.0f,
        val thrust: Thrust = Thrust(),
        val payloads: Payloads = Payloads()
)

data class Thrust(
        val kn: Int = 0,
        val lbf: Int = 0
)

data class Payloads(
        @JsonProperty(value = "option_1")
        val optionOne: String? = "no data",
        @JsonProperty(value = "option_2")
        val optionTwo: String? = "no data",
        @JsonProperty(value = "composite_fairing")
        val compositeFairing: CompositeFairing = CompositeFairing()
)

data class CompositeFairing(
        val height: Height = Height(),
        val diameter: Diameter = Diameter()
)

data class Engines(
        val number: Int = 0,
        val type: String? = "no data",
        val version: String? = "no data",
        val layout: String? = "no data",
        @JsonProperty(value = "engine_loss_max")
        val engineLossMax: Int = 0,
        @JsonProperty(value = "propellant_1")
        val propellantOne: String? = "no data",
        @JsonProperty(value = "propellant_2")
        val propellantTwo: String? = "no data",
        @JsonProperty(value = "thrust_sea_level")
        val thrustSeaLevel: Thrust = Thrust(),
        @JsonProperty(value = "thrust_vacuum")
        val thrustVacuum: Thrust = Thrust(),
        @JsonProperty(value = "thrust_to_weight")
        val thrustToWeight: Int = 0
)

data class LandingLegs(
        val number: Int = 0,
        val material: String? = "no data"
)