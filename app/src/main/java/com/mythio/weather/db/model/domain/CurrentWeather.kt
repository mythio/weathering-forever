package com.mythio.weather.db.model.domain

import com.mythio.weather.R
import com.mythio.weather.Unit
import com.mythio.weather.db.model.entity.current.CurrentImperial
import com.mythio.weather.db.model.entity.current.CurrentMetric

data class CurrentWeather(

    val unit: Unit,
    val temperature: Double,
    val conditionText: String,
    val conditionIconRes: Int,
    val windSpeed: Double,
    val windDirection: String,
    val humidity: Int,
    val feelsLike: Double,
    val uvIndex: Double
)

fun CurrentImperial.convert(): CurrentWeather {
    return CurrentWeather(
        unit = Unit.IMPERIAL,
        temperature = temperature,
        conditionText = conditionText,
        conditionIconRes = getIconRes(conditionCode),
        windSpeed = windSpeed,
        windDirection = windDirection,
        humidity = humidity,
        feelsLike = feelsLikeTemperature,
        uvIndex = uvIndex
    )
}

fun CurrentMetric.convert(): CurrentWeather {
    return CurrentWeather(
        unit = Unit.METRIC,
        temperature = temperature,
        conditionText = conditionText,
        conditionIconRes = getIconRes(conditionCode),
        windSpeed = windSpeed,
        windDirection = windDirection,
        humidity = humidity,
        feelsLike = feelsLikeTemperature,
        uvIndex = uvIndex
    )
}

fun getIconRes(code: Int): Int {
    return when (code) {
        1000 -> R.drawable.ic_wi_day_cloudy
        1003 -> R.drawable.ic_wi_day_cloudy
        1006 -> R.drawable.ic_wi_day_cloudy
        1009 -> R.drawable.ic_wi_day_cloudy
        1030 -> R.drawable.ic_wi_day_cloudy
        1063 -> R.drawable.ic_wi_day_cloudy
        1066 -> R.drawable.ic_wi_day_cloudy
        1069 -> R.drawable.ic_wi_day_cloudy
        1072 -> R.drawable.ic_wi_day_cloudy
        1087 -> R.drawable.ic_wi_day_cloudy
        1114 -> R.drawable.ic_wi_day_cloudy
        1117 -> R.drawable.ic_wi_day_cloudy
        1135 -> R.drawable.ic_wi_day_cloudy
        1147 -> R.drawable.ic_wi_day_cloudy
        1150 -> R.drawable.ic_wi_day_cloudy
        1153 -> R.drawable.ic_wi_day_cloudy
        1168 -> R.drawable.ic_wi_day_cloudy
        1171 -> R.drawable.ic_wi_day_cloudy
        1180 -> R.drawable.ic_wi_day_cloudy
        1183 -> R.drawable.ic_wi_day_cloudy
        1186 -> R.drawable.ic_wi_day_cloudy
        1189 -> R.drawable.ic_wi_day_cloudy
        1192 -> R.drawable.ic_wi_day_cloudy
        1195 -> R.drawable.ic_wi_day_cloudy
        1198 -> R.drawable.ic_wi_day_cloudy
        1201 -> R.drawable.ic_wi_day_cloudy
        1204 -> R.drawable.ic_wi_day_cloudy
        1207 -> R.drawable.ic_wi_day_cloudy
        1210 -> R.drawable.ic_wi_day_cloudy
        1213 -> R.drawable.ic_wi_day_cloudy
        1216 -> R.drawable.ic_wi_day_cloudy
        1219 -> R.drawable.ic_wi_day_cloudy
        1222 -> R.drawable.ic_wi_day_cloudy
        1225 -> R.drawable.ic_wi_day_cloudy
        1237 -> R.drawable.ic_wi_day_cloudy
        1240 -> R.drawable.ic_wi_day_cloudy
        1243 -> R.drawable.ic_wi_day_cloudy
        1246 -> R.drawable.ic_wi_day_cloudy
        1249 -> R.drawable.ic_wi_day_cloudy
        1252 -> R.drawable.ic_wi_day_cloudy
        1255 -> R.drawable.ic_wi_day_cloudy
        1258 -> R.drawable.ic_wi_day_cloudy
        1261 -> R.drawable.ic_wi_day_cloudy
        1264 -> R.drawable.ic_wi_day_cloudy
        1273 -> R.drawable.ic_wi_day_cloudy
        1276 -> R.drawable.ic_wi_day_cloudy
        1279 -> R.drawable.ic_wi_day_cloudy
        1282 -> R.drawable.ic_wi_day_cloudy
        else -> -1
    }
}

