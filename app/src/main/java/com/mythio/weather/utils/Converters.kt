package com.mythio.weather.utils

import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.model.entity.current.CurrentImperial
import com.mythio.weather.model.entity.current.CurrentMetric
import com.mythio.weather.model.entity.forecast.ForecastImperial
import com.mythio.weather.model.entity.forecast.ForecastMetric
import java.util.*

fun CurrentImperial.convert(): CurrentWeather {
    return CurrentWeather(
        unit = UNIT_IMPERIAL,
        temperature = temperature,
        conditionText = codeCondition(conditionCode),
        conditionIconRes = codeIconRes(conditionCode),
        windSpeed = windSpeed,
        windDirection = windDirection,
        humidity = humidity,
        feelsLike = feelsLikeTemperature,
        uvIndex = uvIndex,
        location = locationResponse.name + ", " + locationResponse.region
    )
}

fun CurrentMetric.convert(): CurrentWeather {
    return CurrentWeather(
        unit = UNIT_METRIC,
        temperature = temperature,
        conditionText = codeCondition(conditionCode),
        conditionIconRes = codeIconRes(conditionCode),
        windSpeed = windSpeed,
        windDirection = windDirection,
        humidity = humidity,
        feelsLike = feelsLikeTemperature,
        uvIndex = uvIndex,
        location = locationResponse.name + ", " + locationResponse.region
    )
}

fun ForecastImperial.convert(): ForecastWeather {
    return ForecastWeather(
        unit = UNIT_IMPERIAL,
        temperature = temperature,
        conditionIconRes = codeIconRes(conditionCode),
        day = String.format(Locale.ENGLISH, "%tA", date * 1000L).substring(0, 3).toUpperCase()
    )
}

fun ForecastMetric.convert(): ForecastWeather {
    return ForecastWeather(
        unit = UNIT_METRIC,
        temperature = temperature,
        conditionIconRes = codeIconRes(conditionCode),
        day = String.format(Locale.ENGLISH, "%tA", date * 1000L).substring(0, 3).toUpperCase()
    )
}