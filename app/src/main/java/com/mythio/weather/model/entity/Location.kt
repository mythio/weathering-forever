package com.mythio.weather.model.entity

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "location", primaryKeys = ["name", "region"])
data class Location(

    @Json(name = "name")
    val name: String,

    @Json(name = "region")
    val region: String
)