package com.smile.boda.model

import com.squareup.moshi.Json

data class Flight(
        @Json(name = "Departure")
        val departure: Departure,
        @Json(name = "Arrival")
        val arrival: Arrival,
        @Json(name = "MarketingCarrier")
        val marketingCarrier: MarketingCarrier
)