package com.mythio.weather.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mythio.weather.CURRENT_WEATHER_ID

@Entity(tableName = "current_weather")
data class CurrentWeather(

    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("temp_c")
    val tempC: Float,

    @SerializedName("temp_f")
    val tempF: Float,

    @SerializedName("is_day")
    val isDay: Int,

    @Embedded(prefix = "condition_")
    @SerializedName("condition")
    val condition: Condition,

    @SerializedName("wind_mph")
    val windMph: Float,

    @SerializedName("wind_kph")
    val windKph: Float,

    @SerializedName("wind_degree")
    val windDegree: Int,

    @SerializedName("wind_dir")
    val windDir: String,

    @SerializedName("pressure_mb")
    val pressureMb: Float,

    @SerializedName("pressure_in")
    val pressureIn: Float,

    @SerializedName("precip_mm")
    val precipMm: Float,

    @SerializedName("precip_in")
    val precipIn: Float,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("cloud")
    val cloud: Int,

    @SerializedName("feelslike_c")
    val feelslikeC: Float,

    @SerializedName("feelslike_f")
    val feelslikeF: Float,

    @SerializedName("vis_km")
    val visKm: Float,

    @SerializedName("vis_miles")
    val visMiles: Float,

    @SerializedName("uv")
    val uv: Float,

    @SerializedName("gust_mph")
    val gustMph: Float,

    @SerializedName("gust_kph")
    val gustKph: Float
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}