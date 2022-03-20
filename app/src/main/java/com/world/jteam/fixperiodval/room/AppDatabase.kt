package com.world.jteam.fixperiodval.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Indicator::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun IndicatorDao(): IndicatorDao
}