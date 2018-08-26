package com.smile.boda.model

import com.squareup.moshi.Json

data class TotalJourney(@Json(name = "Duration")
                        val duration:String)