package com.world.jteam.fixperiodval.room

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        if (value == null){
            return null
        } else {
            return Date(value)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        if (date == null) {
            return null
        } else {
            return date.getTime()
        }
    }
}