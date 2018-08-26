package com.smile.boda.model

import com.squareup.moshi.Json

data class Schedule(@Json(name = "TotalJourney")
                    val totalJourney: TotalJourney,
                    @Json(name = "Flight")
                    val flight: List<Flight>)