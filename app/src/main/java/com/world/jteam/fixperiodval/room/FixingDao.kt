package com.world.jteam.fixperiodval.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface FixingDao {
    //Fixing
    @Insert
    fun insert(fixing: Fixing): Long

    @Delete
    fun delete(fixing: Fixing)

    @Update
    fun update(fixing: Fixing)

    //TODO after test TypeDao
}