package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class Flight(
        @SerializedName(value = "Departure")
        val departure: Departure,
        @SerializedName(value = "Arrival")
        val arrival: Arrival,
        @SerializedName(value = "MarketingCarrier")
        val marketingCarrier: MarketingCarrier
)