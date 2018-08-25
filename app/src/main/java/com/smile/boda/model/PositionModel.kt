package com.smile.boda.model

import com.squareup.moshi.Json

data class PositionModel(@Json(name = "Coordinate")
                         val coordinate:CoordinateModel)