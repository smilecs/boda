package com.smile.boda.api.response

import com.smile.boda.model.AirportResource
import com.squareup.moshi.Json

data class AirportResp(@Json(name = "AirportResource") val airportResource: AirportResource,
                       @Json(name = "Meta") val meta: Meta)