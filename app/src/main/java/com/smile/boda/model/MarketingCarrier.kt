package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class MarketingCarrier(@SerializedName(value = "AirlineID")
                            val airlineID: String,
                            @SerializedName(value = "FlightNumber")
                            val flightNumber: Int)