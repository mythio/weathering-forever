package com.mythio.weather

enum class Unit {
    METRIC,
    IMPERIAL
}

const val BASE_URL = "http://api.apixu.com/v1/"

val CODE_TO_RES_ID: Map<Int, Int> = hashMapOf(
    1000 to R.drawable.ic_wi_day_cloudy,
    1003 to R.drawable.ic_wi_day_cloudy,
    1006 to R.drawable.ic_wi_day_cloudy,
    1009 to R.drawable.ic_wi_day_cloudy,
    1030 to R.drawable.ic_wi_day_cloudy,
    1063 to R.drawable.ic_wi_day_cloudy,
    1066 to R.drawable.ic_wi_day_cloudy,
    1069 to R.drawable.ic_wi_day_cloudy,
    1072 to R.drawable.ic_wi_day_cloudy,
    1087 to R.drawable.ic_wi_day_cloudy,
    1114 to R.drawable.ic_wi_day_cloudy,
    1117 to R.drawable.ic_wi_day_cloudy,
    1135 to R.drawable.ic_wi_day_cloudy,
    1147 to R.drawable.ic_wi_day_cloudy,
    1150 to R.drawable.ic_wi_day_cloudy,
    1153 to R.drawable.ic_wi_day_cloudy,
    1168 to R.drawable.ic_wi_day_cloudy,
    1171 to R.drawable.ic_wi_day_cloudy,
    1180 to R.drawable.ic_wi_day_cloudy,
    1183 to R.drawable.ic_wi_day_cloudy,
    1186 to R.drawable.ic_wi_day_cloudy,
    1189 to R.drawable.ic_wi_day_cloudy,
    1192 to R.drawable.ic_wi_day_cloudy,
    1195 to R.drawable.ic_wi_day_cloudy,
    1198 to R.drawable.ic_wi_day_cloudy,
    1201 to R.drawable.ic_wi_day_cloudy,
    1204 to R.drawable.ic_wi_day_cloudy,
    1207 to R.drawable.ic_wi_day_cloudy,
    1210 to R.drawable.ic_wi_day_cloudy,
    1213 to R.drawable.ic_wi_day_cloudy,
    1216 to R.drawable.ic_wi_day_cloudy,
    1219 to R.drawable.ic_wi_day_cloudy,
    1222 to R.drawable.ic_wi_day_cloudy,
    1225 to R.drawable.ic_wi_day_cloudy,
    1237 to R.drawable.ic_wi_day_cloudy,
    1240 to R.drawable.ic_wi_day_cloudy,
    1243 to R.drawable.ic_wi_day_cloudy,
    1246 to R.drawable.ic_wi_day_cloudy,
    1249 to R.drawable.ic_wi_day_cloudy,
    1252 to R.drawable.ic_wi_day_cloudy,
    1255 to R.drawable.ic_wi_day_cloudy,
    1258 to R.drawable.ic_wi_day_cloudy,
    1261 to R.drawable.ic_wi_day_cloudy,
    1264 to R.drawable.ic_wi_day_cloudy,
    1273 to R.drawable.ic_wi_day_cloudy,
    1276 to R.drawable.ic_wi_day_cloudy,
    1279 to R.drawable.ic_wi_day_cloudy,
    1282 to R.drawable.ic_wi_day_cloudy
)