package com.smile.boda.model

import com.squareup.moshi.Json

data class AirportResource(@Json(name = "Airports")
                           val airports: AirportModel.Airports)