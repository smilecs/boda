package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class Arrival(@SerializedName(value = "AirportCode")
                   val airportCode: String,
                   @SerializedName(value = "ScheduledTimeLocal")
                   val scheduledTimeLocal: TimeLocal)