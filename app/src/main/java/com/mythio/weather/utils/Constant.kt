package com.mythio.weather.utils

import com.mythio.weather.R

enum class Unit {
    METRIC,
    IMPERIAL
}

const val BASE_URL = "http://api.apixu.com/v1/"

val CODE_TO_RES_ID: Map<Int, Int> = hashMapOf(
    1000 to R.drawable.ic_wi_day_sunny,
    1003 to R.drawable.ic_wi_day_cloudy,
    1006 to R.drawable.ic_wi_day_cloudy,
    1009 to R.drawable.ic_wi_day_cloudy_high,
    1030 to R.drawable.ic_wi_day_fog,
    1063 to R.drawable.ic_wi_day_showers,
    1066 to R.drawable.ic_wi_day_snow,
    1069 to R.drawable.ic_wi_day_sleet,
    1072 to R.drawable.ic_wi_day_rain_mix,
    1087 to R.drawable.ic_wi_day_lightning,
    1114 to R.drawable.ic_wi_day_snow_wind,
    1117 to R.drawable.ic_wi_day_snow_wind,
    1135 to R.drawable.ic_wi_day_fog,
    1147 to R.drawable.ic_wi_day_fog,
    1150 to R.drawable.ic_wi_day_showers,
    1153 to R.drawable.ic_wi_day_showers,
    1168 to R.drawable.ic_wi_day_rain_mix,
    1171 to R.drawable.ic_wi_day_rain_mix,
    1180 to R.drawable.ic_wi_day_showers,
    1183 to R.drawable.ic_wi_day_showers,
    1186 to R.drawable.ic_wi_day_showers,
    1189 to R.drawable.ic_wi_day_showers,
    1192 to R.drawable.ic_wi_day_rain,
    1195 to R.drawable.ic_wi_day_rain,
    1198 to R.drawable.ic_wi_day_rain_mix,
    1201 to R.drawable.ic_wi_day_rain_mix,
    1204 to R.drawable.ic_wi_day_hail,
    1207 to R.drawable.ic_wi_day_hail,
    1210 to R.drawable.ic_wi_day_snow,
    1213 to R.drawable.ic_wi_day_snow,
    1216 to R.drawable.ic_wi_day_snow,
    1219 to R.drawable.ic_wi_day_snow,
    1222 to R.drawable.ic_wi_day_snow,
    1225 to R.drawable.ic_wi_day_snow,
    1237 to R.drawable.ic_wi_day_hail,
    1240 to R.drawable.ic_wi_day_showers,
    1243 to R.drawable.ic_wi_day_showers,
    1246 to R.drawable.ic_wi_day_rain,
    1249 to R.drawable.ic_wi_day_sleet,
    1252 to R.drawable.ic_wi_day_sleet,
    1255 to R.drawable.ic_wi_day_snow,
    1258 to R.drawable.ic_wi_day_snow,
    1261 to R.drawable.ic_wi_day_hail,
    1264 to R.drawable.ic_wi_day_hail,
    1273 to R.drawable.ic_wi_day_storm_showers,
    1276 to R.drawable.ic_wi_day_storm_showers,
    1279 to R.drawable.ic_wi_day_snow_thunderstorm,
    1282 to R.drawable.ic_wi_day_snow_thunderstorm
)

val DIR_TO_DEGREE: Map<String, Int> = hashMapOf(
    "N" to 0,
    "NNE" to 23,
    "NE" to 45,
    "NEN" to 68,
    "E" to 90,
    "EES" to 113,
    "ES" to 135,
    "ESE" to 158,
    "S" to 180,
    "SSW" to 203,
    "SW" to 225,
    "SWS" to 248,
    "W" to 270,
    "WWN" to 293,
    "WN" to 313,
    "WNW" to 336
)