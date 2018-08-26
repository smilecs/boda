package com.smile.boda.api.response

import com.smile.boda.model.ScheduleResource
import com.squareup.moshi.Json

data class ScheduleResp(@Json(name = "ScheduleResource")
                        val scheduleResource: ScheduleResource)