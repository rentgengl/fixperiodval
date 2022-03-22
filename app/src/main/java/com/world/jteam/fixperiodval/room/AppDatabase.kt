package com.world.jteam.fixperiodval.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Indicator::class,
        Type::class,
        TypeIndicators::class,
        Fixing::class,
        FixingValues::class
               ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun IndicatorDao(): IndicatorDao
    abstract fun TypeDao(): TypeDao
    abstract fun FixingDao(): FixingDao
}