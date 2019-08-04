package com.mythio.weather.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(

    @Json(name = "text")
    val text: String,

    @Json(name = "icon")
    val icon: String,

    @Json(name = "code")
    val code: Int
)