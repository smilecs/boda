package com.smile.boda.model

import com.squareup.moshi.Json

object NamesModel {
    data class Name(@Json(name = "@LanguageCode")
                    val languageCode: String,
                    @Json(name = "$")
                    val langToken: String
    )

    data class Names(@Json(name = "Name")
                     val name: List<Name>)
}