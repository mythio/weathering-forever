package com.mythio.weather.utils

import com.mythio.weather.CODE_TO_RES_ID
import com.mythio.weather.Unit
import com.mythio.weather.db.model.domain.CurrentWeather
import com.mythio.weather.db.model.domain.ForecastWeather
import com.mythio.weather.db.model.entity.current.CurrentImperial
import com.mythio.weather.db.model.entity.current.CurrentMetric
import com.mythio.weather.db.model.entity.forecast.ForecastImperial
import com.mythio.weather.db.model.entity.forecast.ForecastMetric

fun CurrentImperial.convert(): CurrentWeather {
    return CurrentWeather(
        unit = Unit.IMPERIAL,
        temperature = temperature,
        conditionText = conditionText,
        conditionIconRes = CODE_TO_RES_ID[conditionCode] ?: error("Unknown Id Passed"),
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
        conditionIconRes = CODE_TO_RES_ID[conditionCode] ?: error("Unknown Id Passed"),
        windSpeed = windSpeed,
        windDirection = windDirection,
        humidity = humidity,
        feelsLike = feelsLikeTemperature,
        uvIndex = uvIndex
    )
}

fun ForecastImperial.convert(): ForecastWeather {
    return ForecastWeather(
        unit = Unit.IMPERIAL,
        temperature = temperature,
        conditionIconRes = CODE_TO_RES_ID[conditionCode] ?: error("Unknown Id Passed")
    )
}

fun ForecastMetric.convert(): ForecastWeather {
    return ForecastWeather(
        unit = Unit.METRIC,
        temperature = temperature,
        conditionIconRes = CODE_TO_RES_ID[conditionCode] ?: error("Unknown Id Passed")
    )
}