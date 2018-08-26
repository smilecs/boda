package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class TimeLocal(@SerializedName(value = "DateTime")
                     val dateTime:String)