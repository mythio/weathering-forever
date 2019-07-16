package com.mythio.weather.db.entity

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Day(

    @Json(name = "maxtemp_c")
    val maxtempC: Double,

    @Json(name = "maxtemp_f")
    val maxtempF: Double,

    @Json(name = "mintemp_c")
    val mintempC: Double,

    @Json(name = "mintemp_f")
    val mintempF: Double,

    @Json(name = "avgtemp_c")
    val avgtempC: Double,

    @Json(name = "avgtemp_f")
    val avgtempF: Double,

    @Json(name = "maxwind_mph")
    val maxwindMph: Double,

    @Json(name = "maxwind_kph")
    val maxwindKph: Double,

    @Json(name = "totalprecip_mm")
    val totalprecipMm: Double,

    @Json(name = "totalprecip_in")
    val totalprecipIn: Double,

    @Json(name = "avgvis_km")
    val avgvisKm: Double,

    @Json(name = "avgvis_miles")
    val avgvisMiles: Double,

    @Json(name = "avghumidity")
    val avghumidity: Double,

    @Json(name = "condition")
    @Embedded(prefix = "condition_")
    val condition: Condition,

    @Json(name = "uv")
    val uv: Double
)