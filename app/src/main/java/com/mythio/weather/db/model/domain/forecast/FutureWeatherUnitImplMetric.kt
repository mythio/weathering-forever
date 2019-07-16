package com.mythio.weather.db.model.domain.forecast

import androidx.room.ColumnInfo

data class FutureWeatherUnitImplMetric(

    @ColumnInfo(name = "day_maxtempC")
    override val maxtemp: Double,

    @ColumnInfo(name = "day_condition_text")
    override val conditionText: String
) : FutureWeatherUnitImpl