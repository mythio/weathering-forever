package com.mythio.weather.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "forecast_weather")
data class ForecastW(

    @Json(name = "date")
    @PrimaryKey
    var date: String,

    @Json(name = "date_epoch")
    var dateEpoch: Long,

    @Json(name = "day")
    @Embedded(prefix = "day_")
    var day: Day
)