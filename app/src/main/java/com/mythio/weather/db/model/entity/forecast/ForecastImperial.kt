package com.mythio.weather.db.model.entity.forecast

import androidx.room.ColumnInfo

data class ForecastImperial(

    @ColumnInfo(name = "day_maxtempF")
    override val maxtemp: Double,

    @ColumnInfo(name = "day_condition_text")
    override val conditionText: String
) : Forecast