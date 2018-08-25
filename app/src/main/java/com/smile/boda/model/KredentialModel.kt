package com.smile.boda.model

import com.squareup.moshi.Json

data class KredentialModel(@Json(name = "access_token") val token: String,
                           @Json(name = "token_type") val type: String,
                           @Json(name = "expires_in") val expiry: Int)
