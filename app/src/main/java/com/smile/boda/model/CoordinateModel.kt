package com.smile.boda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordinateModel(@Json(name = "Latitude")
                           val latitude: Double,
                           @Json(name = "Longitude")
                           val longitude: Double
):Parcelable