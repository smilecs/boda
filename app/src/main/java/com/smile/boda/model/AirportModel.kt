package com.smile.boda.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

object AirportModel {
    @Parcelize
    data class Airport(
            @SerializedName(value = "AirportCode")
            val airportCode: String,
            @SerializedName(value = "Position")
            val position: PositionModel,
            @SerializedName(value = "CityCode")
            val cityCode: String,
            @SerializedName(value = "CountryCode")
            val countryCode: String,
            @SerializedName(value = "LocationType")
            val locationType: String,
            @SerializedName(value = "Names")
            val names: @RawValue Map<String, Any?>,
            //val values: NamesModel.Names,
            @SerializedName(value = "UtcOffset")
            val utcOffset: Double,
            @SerializedName(value = "TimeZoneId")
            val timeZoneId: String) : Parcelable


    data class Airports(@SerializedName(value = "Airport") val airports: List<Airport>)
}