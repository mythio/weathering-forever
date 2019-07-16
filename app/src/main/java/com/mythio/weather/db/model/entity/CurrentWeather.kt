package com.mythio.weather.db.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "current_weather")
data class CurrentWeather(

    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int,

    @Json(name = "last_updated")
    val lastUpdated: String,

    @Json(name = "temp_c")
    val tempC: Float,

    @Json(name = "temp_f")
    val tempF: Float,

    @Json(name = "is_day")
    val isDay: Int,

    @Json(name = "condition")
    @Embedded(prefix = "condition_")
    val condition: Condition,

    @Json(name = "wind_mph")
    val windMph: Float,

    @Json(name = "wind_kph")
    val windKph: Float,

    @Json(name = "wind_degree")
    val windDegree: Int,

    @Json(name = "wind_dir")
    val windDir: String,

    @Json(name = "pressure_mb")
    val pressureMb: Float,

    @Json(name = "pressure_in")
    val pressureIn: Float,

    @Json(name = "precip_mm")
    val precipMm: Float,

    @Json(name = "precip_in")
    val precipIn: Float,

    @Json(name = "humidity")
    val humidity: Int,

    @Json(name = "cloud")
    val cloud: Int,

    @Json(name = "feelslike_c")
    val feelslikeC: Float,

    @Json(name = "feelslike_f")
    val feelslikeF: Float,

    @Json(name = "vis_km")
    val visKm: Float,

    @Json(name = "vis_miles")
    val visMiles: Float,

    @Json(name = "uv")
    val uv: Float,

    @Json(name = "gust_mph")
    val gustMph: Float,

    @Json(name = "gust_kph")
    val gustKph: Float
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int? = -1
        get() = 0
}