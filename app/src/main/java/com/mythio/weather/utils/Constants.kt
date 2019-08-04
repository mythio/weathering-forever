package com.mythio.weather.utils

import com.mythio.weather.BuildConfig
import com.mythio.weather.R

const val BASE_URL = "http://api.apixu.com/v1/"
const val API_KEY = "70ef3b7f24484a918b782502191207"

const val SHARED_PREF_NAME = BuildConfig.APPLICATION_ID + ".PREF"

const val SHARED_PREF_KEY_LOCATION = BuildConfig.APPLICATION_ID + ".LOCATION"
const val SHARED_PREF_KEY_UNIT = BuildConfig.APPLICATION_ID + ".UNIT"
const val SHARED_PREF_KEY_THEME = BuildConfig.APPLICATION_ID + ".THEME"

const val DEFAULT_LOCATION = "los-angeles-california-united-states-of-america"
const val DEFAULT_UNIT = 0
const val UNIT_METRIC = 0
const val UNIT_IMPERIAL = 1

const val RESULT_CODE_LOC = 31

enum class NetworkState {
    FINISH,
    LOADING,
    ERROR
}

fun codeCondition(code: Int): String {
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

fun codeIconRes(code: Int): Int {
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