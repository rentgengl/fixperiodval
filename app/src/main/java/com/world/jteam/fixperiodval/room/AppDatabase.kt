package com.world.jteam.fixperiodval.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Indicator::class,
        Type::class,
        TypeIndicator::class,
        Fixing::class,
        FixingValues::class
               ],
    version = 2
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun IndicatorDao(): IndicatorDao
    abstract fun TypeDao(): TypeDao
    abstract fun FixingDao(): FixingDao
}