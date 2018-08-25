package com.smile.boda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PositionModel(@Json(name = "Coordinate")
                         val coordinate:CoordinateModel):Parcelable