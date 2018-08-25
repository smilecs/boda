package com.smile.boda.model

import com.squareup.moshi.Json

data class CoordinateModel(@Json(name = "Latitude")
                           val latitude: Double,
                           @Json(name = "Longitude")
                           val longitude: Double
)