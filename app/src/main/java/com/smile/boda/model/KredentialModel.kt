package com.smile.boda.model

import com.google.gson.annotations.SerializedName

data class KredentialModel(@SerializedName(value = "access_token") val token: String,
                           @SerializedName(value = "token_type") val type: String,
                           @SerializedName(value = "expires_in") val expiry: Long)
