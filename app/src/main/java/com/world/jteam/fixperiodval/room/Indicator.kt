package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Indicator(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String?,
    var unit: String?
)
