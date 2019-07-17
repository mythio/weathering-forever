package com.mythio.weather.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "current_weather")
data class CurrentW(

    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int,

    @Json(name = "last_updated")
    val lastUpdated: String,

    @Json(name = "temp_c")
    val tempC: Double,

    @Json(name = "temp_f")
    val tempF: Double,

    @Json(name = "is_day")
    val isDay: Int,

    @Json(name = "condition")
    @Embedded(prefix = "condition_")
    val condition: Condition,

    @Json(name = "wind_mph")
    val windMph: Double,

    @Json(name = "wind_kph")
    val windKph: Double,

    @Json(name = "wind_degree")
    val windDegree: Int,

    @Json(name = "wind_dir")
    val windDir: String,

    @Json(name = "pressure_mb")
    val pressureMb: Double,

    @Json(name = "pressure_in")
    val pressureIn: Double,

    @Json(name = "precip_mm")
    val precipMm: Double,

    @Json(name = "precip_in")
    val precipIn: Double,

    @Json(name = "humidity")
    val humidity: Int,

    @Json(name = "cloud")
    val cloud: Int,

    @Json(name = "feelslike_c")
    val feelslikeC: Double,

    @Json(name = "feelslike_f")
    val feelslikeF: Double,

    @Json(name = "vis_km")
    val visKm: Double,

    @Json(name = "vis_miles")
    val visMiles: Double,

    @Json(name = "uv")
    val uv: Double,

    @Json(name = "gust_mph")
    val gustMph: Double,

    @Json(name = "gust_kph")
    val gustKph: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int? = -1
        get() = 0
}