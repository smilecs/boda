package com.smile.boda.api.response

import com.google.gson.annotations.SerializedName
import com.smile.boda.model.ScheduleResource
import com.squareup.moshi.Json

data class ScheduleResp(@SerializedName(value = "ScheduleResource")
                        val scheduleResource: ScheduleResource)