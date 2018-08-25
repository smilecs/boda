package com.smile.boda.api.response

import com.squareup.moshi.Json

data class Meta(@Json(name = "TotalCount")
                val totalCount: Int)