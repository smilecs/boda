package com.smile.boda.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.smile.boda.api.response.AlwaysListJsonAdapater


data class ScheduleResource(
        @SerializedName(value = "Schedule")
        @JsonAdapter(AlwaysListJsonAdapater::class)
        val schedule: List<Schedule>)