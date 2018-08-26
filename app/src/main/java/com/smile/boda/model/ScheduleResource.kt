package com.smile.boda.model

import com.squareup.moshi.Json


data class ScheduleResource(
        @Json(name = "Schedule")
        val schedule: List<Schedule>)