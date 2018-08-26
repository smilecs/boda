package com.smile.boda.model

import com.squareup.moshi.Json

data class MarketingCarrier(@Json(name = "AirlineID")
                            val airlineID: String,
                            @Json(name = "FlightNumber")
                            val flightNumber: Integer)