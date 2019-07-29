package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mythio.weather.model.entity.Location

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Delete
    fun deleteLocation(location: Location)

    @Query("select * from location")
    fun getLocation(): LiveData<List<Location>>
}