package com.mythio.weather.db.unitspecific.forecast

import androidx.room.ColumnInfo

data class ImperialFutureWeather(

    @ColumnInfo(name = "day_maxtempF")
    override val maxtemp: Double,

    @ColumnInfo(name = "day_condition_text")
    override val conditionText: String
) : UnitSpecificFutureWeather