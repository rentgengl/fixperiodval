package com.world.jteam.fixperiodval.room

import androidx.room.*

@Dao
interface IndicatorDao {
    @Insert
    fun insert(indicator: Indicator)

    @Update
    fun update(indicator: Indicator)

    @Delete
    fun delete(indicator: Indicator)

    @Query("SELECT * FROM Indicator WHERE id = :id")
    fun getIndicatorById(id: Int): Indicator
}