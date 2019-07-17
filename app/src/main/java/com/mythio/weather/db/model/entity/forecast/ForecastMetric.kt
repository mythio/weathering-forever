package com.mythio.weather.db.model.entity.forecast

import androidx.room.ColumnInfo

data class ForecastMetric(

    @ColumnInfo(name = "day_maxtempC")
    override val maxtemp: Double,

    @ColumnInfo(name = "day_condition_text")
    override val conditionText: String
) : Forecast