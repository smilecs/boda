package com.smile.boda.model

import com.squareup.moshi.Json

data class Arrival(@Json(name = "AirportCode")
                   val airportCode: String,
                   @Json(name = "ScheduledTimeLocal")
                   val scheduledTimeLocal: TimeLocal)