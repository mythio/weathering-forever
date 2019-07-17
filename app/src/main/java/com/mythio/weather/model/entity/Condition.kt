package com.mythio.weather.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(

    @Json(name = "text")
    var text: String,

    @Json(name = "icon")
    var icon: String,

    @Json(name = "code")
    var code: Int
)