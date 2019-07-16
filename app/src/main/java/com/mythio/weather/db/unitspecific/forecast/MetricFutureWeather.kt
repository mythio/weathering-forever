package com.mythio.weather.db.unitspecific.forecast

import androidx.room.ColumnInfo

data class MetricFutureWeather(

    @ColumnInfo(name = "day_maxtempC")
    override val maxtemp: Double,

    @ColumnInfo(name = "day_condition_text")
    override val conditionText: String
) : UnitSpecificFutureWeather