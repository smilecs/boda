package com.smile.boda.model

import com.google.gson.annotations.SerializedName
import com.smile.boda.api.response.Meta

data class AirportResource(@SerializedName(value = "Airports")
                           val airports: AirportModel.Airports, @SerializedName(value = "Meta") val meta: Meta)