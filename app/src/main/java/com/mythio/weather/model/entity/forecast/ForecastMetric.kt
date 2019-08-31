package com.mythio.weather.model.entity.forecast

import androidx.room.ColumnInfo

data class ForecastMetric(

    @ColumnInfo(name = "dateEpoch")
    override val date: Long,

    @ColumnInfo(name = "day_avgtempC")
    override val temperature: Double,

    @ColumnInfo(name = "day_condition_code")
    override val conditionCode: Int
) : Forecast