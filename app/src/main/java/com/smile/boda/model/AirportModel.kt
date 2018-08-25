package com.smile.boda.model

import com.squareup.moshi.Json

object AirportModel {
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
            val names: NamesModel.Names,
            @Json(name = "UtcOffset")
            val utcOffset: Int,
            @Json(name = "TimeZoneId")
            val timeZoneId: String)

    data class Airports(val airports: List<Airport>)
}