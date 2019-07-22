package com.mythio.weather.utils

import com.mythio.weather.R

enum class Unit {
    METRIC,
    IMPERIAL
}

enum class NetworkState {
    FINISH,
    LOADING,
    ERROR
}

const val BASE_URL = "http://api.apixu.com/v1/"

fun code_condition(code: Int): String {
    return when (code) {
        1273, 1276, 1204, 1207, 1135, 1147, 1069, 1030, 1003, 1006, 1009 ->
            "Cloudy"
        1240, 1243, 1246, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1063, 1072 ->
            "Rain"
        1279, 1282, 1249, 1252, 1255, 1258, 1261, 1264, 1237, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1066, 1114 ->
            "Snow"
        1087 ->
            "Thunder"
        else ->
            "Clear"
    }
}

fun code_icon_res(code: Int): Int {
    return when (code) {
        1273, 1276, 1204, 1207, 1135, 1147, 1069, 1030, 1003, 1006, 1009 ->
            R.drawable.ic_cloud
        1240, 1243, 1246, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1063, 1072 ->
            R.drawable.ic_cloud_rain
        1279, 1282, 1249, 1252, 1255, 1258, 1261, 1264, 1237, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1066, 1114 ->
            R.drawable.ic_cloud_snow
        1087 ->
            R.drawable.ic_cloud_lightning
        else ->
            R.drawable.ic_sun
    }
}

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