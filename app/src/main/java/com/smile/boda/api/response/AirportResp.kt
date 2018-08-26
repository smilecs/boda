package com.smile.boda.api.response

import com.google.gson.annotations.SerializedName
import com.smile.boda.model.AirportResource
import com.squareup.moshi.Json

data class AirportResp(@SerializedName(value = "AirportResource") val airportResource: AirportResource,
                       @SerializedName(value = "Meta") val meta: Meta)