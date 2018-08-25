package com.smile.boda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

object AirportModel {
    @Parcelize
    data class Airport(
            @Json(name = "AirportCode")
            val airportCode: String,
            @Json(name = "Position")
            val position: PositionModel,
            @Json(name = "CityCode")
            val cityCode: String,
            @Json(name = "CountryCode")
            val countryCode: String,
            @Json(name = "LocationType")
            val locationType: String,
            @Json(name = "Names")
            val names: @RawValue Map<String, Any?>,
            //val names: NamesModel.Names,
            @Json(name = "UtcOffset")
            val utcOffset: Double,
            @Json(name = "TimeZoneId")
            val timeZoneId: String) : Parcelable

    data class Airports(@Json(name = "Airport")val airports: List<Airport>)
}