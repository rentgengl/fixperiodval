package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Fixing(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var date: Date,
    var typeId: Int
)
