package com.mythio.weather.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(

    @Json(name = "name")
    val name: String?,

    @Json(name = "region")
    val region: String
)