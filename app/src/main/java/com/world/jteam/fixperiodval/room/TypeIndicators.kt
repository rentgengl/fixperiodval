package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeIndicators(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var typeId: Int,
    var indicatorId: Int
)
