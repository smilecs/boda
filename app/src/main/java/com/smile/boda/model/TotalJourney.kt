package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class TotalJourney(@SerializedName(value = "Duration")
                        val duration:String)