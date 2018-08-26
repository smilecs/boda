package com.smile.boda.model

import com.squareup.moshi.Json

data class TimeLocal(@Json(name = "DateTime")
                     val dateTime:String)