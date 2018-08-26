package com.smile.boda.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.smile.boda.api.response.AlwaysListJsonAdapater

data class Schedule(@SerializedName(value = "TotalJourney")
                    val totalJourney: TotalJourney,
                    @SerializedName(value = "Flight")
                    @JsonAdapter(AlwaysListJsonAdapater::class)
                    val flight: List<Flight>)