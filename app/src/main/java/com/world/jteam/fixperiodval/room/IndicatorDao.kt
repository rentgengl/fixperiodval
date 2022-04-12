package com.world.jteam.fixperiodval.room

import androidx.room.*

@Dao
interface IndicatorDao {
    @Insert
    fun insert(indicator: Indicator): Long

    @Update
    fun update(indicator: Indicator)

    @Delete
    fun delete(indicator: Indicator)

    @Query("SELECT * FROM Indicator WHERE id = :id")
    fun getIndicatorById(id: Int): Indicator

    @Query("SELECT * FROM Indicator")
    fun getAllIndicator(): List<Indicator>

    fun insertIndicator(indicator: Indicator){
        val id = insert(indicator)
        indicator.id = id.toInt()
    }
}