package com.smile.boda.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


object NamesModel {
    @Parcelize
    data class Name(@SerializedName(value = "@LanguageCode")
                    val languageCode: String,
                    @SerializedName(value = "$")
                    val langToken: String
    ) : Parcelable

    @Parcelize
    data class Names(@SerializedName(value = "Name")
                     val value: List<Name>):Parcelable
}