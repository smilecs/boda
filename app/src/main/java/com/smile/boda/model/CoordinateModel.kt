package com.smile.boda.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordinateModel(@SerializedName(value = "Latitude")
                           val latitude: Double,
                           @SerializedName(value = "Longitude")
                           val longitude: Double
):Parcelable