package com.smile.boda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


object NamesModel {
    @Parcelize
    data class Name(@Json(name = "@LanguageCode")
                    val languageCode: String,
                    @Json(name = "$")
                    val langToken: String
    ) : Parcelable

    @Parcelize
    data class Names(@Json(name = "Name")
                     val name: List<Name>):Parcelable
}