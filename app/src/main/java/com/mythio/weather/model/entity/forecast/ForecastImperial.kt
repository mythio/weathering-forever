package com.mythio.weather.model.entity.forecast

import androidx.room.ColumnInfo

data class ForecastImperial(

    @ColumnInfo(name = "day_avgtempF")
    override val temperature: Double,

    @ColumnInfo(name = "day_condition_code")
    override val conditionCode: Int
) : Forecast