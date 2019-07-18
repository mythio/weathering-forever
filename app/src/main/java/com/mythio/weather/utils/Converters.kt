package com.mythio.weather.utils

import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.model.entity.current.CurrentImperial
import com.mythio.weather.model.entity.current.CurrentMetric
import com.mythio.weather.model.entity.forecast.ForecastImperial
import com.mythio.weather.model.entity.forecast.ForecastMetric

fun CurrentImperial.convert(): CurrentWeather {
    return CurrentWeather(
        unit = Unit.IMPERIAL,
        temperature = temperature,
        conditionText = code_condition(conditionCode),
        conditionIconRes = code_icon_res(conditionCode),
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
        conditionText = code_condition(conditionCode),
        conditionIconRes = code_icon_res(conditionCode),
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
        conditionIconRes = code_icon_res(conditionCode)
    )
}

fun ForecastMetric.convert(): ForecastWeather {
    return ForecastWeather(
        unit = Unit.METRIC,
        temperature = temperature,
        conditionIconRes = code_icon_res(conditionCode)
    )
}