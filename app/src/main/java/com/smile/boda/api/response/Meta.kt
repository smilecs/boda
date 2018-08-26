package com.smile.boda.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(@SerializedName(value = "TotalCount")
                val totalCount: Int):Parcelable